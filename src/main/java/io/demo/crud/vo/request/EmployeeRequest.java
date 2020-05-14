package io.demo.crud.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class EmployeeRequest {
	
	private Integer id;
	
	private String dept_id;
	
	private String email;
	
	private String gender;
	
	private String name;
}
