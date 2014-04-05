package zkl.dietitian.entity.privilege;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * 模块类包含各个功能
 * @author seawind
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="modules")
public class Module implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String name;
	private String description;
	//一对多-功能模块与功能 -交权
	@OneToMany(targetEntity=Function.class,mappedBy="module")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Function> functions = new ArrayList<Function>();
	//-------------------------------------------------
	
	public Integer getId() {
		return id;
	}
	
	public List<Function> getFunctions() {
		return functions;
	}

	public void setFunctions(List<Function> functions) {
		this.functions = functions;
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
