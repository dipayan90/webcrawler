package org.kajjoy.web.webcrawler.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebCrawlerController {

    @RequestMapping("/crawl")
    public String hello(@RequestParam String url) {
        return url;
    }

}
