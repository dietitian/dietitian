package zkl.dietitian.entity.customerstore;

import zkl.dietitian.entity.privilege.Customer;

/**
 * 用户营养水平
 * @author zkl
 *
 */
public class Customer_nut {
	
	private String id;
	private Customer customer;
	private Double energy;
	private Double protein;
	private Double fat;
	private Double df;
	private Double carbohydrate;
	private Double vax;
	private Double vb1;
	private Double vb2;
	private Double vb3;
	private Double ve;
	private Double na;
	private Double ca;
	private Double fe;
	private Double vc;
	private Double cholesterol;
	//---------------------------
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Double getEnergy() {
		return energy;
	}
	public void setEnergy(Double energy) {
		this.energy = energy;
	}
	public Double getProtein() {
		return protein;
	}
	public void setProtein(Double protein) {
		this.protein = protein;
	}
	public Double getFat() {
		return fat;
	}
	public void setFat(Double fat) {
		this.fat = fat;
	}
	public Double getDf() {
		return df;
	}
	public void setDf(Double df) {
		this.df = df;
	}
	public Double getCarbohydrate() {
		return carbohydrate;
	}
	public void setCarbohydrate(Double carbohydrate) {
		this.carbohydrate = carbohydrate;
	}
	public Double getVax() {
		return vax;
	}
	public void setVax(Double vax) {
		this.vax = vax;
	}
	public Double getVb1() {
		return vb1;
	}
	public void setVb1(Double vb1) {
		this.vb1 = vb1;
	}
	public Double getVb2() {
		return vb2;
	}
	public void setVb2(Double vb2) {
		this.vb2 = vb2;
	}
	public Double getVb3() {
		return vb3;
	}
	public void setVb3(Double vb3) {
		this.vb3 = vb3;
	}
	public Double getVe() {
		return ve;
	}
	public void setVe(Double ve) {
		this.ve = ve;
	}
	public Double getNa() {
		return na;
	}
	public void setNa(Double na) {
		this.na = na;
	}
	public Double getCa() {
		return ca;
	}
	public void setCa(Double ca) {
		this.ca = ca;
	}
	public Double getFe() {
		return fe;
	}
	public void setFe(Double fe) {
		this.fe = fe;
	}
	public Double getVc() {
		return vc;
	}
	public void setVc(Double vc) {
		this.vc = vc;
	}
	public Double getCholesterol() {
		return cholesterol;
	}
	public void setCholesterol(Double cholesterol) {
		this.cholesterol = cholesterol;
	}
	
}
