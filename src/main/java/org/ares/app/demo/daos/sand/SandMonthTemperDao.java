package org.ares.app.demo.daos.sand;

import org.ares.app.demo.entities.sand.Smonthtemperature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SandMonthTemperDao extends JpaRepository<Smonthtemperature, Integer> {

}
