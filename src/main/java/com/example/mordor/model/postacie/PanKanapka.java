package com.example.mordor.model.postacie;

import com.example.mordor.service.TworzeniePostaciService;

/**
 * Created by Saad on 2019-02-24.
 */


public class PanKanapka extends Istota {

    public PanKanapka() {
        super(TworzeniePostaciService.losuj(5, 8), TworzeniePostaciService.losuj(4, 7), TworzeniePostaciService.losuj(3, 6), TworzeniePostaciService.losuj(7, 10));
    }
}
