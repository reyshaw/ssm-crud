package org.demo.dto;

public class EmployeeRequest {
	
	public EmployeeRequest() {}
	
	public EmployeeRequest(Integer id, String name, String gender, String email, Integer dept_id) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.dept_id = dept_id;
	}

	@Override
	public String toString() {
		return "EmployeeRequest [name=" + name + ", gender=" + gender + ", email=" + email + ", dept_id=" + dept_id
				+ "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getDept_id() {
		return dept_id;
	}

	public void setDept_id(Integer dept_id) {
		this.dept_id = dept_id;
	}

	private Integer id;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private String name;
	
	private String gender;
	
	private String email;
	
	private Integer dept_id;
}
