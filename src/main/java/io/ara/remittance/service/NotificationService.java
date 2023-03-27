package io.ara.remittance.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class NotificationService {

    private final Logger logger;
//    private final SendGrid sendGrid;

    @Autowired
    public NotificationService(final  Logger logger
//            , SendGrid sendGrid
    ) {
        super();
        this.logger = logger;
//        this.sendGrid = sendGrid;
    }

    public String notificationEmail(String email, String subject, String text) {

//        Email from = new Email("akarolence12@gmail.com");
//        Email to = new Email(email);
//        Content content = new Content("text/plain", text);
//        Mail mail = new Mail(from, subject, to, content);
//
//        //SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
//        Request request = new Request();
//        try {
//            request.setMethod(Method.POST);
//            request.setEndpoint("mail/send");
//            request.setBody(mail.build());
////      Response response = sg.api(request);
//            Response response = this.sendGrid.api(request);
//            System.out.println(response.getStatusCode());
//            System.out.println(response.getBody());
//            System.out.println(response.getHeaders());
//        } catch (IOException ex) {
//            try {
//                throw ex;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        return " Email have been send successfully";
    }
}