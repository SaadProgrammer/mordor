package com.example.mordor.model.postacie;

import lombok.Getter;

/**
 * Created by Saad on 2019-04-07.
 */

@Getter

public enum RodzajeAtakówEnum {

    DONIES(0, 49),
    OBLEJ_KAWA(50, 74),
    ZEPCHNIJ_ZE_SCHODOW(75, 100),
    OBNIZ_WYNAGRODZENIE(75, 100);

    private Integer minWart;
    private Integer maxWart;

    RodzajeAtakówEnum(Integer minWart, Integer maxWart) {
        this.minWart = minWart;
        this.maxWart = maxWart;
    }
}