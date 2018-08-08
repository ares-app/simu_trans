package org.ares.app.demo.daos;

import java.util.List;

import javax.transaction.Transactional;

import org.ares.app.demo.entities.SUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<SUser, String> {

	SUser findByUsername(String username);
	@Transactional @Modifying @Query("update SUser u set u.role='adv_user' where u.username=?1 and username<>'admin'")
	int setAdvUser(String username);
	@Transactional @Modifying @Query("update SUser u set u.role='nor_user' where u.username=?1 and username<>'admin'")
	int setNorUser(String username);
	@Transactional @Modifying @Query("update SUser u set u.role=null where u.username=?1 and username<>'admin'")
	int disableUser(String username);
	@Query("select u from SUser u where u.username<>'admin' and length(u.username)=5")// order by u.username
	List<SUser> queryAll(Pageable page);
	List<SUser> findByUsernameNotLike(String username);
}
