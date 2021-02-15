package entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import util.StringUtil;

@Entity
@Table(name="MEMBER")
public class Member {
	
	@Id
	@Column(name="ID",length=25)
	private String id;
	
	@Column(name="PW",length=25,nullable=false)
	private String pw;
	
	@Column(name="AUTH",length=25)
	private String auth = "2";
	
	@Column(name="DEPARTMENT",length=25)
	private String department;
	
	@Column(name="PHONE_IN",length=25)
	private String phoneIn;
	
	@Column(name="PHONE_EX",length=25)
	private String phoneEx;
	
	@Column(name="REGIST_DATE")
	@DateTimeFormat(iso=ISO.DATE)
	private Date registDate;
	
	@Column(name="PW_UPDATE")
	@DateTimeFormat(iso=ISO.DATE)
	private Date pwUpdate;
	
	@Column(name="LOGIN_CNT", length=25)
	private String loginCnt = "0";
	
	@OneToOne
	@JoinColumn(name="ID")
	private Authority authority;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = StringUtil.encodeString(department, "ISO-8859-1", "EUC-KR");
	}

	public String getPhoneIn() {
		return phoneIn;
	}

	public void setPhoneIn(String phoneIn) {
		this.phoneIn = phoneIn;
	}

	public String getPhoneEx() {
		return phoneEx;
	}

	public void setPhoneEx(String phoneEx) {
		this.phoneEx = phoneEx;
	}

	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	public Date getPwUpdate() {
		return pwUpdate;
	}

	public void setPwUpdate(Date pwUpdate) {
		this.pwUpdate = pwUpdate;
	}

	public String getLoginCnt() {
		return loginCnt;
	}

	public void setLoginCnt(String loginCnt) {
		this.loginCnt = loginCnt;
	}
	
	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}
}
