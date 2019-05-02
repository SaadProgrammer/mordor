package com.example.mordor.model.postacie;

import com.example.mordor.service.TworzeniePostaciService;

/**
 * Created by Saad on 2019-02-24.
 */


public class Sprzataczka extends Humanoid {

    public Sprzataczka() {
        super(TworzeniePostaciService.losuj(4, 7), TworzeniePostaciService.losuj(5, 8), TworzeniePostaciService.losuj(3, 6), TworzeniePostaciService.losuj(5, 8), TypPostaciEnum.SPRZATACZKA, 9);
    }
}
