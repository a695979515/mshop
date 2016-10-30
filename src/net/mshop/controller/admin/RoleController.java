package net.mshop.controller.admin;

import net.mshop.entity.Message;
import net.mshop.entity.Role;
import net.mshop.operator.Pageable;
import net.mshop.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

/**
 * 权限Controller
 * Created by Panfuhao on 2016/10/28.
 */
@Controller("adminRoleController")
@RequestMapping("/admin/role")
public class RoleController extends BaseController {
    @Resource(name = "roleServiceImpl")
    private RoleService roleService;

    /**
     * 列表
     *
     * @param pageable
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model) {
        model.addAttribute("page", roleService.findPage(pageable));
        return "/admin/role/list";
    }

    /**
     * 添加
     *
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "/admin/role/add";
    }

    /**
     * 编辑
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model) {
        model.addAttribute("role", roleService.findById(id));
        return "/admin/role/edit";
    }

    /**
     * 更新
     *
     * @param role
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(Role role, RedirectAttributes redirectAttributes) {
        if (!isValid(role)) {
            return ERROR_VIEW;
        }
        Role oldRole = roleService.findById(role.getId());
        if (oldRole == null || oldRole.getIsSystem()) {
            return ERROR_VIEW;
        }
        roleService.update(role, "isSystem", "admins");
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.html";
    }

    /**
     * 保存
     *
     * @param role
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Role role, RedirectAttributes redirectAttributes) {
        if (!isValid(role)) {
            return ERROR_VIEW;
        }
        role.setIsSystem(false);
        role.setAdmins(null);
        roleService.save(role);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.html";
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public
    @ResponseBody
    Message delete(Long[] ids) {
        if (ids != null) {
            for (Long id : ids) {
                Role role = roleService.findById(id);
                if (role != null && (role.getIsSystem() || (role.getAdmins() != null && !role.getAdmins().isEmpty()))) {
                    return Message.error("删除失败，角色[" + role.getName() + "]下存在管理员");
                }
            }
            roleService.deleteByIds(ids);
        }
        return SUCCESS_MESSAGE;
    }
}
