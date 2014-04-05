package zkl.dietitian.entity.datastore;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * 缺失营养建议
 * @author zkl
 *
 */
@Entity
@Table(name="food_sus")
public class Food_sus {
	@Id
	@GenericGenerator(name="uuidGenerator" ,strategy="uuid")
	@GeneratedValue(generator="uuidGenerator")
	private String id;
	private String name;
	private Double content;
	@ManyToMany(targetEntity=Lowerp_p.class)
	@JoinTable(joinColumns={@JoinColumn(name="food_sus_id")}
				,inverseJoinColumns={@JoinColumn(name="lowerp_p_id")})
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Lowerp_p> lowerp_ps = new HashSet<Lowerp_p>();
	
//	序号	食品名称	含量	缺失营养
//	id	name	content	lowerp_p_id
//	uuid	varchar(24)	Double	uuid
	
	public String getId() {
		return id;
	}
	public Set<Lowerp_p> getLowerp_ps() {
		return lowerp_ps;
	}
	public void setLowerp_ps(Set<Lowerp_p> lowerp_ps) {
		this.lowerp_ps = lowerp_ps;
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
	public Double getContent() {
		return content;
	}
	public void setContent(Double content) {
		this.content = content;
	}

	
}
