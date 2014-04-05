package zkl.dietitian.entity.customerstore;

import zkl.dietitian.entity.privilege.Customer;


/**
 * 用户营养过剩
 * @author zkl
 *
 */
public class Morep_u {
	private String id;
	private String name;
	private Double morepart;
	private Customer customer;
	//--------------------------
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
	public Double getMorepart() {
		return morepart;
	}
	public void setMorepart(Double morepart) {
		this.morepart = morepart;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
