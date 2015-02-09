package com.jdm.ojug.shirotalk.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.persistence.Table

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="ROLE")
class Role {
	
	@JsonIgnore
	@Id
	@Column(name="ROLE_ID")
	@SequenceGenerator(name="ROLE_SEQUENCE", initialValue=1, allocationSize=10)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ROLE_SEQUENCE")
	Long roleId
	
	@Column(name="CODE", length=12, unique=true, nullable=false)
	String code
	
	@JsonIgnore
	@Column(name="DESCRIPTION", length=100, nullable=false)
	String description

}
