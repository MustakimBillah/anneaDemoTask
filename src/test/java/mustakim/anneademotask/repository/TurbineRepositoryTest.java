package mustakim.anneademotask.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.domain.Specification;

import mustakim.anneademotask.entity.SearchCriteria;
import mustakim.anneademotask.entity.Turbine;
import mustakim.anneademotask.specification.TurbineSpecification;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TurbineRepositoryTest {
	@Autowired
	private TurbineRepository turbineRepository;

	@Autowired
	private TestEntityManager entityManager;

	@BeforeEach
	void setUp() {

		Turbine turbine1 = Turbine.builder()
				.indicator(99.945)
				.variable(1)
				.timeStamp(1483380000000L)
				.turbineId(40)
				.serialNo(-1L)
				.build();
		
		Turbine turbine2 = Turbine.builder()
				.indicator(97.945)
				.variable(10)
				.timeStamp(1473380000000L)
				.turbineId(30)
				.serialNo(-2L)
				.build();
	
		
		entityManager.persist(turbine1);
		entityManager.persist(turbine2);
	}

	@Test
	public void whenFindBySerialNo_thenReturnTurbine() {

		   TurbineSpecification spec = 
				      new TurbineSpecification(new SearchCriteria("serialNo", ":", "-1"));
		
		Turbine turbine = turbineRepository.findAll(spec).get(0);

		assertEquals(turbine.getTurbineId(), 40);
	}
	
	@Test
	public void whenFindByCriterias_thenReturnCorrect() {

		   TurbineSpecification spec1 = 
				      new TurbineSpecification(new SearchCriteria("indicator", ">", "97.10"));
		   TurbineSpecification spec2 = 
				      new TurbineSpecification(new SearchCriteria("variable", ":", "10"));
		   TurbineSpecification spec3 = 
				      new TurbineSpecification(new SearchCriteria("serialNo", "<", "0"));
		
		List<Turbine> turbines = turbineRepository.findAll(Specification.where(spec1).and(spec2).and(spec3));

		assertEquals(turbines.size(), 1);
	}
}
