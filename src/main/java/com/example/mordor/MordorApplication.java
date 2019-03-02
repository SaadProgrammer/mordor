package com.example.mordor;

import com.example.mordor.model.postacie.TypPostaciEnum;
import com.example.mordor.service.TworzeniePostaciService;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootConfiguration
@SpringBootApplication
public class MordorApplication {

    public static void main(String[] args) {

        TworzeniePostaciService tps = new TworzeniePostaciService();
        tps.stworzWieleOkreslonychPostaci(4, TypPostaciEnum.MANAGER);

    }
}
