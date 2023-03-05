package ru.otus.asamofalov.pw.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "credit_requests")
public class CreditRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "doc_series")
    private String docSeries;

    @Column(name = "doc_number")
    private String docNumber;

    @Column(name = "doc_date")
    private Date docDate;

    @Column(name = "sum")
    private double Sum;

    @Column(name ="percentage_rate")
    private double percentageRate;

    @Column(name = "state")
    private CreditRequestState state;
}
