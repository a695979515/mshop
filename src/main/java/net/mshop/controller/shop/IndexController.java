package net.mshop.controller.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by Panfuhao on 2017/3/8.
 */
@Controller("indexController")
@RequestMapping("/")
public class IndexController {

    @RequestMapping()
    public String index(){
        return "/shop/index";
    }
}
