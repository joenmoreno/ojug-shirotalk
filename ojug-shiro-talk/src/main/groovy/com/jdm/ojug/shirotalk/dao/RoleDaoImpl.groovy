package com.jdm.ojug.shirotalk.dao

import javax.inject.Inject

import org.apache.onami.persist.EntityManagerProvider
import org.apache.onami.persist.Transactional

import com.jdm.ojug.shirotalk.domain.Role

class RoleDaoImpl implements RoleDao {

	private EntityManagerProvider em

	@Inject
	public UserDaoImpl(EntityManagerProvider em) {
		this.em = em
	}


	@Override
	@Transactional
	public void persistRole(Role role) {
		em.get().persist(role)
	}
}
