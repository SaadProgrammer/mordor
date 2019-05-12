package com.example.mordor.model.postacie;

import lombok.Getter;

@Getter

public enum RodzajeAtakowEnum {

    DONIES(0, 49),
    OBLEJ_KAWA(50, 84),
    ZEPCHNIJ_ZE_SCHODOW(85, 100),
    OBNIZ_WYNAGRODZENIE(85, 100);

    private Integer minWart;
    private Integer maxWart;

    RodzajeAtakowEnum(Integer minWart, Integer maxWart) {
        this.minWart = minWart;
        this.maxWart = maxWart;
    }
}