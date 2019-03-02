package com.example.mordor.model.postacie;

import com.example.mordor.service.TworzeniePostaciService;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Saad on 2019-02-24.
 */

@Getter
@Setter

public class Korpoludek extends Istota {

    public Korpoludek() {
        super(TworzeniePostaciService.losuj(4, 7), TworzeniePostaciService.losuj(6, 9), TworzeniePostaciService.losuj(7, 10), TworzeniePostaciService.losuj(4, 7));
    }

}