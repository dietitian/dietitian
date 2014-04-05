package zkl.dietitian.entity.datastore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 营养水平高造成的问题
 * @author zkl
 *
 */
@Entity
@Table(name="morep_p")
public class Morep_p {
	@Id
	@GenericGenerator(name="uuidGenerator" ,strategy="uuid")
	@GeneratedValue(generator="uuidGenerator")
	private String id;
	
	private String name;
	private String moreproblem;
//	序号	营养元素名称	问题
//	id	name	lowerproblem
//	uuid	varchar(24)	varchar（255）
	public String getId() {
		return id;
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
	public String getMoreproblem() {
		return moreproblem;
	}
	public void setMoreproblem(String moreproblem) {
		this.moreproblem = moreproblem;
	}
	
}
