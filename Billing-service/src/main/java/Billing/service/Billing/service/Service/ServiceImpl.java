package Billing.service.Billing.service.Service;

import Billing.service.Billing.service.Dtos.PlanDto;
import Billing.service.Billing.service.Dtos.customerDto;
import Billing.service.Billing.service.Entity.Bill;
import Billing.service.Billing.service.Exceptions.BillNotFoundException;
import Billing.service.Billing.service.External.PlanClient;
import Billing.service.Billing.service.External.customerClient;
import Billing.service.Billing.service.Repository.BillRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceImpl implements BillService{

    private final BillRepo billRepo;
    private final customerClient client;
    private final PlanClient planClient;

    //Creating bill
    @Override
    public Bill generateBill(Bill bill) {

        // Validate customer exists via Feign Client
        customerDto customer = client.getCustomerById(bill.getCustomerId());

        // 2. Validate plan
        PlanDto plan= planClient.getPlanById(bill.getPlanId());

        // 3. Set generatedDate
        bill.setGeneratedDate(LocalDate.now());

        // 4. Set payment status to unpaid by default
        bill.setIsPaid(false);

        //5. Calculate total amount from plan cost
        bill.setAmount(plan.getMonthlyCost());

        // 6. Save and return the bill
        return billRepo.save(bill);
    }

    //Getting bill by customerId
    @Override
    public List<Bill> getBillByCustomerId(Long customerId) {

        // Validate customer exists via Feign Client
        client.getCustomerById(customerId);

       return billRepo.findByCustomerId(customerId);
    }

    //Getting bill by id
    @Override
    public Bill getBillById(Long id) {

       return billRepo.findById(id)
               .orElseThrow(()->new BillNotFoundException("Bill not found with the given id "+id));
    }

    //update bill
    @Override
    public Bill updateBill(Long id, Bill updatedBill) {

       Bill existing= getBillById(id);
       existing.setDataUsageInGB(updatedBill.getDataUsageInGB());
       existing.setAmount(updatedBill.getAmount());
       existing.setCallMinutes(updatedBill.getCallMinutes());
       existing.setSmsCount(updatedBill.getSmsCount());
       existing.setIsPaid(updatedBill.getIsPaid());
       return billRepo.save(existing);

    }

    @Override
    public void deleteBill(Long id) {

     Bill existing=  billRepo.findById(id)
             .orElseThrow(()-> new BillNotFoundException("Bill not found with the given id "+ id));

     billRepo.delete(existing);

    }
}
