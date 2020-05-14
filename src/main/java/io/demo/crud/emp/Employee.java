package io.demo.crud.emp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.demo.crud.dept.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Entity
@Table(name="emp")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "emp_id")
	private Integer empId;
	
	@Basic
	@Column(name = "emp_name")
	private String empName;
	
	@Basic
	@Column(name = "emp_gender")
	private String empGender;
	
	@Basic
	@Column(name = "emp_email")
	private String empEmail;
	
//	@Basic
//	@Column(name = "d_id")
//	private String deptId;
	
	@ManyToOne
	@JoinColumn(name = "d_id")
	private Department dept;
}
