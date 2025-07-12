package Plan.Service.Plan.service.Services;

import Plan.Service.Plan.service.Entity.Plan;

import java.util.List;

public interface planServiceInter {


    //create plan
    Plan createPlan(Plan plan);

    //Get plan by id
    Plan getPlanById(Long id);

    //Get all plan
    List<Plan> getAllPlan();

    //Update plan
    Plan updatePlan(Long id,Plan plan);

    //Delete plan
    void deletePlan(Long id);


}
