package org.ares.app.test;

import javax.annotation.Resource;

import org.ares.app.demo.daos.CarDao;
import org.ares.app.demo.daos.sand.SandCarInfoDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestappApplication {

	@Test
	public void testCar() {
		log.debug(carDao.count()+"");
	}
	
	@Test
	public void testSCar() {
		String action="Start";
		Integer carid=Integer.valueOf(1);
		sCarInfoDao.findOne(carid).setCaraction(action);
	}
	
	final Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource SandCarInfoDao sCarInfoDao;
	@Resource CarDao carDao;
}
