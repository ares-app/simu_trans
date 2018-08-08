package org.ares.app.demo.actions;

import static org.ares.app.demo.common.cfg.Params.ACCESS_DENY_URL;
import static org.ares.app.demo.common.cfg.Params.ROLE_ADMIN_LABEL;
import static org.ares.app.demo.common.cfg.Params.ROLE_AUSER_LABEL;
import static org.ares.app.demo.common.cfg.Params.ROLE_NUSER_LABEL;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.ares.app.demo.daos.ParamDao;
import org.ares.app.demo.daos.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BizSysAction {

	@RequestMapping(ACCESS_DENY_URL)
	public @ResponseBody String deny(){
		return "sorry,invalid user";
	}
	
	@RequestMapping("/userlogin")
	public String login(){
		return "login";
	}
	
	@RequestMapping("/")
	public String index(@RequestParam(value = "curpage", defaultValue = "0") Integer curpage,
	        @RequestParam(value = "pagesize", defaultValue = "10") Integer pagesize,Model m){
		Map<String,String> roles=new HashMap<>();
		roles.put("admin", ROLE_ADMIN_LABEL);
		roles.put("adv_user", ROLE_AUSER_LABEL);
		roles.put("nor_user", ROLE_NUSER_LABEL);
		/*Sort sort = new Sort(Direction.ASC, "username");*/
		Pageable page=new PageRequest(curpage,pagesize);
		m.addAttribute("users", userdao.queryAll(page));
		m.addAttribute("sand_ip", paramdao.findOne("sand_ip").getVal());
		m.addAttribute("roles", roles);
		return "setup";
	}
	
	@RequestMapping(value="/p",produces="text/event-stream")
	public @ResponseBody String p(){
		Random r=new Random();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "data:"+r.nextInt(100)+"\n\n";
	}
	
	final static Logger log = LoggerFactory.getLogger(BizSysAction.class);
	@Resource UserDao userdao;
	@Resource ParamDao paramdao;
}
