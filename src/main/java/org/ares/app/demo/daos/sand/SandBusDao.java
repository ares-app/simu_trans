package org.ares.app.demo.daos.sand;

import org.ares.app.demo.entities.sand.Sbusinfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SandBusDao extends JpaRepository<Sbusinfo, Integer> {

}
