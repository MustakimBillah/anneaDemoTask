package mustakim.anneademotask.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;

import mustakim.anneademotask.entity.SearchCriteria;
import mustakim.anneademotask.entity.Turbine;

public class TurbineSpecificationsBuilder {
	   
	    private final List<SearchCriteria> params;

	    public TurbineSpecificationsBuilder() {
	        params = new ArrayList<SearchCriteria>();
	    }

	    public TurbineSpecificationsBuilder with(String key, String operation, Object value) {
	        params.add(new SearchCriteria(key, operation, value));
	        return this;
	    }

	    public Specification<Turbine> build() {
	        if (params.size() == 0) {
	            return null;
	        }

	        List<Specification> specs = params.stream()
	                .map(TurbineSpecification::new)
	                .collect(Collectors.toList());
	        
	        Specification result = specs.get(0);

	        for (int i = 1; i < params.size(); i++) {
	            result =Specification.where(result).and(specs.get(i));
	        }       
	        return result;
	    }
}
