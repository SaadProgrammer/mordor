package com.example.mordor.model.postacie;

import com.example.mordor.service.TworzeniePostaciService;

/**
 * Created by Saad on 2019-02-24.
 */


public class Manager extends Humanoid {

    public Manager() {
        super(TworzeniePostaciService.losuj(4, 7), TworzeniePostaciService.losuj(3, 6), TworzeniePostaciService.losuj(6, 9), TworzeniePostaciService.losuj(5, 8), TypPostaciEnum.MANAGER, 11);
    }

}
