package net.mshop.controller.admin;

import net.mshop.entity.DeliveryCorp;
import net.mshop.entity.Message;
import net.mshop.operator.Pageable;
import net.mshop.service.DeliveryCorpService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

/**
 * 物流公司控制器
 * Created by Panfuhao on 2017/3/16.
 */
@Controller("deliveryCorpController")
@RequestMapping("/admin/delivery_corp")
public class DeliveryCorpController extends BaseController {
    @Resource(name = "deliveryCorpServiceImpl")
    private DeliveryCorpService deliveryCorpService;

    /**
     * 列表
     * @param pageable
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model) {
        model.addAttribute("page", deliveryCorpService.findPage(pageable));
        return "/admin/delivery_corp/list";
    }

    /**
     * 添加
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "/admin/delivery_corp/add";
    }

    /**
     *  保存
     * @param deliveryCorp
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(DeliveryCorp deliveryCorp, RedirectAttributes redirectAttributes) {
        if (!isValid(deliveryCorp)) {
            return ERROR_VIEW;
        }
     //   deliveryCorp.setShippingMethods(null);
        deliveryCorpService.save(deliveryCorp);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.html";
    }
    /**
     * 编辑
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model) {
        model.addAttribute("deliveryCorp", deliveryCorpService.findById(id));
        return "/admin/delivery_corp/edit";
    }

    /**
     * 更新
     * @param deliveryCorp
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(DeliveryCorp deliveryCorp, RedirectAttributes redirectAttributes) {
        if (!isValid(deliveryCorp)) {
            return ERROR_VIEW;
        }
        deliveryCorpService.update(deliveryCorp, "shippingMethods");
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.html";
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long[] ids) {
        deliveryCorpService.deleteByIds(ids);
        return SUCCESS_MESSAGE;
    }
}
