package com.mob.casestudy.digitalbanking.helpers;

import com.mob.casestudy.digitalbanking.entities.*;
import com.mob.casestudy.digitalbanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInsertion {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    SecurityQuestionsRepo securityQuestionsRepo;

    @Autowired
    CustomerSecurityImagesRepo customerSecurityImagesRepo;

    @Autowired
    SecurityImagesRepo securityImagesRepo;

    @Autowired
    CustomerSecurityQuestionsRepo customerSecurityQuestionsRepo;

    @Transactional
    public void addCustomerWithTheirRespectiveQuestionAndAnswer(){

        Customer customer = Customer.builder().userName("UzairKhan2706").firstName("Uzair").lastName("Khan").phoneNumber("7226803020").email("uzairkhan27@gmail.com").status(Customer.CustomerStatus.ACTIVE)
                .preferredLanguage(Customer.CustomerPreferredLanguage.EN).externalId("42069").createdBy("Me").createdOn(LocalDateTime.now()).updatedBy("Again Me").updatedOn(LocalDateTime.now()).build();

        Customer customer1 = Customer.builder().userName("NeelKoshti247").firstName("Neel").lastName("Koshti").phoneNumber("8654332664").email("neelkoshti247@gmail.com").status(Customer.CustomerStatus.ACTIVE)
                .preferredLanguage(Customer.CustomerPreferredLanguage.EN).externalId("69069").createdBy("Avengers").createdOn(LocalDateTime.now()).updatedBy("Assemble").updatedOn(LocalDateTime.now()).build();

        Customer customer2 = Customer.builder().userName("Siddu26").firstName("Siddharth").lastName("Unknown").phoneNumber("9458907652").email("siddhu26@gmail.com").status(Customer.CustomerStatus.ACTIVE)
                .preferredLanguage(Customer.CustomerPreferredLanguage.EN).externalId("31032").createdBy("Still").createdOn(LocalDateTime.now()).updatedBy("Unknown").updatedOn(LocalDateTime.now()).build();


        customerRepo.save(customer);
        customerRepo.save(customer1);
        customerRepo.save(customer2);
        addQuestions(customer);
        addQuestions(customer1);

        addImages(customer);

    }


    public void addQuestions(Customer customer) {

        List<SecurityQuestions> list = new ArrayList<>();

        SecurityQuestions questions = securityQuestionsRepo.save(new SecurityQuestions("What's your Favourite Car?"));
        SecurityQuestions questions1 = securityQuestionsRepo.save(new SecurityQuestions("Place that you would've loved to visit"));

        list.add(questions);
        list.add(questions1);
        setQuestionsForCustomer(customer,list);
    }


    public void setQuestionsForCustomer(Customer customer, List<SecurityQuestions> questions) {

        CustomerSecurityQuestions customerSecurityQuestions = new CustomerSecurityQuestions("Porsche 911",LocalDateTime.now());
		CustomerSecurityQuestions customerSecurityQuestions1 = new CustomerSecurityQuestions("Mystic Falls",LocalDateTime.now());

        customerSecurityQuestions.setCustomer(customer);
        customerSecurityQuestions.setSecurityQuestions(questions.get(0));

        customerSecurityQuestions1.setCustomer(customer);
        customerSecurityQuestions1.setSecurityQuestions(questions.get(1));

        customerSecurityQuestionsRepo.save(customerSecurityQuestions);
        customerSecurityQuestionsRepo.save(customerSecurityQuestions1);


    }

    public void addImages(Customer customer) {

        List<SecurityImages> list = new ArrayList<>();

        SecurityImages images = securityImagesRepo.save(new SecurityImages("Pagani","pagani/here"));
        SecurityImages images1 = securityImagesRepo.save(new SecurityImages("Koenigsegg","nothing/here"));

        list.add(images);
        list.add(images1);
        setImagesForCustomer(customer,list);
    }

    public void setImagesForCustomer(Customer customer, List<SecurityImages> questions) {


        CustomerSecurityImages customerSecurityImages = new CustomerSecurityImages("Porsche 911",LocalDateTime.now());

        customerSecurityImages.setCustomer(customer);
        customerSecurityImages.setSecurityImages(questions.get(0));

        customerSecurityImagesRepo.save(customerSecurityImages);
    }

}
