package ru.otus.asamofalov.pw.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.asamofalov.pw.domain.CreditRequest;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class CreditRequestDto {

    private long id;

    private UUID uuid;

    private String name;

    private String docSeries;

    private String docNumber;

    private Date docDate;

    private double Sum;

    public static CreditRequest toDomainObject(CreditRequestDto creditRequestDto) {
        CreditRequest creditRequest = new CreditRequest();
        creditRequest.setId(creditRequestDto.getId());
        creditRequest.setUuid(creditRequestDto.getUuid());
        creditRequest.setName(creditRequestDto.getName());
        creditRequest.setDocSeries(creditRequestDto.getDocSeries());
        creditRequest.setDocNumber(creditRequestDto.getDocNumber());
        creditRequest.setDocDate(creditRequestDto.getDocDate());
        creditRequest.setSum(creditRequestDto.getSum());
        return creditRequest;
    }

    public static CreditRequestDto fromDomainObject(CreditRequest creditRequest) {
        CreditRequestDto creditRequestDto = new CreditRequestDto();
        creditRequestDto.setId(creditRequest.getId());
        creditRequestDto.setUuid(creditRequest.getUuid() == null ? UUID.randomUUID() : creditRequest.getUuid());
        creditRequestDto.setName(creditRequest.getName());
        creditRequestDto.setDocSeries(creditRequest.getDocSeries());
        creditRequestDto.setDocNumber(creditRequest.getDocNumber());
        creditRequestDto.setDocDate(creditRequest.getDocDate());
        creditRequestDto.setSum(creditRequest.getSum());
        return creditRequestDto;
    }

}
