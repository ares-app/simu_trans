package org.ares.app.demo.daos;

import org.ares.app.demo.entities.Carinfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarDao extends JpaRepository<Carinfo, String> {

}
