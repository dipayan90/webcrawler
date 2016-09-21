package org.kajjoy.web.webcrawler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ScheduleViewController {

    @RequestMapping("/")
    public String listJobs() {

        return "configure";
    }
}
