package com.insigma.mvc.controller.index;


import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.config.annotation.Reference;
import com.insigma.mvc.controller.BaseController;
import com.insigma.mvc.service.index.IndexService;



/**
 * @author wengsh
 *
 */
@Controller
public class IndexController extends BaseController {
	
	@Reference
    private IndexService indexService;
	
	/**
	 * Ö÷Ò³
	 * @param request
	 * @return
	 */
	@RequestMapping("/")
	public ModelAndView gotoIndex(HttpServletRequest request,Model model) throws Exception {
		Subject user = SecurityUtils.getSubject();
		request.setAttribute("user", user);
		ModelAndView modelAndView=new ModelAndView("index/index");
        return modelAndView;
	}
	
	/**
	 * http 404 ´íÎó
	 * @param request
	 * @return
	 */
	@RequestMapping("/404")
	public ModelAndView error404(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("error/404");
        return modelAndView;
	}
	
	/**
	 * http 500 ´íÎó
	 * @param request
	 * @return
	 */
	@RequestMapping("/500")
	public ModelAndView error500(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("error/500");
        return modelAndView;
	}
	
	/**
	 *  Î´ÊÚÈ¨
	 * @param request
	 * @return
	 */
	@RequestMapping("/unrecognized")
	public ModelAndView unrecognized(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("error/unrecognized");
        return modelAndView;
	}
	
	/**
	 *  casµÇÂ¼Ê§°Ü
	 * @param request
	 * @return
	 */
	@RequestMapping("/casfail")
	public ModelAndView casfail(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("error/casfail");
        return modelAndView;
	}
}
