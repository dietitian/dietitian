package zkl.dietitian.entity.privilege;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import zkl.dietitian.entity.customerstore.Customer_food;


@Entity
@Table(name="customers")
public class Customer {
	@Id
	@GenericGenerator(name="uuidGenerator",strategy="uuid")
	@GeneratedValue(generator="uuidGenerator")
	private String id;
	private String name;
	private String password;
	private String isactive;//判断是否激活
	@Column(length=64)
	private String activeuuid64;//激活码0未激活1激活2锁定
	private String telephone;
	private String mail;
	private String gender;
	private String member;//是否会员
	private Long login_num;//登录次数
	private Long post_num;//发帖回帖次数
	
	@ManyToMany(targetEntity=Role.class)
	@JoinTable(joinColumns={@JoinColumn(name="customer_id")}
				,inverseJoinColumns={@JoinColumn(name="role_id")})
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Role> roles= new ArrayList<Role>();
	
	//客户与食物摄入表一对多
	@OneToMany(targetEntity=Customer_food.class, mappedBy="customer")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Customer_food> Customer_foods = new HashSet<Customer_food>();
	
	
	@Transient
	private List<String> privilegePaths= new ArrayList<String>();
	//------------------------
	
	public String getId() {
		return id;
	}
	public Set<Customer_food> getCustomer_foods() {
		return Customer_foods;
	}
	public void setCustomer_foods(Set<Customer_food> customer_foods) {
		Customer_foods = customer_foods;
	}
	public List<String> getPrivilegePaths() {
		return privilegePaths;
	}
	public void setPrivilegePaths(List<String> privilegePaths) {
		this.privilegePaths = privilegePaths;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIsactive() {
		return isactive;
	}
	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}
	public String getActiveuuid64() {
		return activeuuid64;
	}
	public void setActiveuuid64(String activeuuid64) {
		this.activeuuid64 = activeuuid64;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	public Long getLogin_num() {
		return login_num;
	}
	public void setLogin_num(Long login_num) {
		this.login_num = login_num;
	}
	public Long getPost_num() {
		return post_num;
	}
	public void setPost_num(Long post_num) {
		this.post_num = post_num;
	}
	
//		telephone	mail	gender(1男0女)	member	login_num	post_num
//		varchar(15)	varchar(40)	char(1)	char（1）	Long	Long

}
