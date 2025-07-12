package Plan.Service.Plan.service.Controller;

import Plan.Service.Plan.service.Entity.Plan;
import Plan.Service.Plan.service.Services.planServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plans")
public class planController {

    private final planServiceImpl planService;

    @Autowired
    public planController(planServiceImpl planService) {
        this.planService = planService;
    }

    //Create plan API
    @PostMapping
    public ResponseEntity<Plan> createPlan(@RequestBody Plan plan)
    {
       Plan createdPlan= planService.createPlan(plan);
       return new ResponseEntity<>(createdPlan, HttpStatus.CREATED);
    }

    //Get plan by id API
    @GetMapping("/{id}")
    public ResponseEntity<Plan> getPlanById(@PathVariable Long id)
    {
       Plan planFound= planService.getPlanById(id);
       return new ResponseEntity<>(planFound,HttpStatus.OK);
    }

    //Get all plan API
    @GetMapping
    public ResponseEntity<List<Plan>> getAllPlans()
    {
      List<Plan> allPlans= planService.getAllPlan();
      return new ResponseEntity<>(allPlans,HttpStatus.OK);
    }

    //Update plan API
    @PostMapping("/{id}")
    public ResponseEntity<Plan> updatePlan(@PathVariable Long id,@RequestBody Plan plan)
    {
        Plan updatedPlan=planService.updatePlan(id,plan);
        return new ResponseEntity<>(updatedPlan,HttpStatus.OK);
    }

    //Delete plan API
    @DeleteMapping("/{id}")
    public String deletePlanById(@PathVariable Long id)
    {
        planService.deletePlan(id);
        return "Record has been successfully deleted";
    }

}
