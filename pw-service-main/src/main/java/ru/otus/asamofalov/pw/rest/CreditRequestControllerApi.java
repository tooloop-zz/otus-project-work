package ru.otus.asamofalov.pw.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.asamofalov.pw.Constants;
import ru.otus.asamofalov.pw.config.RabbitMqConfig;
import ru.otus.asamofalov.pw.domain.CreditRequest;
import ru.otus.asamofalov.pw.domain.CreditRequestState;
import ru.otus.asamofalov.pw.domain.GetResponse;
import ru.otus.asamofalov.pw.domain.PutRequest;
import ru.otus.asamofalov.pw.domain.PutResponse;
import ru.otus.asamofalov.pw.helper.Tools;
import ru.otus.asamofalov.pw.repository.CreditRequestsRepository;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CreditRequestControllerApi {

    private final CreditRequestsRepository creditRequestsRepository;

//    private final RabbitTemplate rabbitTemplate;

    private final Channel channel;

    private final ObjectMapper objectMapper;

    @PostMapping("/api/requests")
    public ResponseEntity<?> put(@Valid @RequestBody PutRequest putRequest, BindingResult bindingResult) throws IOException {

        log.debug("new put request received{}", Tools.getTraceDetails(putRequest));
        CreditRequest creditRequest = PutRequest.toCreditRequest(putRequest);
        if (bindingResult.hasErrors()) {
            PutResponse putResponse = new PutResponse();
            putResponse.setState(CreditRequestState.REJECTED);
            putResponse.setResult(getErrors(bindingResult));
            log.debug("put request declined{}", Tools.getTraceDetails(putResponse));
            return ResponseEntity.badRequest().body(putResponse);
        }

        creditRequest.setState(CreditRequestState.ACCEPTED);
        creditRequestsRepository.save(creditRequest);
        PutResponse putResponse = PutResponse.fromCreditRequest(creditRequest);
        channel.basicPublish("", Constants.SERVICE_PASSPORT_REQUEST_QUEUE, null, objectMapper.writeValueAsBytes(creditRequest));
        creditRequest.setState(CreditRequestState.SENT_FOR_PASSPORT_CHECK);
        creditRequestsRepository.save(creditRequest);
        log.debug("put request accepted{}", Tools.getTraceDetails(creditRequest));
        return ResponseEntity.ok().body(putResponse);
    }

    @GetMapping("/api/requests/{uuid}")
    public ResponseEntity<?> get(@PathVariable UUID uuid){
        log.debug("new get request with uuid: {} received", uuid);
        Optional<CreditRequest> creditRequest = creditRequestsRepository.findByUuid(uuid);
        GetResponse getResponse = new GetResponse();
        getResponse.setUuid(uuid);
        if (creditRequest.isPresent()){
            getResponse.setSum(creditRequest.get().getSum());
            getResponse.setState(creditRequest.get().getState());
            log.debug("data for uuid {} found{}", uuid, Tools.getTraceDetails(getResponse));
            return ResponseEntity.ok().body(getResponse);
        }
        log.debug("data for uuid {} not found", uuid);
        getResponse.setState(CreditRequestState.NOT_FOUND);
        return ResponseEntity.ok().body(getResponse);
    }

//    private String getTraceDetails(Object object) {
//        return log.isTraceEnabled() ? (": " + object) : "";
//    }

    private String getErrors(BindingResult bindingResult) {
        return String.join(
                " ,",
                bindingResult.getAllErrors().stream().map(e ->
                        ((FieldError) e).getField() + ":" + e.getDefaultMessage()).collect(Collectors.toList())
        );
    }
}
