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

import security.enums.RoleEnum;
/**
 * Recopila y mapea la información relacionado con los roles de los usuarios del sistema, conformada unicamente por dos atributos
 * <code>id</code> y <code>role</code> este ultimo haciendo referencia a @see security.enums.RoleEnum enumarador con los
 * roles utilizados por el sistema.
 * Mapeada a la BD con Hibernate ORM como la tabla <code>role</code> y relacionada de muchos a muchos con la tabla <code>accounts</code>
 * @author José Alberto Espinoza
 * 
 */
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

	/**
	 * Constructor que inicializa todos los atributos de la clase Role
	 * @param id identificador unico del rol
	 * @param role nombre del rol
	 */
	public Role(long id, @NotNull RoleEnum role) {
		super();
		this.id = id;
		this.role = role;
	}
    
    
}