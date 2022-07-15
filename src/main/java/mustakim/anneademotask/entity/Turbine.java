package mustakim.anneademotask.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Turbine {

	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.SEQUENCE) private Long searialNo;
	 */
	@Id
	private Long serialNo;
	private Long timeStamp;
	private Double indicator;
	private Integer turbine_id;
	private Integer variable;
	
}
