package com.pse;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootController {

    @RequestMapping(value = "/angular", method = GET)
    public String angular() {
        return "angular/index.html";
    }

}
