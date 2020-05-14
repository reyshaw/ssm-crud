package io.demo.crud.user;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Basic
	@Column(name="username", nullable=false, unique=true)
	private String username;
	
	@Basic
	@Column(name="password")
	private String password;
	
	@Basic
	@Column(name="gender")
	private String gender;
	
	@Basic
	@Column(name="address")
	private String address;
	
	@Basic
	@Column(name="birthday")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date birthday;
	
	@Basic
	@Column(name="avatar")
	private String avatar;
	
	@Basic
	@Column(name="hobbies")
	private String hobbies;
	
	@Basic
	@CreatedDate
	@Column(name="reg_time")
	private Date regTime = new Date();
	
}
