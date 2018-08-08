package org.ares.app.demo.daos.sand;

import org.ares.app.demo.entities.sand.Scarblacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface SandCarBlackListDao extends JpaRepository<Scarblacklist, Integer> {

	@Transactional
	@Modifying
	@Query("delete from Scarblacklist cbl where cbl.username = ?1")
	void deleteByUsername(String username);
}
