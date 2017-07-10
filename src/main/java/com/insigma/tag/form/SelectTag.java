package com.insigma.tag.form;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import net.sf.ehcache.Element;

import com.insigma.common.util.EhCacheUtil;
import com.insigma.mvc.model.CodeValue;


/**
 * �Զ����ǩ֮��ѡ����ǩ
 * @author wengsh
 *
 */
public class SelectTag implements Tag  {
	
	private PageContext pageContext;  
	
	//������������
	private String codetype;
	
	//id
	private String id;
	
	//name
	private String name;
	
    //ֵ
	private String value;

	

	public void setCodetype(String codetype) {
		this.codetype = codetype;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public PageContext getPageContext() {
		return pageContext;
	}

	public String getCodetype() {
		return codetype;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
	   JspWriter out = pageContext.getOut();
	   StringBuffer sb=new StringBuffer();
	   sb.append("<select class=\"form-control\" id=\""+id+"\" name=\""+name+"\"  value=\""+value+"\"  >");
	  Element element=EhCacheUtil.getManager().getCache("webcache").get(codetype);
	  if(element!=null){
		  List<CodeValue> list=(List<CodeValue>)element.getValue();
		   for(CodeValue codevalue:list){
			   sb.append("<option ");
			   if(value!=null&&!value.equals("")){
				   if(value.equals(codevalue.getCode_value())){
					   sb.append(" selected ");
				   }
			   }
			   sb.append("  value=\""+codevalue.getCode_value()+"\">"+codevalue.getCode_name()+"</option>");
		   }
	  }
	  sb.append("</select>");
	  try {  
		   out.write(sb.toString());
       } catch (IOException e) {  
           throw new RuntimeException(e);  
       }     
		return 0;
	}

	@Override
	public Tag getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPageContext(PageContext arg0) {
		// TODO Auto-generated method stub
		this.pageContext = arg0;  
	}

	@Override
	public void setParent(Tag arg0) {
		// TODO Auto-generated method stub
	}

}
