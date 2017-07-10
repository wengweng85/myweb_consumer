package com.insigma.mvc.controller.login;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.insigma.mvc.controller.BaseController;


/**
 * µÇ³öcontroller
 * @author Administrator
 *
 */
@Controller
public class LoginController extends BaseController {
	
	
	/**
	 * µÇ³ö
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/clientlogout")
	public String loginout(HttpServletRequest request,Model model) {
		request.getSession().setAttribute("user", null);
		Subject user = SecurityUtils.getSubject();
		user.logout();
		return "redirect:/logout";
	}
}
