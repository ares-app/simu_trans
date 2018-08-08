package org.ares.app.demo.daos;

import org.ares.app.demo.entities.Loginuser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginDao extends JpaRepository<Loginuser, String> {
	Loginuser findByUsernameAndUserpwd(String username,String userpwd);
}
