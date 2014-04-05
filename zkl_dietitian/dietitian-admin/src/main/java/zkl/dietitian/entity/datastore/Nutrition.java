package zkl.dietitian.entity.datastore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 营养元素表
 * @author zkl
 *
 */
@Entity
@Table(name="nutritions")
public class Nutrition {
	@Id
	@GenericGenerator(name="uuidGenerator",strategy="uuid")
	@GeneratedValue(generator="uuidGenerator")
	private String id;
	
	//名称
	private String name;
	
	//地区
	private String region;
	
	//可食部分
	private Double edible_part;
	
	//能量
	private Double energy;
	
	//水分
	private Double moisture;
	
	//蛋白质
	private Double protein;
	
	//脂肪
	private Double fat;
	
	//膳食纤维
	private Double df;
	
	//碳水化合物
	private Double carbohydrate;
	
	//视黄醇当量
	private Double vax;
	
	//硫胺素VB1
	private Double vb1;
	
	//核黄素VB2
	private Double vb2;
	
	//尼克酸（烟酸VPP）
	private Double vb3;
	
	
	//维生素E
	private Double ve;
	
	//钠
	private Double na;
	
	//钙
	private Double ca;
	
	//铁
	private Double fe;
	
	//维生素C
	private Double vc;
	
	//胆固醇
	private Double cholesterol;
	
	//类别
	private String category;
	
	//类
	private String types;
	
	//自身父类编号
	private String pcategory;
	
	//描述
	@Column(name="description",columnDefinition="TEXT")
	private String description;
	
//	序号	名  称	地区	可食部分	能量	水分	蛋白质	脂肪	膳食纤维	碳水化物	视黄醇当量	硫胺素(VB1)	核黄素(VB2)	"尼克酸
//(烟酸,
//VPP)"	维生素E	钠	钙	铁	类别	抗坏血酸(VC)	类	胆固醇
//	     id	        name	region	edible_part	energy	moisture	protein	fat	df	carbohydrate	vax	vb1	vb2	vb3	ve	na	ca	fe	category	vc	types	cholesterol
//数据类型	uuid	varchar(50)	varchar(30)	Double	Double	Double	Double	Double	Double	Double	Double	Double	Double	Double	Double	Double	Double	Double	varchar(20)	Double	varchar(20)	Double
	
	
	public String getId() {
		return id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPcategory() {
		return pcategory;
	}
	public void setPcategory(String pcategory) {
		this.pcategory = pcategory;
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
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public Double getEdible_part() {
		return edible_part;
	}
	public void setEdible_part(Double edible_part) {
		this.edible_part = edible_part;
	}
	public Double getEnergy() {
		return energy;
	}
	public void setEnergy(Double energy) {
		this.energy = energy;
	}
	public Double getMoisture() {
		return moisture;
	}
	public void setMoisture(Double moisture) {
		this.moisture = moisture;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTypes() {
		return types;
	}
	public void setTypes(String types) {
		this.types = types;
	}
	
}
