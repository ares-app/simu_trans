package org.ares.app.demo.daos;

import javax.transaction.Transactional;

import org.ares.app.demo.entities.SParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ParamDao extends JpaRepository<SParam, String> {
	@Transactional @Modifying @Query("update SParam p set p.val=?1 where p.name='sand_ip'")
	int setip(String val);
}
