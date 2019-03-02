package com.example.mordor.model.postacie;

import com.example.mordor.service.TworzeniePostaciService;

/**
 * Created by Saad on 2019-02-24.
 */


public class Recepcjonistka extends Istota {

    public Recepcjonistka() {
        super(TworzeniePostaciService.losuj(3, 6), TworzeniePostaciService.losuj(3, 6), TworzeniePostaciService.losuj(7, 10), TworzeniePostaciService.losuj(7, 10));
    }
}
