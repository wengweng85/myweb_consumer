package com.insigma.mvc.controller.index;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.controller.BaseController;
import com.insigma.mvc.dwr.DwrDragView;
import com.insigma.mvc.model.PageDesign;
import com.insigma.mvc.model.SFileRecord;
import com.insigma.mvc.model.SLog;
import com.insigma.mvc.service.drag.DragService;
import com.insigma.mvc.service.jms.JmsProducerService;
import com.insigma.mvc.service.log.LogService;
import com.insigma.mvc.service.upload.UploadService;
import com.insigma.resolver.AppException;



/**
 * wegdit页面设计
 * @author wengsh
 *
 */
@Controller
public class DragController extends BaseController {
	
	@Reference
	private DragService dragservice;
	
	@Reference
	private LogService logservice;
	
	@Autowired
	private UploadService uploadservice;
	
	@Resource 
    private JmsProducerService jmsProducerService;
	
	/**
	 * 跳转至列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/drag/list")
	public ModelAndView draglist(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("drag/draglist");
		SLog slog=new SLog("跳转至列表");
		logservice.saveLogInfo(slog);
		//jmsProducerService.sendMessage("跳转至列表");
        return modelAndView;
	}
	
	/**
	 * 跳转至列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/drag/querylist")
	@ResponseBody
	public AjaxReturnMsg querylist(HttpServletRequest request,Model model,PageDesign pagedesign) throws Exception {
		PageInfo<PageDesign> pageinfo =dragservice.queryDesignPageList(pagedesign);
		SLog slog=new SLog("列表查询");
		logservice.saveLogInfo(slog);
		return this.success(pageinfo);
	}
	
	
	/**
	 * 编辑
	 * @param request
	 * @return
	 */
	@RequestMapping("/drag/gotoedit/{id}")
	public ModelAndView dragedit(HttpServletRequest request,Model model,@PathVariable String id) throws Exception {
		ModelAndView modelAndView=new ModelAndView("drag/dragedit");
		SLog slog=new SLog("页面编辑");
		logservice.saveLogInfo(slog);
		PageDesign design=dragservice.queryDesignPageById(id);
		if(design!=null){
			model.addAttribute("design", design);
	        return modelAndView;
		}else{
			throw new Exception("找不对对应的页面信息，请确认是否存在或已经被删除!");
		}
	}
	
	/**
	 * delete
	 * @param request
	 * @return
	 */
	@RequestMapping("/drag/delete/{id}")
	@ResponseBody
	public AjaxReturnMsg dragdelete(HttpServletRequest request,Model model,@PathVariable String id) throws Exception {
		SLog slog=new SLog("删除页面");
		logservice.saveLogInfo(slog);
		dragservice.deletePageDesignById(id);
		return this.success("删除成功");
	}
	
	
	/**
	 * 预览
	 * @param request
	 * @return
	 */
	@RequestMapping("/drag/view/{id}")
	public ModelAndView dragviewbyid(HttpServletRequest request,Model model,@PathVariable String id) throws Exception {
		SLog slog=new SLog("预览页面");
		logservice.saveLogInfo(slog);
		ModelAndView modelAndView=new ModelAndView("drag/dragview");
		PageDesign design=dragservice.queryDesignPageById(id);
		if(design!=null){
			model.addAttribute("design", design);
	        return modelAndView;
		}else{
			throw new Exception("找不对对应的页面信息，请确认是否存在或已经被删除!");
		}
	}
	

	/**
	 * 预览
	 * @param request
	 * @return
	 */
	@RequestMapping("/drag/view")
	public ModelAndView dragviewlatest(HttpServletRequest request,Model model) throws Exception {
		SLog slog=new SLog("预览页面最新页面");
		logservice.saveLogInfo(slog);
		ModelAndView modelAndView=new ModelAndView("drag/dragview");
		//设置分页面信息
		PageDesign pagedesign=new PageDesign();
		pagedesign.setCurpage(1);
		pagedesign.setLimit(1);
		List<PageDesign> list=dragservice.getLatestDesignPage(pagedesign);
		if(list.size()>0){
			model.addAttribute("design", list.get(0));
	        return modelAndView;
		}else{
			throw new Exception("找不到相关页面配置,请确认");
		}
	}
	
	
	/**
	 * 保存
	 * @param request
	 * @return
	 */
	@RequestMapping("/drag/savedata")
	@ResponseBody
	public AjaxReturnMsg savedata(HttpServletRequest request,Model model) throws Exception {
		SLog slog=new SLog("保存页面数据");
		logservice.saveLogInfo(slog);
		String id=request.getParameter("id");
		String serialized_data=request.getParameter("serialized_data");
		PageDesign design=new PageDesign();
		design.setId(id);
		design.setSerialized_data(serialized_data);
		dragservice.updateserializedData(design);
		try{
			DwrDragView.sendMsg("重新加载");
		}catch(Exception e){
		}
	
		return this.success("更新成功");
	}
	
