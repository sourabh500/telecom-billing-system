package telecom_customer_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import telecom_customer_service.Entities.Customer;

public interface customerRepo extends JpaRepository<Customer,Long> {

    boolean existsByEmail(String email);
    Customer findByEmail(String email);
}
