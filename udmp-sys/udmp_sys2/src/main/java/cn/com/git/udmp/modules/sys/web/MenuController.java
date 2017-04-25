

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
import cn.com.git.udmp.modules.sys.entity.Menu;
import cn.com.git.udmp.modules.sys.service.SystemService;
import cn.com.git.udmp.modules.sys.utils.UserUtils;

/**
 * 菜单Controller
 * 
 * @author Spring Cao
 * @version 2013-3-23
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/menu")
public class MenuController extends BaseController {

    @Autowired
    private SystemService systemService;

    @ModelAttribute("menu")
    public Menu get(@RequestParam(required = false) String id) {
        if (StringUtils.isNotBlank(id)) {
            return systemService.getMenu(id);
        } else {
            return new Menu();
        }
    }

    @RequiresPermissions("sys:menu:view")
    @RequestMapping(value = { "list", "" })
    public String list(Model model) {
        return "modules/sys/menuList";
    }

    @ResponseBody
    @RequiresPermissions("sys:menu:view")
    @RequestMapping(value = { "listData" })
    public Table listData(Menu menu, HttpServletRequest request, HttpServletResponse response, Model model) {
        List<Menu> list = systemService.findAllMenu();
        Page<Menu> page = new Page<Menu>(request, response);
        page.setList(list);
        return new Table(page);
    }

    @RequiresPermissions("sys:menu:view")
    @RequestMapping(value = "form")
    public String form(Menu menu, Model model) {
        if (menu.getParent() == null || menu.getParent().getId() == null) {
            menu.setParent(new Menu(Menu.getRootId()));
        }
        menu.setParent(systemService.getMenu(menu.getParent().getId()));
        // 获取排序号，最末节点排序号+30
        if (StringUtils.isBlank(menu.getId())) {
            List<Menu> list = Lists.newArrayList();
            List<Menu> sourcelist = systemService.findAllMenu();
            Menu.sortList(list, sourcelist, menu.getParentId(), false);
            if (list.size() > 0) {
                menu.setSort(list.get(list.size() - 1).getSort() + 30);
            }
        }
        model.addAttribute("menu", menu);
        return "modules/sys/menuForm";
    }

    @ResponseBody
    @RequiresPermissions("sys:menu:edit")
    @RequestMapping(value = "save")
    public Map<String, Object> save(Menu menu, Model model) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", false);
        if (menu.getParent() == null || menu.getParent().getId() == null || menu.getParent().getId().equals("")) {
            menu.setParent(new Menu(Menu.getRootId()));
        }
        if (!UserUtils.getUser().isAdmin()) {
            result.put("message", "越权操作，只有超级管理员才能添加或修改数据！");
            return result;
        }
        if (Global.isDemoMode()) {
            result.put("message", "演示模式，不允许操作！");
            return result;
        }
        if (!beanValidator(model, menu)) {
            result.put("message", model.asMap().get("message"));
            return result;
        }
        systemService.saveMenu(menu);
        result.put("success", true);
        result.put("message", "保存菜单'" + menu.getName() + "'成功");
        return result;
    }

    @ResponseBody
    @RequiresPermissions("sys:menu:edit")
    @RequestMapping(value = "delete")
    public Map<String, Object> delete(Menu menu) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (Global.isDemoMode()) {
            result.put("message", "演示模式，不允许操作！");
            result.put("success", false);
        } else {
            systemService.deleteMenu(menu);
            result.put("message", "删除菜单成功");
            result.put("success", true);
        }
        return result;
    }

    @RequiresPermissions("user")
    @RequestMapping(value = "tree")
    public String tree() {
        return "modules/sys/menuTree";
    }

    @RequiresPermissions("user")
    @RequestMapping(value = "treeselect")
    public String treeselect(String parentId, Model model) {
        model.addAttribute("parentId", parentId);
        return "modules/sys/menuTreeselect";
    }

    /**
     * 批量修改菜单排序
     */
    @ResponseBody
    @RequiresPermissions("sys:menu:edit")
    @RequestMapping(value = "updateSort")
    public Map<String, Object> updateSort(String[] ids, Integer[] sorts) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", false);
        if (Global.isDemoMode()) {
            result.put("message", "演示模式，不允许操作！");
        } else {
            for (int i = 0; i < ids.length; i++) {
                Menu menu = new Menu(ids[i]);
                menu.setSort(sorts[i]);
                systemService.updateMenuSort(menu);
            }
            result.put("message", "保存菜单排序成功!");
            result.put("success", true);
        }
        return result;
        /*
         * if (Global.isDemoMode()) { addMessage(redirectAttributes,
         * "演示模式，不允许操作！"); return "redirect:" + adminPath + "/sys/menu/"; } for
         * (int i = 0; i < ids.length; i++) { Menu menu = new Menu(ids[i]);
         * menu.setSort(sorts[i]); systemService.updateMenuSort(menu); }
         * addMessage(redirectAttributes, "保存菜单排序成功!"); return "redirect:" +
         * adminPath + "/sys/menu/";
         */
    }

    /**
     * isShowHide是否显示隐藏菜单
     * 
     * @param extId
     * @param isShowHidden
     * @param response
     * @return
     */
    @RequiresPermissions("user")
    @ResponseBody
    @RequestMapping(value = "treeData")
    public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId,
            @RequestParam(required = false) String isShowHide, HttpServletResponse response) {
        List<Map<String, Object>> mapList = Lists.newArrayList();
        List<Menu> list = systemService.findAllMenu();
        for (int i = 0; i < list.size(); i++) {
            Menu e = list.get(i);
            if (StringUtils.isBlank(extId) || (extId != null && !extId.equals(e.getId())
                    && e.getParentIds().indexOf("," + extId + ",") == -1)) {
                if (isShowHide != null && isShowHide.equals("0") && e.getIsShow().equals("0")) {
                    continue;
                }
                Map<String, Object> map = Maps.newHashMap();
                map.put("id", e.getId());
                map.put("pId", e.getParentId());
                map.put("name", e.getName());
                mapList.add(map);
            }
        }
        return mapList;
    }
    
    @ResponseBody
    @RequestMapping(value = "node")
    public List<Menu> getNode(String id){
        List<Menu> list = systemService.getAllById(id);
        return list;
    }
}
