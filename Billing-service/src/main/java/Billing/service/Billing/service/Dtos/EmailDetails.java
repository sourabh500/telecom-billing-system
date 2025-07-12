package Billing.service.Billing.service.Dtos;

import lombok.Data;

@Data
public class EmailDetails {

    private String to;
    private String subject;
    private String message;
    private byte[] attachment;    //for pdf attachment
    private String attachmentName;

}
