package com.example.mordor.model.postacie;

import lombok.Getter;

@Getter

public enum RodzajeAtakowObrazniaEnum {

    DONIES(1, 2),
    OBLEJ_KAWA(2, 3),
    ZEPCHNIJ_ZE_SCHODOW(3, 4),
    OBNIZ_WYNAGRODZENIE(3, 4);

    private Integer minWart;
    private Integer maxWart;

    RodzajeAtakowObrazniaEnum(Integer minWart, Integer maxWart) {
        this.minWart = minWart;
        this.maxWart = maxWart;
    }
}
