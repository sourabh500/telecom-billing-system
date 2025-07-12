package Billing.service.Billing.service.Service;

import Billing.service.Billing.service.Dtos.EmailDetails;

public interface EmailService {

    String sendMailWithAttachment(EmailDetails details);

}
