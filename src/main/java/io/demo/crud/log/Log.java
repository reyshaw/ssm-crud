package io.demo.crud.log;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@ToString
@Table(name =  "log")
public class Log {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Basic
	@Column(name="operator")
	private String operator;
	
	@Basic
	@Column(name="type")
	private String type;
	
	@Basic
	@Column(name="result")
	private String result;
	
	@Basic
	@Column(name="opt_time")
	private Date OptTime = new Date();

}
