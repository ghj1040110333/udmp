

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
import cn.com.git.udmp.modules.sys.entity.Area;
import cn.com.git.udmp.modules.sys.service.AreaService;
import cn.com.git.udmp.modules.sys.utils.UserUtils;

/**
 * 区域Controller
 * 
 * @author Spring Cao
 * @version 2013-5-15
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/area")
public class AreaController extends BaseController {

    @Autowired
    private AreaService areaService;

    @ModelAttribute("area")
    public Area get(@RequestParam(required = false) String id) {
        if (StringUtils.isNotBlank(id)) {
            return areaService.get(id);
        } else {
            return new Area();
        }
    }

    @RequiresPermissions("sys:area:view")
    @RequestMapping(value = { "list", "" })
    public String list(Area area, Model model) {
        // model.addAttribute("list", areaService.findAll());
        return "modules/sys/areaList";
    }

    @ResponseBody
    @RequiresPermissions("sys:area:view")
    @RequestMapping(value = { "listData" })
    public Table listData(Area area, HttpServletRequest request, HttpServletResponse response, Model model) {
        List<Area> list = areaService.findList(area);
        Page<Area> page = new Page<Area>(request, response);
        page.setList(list);
        return new Table(page);
    }

    @RequiresPermissions("sys:area:view")
    @RequestMapping(value = "form")
    public String form(Area area, Model model) {
        if (area.getParent() == null || area.getParent().getId() == null) {
            area.setParent(UserUtils.getUser().getOffice().getArea());
        }
        area.setParent(areaService.get(area.getParent().getId()));
        // // 自动获取排序号
        // if (StringUtils.isBlank(area.getId())){
        // int size = 0;
        // List<Area> list = areaService.findAll();
        // for (int i=0; i<list.size(); i++){
        // Area e = list.get(i);
        // if (e.getParent()!=null && e.getParent().getId()!=null
        // && e.getParent().getId().equals(area.getParent().getId())){
        // size++;
        // }
        // }
        // area.setCode(area.getParent().getCode() +
        // StringUtils.leftPad(String.valueOf(size > 0 ? size : 1), 4, "0"));
        // }
        model.addAttribute("area", area);
        return "modules/sys/areaForm";
    }

    @ResponseBody
    @RequiresPermissions("sys:area:edit")
    @RequestMapping(value = "save")
    public  Map<String, Object> save(Area area, Model model) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", false);
        if (Global.isDemoMode()) {
            result.put("message", "演示模式，不允许操作！");
            return result;
        }
        if (!beanValidator(model, area)) {
            result.put("message", model.asMap().get("message"));
            return result;
        }
        areaService.save(area);
        result.put("success", true);
        result.put("message", "保存区域'" + area.getName() + "'成功");
        return result;
    }

    @ResponseBody
    @RequiresPermissions("sys:area:edit")
    @RequestMapping(value = "delete")
    public Map<String, Object> delete(Area area) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (Global.isDemoMode()) {
            result.put("message", "演示模式，不允许操作！");
            result.put("success", false);
        } else {
            areaService.delete(area);
            result.put("message", "删除区域成功");
            result.put("success", true);
        }
        return result;
    }

    @RequiresPermissions("user")
    @ResponseBody
    @RequestMapping(value = "treeData")
    public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId,
            HttpServletResponse response) {
        List<Map<String, Object>> mapList = Lists.newArrayList();
        List<Area> list = areaService.findAll();
        for (int i = 0; i < list.size(); i++) {
            Area e = list.get(i);
            if (StringUtils.isBlank(extId) || (extId != null && !extId.equals(e.getId())
                    && e.getParentIds().indexOf("," + extId + ",") == -1)) {
                Map<String, Object> map = Maps.newHashMap();
                map.put("id", e.getId());
                map.put("pId", e.getParentId());
                map.put("name", e.getName());
                mapList.add(map);
            }
        }
        return mapList;
    }
}
