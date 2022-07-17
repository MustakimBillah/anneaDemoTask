package mustakim.anneademotask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import mustakim.anneademotask.entity.Turbine;

public interface TurbineRepository extends JpaRepository<Turbine, Long>,JpaSpecificationExecutor<Turbine> {

	@Query("SELECT coalesce(max(t.serialNo), 0) FROM Turbine t")
	Long getMaxSerialNo();
}
