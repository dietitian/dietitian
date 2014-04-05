package zkl.dietitian.entity.privilege;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


@Entity
@Table(name="managers")
public class Manager {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String username; // 用户名
	private String password; // 密码
	private String gender;  // 性别
	
	@Temporal(TemporalType.DATE)
	private Date birthday;  // 生日
	private String telephone ; //电话
	
	private String info; // 备注
	
	// 关联角色      多对多管理者与角色
	@ManyToMany(targetEntity=Role.class)
	@JoinTable(joinColumns={@JoinColumn(name="manager_id")}
				,inverseJoinColumns={@JoinColumn(name="role_id")})
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Role> roles= new ArrayList<Role>();
	
	
	//保存路径不与表产生映射
	@Transient
	private List<String> privilegePaths= new ArrayList<String>();
//----------------------------------------------------------------------------
	
	public long getId() {
		return id;
	}

	public List<String> getPrivilegePaths() {
		return privilegePaths;
	}

	public void setPrivilegePaths(List<String> privilegePaths) {
		this.privilegePaths = privilegePaths;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}


	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}



	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
