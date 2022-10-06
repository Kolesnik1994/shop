package shop.com.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


/**
 * simple POJO Java class that represented entity
 * @author JCoder & (VLADISLAV KOLESNYK)
 */

@Entity
@Table (name = "User")
public class User {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty	
	@Column (name = "firstName", nullable=false)
	private String firstName;
	@Column (name="lastName")
	private String lastName;
	@NotEmpty
	@Column (name = "email", nullable = false, unique = true)
	@Email (message = "{errors.invalid_email}")
	private String email;
	@NotEmpty
	@Column (name = "password")
	private String password;
	@ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable (
			    name = "user_role",
			    joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn (name = "role_id", referencedColumnName = "id"))
	
    private List <Role> roles;
	
	public User () {}
	
    public User( User user) {
	this.firstName = user.getFirstName();
	this.lastName = user.getLastName();
	this.email = user.getEmail();
	this.password = user.getPassword();
	this.roles = user.getRoles();

    }
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
