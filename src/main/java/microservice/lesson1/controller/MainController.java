package microservice.lesson1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @RequestMapping(value = {"/", "/index**"}, method = {RequestMethod.GET})
    public ModelAndView indexPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        return model;
    }
}
