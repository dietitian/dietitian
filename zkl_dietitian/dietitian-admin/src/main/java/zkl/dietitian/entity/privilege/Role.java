package zkl.dietitian.entity.privilege;



import java.util.ArrayList;

import java.util.List;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * 角色表包含多个模块
 * 
 * @author seawind
 * 
 */
@Entity
@Table(name="roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private String description;

	// 关联多个权限
	@ManyToMany(targetEntity=Function.class)
	@JoinTable(joinColumns={@JoinColumn(name="role_id")},inverseJoinColumns={@JoinColumn(name="function_id")})
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Function> functions = new ArrayList<Function>();
	
	//与管理员多对多-交权
	@ManyToMany(targetEntity=User.class,mappedBy="roles")
	private List<User> managers = new ArrayList<User>();
	//-----------------------------------------
	
	

//	public Set<Customer> getCustomers() {
//		return customers;
//	}
//
//	public void setCustomers(Set<Customer> customers) {
//		this.customers = customers;
//	}
//
//	//与用户多对多-交权
//	@ManyToMany(targetEntity=Customer.class,mappedBy="roles")
//	private Set<Customer> customers = new HashSet<Customer>();
	//---------------------------
	
	public Integer getId() {
		return id;
	}

	public List<Function> getFunctions() {
		return functions;
	}

	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}

	public List<User> getManagers() {
		return managers;
	}

	public void setManagers(List<User> managers) {
		this.managers = managers;
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
