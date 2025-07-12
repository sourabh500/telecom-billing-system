package Billing.service.Billing.service.Dtos;

import lombok.Data;

@Data
public class customerDto {

    private Long id;
    private String name;
    private String email;
    private String mobile;
    private String address;
}
