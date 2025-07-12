package Billing.service.Billing.service.Repository;

import Billing.service.Billing.service.Entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepo extends JpaRepository<Bill,Long> {

    List<Bill> findByCustomerId(Long customerId);
}
