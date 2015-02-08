package com.jdm.ojug.shirotalk.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.OneToMany
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table(name="SHIROTALK_USER")
class User {
	
	@Id
	@Column(name="USER_ID")
	@SequenceGenerator(name="USER_SEQUENCE", initialValue=1, allocationSize=10)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_SEQUENCE")
	Long userId
	
	@Column(name="USERNAME", length=12, unique=true, nullable=false)
	String username
	
	@Column(name="EMAIL", length=100, unique=true, nullable=false)
	String email
	
	@Column(name="PASSWORD", length=100, nullable=false)
	String password
	
	@Column(name="SALT", length=100, nullable=false)
	String salt
	
	@Column(name="FIRST_NAME", length=50)
	String firstName
	
	@Column(name="LAST_NAME", length=50)
	String lastName
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinTable
	(
		name="USER_ROLE_JOIN",
		joinColumns=[ @JoinColumn(name="USER_ID", referencedColumnName="USER_ID") ],
		inverseJoinColumns=[ @JoinColumn(name="ROLE_ID", referencedColumnName="ROLE_ID", unique=true) ]
	)
	List<Role> roles
	
}
