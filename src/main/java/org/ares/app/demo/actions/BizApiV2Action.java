package org.ares.app.demo.actions;

import static org.ares.app.demo.common.cfg.Params.CODE_FAILED_OF_SRV;
import static org.ares.app.demo.common.cfg.Params.CODE_SUCCESS_OF_SRV;
import static org.ares.app.demo.common.cfg.Params.ERR_MSG_ACCESS_SANDTABLE_FAILED;
import static org.ares.app.demo.common.cfg.Params.ERR_MSG_SANDTABLE_NULL;
import static org.ares.app.demo.common.cfg.Params.ERR_MSG_USERNAME_INVALID;
import static org.ares.app.demo.common.cfg.Params.ERR_MSG_USER_NOT_APPROVE;
import static org.ares.app.demo.common.cfg.Params.ERR_MSG_USER_NOT_AUTH;
import static org.ares.app.demo.common.cfg.Params.KEY_ERRMSG_OF_SRV;
import static org.ares.app.demo.common.cfg.Params.KEY_OF_AUTH_USER;
import static org.ares.app.demo.common.cfg.Params.KEY_RESULT_OF_SAND;
import static org.ares.app.demo.common.cfg.Params.KEY_RESULT_OF_SRV;
import static org.ares.app.demo.common.cfg.Params.KEY_ROWS_OF_SRV;
import static org.ares.app.demo.common.cfg.Params.MSG_FAILED_OF_SAND;
import static org.ares.app.demo.common.cfg.Params.MSG_FAILED_OF_SRV;
import static org.ares.app.demo.common.cfg.Params.MSG_SUCCESS_OF_SAND;
import static org.ares.app.demo.common.cfg.Params.MSG_SUCCESS_OF_SRV;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.ares.app.demo.common.danger.DemoDanger;
import org.ares.app.demo.common.exception.SignException;
import org.ares.app.demo.daos.AuthService;
import org.ares.app.demo.daos.CarDao;
import org.ares.app.demo.daos.CaruserDao;
import org.ares.app.demo.daos.LoginDao;
import org.ares.app.demo.daos.ParamDao;
import org.ares.app.demo.daos.PeccancyDao;
import org.ares.app.demo.daos.PectypeDao;
import org.ares.app.demo.daos.UserDao;
import org.ares.app.demo.entities.Loginuser;
import org.ares.app.demo.entities.SUser;
import org.ares.app.demo.models.CarBlackListModel;
import org.ares.app.demo.models.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@SuppressWarnings({"unchecked","rawtypes"})
@RequestMapping("/api/v2")
public class BizApiV2Action {
	
	@RequestMapping({"/user/register"})
	public Map<String, Object> register(@RequestBody UserModel model) {
		Map<String, Object> r = new HashMap<>();
		SUser u = new SUser();
		BeanUtils.copyProperties(model, u);
		userdao.save(u);
		r.put("msg", "success");
		return r;
	}

	@RequestMapping({"/set_etc_blacklist"})
	public Map<String, Object> jsonAry(@RequestBody CarBlackListModel param){
		String username=param.getUsername();
		SUser u=userdao.findByUsername(username);
		if(u==null)
			throw new RuntimeException(ERR_MSG_USERNAME_INVALID);
		if(StringUtils.isEmpty(u.getRole()))
			throw new RuntimeException(ERR_MSG_USER_NOT_APPROVE);
		if(!auth.auth(u.getRole(), "set_etc_blacklist"))
			throw new RuntimeException(ERR_MSG_USER_NOT_AUTH);
		String simu_sand_url="http://" + ip + ":" + port+contextPath +prefix+"set_etc_blacklist";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(param, headers);
		log.info("[to sand table params]--"+entity);
		Map<String, Object> r=null;
		try{
			r=getSandTableData(simu_sand_url,entity);
		}catch(Exception e){
			throw new RuntimeException(ERR_MSG_ACCESS_SANDTABLE_FAILED);
		}
		return r;
	}
	
