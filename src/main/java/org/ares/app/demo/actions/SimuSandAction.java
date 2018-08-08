package org.ares.app.demo.actions;

import static org.ares.app.demo.common.cfg.Params.CODE_SUCCESS_OF_SRV;
import static org.ares.app.demo.common.cfg.Params.ERR_MSG_REQUEST_PARAM_LOSE;
import static org.ares.app.demo.common.cfg.Params.KEY_ERRMSG_OF_SRV;
import static org.ares.app.demo.common.cfg.Params.KEY_RESULT_OF_SRV;
import static org.ares.app.demo.common.cfg.Params.MSG_SUCCESS_OF_SRV;
import static org.ares.app.demo.common.cfg.Params.SAND_SIMULATE_URL1;
import static org.ares.app.demo.common.cfg.Params.SAND_SIMULATE_URL2;

import static org.springframework.util.StringUtils.*;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.ares.app.demo.services.SimuSandService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * SetCarMove 有 有 W CarId,CarAction[Start|Stop] GetCarMove 有 有 R CarId Status
 * GetCarSpeed 有 无 R CarId CarSpeed GetCarAccountBalance 有 有 R CarId[1-15]
 * Balance SetCarAccountRecharge 有 有 W CarId,Money GetCarAccountRecord 有 R CarId
 * ROWS_DETAIL[Cost,Card,Time] GetCarPeccancy 无 有 GetAllCarPeccancy 无 有
 * SetTrafficLightNowStatus 有 有 W
 * TrafficLightId,Status[Red/Green/Yellow],Time:int GetTrafficLightNowStatus 有 有
 * R TrafficLightId Status:Red,Time:8 SetTrafficLightConfig 有 有 W
 * TrafficLightId,RedTime,GreenTime,YellowTime[3-5] GetTrafficLightConfigAction
 * 有 有 R TrafficLightId RedTime,GreenTime,YellowTime SetRoadLightControlMode 有 有
 * W ControlMode[Auto|Manual] SetRoadLightStatusAction 有 有 W
 * RoadLightId[1|2|3],Action[Open|Close] GetRoadLightStatus 有 有 R
 * RoadLightId[1|2|3] Status[Open|Close] SetParkBlackList 有 无 SetParkRate 有 无
 * GetParkRate 有 无 GetPakingListReport 有 无 GetParkFree 有 无 SetEtcBlackList 有 无
 * SetEtcRate 有 无 GetEtcRate 有 无 GetEtcListReport 有 无 GetAllSense 有 有 R
 * pm2.5,co2,LightIntensity,humidity,temperature GetSenseByName 有 有 R
 * pm2.5,co2,LightIntensity,humidity,temperature SetLightSenseValve 有 无 W
 * Down,Up GetLightSenseValve 有 无 R Down,Up GetBusStationInfo 有 有 R BusStationId
 * ROWS_DETAIL[Distance:?,BusId:?] GetBusCapacity 有 有 R BusId BusCapacity
 * GetBusSpeed 有 无 GetRoadStatus 有 有 R RoadId Status[1|2|3|4|5] GetCarInfo 无 有
 * GetPeccancyType 无 有 user_login 无 有 GetSUserInfo 无 有 GetWeather 有 有
 * -----------------------------------------------------------------------------------------------------------------------------------------------
 * 传感器名称 阈值 PM2.5 [299, 0] 湿度 [90, 20] 温度 [49, 0] 二氧化碳 [9995, 15] 光照强度 [4092, 1]
 * 公交车与站点距离 [110850, 0]
 * -----------------------------------------------------------------------------------------------------------------------------------------------
 * 气象
 * {"RESULT":"S","ERRMSG":"查询成功","WCurrent":"19,"ROWS_DETAIL":[{"WData":"2017-06-06","temperature":"14~
 * 22"}, {"WData":"2017-06-07","temperature":"15~ 24"},
 * {"WData":"2017-06-08","temperature":"16~ 25"},
 * {"WData":"2017-06-09","temperature":"17~ 25"},
 * {"WData":"2017-06-10","temperature":"16~ 25"},
 * {"WData":"2017-06-11","temperature":"16~ 22"]}
 * -----------------------------------------------------------------------------------------------------------------------------------------------
 * 获取充值记录 {"CarId":1,"CostType":"All"} {"RESULT":"S","ERRMSG":"成功",
 * ROWS_DETAIL:[{"Cost":10," CarId ":1, "time":"2016-12-25
 * 01:28:21"},{"Cost":20," CarId ":1,"time":"2016-12-25 01:28:21"}]}
 * {"RESULT":"F","ERRMSG":"失败"}
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping({ SAND_SIMULATE_URL1, SAND_SIMULATE_URL2 })
public class SimuSandAction {

	/**
	 * CarId,CarAction[Start|Stop]
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping({ "/SetCarMove", "set_car_move" })
	public Map<String, Object> setCarMove(/* @RequestParam */ @RequestBody Map<String, String> param) {
		Map<String, Object> r = new HashMap<>();
		String s_carid = param.get(KEY_CARID);
		String caraction = param.get(KEY_CARACTION);
		if (isEmpty(s_carid) || isEmpty(caraction))
			throw new RuntimeException(ERR_MSG_REQUEST_PARAM_LOSE);
		r.put(KEY_RESULT_OF_SRV, CODE_SUCCESS_OF_SRV);
		r.put(KEY_ERRMSG_OF_SRV, MSG_SUCCESS_OF_SRV);
		Integer carid = Integer.parseInt(s_carid.trim());
		ssService.setCarAction(carid, caraction);
		return r;
	}

	@RequestMapping({ "/GetCarMove", "/get_car_move" })
	public Map<String, Object> getCarMove(@RequestBody Map<String, String> param) {
		Map<String, Object> r = new HashMap<>();
		String s_carid = param.get(KEY_CARID);
		if (isEmpty(s_carid))
			throw new RuntimeException(ERR_MSG_REQUEST_PARAM_LOSE);
		r.put(KEY_RESULT_OF_SRV, CODE_SUCCESS_OF_SRV);
		r.put(KEY_ERRMSG_OF_SRV, MSG_SUCCESS_OF_SRV);
		Integer carid = Integer.parseInt(s_carid.trim());
		r.put(KEY_STATUS, ssService.getCarAction(carid));
		return r;
	}

	@RequestMapping({ "/GetCarAccountBalance", "/get_car_account_balance" })
	public Map<String, Object> getCarAccountBalance(@RequestBody Map<String, String> param) {
		Map<String, Object> r = new HashMap<>();
		String s_carid = param.get(KEY_CARID);
		if (isEmpty(s_carid))
			throw new RuntimeException(ERR_MSG_REQUEST_PARAM_LOSE);
		r.put(KEY_RESULT_OF_SRV, CODE_SUCCESS_OF_SRV);
		r.put(KEY_ERRMSG_OF_SRV, MSG_SUCCESS_OF_SRV);
		Integer carid = Integer.parseInt(s_carid.trim());
		r.put(KEY_BALANCE, ssService.getCarAccountBalance(carid));
		return r;
	}

	@RequestMapping({ "/SetCarAccountRecharge", "/set_car_account_recharge" })
	public Map<String, Object> setCarAccountRecharge(@RequestBody Map<String, String> param) {
		Map<String, Object> r = new HashMap<>();
		String s_carid = param.get(KEY_CARID);
		String s_money = param.get(KEY_MONEY);
		if (isEmpty(s_carid) || isEmpty(s_money))
			throw new RuntimeException(ERR_MSG_REQUEST_PARAM_LOSE);
		r.put(KEY_RESULT_OF_SRV, CODE_SUCCESS_OF_SRV);
		r.put(KEY_ERRMSG_OF_SRV, MSG_SUCCESS_OF_SRV);
		Integer carid = Integer.parseInt(s_carid.trim());
		Integer money = Integer.parseInt(s_money.trim());
		ssService.setCarAccountRecharge(carid, money);
		return r;
	}

	@RequestMapping({ "/GetCarAccountRecord", "/get_car_account_record" })
	public Map<String, Object> getCarAccountRecord(@RequestBody Map<String, String> param) {
		Map<String, Object> r = new HashMap<>();
		String s_carid = param.get(KEY_CARID);
		if (isEmpty(s_carid))
			throw new RuntimeException(ERR_MSG_REQUEST_PARAM_LOSE);
		r.put(KEY_RESULT_OF_SRV, CODE_SUCCESS_OF_SRV);
		r.put(KEY_ERRMSG_OF_SRV, MSG_SUCCESS_OF_SRV);
		Integer carid = Integer.parseInt(s_carid.trim());
		r.put(KEY_ROWS_DETAIL, ssService.getCarAccountRecord(carid));
		return r;
	}
	
	@RequestMapping({ "/SetCarAccountFee", "/set_car_account_fee" })
	public Map<String, Object> setCarAccountFee(@RequestBody Map<String, String> param) {
		Map<String, Object> r = new HashMap<>();
		String s_carid = param.get(KEY_CARID);
		String s_money = param.get(KEY_MONEY);
		if (isEmpty(s_carid) || isEmpty(s_money))
			throw new RuntimeException(ERR_MSG_REQUEST_PARAM_LOSE);
		r.put(KEY_RESULT_OF_SRV, CODE_SUCCESS_OF_SRV);
		r.put(KEY_ERRMSG_OF_SRV, MSG_SUCCESS_OF_SRV);
		Integer carid = Integer.parseInt(s_carid.trim());
		Integer money = Integer.parseInt(s_money.trim());
		ssService.setCarAccountFee(carid, money);
		return r;
	}

	@RequestMapping({ "/GetCarAccountFee", "/get_car_account_fee" })
	public Map<String, Object> getCarAccountFee(@RequestBody Map<String, String> param) {
		Map<String, Object> r = new HashMap<>();
		String s_carid = param.get(KEY_CARID);
		if (isEmpty(s_carid))
			throw new RuntimeException(ERR_MSG_REQUEST_PARAM_LOSE);
		r.put(KEY_RESULT_OF_SRV, CODE_SUCCESS_OF_SRV);
		r.put(KEY_ERRMSG_OF_SRV, MSG_SUCCESS_OF_SRV);
		Integer carid = Integer.parseInt(s_carid.trim());
		r.put(KEY_ROWS_DETAIL, ssService.getCarAccountFee(carid));
		return r;
	}

	@RequestMapping({ "/SetTrafficLightNowStatus", "/set_trafficlight_now_status" })
	public Map<String, Object> setTrafficLightNowStatus(@RequestBody Map<String, String> param) {
		Map<String, Object> r = new HashMap<>();
		String s_trafficlightid = param.get(KEY_TRAFFICLIGHTID);
		String status = param.get(KEY_STATUS);
		String s_time = param.get(KEY_TIME);
		if (isEmpty(s_trafficlightid) || isEmpty(status) || isEmpty(s_time))
			throw new RuntimeException(ERR_MSG_REQUEST_PARAM_LOSE);
		r.put(KEY_RESULT_OF_SRV, CODE_SUCCESS_OF_SRV);
		r.put(KEY_ERRMSG_OF_SRV, MSG_SUCCESS_OF_SRV);
		Integer trafficlightid = Integer.parseInt(s_trafficlightid.trim());
		Integer time = Integer.parseInt(s_time.trim());
		ssService.setTrafficLightNowStatus(trafficlightid, status, time);
		return r;
	}

	@RequestMapping({ "/GetTrafficLightNowStatus", "/get_trafficlight_now_status" })
	public Map<String, Object> getTrafficLightNowStatus(@RequestBody Map<String, String> param) {
		Map<String, Object> r = new HashMap<>();
		String s_trafficlightid = param.get(KEY_TRAFFICLIGHTID);
		if (isEmpty(s_trafficlightid))
			throw new RuntimeException(ERR_MSG_REQUEST_PARAM_LOSE);
		r.put(KEY_RESULT_OF_SRV, CODE_SUCCESS_OF_SRV);
		r.put(KEY_ERRMSG_OF_SRV, MSG_SUCCESS_OF_SRV);
		Integer trafficlightid = Integer.parseInt(s_trafficlightid.trim());
		Map<String, Object> m = ssService.getTrafficLightNowStatus(trafficlightid);
		m.keySet().stream().forEach(e -> {
			r.put(e, m.get(e));
		});
		return r;
	}

	@RequestMapping({ "/SetTrafficLightConfig", "/set_trafficlight_config" })
	public Map<String, Object> setTrafficLightConfig(@RequestBody Map<String, String> param) {
		Map<String, Object> r = new HashMap<>();
		String s_trafficlightid = param.get(KEY_TRAFFICLIGHTID);
		String s_redtime = param.get(KEY_REDTIME);
		String s_greentime = param.get(KEY_GREENTIME);
		String s_yellowtime = param.get(KEY_YELLOWTIME);
		if (isEmpty(s_trafficlightid) || isEmpty(s_redtime) || isEmpty(s_greentime) || isEmpty(s_yellowtime))
			throw new RuntimeException(ERR_MSG_REQUEST_PARAM_LOSE);
		r.put(KEY_RESULT_OF_SRV, CODE_SUCCESS_OF_SRV);
		r.put(KEY_ERRMSG_OF_SRV, MSG_SUCCESS_OF_SRV);
		Integer trafficlightid = Integer.parseInt(s_trafficlightid.trim());
		Integer redtime = Integer.parseInt(s_redtime.trim());
		Integer greentime = Integer.parseInt(s_greentime.trim());
		Integer yellowtime = Integer.parseInt(s_yellowtime.trim());
		ssService.setTrafficLightConfig(trafficlightid, redtime, greentime, yellowtime);
		return r;
	}

	// get_trafficlight_config_action
	@RequestMapping({ "/GetTrafficLightConfigAction", "/get_trafficlight_config" })
	public Map<String, Object> getTrafficLightConfigAction(@RequestBody Map<String, String> param) {
		Map<String, Object> r = new HashMap<>();
		String s_trafficlightid = param.get(KEY_TRAFFICLIGHTID);
		if (isEmpty(s_trafficlightid))
			throw new RuntimeException(ERR_MSG_REQUEST_PARAM_LOSE);
		r.put(KEY_RESULT_OF_SRV, CODE_SUCCESS_OF_SRV);
		r.put(KEY_ERRMSG_OF_SRV, MSG_SUCCESS_OF_SRV);
		Integer trafficlightid = Integer.parseInt(s_trafficlightid.trim());
		Map<String, Object> m = ssService.getTrafficLightConfigAction(trafficlightid);
		m.keySet().stream().forEach(e -> {
			r.put(e, m.get(e));
		});
		return r;
	}

	// SetRoadLightControlMode 有 有 W ControlMode[Auto|Manual]
	@RequestMapping({ "/SetRoadLightControlMode", "/set_roadlight_control_mode" })
	public Map<String, Object> setRoadLightControlMode(@RequestBody Map<String, String> param) {
		Map<String, Object> r = new HashMap<>();
		String s_controlmode = param.get(KEY_CONTROLMODE);
		if (isEmpty(s_controlmode) )
			throw new RuntimeException(ERR_MSG_REQUEST_PARAM_LOSE);
		r.put(KEY_RESULT_OF_SRV, CODE_SUCCESS_OF_SRV);
		r.put(KEY_ERRMSG_OF_SRV, MSG_SUCCESS_OF_SRV);
		ssService.setRoadLightControlMode(s_controlmode);
		return r;
	}
	
	@RequestMapping({ "/GetRoadLightControlMode", "/get_roadlight_control_mode" })
	public Map<String, Object> getRoadLightControlMode(@RequestBody Map<String, String> param) {
		Map<String, Object> r = new HashMap<>();
		r.put(KEY_RESULT_OF_SRV, CODE_SUCCESS_OF_SRV);
		r.put(KEY_ERRMSG_OF_SRV, MSG_SUCCESS_OF_SRV);
		r.put(KEY_CONTROLMODE, ssService.getRoadLightControlMode());
		return r;
	}

	// set_roadlight_status_action
	@RequestMapping({ "/SetRoadLightStatusAction", "/set_roadlight_status" })
	public Map<String, Object> setRoadLightStatusAction(@RequestBody Map<String, String> param) {
		Map<String, Object> r = new HashMap<>();
		String s_roadlightid = param.get(KEY_ROADLIGHTID);
		String action = param.get(KEY_ACTION);
		if (isEmpty(s_roadlightid) || isEmpty(action))
			throw new RuntimeException(ERR_MSG_REQUEST_PARAM_LOSE);
		r.put(KEY_RESULT_OF_SRV, CODE_SUCCESS_OF_SRV);
		r.put(KEY_ERRMSG_OF_SRV, MSG_SUCCESS_OF_SRV);
		Integer roadlightid = Integer.parseInt(s_roadlightid.trim());
		ssService.setRoadLightStatusAction(roadlightid, action);
		return r;
	}

	@RequestMapping({ "/GetRoadLightStatus", "/get_roadlight_status" })
	public Map<String, Object> getRoadLightStatus(@RequestBody Map<String, String> param) {
		Map<String, Object> r = new HashMap<>();
		String s_roadlightid = param.get(KEY_ROADLIGHTID);
		if (isEmpty(s_roadlightid))
			throw new RuntimeException(ERR_MSG_REQUEST_PARAM_LOSE);
		r.put(KEY_RESULT_OF_SRV, CODE_SUCCESS_OF_SRV);
		r.put(KEY_ERRMSG_OF_SRV, MSG_SUCCESS_OF_SRV);
		Integer roadlightid = Integer.parseInt(s_roadlightid.trim());
		String v = ssService.getRoadLightStatus(roadlightid);
		r.put("Status", v);
		return r;
	}

	@RequestMapping({ "/GetAllSense", "/get_all_sense" })
	public Map<String, Object> getAllSense() {
		Map<String, Object> r = new HashMap<>();
		r.put(KEY_RESULT_OF_SRV, CODE_SUCCESS_OF_SRV);
		r.put(KEY_ERRMSG_OF_SRV, MSG_SUCCESS_OF_SRV);
		Map<String, Object> m = ssService.getAllSense();
		m.keySet().stream().forEach(e -> {
			r.put(e, m.get(e));
		});
		return r;
	}

	@RequestMapping({ "/GetSenseByName", "/get_sense_by_name" })
	public Map<String, Object> getSenseByName(@RequestBody Map<String, String> param) {
		Map<String, Object> r = new HashMap<>();
		String s_sensename = param.get(KEY_SENSENAME);
		if (isEmpty(s_sensename))
			throw new RuntimeException(ERR_MSG_REQUEST_PARAM_LOSE);
		r.put(KEY_RESULT_OF_SRV, CODE_SUCCESS_OF_SRV);
		r.put(KEY_ERRMSG_OF_SRV, MSG_SUCCESS_OF_SRV);
		Object v = ssService.getSenseByName(s_sensename);
		r.put(s_sensename, v);
		return r;
	}

	@RequestMapping({ "/SetLightSenseValve", "/set_light_sense_valve" })
	public Map<String, Object> setLightSenseValve(@RequestBody Map<String, String> param) {
		Map<String, Object> r = new HashMap<>();
		String s_down = param.get(KEY_LIGHTSENSE_DOWN);
		String s_up = param.get(KEY_LIGHTSENSE_UP);
		if (isEmpty(s_down) || isEmpty(s_up))
			throw new RuntimeException(ERR_MSG_REQUEST_PARAM_LOSE);
		r.put(KEY_RESULT_OF_SRV, CODE_SUCCESS_OF_SRV);
		r.put(KEY_ERRMSG_OF_SRV, MSG_SUCCESS_OF_SRV);
		Integer down = Integer.parseInt(s_down.trim());
		Integer up = Integer.parseInt(s_up.trim());
		ssService.setLightSenseValve(down, up);
		return r;
	}

	@RequestMapping({ "/GetLightSenseValve", "/get_light_sense_valve" })
	public Map<String, Object> getLightSenseValve(@RequestBody Map<String, String> param) {
		Map<String, Object> r = new HashMap<>();
		r.put(KEY_RESULT_OF_SRV, CODE_SUCCESS_OF_SRV);
		r.put(KEY_ERRMSG_OF_SRV, MSG_SUCCESS_OF_SRV);
		Map<String, Object> m = ssService.getLightSenseValve();
		m.keySet().stream().forEach(e -> {
			r.put(e, m.get(e));
		});
		return r;
	}

	@RequestMapping({ "/GetBusCapacity", "/get_bus_capacity" })
	public Map<String, Object> getBusCapacity(@RequestBody Map<String, String> param) {
		Map<String, Object> r = new HashMap<>();
		String s_busid = param.get(KEY_BUSID);
		if (isEmpty(s_busid))
			throw new RuntimeException(ERR_MSG_REQUEST_PARAM_LOSE);
		r.put(KEY_RESULT_OF_SRV, CODE_SUCCESS_OF_SRV);
		r.put(KEY_ERRMSG_OF_SRV, MSG_SUCCESS_OF_SRV);
		Integer busid = Integer.parseInt(s_busid.trim());
		int count = ssService.getBusCapacity(busid);// busid not be used
		r.put("BusCapacity", count);
		return r;
	}

	@RequestMapping({ "/GetWeather", "/get_weather" })
	public Map<String, Object> getWeather(@RequestBody Map<String, String> param) {
		Map<String, Object> r = new HashMap<>();
		r.put(KEY_RESULT_OF_SRV, CODE_SUCCESS_OF_SRV);
		r.put(KEY_ERRMSG_OF_SRV, MSG_SUCCESS_OF_SRV);
		Map<String, Object> m = ssService.getWeather();
		m.keySet().stream().forEach(e -> {
			r.put(e, m.get(e));
		});
		return r;
	}

	@RequestMapping({ "/GetBusStationInfo", "/get_bus_station_info" })
	public Map<String, Object> getBusStationInfo(@RequestBody Map<String, String> param) {
		Map<String, Object> r = new HashMap<>();
		String s_busstationid = param.get(KEY_BUSSTATIONID);
		if (isEmpty(s_busstationid))
			throw new RuntimeException(ERR_MSG_REQUEST_PARAM_LOSE);
		r.put(KEY_RESULT_OF_SRV, CODE_SUCCESS_OF_SRV);
		r.put(KEY_ERRMSG_OF_SRV, MSG_SUCCESS_OF_SRV);
		Integer busstationid = Integer.parseInt(s_busstationid.trim());
		int buscount=10;
		r.put(KEY_ROWS_DETAIL, ssService.getBusStationInfo(busstationid,buscount));
		return r;
	}

	@RequestMapping({ "/GetRoadStatus", "/get_road_status" })
	public Map<String, Object> getRoadStatus(@RequestBody Map<String, String> param) {
		Map<String, Object> r = new HashMap<>();
		String s_roadid = param.get(KEY_ROADID);
		if (isEmpty(s_roadid))
			throw new RuntimeException(ERR_MSG_REQUEST_PARAM_LOSE);
		r.put(KEY_RESULT_OF_SRV, CODE_SUCCESS_OF_SRV);
		r.put(KEY_ERRMSG_OF_SRV, MSG_SUCCESS_OF_SRV);
		Integer roadid = Integer.parseInt(s_roadid.trim());
		r.put(KEY_STATUS, ssService.getRoadStatus(roadid));//roadid not be used
		return r;
	}
	
	@RequestMapping({ "/SetEtcRate", "/set_etc_rate" })
	public Map<String, Object> setEtcRate(@RequestBody Map<String, String> param) {
		Map<String, Object> r = new HashMap<>();
		String s_rate = param.get(KEY_MONEY);
		if (isEmpty(s_rate))
			throw new RuntimeException(ERR_MSG_REQUEST_PARAM_LOSE);
		r.put(KEY_RESULT_OF_SRV, CODE_SUCCESS_OF_SRV);
		r.put(KEY_ERRMSG_OF_SRV, MSG_SUCCESS_OF_SRV);
		Integer rate = Integer.parseInt(s_rate.trim());
		ssService.setEtcRate(rate);
		return r;
	}

	@RequestMapping({ "/GetEtcRate", "/get_etc_rate" })
	public Map<String, Object> getEtcRate(@RequestBody Map<String, String> param) {
		Map<String, Object> r = new HashMap<>();
		r.put(KEY_RESULT_OF_SRV, CODE_SUCCESS_OF_SRV);
		r.put(KEY_ERRMSG_OF_SRV, MSG_SUCCESS_OF_SRV);
		int money = ssService.getEtcRate();
		r.put(KEY_MONEY, money);
		return r;
	}
	
	@RequestMapping({ "/GetEtcTtrafficLog", "/get_etc_traffic_log" })
	public Map<String, Object> getEtcTtrafficLog(@RequestBody Map<String, String> param) {
		Map<String, Object> r = new HashMap<>();
		r.put(KEY_RESULT_OF_SRV, CODE_SUCCESS_OF_SRV);
		r.put(KEY_ERRMSG_OF_SRV, MSG_SUCCESS_OF_SRV);
		r.put(KEY_ROWS_DETAIL, ssService.getEtcTtrafficLog());
		return r;
	}
	
	
	@RequestMapping({ "/SetEtcBlacklist", "/set_etc_blacklist" })
	public Map<String, Object> setEtcBlacklist(@RequestBody Map<String, String> param) {
		Map<String, Object> r = new HashMap<>();
		String s_rate = param.get(KEY_MONEY);
		if (isEmpty(s_rate))
			throw new RuntimeException(ERR_MSG_REQUEST_PARAM_LOSE);
		r.put(KEY_RESULT_OF_SRV, CODE_SUCCESS_OF_SRV);
		r.put(KEY_ERRMSG_OF_SRV, MSG_SUCCESS_OF_SRV);
		Integer rate = Integer.parseInt(s_rate.trim());
		ssService.setEtcRate(rate);
		return r;
	}

	@RequestMapping({ "/GetEtcBlacklist", "/get_etc_blacklist" })
	public Map<String, Object> getEtcBlacklist(@RequestBody Map<String, String> param) {
		Map<String, Object> r = new HashMap<>();
		r.put(KEY_RESULT_OF_SRV, CODE_SUCCESS_OF_SRV);
		r.put(KEY_ERRMSG_OF_SRV, MSG_SUCCESS_OF_SRV);
		r.put(KEY_ROWS_DETAIL, ssService.getEtcBlacklist());
		return r;
	}
	
	final static String KEY_CARID = "CarId";
	final static String KEY_CARACTION = "CarAction";
	final static String KEY_STATUS = "Status";
	final static String KEY_MONEY = "Money";
	final static String KEY_BALANCE = "Balance";
	final static String KEY_ROWS_DETAIL = "ROWS_DETAIL";

	final static String KEY_TRAFFICLIGHTID = "TrafficLightId";
	final static String KEY_TIME = "Time";
	final static String KEY_REDTIME = "RedTime";
	final static String KEY_GREENTIME = "GreenTime";
	final static String KEY_YELLOWTIME = "YellowTime";

	final static String KEY_ROADLIGHTID = "RoadLightId";
	final static String KEY_ACTION = "Action";
	final static String KEY_CONTROLMODE = "ControlMode";

	final static String KEY_SENSENAME = "SenseName";

	final static String KEY_LIGHTSENSE_DOWN = "Down";
	final static String KEY_LIGHTSENSE_UP = "Up";

	final static String KEY_BUSID = "BusId";
	final static String KEY_BUSSTATIONID = "BusStationId";
	
	final static String KEY_ROADID = "RoadId";

	@Resource
	SimuSandService ssService;
}
