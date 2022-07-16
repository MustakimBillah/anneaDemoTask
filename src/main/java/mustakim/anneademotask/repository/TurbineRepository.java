package mustakim.anneademotask.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import mustakim.anneademotask.entity.Turbine;

public interface TurbineRepository extends JpaRepository<Turbine, Long> {
	Slice<Turbine> findAllBy(Pageable pageable);
	Slice<Turbine> findByTurbineIdAndVariable(Integer turbineId,Integer variable,Pageable pageable);
}
