package org.ares.app.demo.daos.sand;

import java.util.List;

import org.ares.app.demo.entities.sand.Sbusstation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SandBusStationDao extends JpaRepository<Sbusstation, Integer> {

	List<Sbusstation> findByBusstationid(int busstationid);
}
