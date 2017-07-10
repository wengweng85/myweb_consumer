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
 * wegditҳ�����
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
	 * ��ת���б�ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/drag/list")
	public ModelAndView draglist(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("drag/draglist");
		SLog slog=new SLog("��ת���б�");
		logservice.saveLogInfo(slog);
		//jmsProducerService.sendMessage("��ת���б�");
        return modelAndView;
	}
	
	/**
	 * ��ת���б�ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/drag/querylist")
	@ResponseBody
	public AjaxReturnMsg querylist(HttpServletRequest request,Model model,PageDesign pagedesign) throws Exception {
		PageInfo<PageDesign> pageinfo =dragservice.queryDesignPageList(pagedesign);
		SLog slog=new SLog("�б��ѯ");
		logservice.saveLogInfo(slog);
		return this.success(pageinfo);
	}
	
	
	/**
	 * �༭
	 * @param request
	 * @return
	 */
	@RequestMapping("/drag/gotoedit/{id}")
	public ModelAndView dragedit(HttpServletRequest request,Model model,@PathVariable String id) throws Exception {
		ModelAndView modelAndView=new ModelAndView("drag/dragedit");
		SLog slog=new SLog("ҳ��༭");
		logservice.saveLogInfo(slog);
		PageDesign design=dragservice.queryDesignPageById(id);
		if(design!=null){
			model.addAttribute("design", design);
	        return modelAndView;
		}else{
			throw new Exception("�Ҳ��Զ�Ӧ��ҳ����Ϣ����ȷ���Ƿ���ڻ��Ѿ���ɾ��!");
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
		SLog slog=new SLog("ɾ��ҳ��");
		logservice.saveLogInfo(slog);
		dragservice.deletePageDesignById(id);
		return this.success("ɾ���ɹ�");
	}
	
	
	/**
	 * Ԥ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/drag/view/{id}")
	public ModelAndView dragviewbyid(HttpServletRequest request,Model model,@PathVariable String id) throws Exception {
		SLog slog=new SLog("Ԥ��ҳ��");
		logservice.saveLogInfo(slog);
		ModelAndView modelAndView=new ModelAndView("drag/dragview");
		PageDesign design=dragservice.queryDesignPageById(id);
		if(design!=null){
			model.addAttribute("design", design);
	        return modelAndView;
		}else{
			throw new Exception("�Ҳ��Զ�Ӧ��ҳ����Ϣ����ȷ���Ƿ���ڻ��Ѿ���ɾ��!");
		}
	}
	

	/**
	 * Ԥ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/drag/view")
	public ModelAndView dragviewlatest(HttpServletRequest request,Model model) throws Exception {
		SLog slog=new SLog("Ԥ��ҳ������ҳ��");
		logservice.saveLogInfo(slog);
		ModelAndView modelAndView=new ModelAndView("drag/dragview");
		//���÷�ҳ����Ϣ
		PageDesign pagedesign=new PageDesign();
		pagedesign.setCurpage(1);
		pagedesign.setLimit(1);
		List<PageDesign> list=dragservice.getLatestDesignPage(pagedesign);
		if(list.size()>0){
			model.addAttribute("design", list.get(0));
	        return modelAndView;
		}else{
			throw new Exception("�Ҳ������ҳ������,��ȷ��");
		}
	}
	
	
	/**
	 * ����
	 * @param request
	 * @return
	 */
	@RequestMapping("/drag/savedata")
	@ResponseBody
	public AjaxReturnMsg savedata(HttpServletRequest request,Model model) throws Exception {
		SLog slog=new SLog("����ҳ������");
		logservice.saveLogInfo(slog);
		String id=request.getParameter("id");
		String serialized_data=request.getParameter("serialized_data");
		PageDesign design=new PageDesign();
		design.setId(id);
		design.setSerialized_data(serialized_data);
		dragservice.updateserializedData(design);
		try{
			DwrDragView.sendMsg("���¼���");
		}catch(Exception e){
		}
	
		return this.success("���³ɹ�");
	}
	
	/**
	 * ��ת���б�ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/drag/toadd")
	public ModelAndView toadd(HttpServletRequest request,Model model) throws Exception {
		SLog slog=new SLog("��ת������ҳ��");
		logservice.saveLogInfo(slog);
		ModelAndView modelAndView=new ModelAndView("drag/adddrag");
		return modelAndView;
	}
	
	/**
	 * ��ת���б�ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/drag/toEdit2/{id}")
	public ModelAndView toEdit(HttpServletRequest request,Model model,@PathVariable String id) throws Exception {
		SLog slog=new SLog("��ת���༭ҳ��");
		logservice.saveLogInfo(slog);
		ModelAndView modelAndView=new ModelAndView("drag/adddrag");
		PageDesign design=dragservice.queryDesignPageById(id);
		model.addAttribute("design", design);
		return modelAndView;
	}
	
	
	/**
	 * ��ת���б�ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/drag/saveorupdate")
	@ResponseBody
	public AjaxReturnMsg saveorupdate(HttpServletRequest request,Model model,PageDesign design) throws Exception {
		SLog slog=new SLog("����ҳ��");
		logservice.saveLogInfo(slog);
		design.setIsvalid("1");
		if(null==design.getSerialized_data()||  design.getSerialized_data().equals("")){
			design.setSerialized_data("[]");
		}
		try{
			JSONArray.fromObject(design.getSerialized_data());
		}catch(Exception ex){
			ex.printStackTrace();
			  return this.error("ҳ�����ݸ�ʽ����,��ο���ʽ˵����д");
	    }
		//����
		if(design.getId().equals("")){
			  String id=dragservice.savePageDesign(design);
			  System.out.println("id"+id);
			  return this.success("�����ɹ�");
		}
		//�޸�
		else{
			 dragservice.updatePageDesign(design);
			 return this.success("���³ɹ�");
		}
	}
	
	
	/**
	 * ��ת���ϴ�ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/drag/toupload")
	public ModelAndView toupload(HttpServletRequest request,Model model) throws Exception {
		SLog slog=new SLog("��ת���༭ҳ��");
		logservice.saveLogInfo(slog);
		ModelAndView modelAndView=new ModelAndView("drag/fileupload");
		return modelAndView;
	}
	
	
	/**
     * �ļ��ϴ�
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
        //���ҵ���Ų���
        if(null==business_id||business_id.equals("")){
        	this.error(response, "ҵ���Ų���Ϊ��,����");
        	return ;
        }
        
		long MAX_SIZE = 20* 1024 * 1024L;//100m
		
    	try {
            //����һ��ͨ�õĶಿ�ֽ�����
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            //�ж� request �Ƿ����ļ��ϴ�,���ಿ������
            if (multipartResolver.isMultipart(request)) {
                //ת���ɶಿ��request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                //ȡ��request�е������ļ���
                Iterator<String> iter = multiRequest.getFileNames();
                while (iter.hasNext()) {
                    //ȡ���ϴ��ļ�
                    MultipartFile multipartFile = multiRequest.getFile(iter.next());
                    if (multipartFile.getSize() > MAX_SIZE) {
                        this.error(response, "�ļ��ߴ糬���涨��С:" + MAX_SIZE / 1024 / 1024 + "M");
                        break;
                    } else {
                       
                        // �õ�ȥ��·�����ļ���
                        String originalFilename = multipartFile.getOriginalFilename();
                        int indexofdoute = originalFilename.lastIndexOf(".");
                        
                        /**��ȡ�ļ��ĺ�׺**/
                        String endfix = "";
                        if (indexofdoute != -1) {
                            // β
                            endfix = originalFilename.substring(indexofdoute).toLowerCase();
                            if(endfix.equals(".jpg")||endfix.equals(".jpeg")||endfix.equals(".gif")||endfix.equals(".png") ||endfix.equals(".pdf")||endfix.equals(".doc")||endfix.equals(".docx")||endfix.equals(".xls")||endfix.equals(".xlsx")||endfix.equals(".rar")||endfix.equals(".zip")) {
	     						//�ϴ�����¼��־
                            	String id=uploadservice.upload(originalFilename,multipartFile.getInputStream() );
                            	System.out.println(id);
                                this.success(response, id);
                            }else{
                                this.error(response, "�ļ���ʽ����ȷ,��ȷ��,ֻ�����ϴ���ʽΪjpg��jpeg��gif��png��pdf��doc��docx��xls��xlsx��rar��zip��ʽ���ļ�!!");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
        	e.printStackTrace();
			// �����ļ��ߴ�����쳣
			if (e instanceof SizeLimitExceededException) {
				this.error(response, "�ļ��ߴ糬���涨��С:" + MAX_SIZE / 1024 / 1024 + "M");
			}
			this.error(response, e.getMessage());
        }

    }

    /**
     * �ļ�����
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
                 	//���д����Ƿ�ֹ��������Ĺؼ�����
                     response.setHeader("Content-disposition","attachment; filename="+ new String(filerecord.getFile_name().getBytes("GBK"),"iso-8859-1"));
                     BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(temp));
                     BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
                     //�½�һ��2048�ֽڵĻ�����
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
                 	throw new Exception("���ش���,�����ڵ�id");
                 }
        	}
        }catch(Exception e){
            //log.error(e.getMessage());
        }
    }
}
