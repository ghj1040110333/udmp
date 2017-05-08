package activiti_maven_project.com.git.controller.web;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import activiti_maven_project.com.git.common.Page;
import activiti_maven_project.com.git.common.exception.BpmException;
import activiti_maven_project.com.git.common.vo.ActDefinitionPage;
import activiti_maven_project.com.git.common.vo.ActFromVO;
import activiti_maven_project.com.git.common.vo.ModelExportVO;
import activiti_maven_project.com.git.service.ProcessDefinitionService;
import activiti_maven_project.com.git.service.ProcessService;
import activiti_maven_project.com.git.user.Users;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
@Controller
@RequestMapping(value = "/process")
public class ProcessController extends BaseController {

	@Autowired(required = true)
	private ProcessDefinitionService processDefinitionService;
	
	@Autowired(required = true)
	private ProcessService processService;
	/**
	 * bpm model处理页面初始化
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws BpmException
	 */
	@RequestMapping(value = "/initDefinition")
	public  ModelAndView  init( HttpServletRequest request, HttpServletResponse response, Model model) throws BpmException{
		ActDefinitionPage adp=null;
		try {
			adp = processDefinitionService.findProcessDefinitons("", new Page());
		} catch (BpmException e) {
			throw new BpmException(e.getMessage());
		}
		
		model.addAttribute("templateName", "");
		model.addAttribute("templates", adp);
		model.addAttribute("page", adp.getPage());
		
		
		ModelAndView modelAndView = new ModelAndView(); 
		modelAndView.setViewName("../operation/processDef/definitionList.jsp");
		return modelAndView;
	}
	
	
	

	/**
	 * bpm model页面列表查询
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws BpmException 
	 */
	@RequestMapping(value = "/showList", method=RequestMethod.GET)
	public ModelAndView showList(HttpServletRequest request, HttpServletResponse response, Model model) throws BpmException{
		String pageNo=request.getParameter("pageNo");
		String templateName=request.getParameter("templateName");
		Page page=new Page();
		if(!StringUtils.isBlank(pageNo)){
			page.setPageNo(new Integer(pageNo));
		}
		
		ActDefinitionPage adp=null;
		try {
			adp = processDefinitionService.findProcessDefinitons(templateName, page);
		} catch (BpmException e) {
			throw new BpmException(e.getMessage());
		}
		model.addAttribute("templateName", templateName);
		model.addAttribute("templates", adp);
		model.addAttribute("page", adp.getPage());
		ModelAndView modelAndView = new ModelAndView(); 
		modelAndView.setViewName("../../operation/processDef/definitionList.jsp");
		return modelAndView;
	}
	
	
	/**
	 * 删除bpm model
	 * @param deploymentId
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = "/remove", method=RequestMethod.POST) 
	@ResponseBody
	public void delete(@RequestBody String deploymentId ,HttpServletRequest request,HttpServletResponse response, Model model)  { 
		logger.debug("deploymentId===="+deploymentId);
		processDefinitionService.deleteDeployment(deploymentId, true);
	}
	
	
	/**
	 * 展现 流程图
	 * @param modelId
	 * @param request
	 * @param response
	 * @param model
	 * @throws BpmException
	 */
	@RequestMapping(value = "/pic/{pefId}") 
	@ResponseBody	
	public void pic(@PathVariable("pefId") String pefId ,HttpServletRequest request,HttpServletResponse response, Model model) throws BpmException{
		InputStream is=processDefinitionService.readPictrueStreamForPdf(pefId);
		byte[] b = new byte[1024];
		int len = -1;
		ServletOutputStream sos;
		try {
			sos = response.getOutputStream();
		
		while ((len = is.read(b, 0, 1024)) != -1) {
			sos.write(b, 0, len);
		}
		
		sos.flush();
		} catch (IOException e) {
			throw new BpmException(e.getMessage());
		}finally {
			try {
				is.close();
			} catch (IOException e) {
				throw new BpmException(e.getMessage());
			}
		}
		
	}
	
	   /**
	    * 根据Model部署流程
	    * @param modelId
	    * @param redirectAttributes
	    * @throws BpmException
	    */
	    @RequestMapping(value = "/signalProcess/{executionId}")
	    @ResponseBody
	    public void signalProcess(@PathVariable("executionId") String executionId,@RequestBody String parms, RedirectAttributes redirectAttributes) throws BpmException {
			Map<String, Object> variables = new HashMap<String, Object>();
			if (StringUtils.isNotBlank(parms)) {
				variables = str2map(parms);
			}
	    	processService.signalProcess(executionId, variables);
	    }
	
