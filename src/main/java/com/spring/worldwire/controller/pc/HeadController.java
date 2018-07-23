package com.spring.worldwire.controller.pc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/head")
@Controller
public class HeadController {

    @RequestMapping("/lc/publish")
    public String publishRequireMent(){
        return "pc/releaseRequest";
    }
}
