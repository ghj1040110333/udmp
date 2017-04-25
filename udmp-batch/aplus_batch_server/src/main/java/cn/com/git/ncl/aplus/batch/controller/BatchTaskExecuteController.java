

package cn.com.git.ncl.aplus.batch.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.git.ncl.aplus.batch.entity.BatchTaskExecuteData;
import cn.com.git.ncl.aplus.batch.service.BatchTaskExecuteService;
import cn.com.git.udmp.common.persistence.Page;
import cn.com.git.udmp.common.persistence.Table;


/** 
 * @description 按法人代码启动任务
 * @author sunliang@git.com.cn  
 * @date 2017年2月24日 下午1:49:16  
*/
@Controller
@RequestMapping(value = "${adminPath}/batch/taskExecute")
public class BatchTaskExecuteController{
    
    @Autowired
    private BatchTaskExecuteService batchTaskExecuteService;
    
    @RequestMapping(value = "")
    public String list(HttpServletRequest request, HttpServletResponse response, Model model) {
        
        return "batch/batchTaskExecute";
        
    }
    
    @ResponseBody
    @RequestMapping(value = "/list")
    public Table list(BatchTaskExecuteData batchTaskExecuteData,HttpServletRequest request, HttpServletResponse response, Model model){
        
        
        Page<BatchTaskExecuteData> page = batchTaskExecuteService.findPage(new Page<BatchTaskExecuteData>(request, response), batchTaskExecuteData); 
        model.addAttribute("page", page);
        
        return new Table(page);
    }
    
}

