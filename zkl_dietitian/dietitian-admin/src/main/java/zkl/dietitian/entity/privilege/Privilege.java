package zkl.dietitian.entity.privilege;



import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 功能点 原子性功能
 * @author seawind
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="privileges")
public class Privilege implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id ;
	
	private String name;
	
	private String description;
	
	private String path; // 功能点对应路径
	
	private String generateMenu; // 1 生成 2 不生成
	
	//父菜单id
		@Column(name="parent_id")
		private Integer parentId;
		
		//状态
		private String state;
		
		//创建时间
		@Column(name="create_time")
		private String createTime;
		
		//创建人
		@Column(name="create_person")
		private String createPerson;

		//排序序号
		private Integer sort;


	// 关联多个功能
	@ManyToMany(targetEntity=Function.class,mappedBy="privileges")
	private Set<Function> functions = new HashSet<Function>();
	
	//-------------------------------------------
	
	public Integer getId() {
		return id;
	}

	

	public Integer getParentId() {
		return parentId;
	}


	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}



	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Set<Function> getFunctions() {
		return functions;
	}

	public void setFunctions(Set<Function> functions) {
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

	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getGenerateMenu() {
		return generateMenu;
	}

	public void setGenerateMenu(String generateMenu) {
		this.generateMenu = generateMenu;
	}

	
}
