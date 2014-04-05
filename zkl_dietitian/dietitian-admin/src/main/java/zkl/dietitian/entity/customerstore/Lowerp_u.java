package zkl.dietitian.entity.customerstore;

import zkl.dietitian.entity.privilege.Customer;


/**
 * 用户营养缺失
 * @author zkl
 *
 */
public class Lowerp_u {
	private String id;
	private String name;
	private Double lowerpart;
	private Customer customer;

//	序号	名称	缺失量	用户
//	id	name	lowerpart	customer_id
//	uuid	varchar(24)	Double	varchar(32)
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
	public Double getLowerpart() {
		return lowerpart;
	}
	public void setLowerpart(Double lowerpart) {
		this.lowerpart = lowerpart;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
