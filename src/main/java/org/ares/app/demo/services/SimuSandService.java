package org.ares.app.demo.services;

import static org.ares.app.demo.common.cfg.Params.ERR_MSG_MONEY_LE_ZERO;
import static org.ares.app.demo.common.cfg.Params.ERR_MSG_MONEY_NOT_SUFF;
import static org.ares.app.demo.common.cfg.Params.ERR_MSG_NOT_FOUND_ENTITY;
import static org.ares.app.demo.common.cfg.Params.ERR_MSG_PARAM_VALUE;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.ares.app.demo.daos.sand.SandBusStationDao;
import org.ares.app.demo.daos.sand.SandCarChangeDao;
import org.ares.app.demo.daos.sand.SandCarFeeDao;
import org.ares.app.demo.daos.sand.SandCarInfoDao;
import org.ares.app.demo.daos.sand.SandEtcTraLogDao;
import org.ares.app.demo.daos.sand.SandMonthTemperDao;
import org.ares.app.demo.daos.sand.SandOtherSingleDao;
import org.ares.app.demo.daos.sand.SandRoadLightDao;
import org.ares.app.demo.daos.sand.SandTransLightDao;
import org.ares.app.demo.entities.sand.Scarcharge;
import org.ares.app.demo.entities.sand.Scarfee;
import org.ares.app.demo.entities.sand.Scarinfo;
import org.ares.app.demo.entities.sand.Smonthtemperature;
import org.ares.app.demo.entities.sand.Sothersingle;
import org.ares.app.demo.entities.sand.Sroadlight;
import org.ares.app.demo.entities.sand.Stralight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public class SimuSandService {

	public void setCarAction(Integer carid,String action) {
		boolean dataSuccess="Start".equals(action)||"Stop".equals(action);
		if(!dataSuccess) throw new RuntimeException(ERR_MSG_PARAM_VALUE);
		Scarinfo sc=sCarInfoDao.findOne(carid);
		if(sc==null) throw new RuntimeException(ERR_MSG_NOT_FOUND_ENTITY);
		sc.setCaraction(action);
		sCarInfoDao.save(sc);
	}
	
	public String getCarAction(Integer carid) {
		Scarinfo sc=sCarInfoDao.findOne(carid);
		if(sc==null) throw new RuntimeException(ERR_MSG_NOT_FOUND_ENTITY);
		return sc.getCaraction();
	}
	
	public Integer getCarAccountBalance(Integer carid) {
		Scarinfo sc=sCarInfoDao.findOne(carid);
		if(sc==null) throw new RuntimeException(ERR_MSG_NOT_FOUND_ENTITY);
		return sc.getBalance();
	}
	
	public void setCarAccountRecharge(Integer carid,Integer money) {
		Scarinfo sc=sCarInfoDao.findOne(carid);
		if(sc==null) throw new RuntimeException(ERR_MSG_NOT_FOUND_ENTITY);
		if(money<=0) throw new RuntimeException(ERR_MSG_MONEY_LE_ZERO);
		sc.setBalance(sc.getBalance()+money.intValue());
		sCarInfoDao.save(sc);
		Scarcharge scc=new Scarcharge();
		scc.setChargetime(new Date());
		scc.setMoney(money.intValue());
		scc.setScarinfo(sc);
		sCarChargeDao.save(scc);
	}
	
	public List<Map<String,Object>> getCarAccountRecord(Integer carid){
		List<Map<String,Object>> r=new ArrayList<>();
		sCarInfoDao.getOne(carid).getScarcharges().stream().forEach(e->{
			Map<String,Object> m=new HashMap<>();
			m.put("CarId", e.getScarinfo().getCarid());
			m.put("Cost", e.getMoney());
			m.put("Time", sdf.format(e.getChargetime()));
			r.add(m);
		});
		return r;
	}
	
	public void setCarAccountFee(Integer carid,Integer money) {
		Scarinfo sc=sCarInfoDao.findOne(carid);
		if(sc==null) throw new RuntimeException(ERR_MSG_NOT_FOUND_ENTITY);
		if(money<=0) throw new RuntimeException(ERR_MSG_MONEY_LE_ZERO);
		if(sc.getBalance()<money) throw new RuntimeException(ERR_MSG_MONEY_NOT_SUFF);
		sc.setBalance(sc.getBalance()-money.intValue());
		sCarInfoDao.save(sc);
		Scarfee scc=new Scarfee();
		scc.setFeetime(new Date());
		scc.setMoney(money.intValue());
		scc.setScarinfo(sc);
		sCarFeeDao.save(scc);
	}
	
	public List<Map<String,Object>> getCarAccountFee(Integer carid){
		List<Map<String,Object>> r=new ArrayList<>();
		sCarInfoDao.getOne(carid).getScarfees().stream().forEach(e->{
			Map<String,Object> m=new HashMap<>();
			m.put("CarId", e.getScarinfo().getCarid());
			m.put("Cost", e.getMoney());
			m.put("Time", sdf.format(e.getFeetime()));
			r.add(m);
		});
		return r;
	}
	
	public void setTrafficLightNowStatus(Integer traLightid,String status,Integer time) {
		boolean dataSuccess="Red".equals(status)||"Green".equals(status)||"Yellow".equals(status);
		if(!dataSuccess) throw new RuntimeException(ERR_MSG_PARAM_VALUE);
		Stralight tl=sTransLightDao.findOne(traLightid);
		if(tl==null) throw new RuntimeException(ERR_MSG_NOT_FOUND_ENTITY);
		tl.setStatus(status);
		tl.setTime(time);
		sTransLightDao.save(tl);
	}
	
	public Map<String,Object> getTrafficLightNowStatus(Integer traLightid){
		Map<String,Object> r=new HashMap<>();
		Stralight tl=sTransLightDao.findOne(traLightid);
		if(tl==null) throw new RuntimeException(ERR_MSG_NOT_FOUND_ENTITY);
		r.put("Status", tl.getStatus());
		r.put("Time", tl.getTime());
		return r;
	}
	
	public void setTrafficLightConfig(Integer traLightid,int redtime,int greentime,int yellowtime) {
		boolean dataSuccess=yellowtime>=MIN_YELLOW_TIME&&yellowtime<=MAX_YELLOW_TIME;
		if(!dataSuccess) throw new RuntimeException(ERR_MSG_PARAM_VALUE);
		Stralight tl=sTransLightDao.findOne(traLightid);
		if(tl==null) throw new RuntimeException(ERR_MSG_NOT_FOUND_ENTITY);
		tl.setGreentime(greentime);
		tl.setRedtime(redtime);
		tl.setYellowtime(yellowtime);
		sTransLightDao.save(tl);
	}
	
	public Map<String,Object> getTrafficLightConfigAction(Integer traLightid){
		Map<String,Object> r=new HashMap<>();
		Stralight tl=sTransLightDao.findOne(traLightid);
		r.put("RedTime", tl.getRedtime());
		r.put("GreenTime", tl.getGreentime());
		r.put("YellowTime", tl.getYellowtime());
		return r;
	}
	
	public void setRoadLightStatusAction(Integer roadlightid,String action) {
		boolean dataSuccess="Close".equals(action)||"Open".equals(action);
		if(!dataSuccess) throw new RuntimeException(ERR_MSG_PARAM_VALUE);
		Sroadlight rl=sRoadLightDao.findOne(roadlightid);
		if(rl==null) throw new RuntimeException(ERR_MSG_NOT_FOUND_ENTITY);
		rl.setStatus(action);
		sRoadLightDao.save(rl);
	}
	
	public String getRoadLightStatus(Integer roadlightid) {
		Sroadlight rl=sRoadLightDao.findOne(roadlightid);
		if(rl==null) throw new RuntimeException(ERR_MSG_NOT_FOUND_ENTITY);
		return rl.getStatus();
	}
	
	public void setLightSenseValve(int down,int up) {
		Sothersingle d=sOtherSingleDao.findOne("Down");
		d.setValue(down+"");
		Sothersingle u=sOtherSingleDao.findOne("Up");
		u.setValue(up+"");
		sOtherSingleDao.save(d);
		sOtherSingleDao.save(u);
	}
	
	public void setRoadLightControlMode(String cmValue) {
		boolean dataSuccess="Auto".equals(cmValue)||"Manual".equals(cmValue);
		if(!dataSuccess) throw new RuntimeException(ERR_MSG_PARAM_VALUE);
		Sothersingle cm=sOtherSingleDao.findOne("ControlMode");
		cm.setValue(cmValue);
		sOtherSingleDao.save(cm);
	}
	
	public String getRoadLightControlMode() {
		Sothersingle cm=sOtherSingleDao.findOne("ControlMode");
		return cm.getValue();
	}
	
	public Map<String,Object> getLightSenseValve(){
		Map<String,Object> r=new HashMap<>();
		Sothersingle d=sOtherSingleDao.findOne("Down");
		Sothersingle u=sOtherSingleDao.findOne("Up");
		r.put("Down", Integer.parseInt(d.getValue()));
		r.put("Up", Integer.parseInt(u.getValue()));
		return r;
	}
	
	public List<Map<String,Object>> getEtcTtrafficLog(){
		List<Map<String,Object>> r=new ArrayList<>();
		sEtcTraLogDao.findAll().stream().forEach(e->{
			Map<String,Object> m=new HashMap<>();
			m.put("carid", e.getCarid());
			m.put("intime", e.getIntime());
			m.put("outtime", e.getOuttime());
			m.put("money", e.getMoney());
		});
		return r;
	}
	
	public List<Map<String,Object>> getEtcBlacklist(){
		List<Map<String,Object>> r=new ArrayList<>();
		sEtcTraLogDao.findAll().stream().forEach(e->{
			Map<String,Object> m=new HashMap<>();
			m.put("carid", e.getCarid());
			m.put("datetime", e.getIntime());
		});
		return r;
	}
	
	public void setEtcRate(int rate) {
		Sothersingle fr=sOtherSingleDao.findOne("FeeRate");
		fr.setValue(rate+"");
		sOtherSingleDao.save(fr);
	}
	
	public int getEtcRate() {
		Sothersingle fr=sOtherSingleDao.findOne("FeeRate");
		return Integer.parseInt(fr.getValue());
	}
	
	public int getRoadStatus(int roadid) {
		return randomOfRange(1,5);
	}
	
	public int getBusCapacity(int busid) {
		return randomOfRange(30,100);
	}
	
	public List<Map<String,Object>> getBusStationInfoFromDB(int busstationid) {
		List<Map<String,Object>> r=new ArrayList<>();
		sBusStationDao.findByBusstationid(busstationid).stream().forEach(e->{
			Map<String,Object> m=new HashMap<>();
			m.put("Distance", e.getDistance());
			m.put("BusId", e.getBusid());
			r.add(m);
		});
		return r;
	}
	
	/**
	 * 随机获取距离
	 * @param busstationid
	 * @return
	 */
	public List<Map<String,Object>> getBusStationInfo(int busstationid,int buscount) {
		List<Map<String,Object>> r=new ArrayList<>();
		for(int i=1;i<=buscount;i++){
			Map<String,Object> m=new HashMap<>();
			m.put("Distance", randomOfRange(DISTANCE[0],DISTANCE[1]));
			m.put("BusId", Integer.valueOf(i));
			r.add(m);
		}
		return r;
	}
	
	//WCurrent	WData,temperature	ROWS_DETAIL
	public Map<String,Object> getWeather(){
		Map<String,Object> r=new HashMap<>();
		r.put("WCurrent", getTemperOfToday());
		int days_length=6;
		List<Map<String,?>> rows=new ArrayList<>();
		getTemperRangeOfAfterToday(days_length).stream().forEach(e->{
			Map<String,Object> m=new HashMap<>();
			m.put("WData", e[0]);
			m.put("temperature", e[1]);
			rows.add(m);
		});
		r.put("ROWS_DETAIL", rows);
		return r;
	}
	
	/**
	 * 获取给定范围随机数
	 * @param start
	 * @param end
	 */
	int randomOfRange(int start,int end){
		if (start > end){
			int tmp=end;
			end=start;
			start=tmp;
		}
		return (int)(Math.random()*(end-start+1)+start);
	}
	
	/**
	 * 获取给定月份温度范围
	 * @param month
	 */
	int[] getTempeRangeByMonth(int month) {
		if(month>12 ||month<1)
			throw new RuntimeException("Month is invalid");
		Smonthtemperature e=sMonthTemperDao.getOne(month);
		return new int[] {e.getMint(),e.getMaxt()};
	}
	
	int getTemperOfMonthDay(int month,int day) {
		if(month>12||month<1||day<1||day>31)
			throw new RuntimeException("Month or Day is invalid");
		int[] cur_month_temper=getTempeRangeByMonth(month);
		int start=cur_month_temper[0];
		start+=randomOfRange(1,5);
		int end=cur_month_temper[1];
		float adjust=day/30f;
		if(8<=month)
			adjust=(1-adjust);
		int temperOfToday=(int)(adjust*(end-start))+start;
		return temperOfToday;
	}
	
	int getTemperOfToday() {
		LocalDate now=LocalDate.now();
		int month=now.getMonthValue();
		int day=now.getDayOfMonth();
		return getTemperOfMonthDay(month,day);
	}
	
	int[] getTemperRangeOfToday() {
		int[] r=new int[2];
		int start=getTemperOfToday();
		int end=start+randomOfRange(3,8);
		r[0]=start;
		r[1]=end;
		return r;
	}
	
	List<String[]> getTemperRangeOfAfterToday(int days) {
		List<String[]> r=new ArrayList<>();
		int today_start=getTemperOfToday();
		LocalDate now=LocalDate.now();
		for(int i=0;i<days;i++) {
			String[] min_max=new String[2];
			int start=today_start+randomOfRange(-2,2);
			int end=start+randomOfRange(3,8);
			now=now.plusDays(1l);
			min_max[0]=now+"";
			min_max[1]=start+"~"+end;
			r.add(min_max);
		}
		return r;
	}
	
	//pm2.5,co2,LightIntensity,humidity,temperature
	public Map<String,Object> getAllSense() {
		Map<String,Object> r=new HashMap<>();
		r.put("pm2.5", randomOfRange(PM25[0],PM25[1]));
		r.put("co2", randomOfRange(CO2[0],CO2[1]));
		r.put("temperature", getTemperOfToday());
		r.put("humidity", randomOfRange(HUMIDITY[0],HUMIDITY[1]));
		r.put("LightIntensity", randomOfRange(LIGHTINTENSITY[0],LIGHTINTENSITY[1]));
		return r;
	}
	
	public Object getSenseByName(String key) {
		Object value=getAllSense().get(key);
		if(value==null)
			throw new RuntimeException(ERR_MSG_PARAM_VALUE);
		return value;
	}
	
	@Scheduled(cron="${etc.auto.fee.frequency}")
	public void etcAutoFee(){
		if(!auto_fee) return;
		int etcRate=getEtcRate();
		sCarInfoDao.findAll().forEach(e->{
			try{
				setCarAccountFee(e.getCarid(),etcRate);
				log.info("["+e.getCarid()+"] fee:"+etcRate);
			}catch(Exception ex){
				
			}
		});
	}
	
	
	static final int[] PM25= {0,299};
	static final int[] CO2= {15,9995};
	static final int[] LIGHTINTENSITY= {1,4092};
	static final int[] HUMIDITY= {20,90};
	static final int[] TEMPERATURE= {0,49};
	static final int[] DISTANCE= {0,110850};
	
	static final int MIN_YELLOW_TIME=3;
	static final int MAX_YELLOW_TIME=5;
	
	final Logger log = LoggerFactory.getLogger(getClass());
	final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	@Resource SandCarInfoDao sCarInfoDao;
	@Resource SandCarChangeDao sCarChargeDao;
	@Resource SandCarFeeDao sCarFeeDao;
	@Resource SandTransLightDao sTransLightDao;
	@Resource SandRoadLightDao sRoadLightDao;
	@Resource SandMonthTemperDao sMonthTemperDao;
	@Resource SandOtherSingleDao sOtherSingleDao;
	@Resource SandBusStationDao sBusStationDao;
	@Resource SandEtcTraLogDao sEtcTraLogDao;
	
	@Value("${etc.auto.fee.enable}")
	boolean auto_fee;
	
}
