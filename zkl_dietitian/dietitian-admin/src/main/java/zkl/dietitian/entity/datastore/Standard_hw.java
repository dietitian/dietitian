package zkl.dietitian.entity.datastore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 身高体重表
 * @author zkl
 *
 */
@Entity
@Table(name="standard_hw")
public class Standard_hw {
	@Id
	@GenericGenerator(name="uuidGenerator" ,strategy="uuid")
	@GeneratedValue(generator="uuidGenerator")
	private String id;
	private String gender;//男，女
	private Double height;
	private Double weight;
	private Integer age;
	
//	序号	性别	身高	体重	年龄
//	Integer	gender	height	weight	age
//	自增	char（1）	Double	Double	Integer
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
}
