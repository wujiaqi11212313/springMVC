package controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
    private static final Log logger = LogFactory.getLog(HelloController.class);
    @RequestMapping(value = "/hello")
    public String hello(){
        logger.info("方法被调用");
        ModelAndView mv = new ModelAndView();
        mv.addObject("message","hello");
        return "index";
    }
}
