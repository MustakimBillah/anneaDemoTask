package mustakim.anneademotask.controller;

import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import mustakim.anneademotask.entity.Turbine;
import mustakim.anneademotask.service.TurbineService;


@WebMvcTest(TurbineController.class)
public class TurbineControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TurbineService turbineService;
	
	private Turbine turbine;
	
	@BeforeEach
	void setUp() {
		turbine = Turbine.builder()
				.indicator(99.945)
				.variable(1)
				.timeStamp(1483380000000L)
				.turbineId(40)
				.serialNo(-1L)
				.build();
	}
	
	
	@Test
	 void search() throws Exception {
		
		Map<String, Object> demoResult = new HashedMap<>();
		demoResult.put("content", turbine);
		
		String searchQuery="serialNo:\"-1\"";
		
		Mockito.when(turbineService.searchData(searchQuery))
		.thenReturn(demoResult);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/search")
				.param("search", searchQuery)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
