package net.mshop.controller.admin;

import net.mshop.entity.ShippingMethod;
import net.mshop.operator.Pageable;
import net.mshop.service.DeliveryCorpService;
import net.mshop.service.ShippingMethodService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

/**
 * 配送方式Controller
 * Created by Panfuhao on 2017/3/23.
 */
@Controller("adminShippingMethodController")
@RequestMapping("/admin/shipping_method")
public class ShippingMethodController extends BaseController {
    @Resource(name = "shippingMethodServiceImpl")
    private ShippingMethodService shippingMethodService;
    @Resource(name = "deliveryCorpServiceImpl")
    private DeliveryCorpService deliveryCorpService;

    /**
     * 配送方式 列表
     * @param pageable
     * @param model
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model){
        System.out.println("adfasfdsaf");
        model.addAttribute("page",shippingMethodService.findPage(pageable));
        return "/admin/shipping_method/list";
    }

    /**
     * 添加
     * @param model
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(ModelMap model){
        model.addAttribute("deliveryCorps",deliveryCorpService.findAll());
        return "/admin/shipping_method/add";
    }

    /**
     * 保存
     * @param shippingMethod
     * @param defaultDeliveryCorpId
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(ShippingMethod shippingMethod, Long defaultDeliveryCorpId, RedirectAttributes redirectAttributes){
        shippingMethod.setDefaultDeliveryCorp(deliveryCorpService.findById(defaultDeliveryCorpId));
        if (!isValid(shippingMethod)) {
            return ERROR_VIEW;
        }
        shippingMethodService.save(shippingMethod);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.html";
    }

}
