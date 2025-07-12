package telecom_customer_service.Entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String mobile;
    private String password;
    private String address;
}
