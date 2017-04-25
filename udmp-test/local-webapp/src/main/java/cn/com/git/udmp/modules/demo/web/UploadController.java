package cn.com.git.udmp.modules.demo.web;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.com.git.udmp.common.web.BaseController;
import cn.com.git.udmp.common.web.MobileResultVO;

@Controller
@RequestMapping(value = "${frontPath}/demo/upload")
public class UploadController extends BaseController{
	
	@RequestMapping(value = {"sendFile"})
	@ResponseBody
	public MobileResultVO<String> sendFile(@RequestParam("file")  MultipartFile file,String bizId){
		File f = new File("/Users/guosg/workspace/tmp/gson/"+file.getOriginalFilename());
		MobileResultVO<String> vo = new MobileResultVO<String>();
		
		try {
			IOUtils.write(file.getBytes(),new FileOutputStream(f));
		} catch (Exception e) {
			vo.setMessage(e.getMessage());
			vo.setResult(MobileResultVO.ERROR);
			return vo;
		} 
		vo.setResult(MobileResultVO.SUCCESS);
		return vo;
	}
	
	@RequestMapping(value = {"view"})
	public String uploadView(){
		
		return "udmp/modules/demo/upload";
	}
}
