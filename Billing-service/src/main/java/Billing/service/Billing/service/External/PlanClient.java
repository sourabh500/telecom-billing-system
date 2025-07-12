package Billing.service.Billing.service.External;

import Billing.service.Billing.service.Dtos.PlanDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name= "PLAN-SERVICE")
public interface PlanClient {

    @GetMapping("/api/plans/{id}")
    PlanDto getPlanById(@PathVariable Long id);


}
