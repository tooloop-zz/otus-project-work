package ru.otus.asamofalov.pw.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class GetResponse {

    private UUID uuid;

    private double sum;

    private CreditRequestState state;

}
