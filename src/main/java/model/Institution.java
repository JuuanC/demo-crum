package model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name="institution")
public class Institution extends PanacheEntityBase{
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "SEQ_PROD")
    @SequenceGenerator(name="SEQ_PROD", sequenceName = "SEQ_PROD", allocationSize = 1, initialValue = 11)
    private Long id;
	
	private String name;

	public Institution(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Institution() {}
	
	
}
