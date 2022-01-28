package com.mob.casestudy.digitalbanking;

import com.mob.casestudy.digitalbanking.helpers.DataInsertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DigitalBankingApplication implements CommandLineRunner {

    @Autowired
    DataInsertion dataInsertion;

    public static void main(String[] args) {
        SpringApplication.run(DigitalBankingApplication.class, args);
    }

    @Override
    public void run(String... args) {
        dataInsertion.addCustomerWithTheirRespectiveQuestionAndAnswer();
    }
}
