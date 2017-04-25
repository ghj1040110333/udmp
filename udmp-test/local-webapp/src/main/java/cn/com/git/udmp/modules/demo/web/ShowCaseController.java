package cn.com.git.udmp.modules.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.git.udmp.modules.act.service.ActTaskService;

@Controller
@RequestMapping(value = "/{ownerId}/owners", method = RequestMethod.GET)
public class ShowCaseController {
    @Autowired
    private ActTaskService actTaskService;

    @RequestMapping(value = "")
    public String test(@PathVariable String ownerId) {
        System.out.println("showcase进来了，参数是" + ownerId);
        actTaskService.startProcess("test_process", null, null);
        return "";
    }

    public void setActTaskService(ActTaskService actTaskService) {
        this.actTaskService = actTaskService;
    }

}
