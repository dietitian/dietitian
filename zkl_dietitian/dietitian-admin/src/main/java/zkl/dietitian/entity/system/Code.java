package zkl.dietitian.entity.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 代码表
 * 
 * @author Lenovo
 * 
 */
@Entity
public class Code {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "code_type")
	private String codeType;
	
	@Column(name = "code_id")
	private String codeId;
	
	@Column(name = "code_name")
	private String codeName;
	
	private String description;
	
	@Column(name = "create_time")
	private String createTime;
	
	@Column(name = "create_person")
	private String createPerson;
	
	@Column(name = "update_time")
	private String updateTime;
	
	@Column(name = "update_person")
	private String updatePerson;
	
	@Column(name = "is_valid")
	private String isValid;
	
	@ManyToOne(targetEntity=CodeMain.class)
	@JoinColumn(name="codeMain_id")
	private CodeMain codeMain;
	
	
	public CodeMain getCodeMain() {
		return codeMain;
	}

	public void setCodeMain(CodeMain codeMain) {
		this.codeMain = codeMain;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdatePerson() {
		return updatePerson;
	}

	public void setUpdatePerson(String updatePerson) {
		this.updatePerson = updatePerson;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	
}
