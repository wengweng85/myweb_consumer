package com.insigma.common.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.sf.ehcache.Element;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.insigma.common.util.EhCacheUtil;
import com.insigma.mvc.model.CodeType;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.service.init.InitService;

/**
 * 项目初始化
 * 
 * @author wengsh
 * 
 */
public class ApplicationListener implements   ServletContextListener  {
	Log log=LogFactory.getLog(ApplicationListener.class);


	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		// TODO Auto-generated method stub
		InitService initservice = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext()).getBean(InitService.class);
		List <CodeType> list=initservice.getInitcodetypeList();
		for(int i=0;i<list.size();i++){
			CodeType codetype =list.get(i);
			String code_type=codetype.getCode_type();
			//log.info(code_type);
			List<CodeValue> list_codevalue =initservice.getInitCodeValueList(code_type);
			if (list_codevalue.size() > 0) {
				//将代码参加加载到ehcache缓存中
				EhCacheUtil.getManager().getCache("webcache").put(new Element(code_type,list_codevalue));
				//List<CodeValue> list_cached_value= (List<CodeValue>)EhCacheUtil.getManager().getCache("webcache").get(code_type).getValue();
			    //System.out.println(list_cached_value+" "+list_cached_value.size());
			}
		}
		
	}
}
