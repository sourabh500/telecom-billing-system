package Billing.service.Billing.service.Service;

import Billing.service.Billing.service.Entity.Bill;

import java.util.List;

public interface BillService {

    Bill generateBill(Bill bill);
    List<Bill> getBillByCustomerId(Long customerId);
    Bill getBillById(Long id);
    Bill updateBill(Long id, Bill updatedBill);
    void deleteBill(Long id);

}
