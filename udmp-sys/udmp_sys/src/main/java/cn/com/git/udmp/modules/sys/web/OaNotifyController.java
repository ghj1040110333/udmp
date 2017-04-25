
package cn.com.git.udmp.modules.sys.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.com.git.udmp.common.persistence.Page;
import cn.com.git.udmp.common.persistence.Table;
import cn.com.git.udmp.common.utils.StringUtils;
import cn.com.git.udmp.common.web.BaseController;
import cn.com.git.udmp.core.config.Global;
import cn.com.git.udmp.modules.sys.entity.OaNotify;
import cn.com.git.udmp.modules.sys.service.OaNotifyService;

/**
 * 通知通告Controller
 * 
 * @author 叶清平
 * @version 2016-02-24
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/oaNotify")
public class OaNotifyController extends BaseController {

    @Autowired
    private OaNotifyService oaNotifyService;

    @ModelAttribute
    public OaNotify get(@RequestParam(required = false) String id) {
        OaNotify entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = oaNotifyService.get(id);
        }
        if (entity == null) {
            entity = new OaNotify();
        }
        return entity;
    }

    @RequiresPermissions("sys:oaNotify:view")
    @RequestMapping(value = { "list", "" })
    public String list(OaNotify oaNotify, HttpServletRequest request, HttpServletResponse response, Model model) {
        return "modules/sys/oaNotifyList";
    }

    /**
     * 通知列表
     * 
     * @param notify
     * @param request
     * @param response
     * @param model
     * @return
     */
    @ResponseBody
    @RequiresPermissions("sys:oaNotify:view")
    @RequestMapping(value = { "listData" })
    public Table listData(OaNotify oaNotify, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<OaNotify> page = oaNotifyService.find(new Page<OaNotify>(request, response), oaNotify);
        return new Table(page);
    }

    @RequiresPermissions("sys:oaNotify:view")
    @RequestMapping(value = "form")
    public String form(OaNotify oaNotify, Model model) {
        if (StringUtils.isNotBlank(oaNotify.getId())) {
            oaNotify = oaNotifyService.getRecordList(oaNotify);
        }
        model.addAttribute("oaNotify", oaNotify);
        return "modules/sys/oaNotifyForm";
    }

    @ResponseBody
    @RequiresPermissions("sys:oaNotify:edit")
    @RequestMapping(value = "save")
    public Map<String, Object> save(OaNotify oaNotify, Model model, RedirectAttributes redirectAttributes) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", false);
        if (Global.isDemoMode()) {
            result.put("message", "演示模式，不允许操作！");
            return result;
        }
        if (!beanValidator(model, oaNotify)) {
            result.put("message", model.asMap().get("message"));
            return result;
        }
        // 如果是修改，则状态为已发布，则不能再进行操作
        if (StringUtils.isNotBlank(oaNotify.getId())) {
            OaNotify e = oaNotifyService.get(oaNotify.getId());
            if ("1".equals(e.getStatus())) {
                result.put("message", "已发布，不能操作！");
                return result;
            }
        }
        oaNotifyService.save(oaNotify);
        result.put("success", true);
        result.put("message", "保存通知'" + oaNotify.getTitle() + "'成功");
        return result;
    }

    @ResponseBody
    @RequiresPermissions("sys:oaNotify:edit")
    @RequestMapping(value = "delete")
    public Map<String, Object> delete(OaNotify oaNotify, RedirectAttributes redirectAttributes) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", false);
        if (Global.isDemoMode()) {
            result.put("message", "演示模式，不允许操作！");
            return result;
        }
        oaNotifyService.delete(oaNotify);
        result.put("success", true);
        result.put("message", "删除通知成功");
        return result;
    }

    /**
     * 我的通知列表
     */
    @RequestMapping(value = "self")
    public String selfList(OaNotify oaNotify, HttpServletRequest request, HttpServletResponse response, Model model) {
        oaNotify.setSelf(true);
        return "modules/sys/oaNotifyList";
    }

    /**
     * 查看我的通知
     */
    @RequestMapping(value = "view")
    public String view(OaNotify oaNotify, Model model) {
        if (StringUtils.isNotBlank(oaNotify.getId())) {
            oaNotifyService.updateReadFlag(oaNotify);
            oaNotify = oaNotifyService.getRecordList(oaNotify);
            model.addAttribute("oaNotify", oaNotify);
            return "modules/sys/oaNotifyForm";
        }
        return "redirect:" + adminPath + "/sys/oaNotify/self?repage";
    }

    /**
     * 获取我的未读通知
     */
    @RequestMapping(value = "self/unread")
    @ResponseBody
    public List<OaNotify> unread(OaNotify oaNotify, Model model) {
        return oaNotifyService.findUnread(oaNotify);
    }
}