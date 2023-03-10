package ru.otus.asamofalov.pw.domain;

public enum CreditRequestState {
    ACCEPTED,
    REJECTED,
    NOT_FOUND,
    SENT_FOR_PASSPORT_CHECK,
    PASSPORT_CHECKED,
    DECLINED_BY_PASSPORT_CHECK,
    SENT_FOR_TERROR_CHECK,
    TERRORIST_CHECKED,
    DECLINED_BY_TERROR_CHECK,
    SENT_FOR_FINAL,
    FINAL_APPROVED,
    FINAL_DECLINED
}