	@RequestMapping({"/{app_req_url}"})
	public Map<String, Object> transport(@PathVariable String app_req_url,@RequestBody Map<String,String> param){
		String k_username=KEY_OF_AUTH_USER;
		if(!param.containsKey(k_username)||StringUtils.isEmpty(param.get(k_username)))
			throw new SignException();
		String username=param.get(KEY_OF_AUTH_USER)+"";
		SUser u=userdao.findByUsername(username);
		if(u==null)
			throw new RuntimeException(ERR_MSG_USERNAME_INVALID);
		if(StringUtils.isEmpty(u.getRole()))
			throw new RuntimeException(ERR_MSG_USER_NOT_APPROVE);
		if(!auth.auth(u.getRole(), app_req_url))
			throw new RuntimeException(ERR_MSG_USER_NOT_AUTH);
		
		Map<String, Object> c=new HashMap<>();
		boolean query=false;
		switch(app_req_url){
		case"get_all_user_info":
			c.put(KEY_ROWS_OF_SRV,caruserdao.findAll());
			query=true;
			break;
		case"get_car_peccancy":
			String carnumber=""+param.get("carnumber");
			if(StringUtils.isEmpty(param.get("carnumber")))
				throw new RuntimeException("Param carnumber is must ");
			c.put(KEY_ROWS_OF_SRV, peccancydao.findByCarnumber(carnumber));
			query=true;
			break;
		case"get_all_car_peccancy":
			c.put(KEY_ROWS_OF_SRV, peccancydao.findAll());
			query=true;
			break;
		case"get_car_info":
			c.put(KEY_ROWS_OF_SRV, cardao.findAll());
			query=true;
			break;
		case"get_peccancy_type":
			c.put(KEY_ROWS_OF_SRV, pecdao.findAll());
			query=true;
			break;
		case"user_login":
			String upwd=param.get("UserPwd")+"";
			if(StringUtils.isEmpty(param.get("UserPwd")))
				throw new RuntimeException("Param UserPwd is must ");
			Loginuser usr=logindao.findByUsernameAndUserpwd(username, upwd);
			if(usr==null)
				throw new RuntimeException("UserPwd is invalid");
			c.put("UserRole",usr.getUserrole());
			query=true;
			break;
		}
		if(query){
			if(dg.danger())
				c.remove(KEY_ROWS_OF_SRV);
			c.put(KEY_RESULT_OF_SRV, CODE_SUCCESS_OF_SRV);
			c.put(KEY_ERRMSG_OF_SRV, MSG_SUCCESS_OF_SRV);
			return c;
		}
		
		/*String ip = paramdao.findOne("sand_ip").getVal();
		String port = paramdao.findOne("sand_port").getVal();
		String real_sand_url=getSandUrl(app_req_url);
		String url = "http://" + ip + ":" + port + prefix + real_sand_url;*/
		String simu_sand_url="http://" + ip + ":" + port+contextPath +prefix+app_req_url;
		//param.remove(KEY_OF_AUTH_USER);
		log.info("[to sand table url]--"+simu_sand_url);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(param, headers);
		log.info("[to sand table params]--"+entity);
		Map<String, Object> r=null;
		try{
			r=getSandTableData(simu_sand_url,entity);
		}catch(Exception e){
			throw new RuntimeException(ERR_MSG_ACCESS_SANDTABLE_FAILED);
		}
		//buildResultOfSand(r);
		return r;
	}
	
	String getSandUrl(String req_url) {
		return req_url;
	}
	
	Map<String,Object> getSandTableData(String full_url,HttpEntity param) throws Exception{
		Map<String,Object> r=new HashMap<>();
		r = restTemplate.postForObject(full_url, param, Map.class);
		log.info("[from sand table data]--"+r);
		return r;
	}
	
	void buildResultOfSand(Map<String,Object> m){
		if(m==null)
			throw new RuntimeException(ERR_MSG_SANDTABLE_NULL);
		String r_sand=null;
		if(m.containsKey(KEY_RESULT_OF_SAND)){
			r_sand=m.get(KEY_RESULT_OF_SAND)+"";
			if(MSG_SUCCESS_OF_SAND.equals(r_sand)){
				m.put(KEY_RESULT_OF_SRV, CODE_SUCCESS_OF_SRV);
				m.put(KEY_ERRMSG_OF_SRV, MSG_SUCCESS_OF_SRV);
			}
			if(MSG_FAILED_OF_SAND.equals(r_sand)){
				m.put(KEY_RESULT_OF_SRV, CODE_FAILED_OF_SRV);
				m.put(KEY_ERRMSG_OF_SRV, MSG_FAILED_OF_SRV);
			}
			m.remove(KEY_RESULT_OF_SAND);
			return;
		}
		m.put(KEY_RESULT_OF_SRV, CODE_SUCCESS_OF_SRV);
		m.put(KEY_ERRMSG_OF_SRV, MSG_SUCCESS_OF_SRV);
	}

	@Value("${server.port}")
	int port;
	@Value("${server.context-path}")
	String contextPath;
	final String ip="localhost";
	final String prefix = "/simusand/api/v2/";
	final ObjectMapper mapper = new ObjectMapper();
	final static Logger log = LoggerFactory.getLogger(BizApiV2Action.class);
	
	@Resource AuthService auth;
	@Resource UserDao userdao;
	@Resource ParamDao paramdao;
	@Resource PeccancyDao peccancydao;
	@Resource CarDao cardao;
	@Resource CaruserDao caruserdao;
	@Resource LoginDao logindao;
	@Resource PectypeDao pecdao;
	@Resource RestTemplate restTemplate;
	@Resource DemoDanger dg;
}
