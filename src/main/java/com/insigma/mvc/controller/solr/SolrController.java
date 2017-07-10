package com.insigma.mvc.controller.solr;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.controller.BaseController;
import com.insigma.mvc.model.SLog;
import com.insigma.mvc.model.SolrQuery;
import com.insigma.mvc.service.log.LogService;
import com.insigma.mvc.service.solr.SolrService;



/**
 * solr服务
 * @author wengsh
 *
 */
@Controller
public class SolrController extends BaseController {
	
	@Reference
	private SolrService solrservice;
	
	@Reference
	private LogService logservice;
	
	/**
	 * 跳转到搜索测试页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/solr/list")
	public ModelAndView solrlist(HttpServletRequest request,Model model) throws Exception {
		String q=request.getParameter("q")==null?"":request.getParameter("q");
		ModelAndView modelAndView=new ModelAndView("solr/solrsearchlist");
		SLog slog=new SLog("跳转到搜索测试页面");
		logservice.saveLogInfo(slog);
		request.setAttribute("q", q);;
        return modelAndView;
	}
	
	
	/**
	 * solr搜索页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/solr/querylist")
	@ResponseBody
	public AjaxReturnMsg querylist(HttpServletRequest request,Model model,SolrQuery solrquery) throws Exception {
		PageInfo pageinfo=solrservice.search_cms_info(solrquery.getQ(), solrquery.getCurpage(), solrquery.getLimit());
		SLog slog=new SLog("solr列表查询");
		logservice.saveLogInfo(slog);
		return this.success(pageinfo);
	}
}