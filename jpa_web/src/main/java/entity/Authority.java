package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="AUTHORITY")
public class Authority {
	
	@Id
	private String id;
	
	@Column(name = "ROLE")
	private String role;
	
	public Authority() {}
	public Authority(String id, String role){
		this.id = id;
		this.role = role;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