   /**
    * 根据Model部署流程
    * @param modelId
    * @param redirectAttributes
    * @throws BpmException
    */
    @RequestMapping(value = "/deploy")
    @ResponseBody
    public void deploy(@RequestBody String modelId, RedirectAttributes redirectAttributes) throws BpmException {
    	processDefinitionService.deploymentModel(modelId);
    }
    
    
    /**
     * 通过activiti modeler新建流程图
     * @param name
     * @param key
     * @param description
     * @param request
     * @param response
     * @throws BpmException
     */
	  @RequestMapping(value = "/create" )
	  @ResponseBody
	  public void startProcessInstance(@RequestBody String parms,@ModelAttribute("user") Users user,  HttpServletRequest request, HttpServletResponse response) throws BpmException {
		  Map<String,Object> variables=str2map(parms);
		  String key=(String)variables.get("pdfKey");
		  variables.remove("pdfKey");
		  String bizId=(String)variables.get("bizId");
//		  variables.remove("bizId");
		  logger.debug("key:{}|bizId:{}|variables:{}",key,bizId,variables);
		  
	    try {
	    	String pid=processService.startProcess(user.getUserCd(), key, bizId, variables);
	    	logger.debug("processService.startProcess===="+pid);
	    } catch (Exception e) {
	      logger.error("创建模型失败：", e);
	      throw new BpmException("创建模型失败：",e);
	    }
	  }
	  
	/**
	 * 导出bpm model对应的bpmn文件或json文件
	 * @param modelId
	 * @param type
	 * @param response
	 * @throws BpmException 
	 */
	@RequestMapping(value = "/export/{modelId}/{type}")
	@ResponseBody
	 public void export(@PathVariable("modelId") String modelId,@PathVariable("type") String type, HttpServletResponse response) throws BpmException { 
        try {
        	ModelExportVO mev=processDefinitionService.readResourceStream(modelId, type);
            ByteArrayInputStream in = new ByteArrayInputStream(mev.getProcessDef());
            IOUtils.copy(in, response.getOutputStream());
            response.setHeader("Content-Disposition", "attachment; filename=" + mev.getProcessName());
            response.flushBuffer();
        } catch (Exception e) {
            logger.error("导出model的xml文件失败：modelId={}, type={}", modelId, type, e);
            throw new BpmException("导出模型失败：",e);
        }
	  }
	

	
	
	/**
	 * 上传bpmn文件
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws BpmException
	 */
	@RequestMapping("/upload")  
    public ModelAndView upload(HttpServletRequest request,HttpServletResponse response, Model model) throws BpmException{  
		javax.servlet.ServletContext sc=request.getSession().getServletContext();
		String absoultePath = sc.getRealPath("template");
		logger.debug("absoultePath===="+absoultePath);
        //创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(sc);  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            Iterator<String> iter = multiRequest.getFileNames();  
            while(iter.hasNext()){  
                //记录上传过程起始时的时间，用来计算上传时间  
                int pre = (int) System.currentTimeMillis();  
                //取得上传文件  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if(file != null){  
                    //取得当前上传文件的文件名称  
                	InputStream io=null;
                	try {
                		io=file.getInputStream();
						processDefinitionService.uploadProcess(io, file.getOriginalFilename());
					} catch (IOException e) {
						 throw new BpmException("上传失败：",e);
					}finally {
						try {
							io.close();
						} catch (IOException e) {
							throw new BpmException("io.close失败：",e);
						}
					}
                	
                }  
                //记录上传该文件后的时间  
                int finaltime = (int) System.currentTimeMillis();  
                System.out.println(finaltime - pre);  
            }  
              
        }  
		return init(request, response, model);
    }  
	
	@Deprecated
	@SuppressWarnings("unused")
	public static void inputstreamTofile(InputStream ins,File file) {
		  try {
		   OutputStream os = new FileOutputStream(file);
		   int bytesRead = 0;
		   byte[] buffer = new byte[8192];
		   while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
		    os.write(buffer, 0, bytesRead);
		   }
		   os.close();
		   ins.close();
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		 }
}
