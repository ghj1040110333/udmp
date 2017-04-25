/**
 * GIT Confidential
 * Licensed Materials - Property of GIT
 * Copyright (c) 1998-2015 Global InfoTech Corp. All Rights Reserved.
 * Global InfoTech, Inc. owns the copyright and other intellectual
 * property rights in this software.
 *
 * The source code for this program is not published.
 * Duplication or use of the Software is not permitted
 * except as expressly provided in a written agreement
 * between your company and Global InfoTech, Inc.
 */

package cn.com.git.udmp.modules.demo.web;

/**
 * @author Sammy
 *
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



import com.codahale.metrics.annotation.Counted;
import com.codahale.metrics.annotation.Timed;

@Controller
public class ShowCaseControllerMon {

    @Counted(name = "udmp-counted")
    @RequestMapping(method= RequestMethod.GET, value = "mon/showcase/counted")
    @ResponseBody
    public String getAll(@RequestParam java.util.Map<String,String> allRequestParams, ModelMap model) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ioe) {}
        return "getAll has counted";
    }
    
    @Timed(name = "udmp-timed")
    @RequestMapping(method=RequestMethod.GET, value = "mon/showcase/timed")
    @ResponseBody
    public String getSingle(@RequestParam java.util.Map<String,String> allRequestParams, ModelMap model) {
    	try{
    		Thread.sleep((long) Math.floor(Math.random()*1000));
    	}catch (InterruptedException ioe) {}
        return "getSingle has timed";
    }
}
