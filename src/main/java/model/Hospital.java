package model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;

@Entity(name="hospital")
@Data
@Table(name ="hospital")
public class Hospital extends PanacheEntityBase {
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "SEQ_PROD")
    @SequenceGenerator(name="SEQ_PROD", sequenceName = "SEQ_PROD", allocationSize = 1, initialValue = 1)
	@JsonProperty(value = "id")
	private Long id;
	
	@Column(unique=true)
	@JsonProperty(value = "nameHospital")
	private String nameHospital;
	
	@JsonProperty(value = "street")
	private String street;
	
	@JsonProperty(value = "streetNumber")
	private String streetNumber;
	
	@JsonProperty(value = "zipcode")
	private String zipCode;
	
	@JsonIgnore
	@JsonProperty(value = "institution")
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Institution institution;

	public Hospital(Long id, String nameHospital, String street, String streetNumber, String zipCode,
			Institution institution) {
		super();
		this.id = id;
		this.nameHospital = nameHospital;
		this.street = street;
		this.streetNumber = streetNumber;
		this.zipCode = zipCode;
		this.institution = institution;
	}
	
	public Hospital() {}
	
	
}
