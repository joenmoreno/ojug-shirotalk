package com.jdm.ojug.shirotalk.dao

import javax.inject.Inject
import javax.persistence.EntityManager

import com.google.inject.persist.Transactional
import com.jdm.ojug.shirotalk.domain.Role

class RoleDaoImpl implements RoleDao {

	private EntityManager em
	
		@Inject
		public UserDaoImpl(EntityManager em) {
			this.em = em
		}
	
	
		@Override
		@Transactional
		public void persistRole(Role role) {
			em.persist(role)
			
		}
}
