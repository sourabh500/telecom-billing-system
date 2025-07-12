package Plan.Service.Plan.service.Repository;

import Plan.Service.Plan.service.Entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface planRepository extends JpaRepository<Plan,Long> {
}
