package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import security.enums.RoleEnum;

@Entity(name="role")
@Table(name="role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleSequence")
	@SequenceGenerator(name="roleSequence", sequenceName = "roleSequence", allocationSize = 1, initialValue = 1)
    @Column
    private long id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleEnum role;
    
    public Role(@NotNull RoleEnum role) {
    	this.role = role;
    }
    
    public Role() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public RoleEnum getRole() {
		return role;
	}

	public void setRole(RoleEnum role) {
		this.role = role;
	}

	public Role(long id, @NotNull RoleEnum role) {
		super();
		this.id = id;
		this.role = role;
	}
    
    
}