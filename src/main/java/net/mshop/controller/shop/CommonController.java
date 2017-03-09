package net.mshop.controller.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Panfuhao on 2017/3/9.
 */
@Controller("shopCommonController")
@RequestMapping("/common")
public class CommonController {

    @RequestMapping("/resource_not_found")
    public String resource_not_found(){
        return "/shop/404";
    }

}
