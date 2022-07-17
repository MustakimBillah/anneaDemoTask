package mustakim.anneademotask.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

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
	private Long serialNo;
	private Long timeStamp;
	private Double indicator;
	private Integer turbineId;
	private Integer variable;
	@Transient 
	private String  dateTime;

}
