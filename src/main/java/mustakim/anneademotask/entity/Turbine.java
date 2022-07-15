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

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long searialNo;
	private long timeStamp;
	private double indicator;
	private int turbine_id;
	private int variable;
	
}
