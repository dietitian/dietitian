package zkl.dietitian.entity.privilege;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;



@Entity
@Table(name = "t_user")
@NamedQueries({
	@NamedQuery(name="User.nullDescription",query="from User where description is null")
})
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(length = 32)
	private int id;
	
	private String realname; // 真实姓名
	
	@NotEmpty(message="用户名不能为空")
	private String name;//用户名
	
	private String telephone ; //电话
	
	private String email;//邮箱
	
	@Column(length = 32)
	private String password; // 密码
	
	private String gender;  // 性别
	
	@Range(min=0,max=100,message="年龄需在0到100之间") 
	private int age;
	
	@Column(name="description",columnDefinition="TEXT")
	private String description;
	
	@Temporal(TemporalType.DATE)
	private Date birthday;  // 生日
	
	
	
	
	
	// 关联角色      多对多管理者与角色
	@ManyToMany(targetEntity=Role.class)
	
	@JoinTable(joinColumns={@JoinColumn(name="manager_id")}
			,inverseJoinColumns={@JoinColumn(name="role_id")})
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Role> roles= new ArrayList<Role>();
	
	
	//保存路径不与表产生映射
	@Transient
	private List<String> privilegePaths= new ArrayList<String>();
	
	@Transient
	private String checkcode;
	
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCheckcode() {
		return checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<String> getPrivilegePaths() {
		return privilegePaths;
	}

	public void setPrivilegePaths(List<String> privilegePaths) {
		this.privilegePaths = privilegePaths;
	}

	
}
