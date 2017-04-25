

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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cn.com.git.udmp.common.persistence.Page;
import cn.com.git.udmp.common.persistence.Table;
import cn.com.git.udmp.common.utils.StringUtils;
import cn.com.git.udmp.common.web.BaseController;
import cn.com.git.udmp.core.config.Global;
import cn.com.git.udmp.modules.sys.entity.Dict;
import cn.com.git.udmp.modules.sys.service.DictService;

/**
 * 字典Controller
 * 
 * @author Spring Cao
 * @version 2014-05-16
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/dict")
public class DictController extends BaseController {

    @Autowired
    private DictService dictService;

    @ModelAttribute
    public Dict get(@RequestParam(required = false) String id) {
        if (StringUtils.isNotBlank(id)) {
            return dictService.get(id);
        } else {
            return new Dict();
        }
    }

    @RequiresPermissions("sys:dict:view")
    @RequestMapping(value = { "list", "" })
    public String list(Dict dict, HttpServletRequest request, HttpServletResponse response, Model model) {
        return "modules/sys/dictList";
    }

    @ResponseBody
    @RequiresPermissions("sys:dict:view")
    @RequestMapping(value = { "listType" })
    public List<String> listType(Dict dict, HttpServletRequest request, HttpServletResponse response, Model model) {
        List<String> typeList = dictService.findTypeList();
        return typeList;
    }

    @ResponseBody
    @RequiresPermissions("sys:dict:view")
    @RequestMapping(value = { "listPageData" })
    public Table listPageData(Dict dict, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Dict> page = dictService.findPage(new Page<Dict>(request, response), dict);
        return new Table(page);
    }

    @RequiresPermissions("sys:dict:view")
    @RequestMapping(value = "form")
    public String form(Dict dict, Model model) {
        model.addAttribute("dict", dict);
        return "modules/sys/dictForm";
    }

    @ResponseBody
    @RequiresPermissions("sys:dict:edit")
    @RequestMapping(value = "save") // @Valid
    public Map<String, Object> save(Dict dict, Model model) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", false);
        if (Global.isDemoMode()) {
            result.put("message", "演示模式，不允许操作！");
            return result;
        }
        if (!beanValidator(model, dict)) {
            result.put("message", model.asMap().get("message"));
            return result;
        }
        dictService.save(dict);
        result.put("success", true);
        result.put("message", "保存字典'" + dict.getLabel() + "'成功");
        return result;
    }

    @ResponseBody
    @RequiresPermissions("sys:dict:edit")
    @RequestMapping(value = "delete")
    public Map<String, Object> delete(Dict dict) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (Global.isDemoMode()) {
            result.put("message", "演示模式，不允许操作！");
            result.put("success", false);
        } else {
            dictService.delete(dict);
            result.put("message", "删除字典成功");
            result.put("success", true);
        }
        return result;
    }

    @RequiresPermissions("user")
    @ResponseBody
    @RequestMapping(value = "treeData")
    public List<Map<String, Object>> treeData(@RequestParam(required = false) String type,
            HttpServletResponse response) {
        List<Map<String, Object>> mapList = Lists.newArrayList();
        Dict dict = new Dict();
        dict.setType(type);
        List<Dict> list = dictService.findList(dict);
        for (int i = 0; i < list.size(); i++) {
            Dict e = list.get(i);
            Map<String, Object> map = Maps.newHashMap();
            map.put("id", e.getId());
            map.put("pId", e.getParentId());
            map.put("name", StringUtils.replace(e.getLabel(), " ", ""));
            mapList.add(map);
        }
        return mapList;
    }

    @ResponseBody
    @RequestMapping(value = "listData")
    public List<Dict> listData(@RequestParam(required = false) String type) {
        Dict dict = new Dict();
        dict.setType(type);
        return dictService.findList(dict);
    }

}
