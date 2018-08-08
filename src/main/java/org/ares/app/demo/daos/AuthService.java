package org.ares.app.demo.daos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class AuthService {

	public boolean auth(String role,String url){
		boolean r=false;
		if(StringUtils.isEmpty(role))
			return false;
		if(ROLE_ADMIN.equals(role))
			return true;
		if(m_auth.get(role)==null)
			return false;
		r=m_auth.get(role).contains(url);
		return r;
	}
	
	
	public void init(){
		for(String a:nor){
			role_nuser.add(a);
			role_auser.add(a);
		}
		for(String a:adv)
			role_auser.add(a);
		
		m_auth.put(ROLE_AUSER, role_auser);
		m_auth.put(ROLE_NUSER, role_nuser);
	}

	/**
	 * 高级用户adv
	 * 普通用户nor
	 */
	@PostConstruct
	public void init_v2(){
		for(String a:nor_v2){
			role_nuser.add(a);
		}
		for(String a:adv_v2)
			role_auser.add(a);
		
		m_auth.put(ROLE_AUSER, role_auser);
		m_auth.put(ROLE_NUSER, role_nuser);
	}
	
	String[] adv={"GetTrafficLightNowStatus","SetTrafficLightNowStatus","GetTrafficLightConfigAction",
			"SetTrafficLightConfig","GetRoadLightStatus","SetRoadLightStatusAction","SetRoadLightControlMode",
			"SetLightSenseValve","GetAllCarPeccancy","GetCarInfo","GetSUserInfo"};
	
	String[] nor={"GetCarSpeed","SetCarMove","GetCarAccountBalance","SetCarAccountRecharge","GetCarRechargeRecord",
			"GetAllSense","GetSenseByName","GetLightSenseValve","GetBusStationInfo","GetBusCapacity","GetRoadStatus",
			"GetPeccancyType","GetCarPeccancy","GetWeather","user_login"};
	
	String adv_v2[]= {"set_car_move","get_all_sense","get_roadlight_status","set_roadlight_status",
			"get_roadlight_control_mode","set_roadlight_control_mode","get_trafficlight_config",
			"set_trafficlight_config","get_trafficlight_now_status","set_trafficlight_now_status",
			"get_all_car_peccancy","get_car_peccancy","set_car_account_recharge","get_car_account_balance",
			"get_car_move","get_sense_by_name","get_bus_capacity","get_road_status","get_busstation_info",
			"get_car_info","get_peccancy_type","user_login","get_all_user_info","get_weather",
			"set_etc_rate","get_etc_rate","get_etc_traffic_log","get_etc_blacklist","get_etc_ blacklist",
			"get_car_account_record","get_car_account_fee","set_car_account_fee"};
	
	String nor_v2[]= {"set_car_move","set_etc_rate","get_weather","user_login","get_peccancy_type",
			"get_busstation_info","get_road_status","get_bus_capacity","get_sense_by_name","get_all_sense",
			"get_roadlight_control_mode","get_car_peccancy","set_car_account_recharge","get_car_account_balance",
			"get_car_move","get_etc_rate","get_etc_traffic_log","get_etc_blacklist","get_etc_ blacklist",
			"get_car_account_record","get_car_account_fee","set_car_account_fee"};
	
	List<String> role_auser=new ArrayList<>();
	List<String> role_nuser=new ArrayList<>();
	Map<String,List<String>> m_auth=new HashMap<>();
	
	static final String ROLE_ADMIN="admin";
	static final String ROLE_AUSER="adv_user";
	static final String ROLE_NUSER="nor_user";
	
}
