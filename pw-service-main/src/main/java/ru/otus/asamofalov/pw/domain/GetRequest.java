package ru.otus.asamofalov.pw.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
public class GetRequest {

    @NotBlank(message = "Необходимо указать идентификатор")
    private UUID uuid;

}
