package Billing.service.Billing.service.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private Long planId;
    private String billingMonth;
    private Double dataUsageInGB;
    private Integer callMinutes;
    private Integer smsCount;
    private Double amount;
    private Boolean isPaid;

    private LocalDate generatedDate;
}
