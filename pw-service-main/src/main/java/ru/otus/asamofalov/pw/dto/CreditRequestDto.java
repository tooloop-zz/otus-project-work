package ru.otus.asamofalov.pw.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.asamofalov.pw.domain.CreditRequest;
import ru.otus.asamofalov.pw.domain.CreditRequestHistoryItem;
import ru.otus.asamofalov.pw.domain.CreditRequestState;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CreditRequestDto {

    private long id;

    private String uuid;

    private String name;

    private String docSeries;

    private String docNumber;

    private String docDate;

    private double Sum;

    private CreditRequestState state;

    private String created;

    private String updated;

    private double percentageRate;

    private List<CreditRequestHistoryItemDto> history;

//    public static CreditRequest toDomainObject(CreditRequestDto creditRequestDto) {
//        CreditRequest creditRequest = new CreditRequest();
//        creditRequest.setId(creditRequestDto.getId());
//        creditRequest.setUuid(creditRequestDto.getUuid());
//        creditRequest.setName(creditRequestDto.getName());
//        creditRequest.setDocSeries(creditRequestDto.getDocSeries());
//        creditRequest.setDocNumber(creditRequestDto.getDocNumber());
//        creditRequest.setDocDate(creditRequestDto.getDocDate());
//        creditRequest.setSum(creditRequestDto.getSum());
//        creditRequest.setState(creditRequestDto.getState());
//        return creditRequest;
//    }

    public static CreditRequestDto fromDomainObject(CreditRequest creditRequest) {
        CreditRequestDto creditRequestDto = new CreditRequestDto();
        creditRequestDto.setId(creditRequest.getId());
        creditRequestDto.setUuid(creditRequest.getUuid().toString());
//        creditRequestDto.setUuid(creditRequest.getUuid() == null ? UUID.randomUUID() : creditRequest.getUuid());
        creditRequestDto.setName(creditRequest.getName());
        creditRequestDto.setDocSeries(creditRequest.getDocSeries());
        creditRequestDto.setDocNumber(creditRequest.getDocNumber());
        creditRequestDto.setDocDate(new SimpleDateFormat("dd/MM/yyyy").format(creditRequest.getDocDate()));
        creditRequestDto.setSum(creditRequest.getSum());
        creditRequestDto.setState(creditRequest.getState());
        creditRequestDto.setPercentageRate(Math.floor(creditRequest.getPercentageRate() * 100) / 100);
        creditRequestDto.setCreated(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(creditRequest.getCreated()));
        creditRequestDto.setUpdated(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(creditRequest.getUpdated()));
        creditRequestDto.setHistory(creditRequest.getHistory().stream().map(CreditRequestHistoryItemDto::fromDomainObject).collect(Collectors.toList()));
        return creditRequestDto;
    }

}
