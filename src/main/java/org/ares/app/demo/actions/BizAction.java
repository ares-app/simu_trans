package org.ares.app.demo.actions;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

import org.ares.app.demo.daos.ParamDao;
import org.ares.app.demo.daos.UserDao;
import org.ares.app.demo.entities.SParam;
import org.ares.app.demo.models.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BizAction {
	
	@RequestMapping("/user/query_all")
	public String user_query(Model m){
		m.addAttribute("users", userdao.findAll());
		return "setup";
	}
	
	@RequestMapping("/user/set_adv")
	public String user_setadv(UserModel m){
		String username=m.getUsername();
		if(!StringUtils.isEmpty(username))
			userdao.setAdvUser(username);
		return "redirect:/";
	}
	
	@RequestMapping("/user/set_nor")
	public String user_setnor(UserModel m){
		String username=m.getUsername();
		if(!StringUtils.isEmpty(username))
			userdao.setNorUser(username);
		return "redirect:/";
	}
	
	@RequestMapping("/user/disable")
	public String user_dis(UserModel m){
		String username=m.getUsername();
		if(!StringUtils.isEmpty(username))
			userdao.disableUser(username);
		return "redirect:/";
	}
	
	@RequestMapping("/param/setip")
	public String prm_setip(@NotNull String sand_ip){
		SParam p=prmdao.findOne("sand_ip");
		p.setVal(sand_ip);
		prmdao.save(p);
		return "redirect:/";
	}
	
	final static Logger log = LoggerFactory.getLogger(BizAction.class);
	@Resource UserDao userdao;
	@Resource ParamDao prmdao;
}
