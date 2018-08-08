package org.ares.app.demo.daos.sand;

import org.ares.app.demo.entities.sand.Scarinfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SandCarInfoDao extends JpaRepository<Scarinfo, Integer> {

}
