package org.example.quickclothapp.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.example.quickclothapp.model.*;
import org.example.quickclothapp.payload.request.EmailRequest;
import org.example.quickclothapp.payload.request.EmailsRequest;
import org.example.quickclothapp.payload.response.CampaignResponse;
import org.example.quickclothapp.service.intf.IEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Arrays;
import java.util.List;

@Service
public class EmailService implements IEmailService {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    private final Logger loggerEmailService = LoggerFactory.getLogger(EmailService.class);

    public EmailService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendEmailNewCampaign(EmailRequest emailRequest, User user, Campaign campaign) {

        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true, "utf-8");

            helper.setTo(emailRequest.getTo());
            helper.setSubject(emailRequest.getSubject());

            Context context = new Context();
            context.setVariable("full_name", String.format("Hola %s %s", user.getName(), user.getLast_name()));
            context.setVariable("name", String.format("Nueva Campaña de %s", campaign.getName()));
            context.setVariable("description", campaign.getMessage_campaign());
            context.setVariable("initial_date", String.format("Fecha de Inicio %s", campaign.getCreation_date()));
            context.setVariable("end_date", String.format("Fecha Final %s", campaign.getEnd_date()));

            String html = templateEngine.process("campaing-template", context);
            helper.setText(html, true);
            javaMailSender.send(mimeMessage);

            loggerEmailService.info("Email sent successfully to: " + emailRequest.getTo());

        }catch (Exception e){
            loggerEmailService.error("Error sending email: " + e.getMessage());
        }

    }

    @Override
    public void sendEmailNewSale(Sale sale, List<SaleList> saleLists, Integer newPoints ,EmailRequest emailRequest, List<CampaignResponse> campaignList) {
        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true, "utf-8");

            helper.setTo(emailRequest.getTo());
            helper.setSubject(emailRequest.getSubject());

            Context context = new Context();
            context.setVariable("sale_uuid", sale.getUuid());
            context.setVariable("sale_date", sale.getSale_date());
            context.setVariable("full_name", String.format("Hola %s %s", sale.getUser().getName(), sale.getUser().getLast_name()));
            context.setVariable("saleLists", saleLists);
            context.setVariable("campaignList", campaignList);
            context.setVariable("totalDiscount", campaignList.stream().mapToDouble(CampaignResponse::getValueDiscount).sum());
            context.setVariable("totalValue", sale.getValue());
            context.setVariable("payPoints", sale.getPay_points() *-1);
            context.setVariable("new_points", newPoints);
            context.setVariable("total_points", sale.getUser().getPoints());

            String html = templateEngine.process("sale-receipt-template", context);
            helper.setText(html, true);
            javaMailSender.send(mimeMessage);

            loggerEmailService.info("Email sent successfully to: " + emailRequest.getTo());

        }catch (Exception e){
            loggerEmailService.error("Error sending email: " + e.getMessage());
        }
    }

    @Override
    public void sendEmailNewUser(User user, EmailRequest emailRequest){
        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true, "utf-8");

            helper.setTo(emailRequest.getTo());
            helper.setSubject(emailRequest.getSubject());

            Context context = new Context();
            context.setVariable("full_name", String.format("Hola %s %s,", user.getName(), user.getLast_name()));
            context.setVariable("user_name", String.format("Usuario: %s", user.getUser_name()));
            context.setVariable("password", String.format("Contraseña: %s", "12345"));

            String html = templateEngine.process("welcome-email", context);
            helper.setText(html, true);
            javaMailSender.send(mimeMessage);

            loggerEmailService.info("Email sent successfully to: " + emailRequest.getTo());

        }catch (Exception e){
            loggerEmailService.error("Error sending email: " + e.getMessage());
        }
    }
    
    @Override
    public void sendEmailNewClothes(List<Clothe> clothes, Wardrobe wardrobe, EmailsRequest emailsRequest) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");

            helper.setTo(emailsRequest.getTo());
            helper.setSubject(emailsRequest.getSubject());

            Context context = new Context();
            context.setVariable("wardrobe_name", wardrobe.getName());
            context.setVariable("wardrobe_address", wardrobe.getAddress());
            context.setVariable("clothes", clothes);

            String html = templateEngine.process("new-clothes-template", context);
            helper.setText(html, true);
            javaMailSender.send(mimeMessage);

            loggerEmailService.info("Email sent successfully to: " + emailsRequest.getTo());

        } catch (MessagingException e) {
            loggerEmailService.error("Error sending email: " + e.getMessage());
        }
    }

}
