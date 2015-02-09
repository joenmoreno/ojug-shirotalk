package com.jdm.ojug.shirotalk.dao

import javax.inject.Inject

import org.apache.onami.persist.EntityManagerProvider
import org.apache.onami.persist.Transactional

import com.jdm.ojug.shirotalk.domain.User

class UserDaoImpl implements UserDao {

	private EntityManagerProvider em

	@Inject
	public UserDaoImpl(EntityManagerProvider em) {
		this.em = em
	}

	@Override
	@Transactional
	public User fetchUserByUsername(String username) {

		List<User> result = em.get().createQuery("from User where username = :username")
				.setParameter('username', username).getResultList()

		return result?.size() > 0 ? result[0] : null
	}

	@Override
	@Transactional
	public void persistUser(User user) {
		em.get().persist(user)
	}

}
