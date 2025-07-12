package Billing.service.Billing.service.External;

import Billing.service.Billing.service.Dtos.customerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface customerClient {

    @GetMapping("/api/customers/{id}")
    customerDto getCustomerById(@PathVariable Long id);

}
