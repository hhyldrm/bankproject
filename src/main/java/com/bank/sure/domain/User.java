package com.bank.sure.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name="tbl_user")
@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=50,nullable=false)
	private String firstName;
	
	@Column(length=50,nullable=false)
	private String lastName;
	
	@Column(length=12,nullable=false,unique = true)
	private String ssn;

	@Column(length=20,nullable = false,unique = true)
	private String userName;
	
	@Column(length=100,nullable=false,unique =true) 
	private String email;
	
	@Column(length=255,nullable = false)
	private String password;
	
	@Column(length=14,nullable = false)
	private String phoneNumber;
	
	@Column(length=250,nullable = false)
	private String address;
	
	@Column(nullable=false)
	private Boolean enabled=true;
	
	@Column(nullable=false)
	private String dateOfBirth;
	
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="tbl_user_role",joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns =@JoinColumn(name="role_id"))
	private Set<Role> roles=new HashSet<>();
	
	
	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Recipient> recipients;
	
	
	public User(String firstName, String lastName, String ssn,
			String username, String email, String password,
			String phoneNumber, String address, String dateOfBirth,Set<Role> roles){
		
		this.firstName=firstName;
		this.lastName=lastName;
		this.ssn=ssn;
		this.userName=username;
		this.email=email;
		this.password=password;
		this.phoneNumber=phoneNumber;
		this.address=address;
		this.dateOfBirth=dateOfBirth;
		this.roles=roles;
	}
			
	

	
}
