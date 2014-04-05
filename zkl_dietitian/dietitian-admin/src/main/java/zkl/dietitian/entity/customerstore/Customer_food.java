package zkl.dietitian.entity.customerstore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import zkl.dietitian.entity.privilege.Customer;


/**
 * 用户饮食信息表
 * @author zkl
 *
 */
@Entity
@Table(name="customer_food")
public class Customer_food {
	@Id
	@GenericGenerator(name="uuidGenerator" ,strategy="uuid")
	@GeneratedValue(generator="uuidGenerator")
	private String id;
	private String name;
	private Double adi;
	private String times;
	@ManyToOne(targetEntity=Customer.class)
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	
	
//	序号	食品名称	摄入量	时间	用户
//	id	name	adi	time	user_id
//	varchar(24)	Double	varchar(16)	uuid
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
	public Double getAdi() {
		return adi;
	}
	public void setAdi(Double adi) {
		this.adi = adi;
	}

	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
