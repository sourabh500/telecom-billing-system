package Billing.service.Billing.service.Service;

import Billing.service.Billing.service.Dtos.EmailDetails;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    // Injecting JavaMailSender to send emails
    private final JavaMailSender mailSender;

    @Override
    public String sendMailWithAttachment(EmailDetails details) {

        try {
            // Creating a MIME message (used for attachments)
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            // MimeMessageHelper handles multipart emails
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(details.getTo());
            helper.setSubject(details.getSubject());
            helper.setText(details.getMessage(), true);  // true enables HTML

            // Add PDF attachment if present
            if (details.getAttachment() != null) {
                helper.addAttachment(
                        details.getAttachmentName(),
                        new ByteArrayResource(details.getAttachment())
                );
            }

            //Sending email

            mailSender.send(mimeMessage);
            return "Mail sent successfully to " + details.getTo();

        } catch (MessagingException | MailException exception) {
            return "Failed to send email: " + exception.getMessage();
        } catch (Exception e) {
            return "An unexpected error occurred: " + e.getMessage();
        }
    }
}
