package com.mob.casestudy.digitalbanking.helpers;

import com.digitalbanking.openapi.model.PreferredLanguage;
import com.mob.casestudy.digitalbanking.embeddables.CustomerOtpId;
import com.mob.casestudy.digitalbanking.embeddables.CustomerSecurityImagesId;
import com.mob.casestudy.digitalbanking.embeddables.CustomerSecurityQuestionsId;
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

    @Autowired
    CustomerOtpRepo customerOtpRepo;

    @Transactional
    public void addCustomerWithTheirRespectiveQuestionAndAnswer(){

        Customer customer = Customer.builder().userName("UzairKhan2706").firstName("Uzair").lastName("Khan").phoneNumber("7226803020").email("uzairkhan27@gmail.com").status(Customer.CustomerStatus.ACTIVE)
                .preferredLanguage(PreferredLanguage.EN).externalId("42069").createdBy("Me").createdOn(LocalDateTime.now()).updatedBy("Again Me").updatedOn(LocalDateTime.now()).build();

        Customer customer1 = Customer.builder().userName("NeelKoshti247").firstName("Neel").lastName("Koshti").phoneNumber("8654332664").email("neelkoshti247@gmail.com").status(Customer.CustomerStatus.INACTIVE)
                .preferredLanguage(PreferredLanguage.FR).externalId("69069").createdBy("Avengers").createdOn(LocalDateTime.now()).updatedBy("Assemble").updatedOn(LocalDateTime.now()).build();

        Customer customer2 = Customer.builder().userName("Siddu26").firstName("Siddharth").lastName("Unknown").phoneNumber("9458907652").email("siddhu26@gmail.com").status(Customer.CustomerStatus.PENDING)
                .preferredLanguage(PreferredLanguage.DE).externalId("31032").createdBy("Still").createdOn(LocalDateTime.now()).updatedBy("Unknown").updatedOn(LocalDateTime.now()).build();


        customerRepo.save(customer);
        customerRepo.save(customer1);
        customerRepo.save(customer2);
        addQuestions(customer);
        addQuestions(customer1);

        addImages(customer);
        setOtp(customer);
    }


    public void addQuestions(Customer customer) {

        List<SecurityQuestions> list = new ArrayList<>();

        SecurityQuestions questions = SecurityQuestions.builder().securityQuestionText("What's your Favourite Car?").build();
        SecurityQuestions questions1 = SecurityQuestions.builder().securityQuestionText("Place that you would've loved to visit").build();

        list.add(questions);
        list.add(questions1);
        securityQuestionsRepo.save(questions);
        securityQuestionsRepo.save(questions1);
        setQuestionsForCustomer(customer,list);
    }


    public void setQuestionsForCustomer(Customer customer, List<SecurityQuestions> questions) {

        CustomerSecurityQuestions customerSecurityQuestions = CustomerSecurityQuestions.builder().customerSecurityQuestionsId(new CustomerSecurityQuestionsId()).securityQuestionAnswer("Porsche 911").createdOn(LocalDateTime.now()).build();
        CustomerSecurityQuestions customerSecurityQuestions1 = CustomerSecurityQuestions.builder().customerSecurityQuestionsId(new CustomerSecurityQuestionsId()).securityQuestionAnswer("Mystic Falls").createdOn(LocalDateTime.now()).build();

        customerSecurityQuestions.setCustomer(customer);
        customerSecurityQuestions.setSecurityQuestions(questions.get(0));
        customerSecurityQuestionsRepo.save(customerSecurityQuestions);

        customerSecurityQuestions1.setCustomer(customer);
        customerSecurityQuestions1.setSecurityQuestions(questions.get(1));
        customerSecurityQuestionsRepo.save(customerSecurityQuestions1);
    }

    public void addImages(Customer customer) {

        List<SecurityImages> list = new ArrayList<>();

        SecurityImages images = SecurityImages.builder().securityImageName("Shelby").securityImageUrl("www.shelby_motors.com").build();
        SecurityImages images1 = SecurityImages.builder().securityImageName("GT").securityImageUrl("www.Gt_works.com").build();
        list.add(images);
        list.add(images1);
        securityImagesRepo.save(images);
        securityImagesRepo.save(images1);
        setImagesForCustomer(customer,list);
    }

    public void setImagesForCustomer(Customer customer, List<SecurityImages> securityImages) {

        CustomerSecurityImages customerSecurityImages =  CustomerSecurityImages.builder().customerSecurityImagesId(new CustomerSecurityImagesId()).securityImageCaption("Ford").createdOn(LocalDateTime.now()).build();

        customerSecurityImages.setCustomer(customer);
        customerSecurityImages.setSecurityImages(securityImages.get(0));

        customerSecurityImagesRepo.save(customerSecurityImages);
    }

    public void setOtp(Customer customer){

        CustomerOtp customerOtp = CustomerOtp.builder().customerOtpId(new CustomerOtpId()).otpMessage("Otp Generated").otp("786930").otpRetries(0).expiryOn(LocalDateTime.now()).createdOn(LocalDateTime.now()).build();
        customerOtp.setCustomer(customer);
        customerOtpRepo.save(customerOtp);
    }



}
