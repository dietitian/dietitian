package zkl.dietitian.entity.datastore;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 营养水平低造成的问题
 * @author zkl
 *
 */
@Entity
@Table(name="lowerp_p")
public class Lowerp_p {
	@Id
	@GenericGenerator(name="uuidGenerator" ,strategy="uuid")
	@GeneratedValue(generator="uuidGenerator")
	private String id;
	
	private String name;
	private String lowerproblem;
	//与缺失营养食品建议表多对多，交权
	@ManyToMany(targetEntity=Food_sus.class,mappedBy="lowerp_ps")
	private Set<Food_sus> food_suses = new HashSet<Food_sus>();
//	序号	营养元素名称	问题
//	id	name	lowerproblem
//	uuid	varchar(24)	varchar（255）
	
	public String getId() {
		return id;
	}

	public Set<Food_sus> getFood_suses() {
		return food_suses;
	}

	public void setFood_suses(Set<Food_sus> food_suses) {
		this.food_suses = food_suses;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLowerproblem() {
		return lowerproblem;
	}
	public void setLowerproblem(String lowerproblem) {
		this.lowerproblem = lowerproblem;
	}
	
}
