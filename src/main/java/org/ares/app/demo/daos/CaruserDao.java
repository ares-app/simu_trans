package org.ares.app.demo.daos;

import org.ares.app.demo.entities.Caruser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaruserDao extends JpaRepository<Caruser, String> {

}
