package mustakim.anneademotask.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.Nullable;

import mustakim.anneademotask.entity.Turbine;

public interface TurbineRepository extends JpaRepository<Turbine, Long>,JpaSpecificationExecutor<Turbine> {
	Slice<Turbine> findAllBy(Specification<Turbine> spec,Pageable pageable);
	Slice<Turbine> findByTurbineIdAndVariable(Integer turbineId,Integer variable,Pageable pageable);
}
