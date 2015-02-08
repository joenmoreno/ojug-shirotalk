package com.jdm.ojug.shirotalk.dao

import javax.inject.Inject
import javax.persistence.EntityManager

import com.google.inject.persist.Transactional
import com.jdm.ojug.shirotalk.domain.User

class UserDaoImpl implements UserDao {

	private EntityManager em

	@Inject
	public UserDaoImpl(EntityManager em) {
		this.em = em
	}

	@Override
	@Transactional
	public User fetchUserByUsername(String username) {

		List<User> result = em.createQuery("from User where username = :username")
				.setParameter('username', username).getResultList()

		return result?.size() > 0 ? result[0] : null
	}

	@Override
	@Transactional
	public void persistUser(User user) {
		em.persist(user)
		
	}

}
