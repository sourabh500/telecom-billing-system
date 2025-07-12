package Plan.Service.Plan.service.Services;

import Plan.Service.Plan.service.Entity.Plan;
import Plan.Service.Plan.service.Exceptions.PlanNotFoundException;
import Plan.Service.Plan.service.Repository.planRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class planServiceImpl implements planServiceInter{

    private final planRepository repository;

    @Autowired
    public planServiceImpl(planRepository repository)
    {
        this.repository=repository;
    }

    @Override
    public Plan createPlan(Plan plan) {
       return repository.save(plan);
    }

    @Override
    public Plan getPlanById(Long id) {
       return repository.findById(id)
               .orElseThrow(()-> new PlanNotFoundException("Plan not found with the given id:" +id));
    }

    @Override
    public List<Plan> getAllPlan() {
        return repository.findAll();
    }

    @Override
    public Plan updatePlan(Long id, Plan plan) {

      Plan existingPlan= getPlanById(id);
      existingPlan.setName(plan.getName());
      existingPlan.setDescription(plan.getDescription());
      existingPlan.setValidity(plan.getValidity());
      existingPlan.setMonthlyCost(plan.getMonthlyCost());
      return repository.save(existingPlan);
    }

    @Override
    public void deletePlan(Long id) {

        repository.deleteById(id);
    }
}
