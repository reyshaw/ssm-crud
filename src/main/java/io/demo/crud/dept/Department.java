package io.demo.crud.dept;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data // getter/setter
@ToString
@Entity
@Table(name = "dept")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Department {
	
	@Id
	@Column(name = "dept_id")
	private Integer deptId;
	
	@Basic
	@Column(name = "dept_name")
	private String deptName;
	
}
