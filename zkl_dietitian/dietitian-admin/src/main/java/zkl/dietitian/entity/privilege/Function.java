package zkl.dietitian.entity.privilege;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


/**
 * 功能 包含多个功能点
 * @author zkl
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="functions")
public class Function implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String name;
	private String description;
	
	
	//多对多角色与功能
	@ManyToMany(targetEntity=Role.class,mappedBy="functions")
	private List<Role> roles = new ArrayList<Role>();
	
	
	//多对多-功能与功能点
	@ManyToMany(targetEntity=Privilege.class)
	@JoinTable(joinColumns={@JoinColumn(name="function_id")}
				,inverseJoinColumns={@JoinColumn(name="privilege_id")})
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Privilege> privileges = new ArrayList<Privilege>();
	
	
	//多对一-功能与功能模块
	@ManyToOne(targetEntity=Module.class)
	@JoinColumn(name="module_id")
	private Module module ;
	
	
//-------------------------------------------------------
	
	
	
	public Module getModule() {
		return module;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public List<Privilege> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
