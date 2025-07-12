package Billing.service.Billing.service.Dtos;

import lombok.Data;

@Data
public class PlanDto {

    private Long id;
    private String name;
    private String description;
    private Double monthlyCost;
    private Integer dataLimitGB;
    private Integer callMinutes;
    private Integer smsLimit;
}