	/**
	 * 跳转至列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/drag/toadd")
	public ModelAndView toadd(HttpServletRequest request,Model model) throws Exception {
		SLog slog=new SLog("跳转至新增页面");
		logservice.saveLogInfo(slog);
		ModelAndView modelAndView=new ModelAndView("drag/adddrag");
		return modelAndView;
	}
	
	/**
	 * 跳转至列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/drag/toEdit2/{id}")
	public ModelAndView toEdit(HttpServletRequest request,Model model,@PathVariable String id) throws Exception {
		SLog slog=new SLog("跳转至编辑页面");
		logservice.saveLogInfo(slog);
		ModelAndView modelAndView=new ModelAndView("drag/adddrag");
		PageDesign design=dragservice.queryDesignPageById(id);
		model.addAttribute("design", design);
		return modelAndView;
	}
	
	
	/**
	 * 跳转至列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/drag/saveorupdate")
	@ResponseBody
	public AjaxReturnMsg saveorupdate(HttpServletRequest request,Model model,PageDesign design) throws Exception {
		SLog slog=new SLog("保存页面");
		logservice.saveLogInfo(slog);
		design.setIsvalid("1");
		if(null==design.getSerialized_data()||  design.getSerialized_data().equals("")){
			design.setSerialized_data("[]");
		}
		try{
			JSONArray.fromObject(design.getSerialized_data());
		}catch(Exception ex){
			ex.printStackTrace();
			  return this.error("页面数据格式错误,请参考格式说明编写");
	    }
		//新增
		if(design.getId().equals("")){
			  String id=dragservice.savePageDesign(design);
			  System.out.println("id"+id);
			  return this.success("新增成功");
		}
		//修改
		else{
			 dragservice.updatePageDesign(design);
			 return this.success("更新成功");
		}
	}
	
	
	/**
	 * 跳转至上传页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/drag/toupload")
	public ModelAndView toupload(HttpServletRequest request,Model model) throws Exception {
		SLog slog=new SLog("跳转至编辑页面");
		logservice.saveLogInfo(slog);
		ModelAndView modelAndView=new ModelAndView("drag/fileupload");
		return modelAndView;
	}
	
	
	/**
     * 文件上传
     *
     * @param request
     * @param response
     * @return
     * @throws AppException
     */
    @RequestMapping("/drag/upload")
    public void upload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//String business_id=request.getParameter("business_id");
    	String business_id="1";
        //检查业务编号参数
        if(null==business_id||business_id.equals("")){
        	this.error(response, "业务编号参数为空,请检查");
        	return ;
        }
        
		long MAX_SIZE = 20* 1024 * 1024L;//100m
		
    	try {
            //创建一个通用的多部分解析器
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            //判断 request 是否有文件上传,即多部分请求
            if (multipartResolver.isMultipart(request)) {
                //转换成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                //取得request中的所有文件名
                Iterator<String> iter = multiRequest.getFileNames();
                while (iter.hasNext()) {
                    //取得上传文件
                    MultipartFile multipartFile = multiRequest.getFile(iter.next());
                    if (multipartFile.getSize() > MAX_SIZE) {
                        this.error(response, "文件尺寸超过规定大小:" + MAX_SIZE / 1024 / 1024 + "M");
                        break;
                    } else {
                       
                        // 得到去除路径的文件名
                        String originalFilename = multipartFile.getOriginalFilename();
                        int indexofdoute = originalFilename.lastIndexOf(".");
                        
                        /**获取文件的后缀**/
                        String endfix = "";
                        if (indexofdoute != -1) {
                            // 尾
                            endfix = originalFilename.substring(indexofdoute).toLowerCase();
                            if(endfix.equals(".jpg")||endfix.equals(".jpeg")||endfix.equals(".gif")||endfix.equals(".png") ||endfix.equals(".pdf")||endfix.equals(".doc")||endfix.equals(".docx")||endfix.equals(".xls")||endfix.equals(".xlsx")||endfix.equals(".rar")||endfix.equals(".zip")) {
	     						//上传并记录日志
                            	String id=uploadservice.upload(originalFilename,multipartFile.getInputStream() );
                            	System.out.println(id);
                                this.success(response, id);
                            }else{
                                this.error(response, "文件格式不正确,请确认,只允许上传格式为jpg、jpeg、gif、png、pdf、doc、docx、xls、xlsx、rar、zip格式的文件!!");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
        	e.printStackTrace();
			// 处理文件尺寸过大异常
			if (e instanceof SizeLimitExceededException) {
				this.error(response, "文件尺寸超过规定大小:" + MAX_SIZE / 1024 / 1024 + "M");
			}
			this.error(response, e.getMessage());
        }

    }

    /**
     * 文件下载
     * @param request
     * @param response
     * @throws AppException
     */
    @RequestMapping(value = "/drag/download/{file_uuid}")
    public void download(@PathVariable(value="file_uuid") String file_uuid, HttpServletRequest request ,HttpServletResponse response) throws  AppException{
        try{
        	SFileRecord filerecord=uploadservice.getFileInfo(file_uuid);
        	if(filerecord!=null){
        		 byte[] temp=uploadservice.download(filerecord.getFile_path());
                 if(temp!=null){
                 	//此行代码是防止中文乱码的关键！！
                     response.setHeader("Content-disposition","attachment; filename="+ new String(filerecord.getFile_name().getBytes("GBK"),"iso-8859-1"));
                     BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(temp));
                     BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
                     //新建一个2048字节的缓冲区
                     byte[] buff = new byte[2048];
                     int bytesRead=0;
                     while ((bytesRead = bis.read(buff, 0, buff.length)) != -1) {
                         bos.write(buff,0,bytesRead);
                     }
                     bos.flush();
                     if (bis != null)
                         bis.close();
                     if (bos != null)
                         bos.close();
                 }else{
                 	throw new Exception("下载错误,不存在的id");
                 }
        	}
        }catch(Exception e){
            //log.error(e.getMessage());
        }
    }
}
