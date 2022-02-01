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
//        dataInsertion.addCustomerWithTheirRespectiveQuestionAndAnswer();
    }
}



//        CREATE OR REPLACE PROCEDURE public.delete_all(
//        IN UUID, IN timestamp without time zone, IN character varying, IN UUID, IN UUID)
//        LANGUAGE 'plpgsql'
//        AS $BODY$
//        begin
//        DELETE FROM public.customer_security_images WHERE customer_id like c_id;
//        INSERT INTO public.customer_security_images (created_on, security_image_caption, customer_id, security_images_id) values (created_time,caption,c_id,i_id);
//        commit;
//        end;
//        $BODY$;
