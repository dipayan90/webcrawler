package org.kajjoy.web.webcrawler.controller;


import org.kajjoy.web.webcrawler.service.analytics.counter.WebSiteFrequencyCounter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
public class WebCrawlerController {

    @Resource
    private WebSiteFrequencyCounter webSiteFrequencyCounter;

    @RequestMapping("/crawl")
    public String hello(@RequestParam String url) throws IOException {
        webSiteFrequencyCounter.getFrequency(url);
        return url;
    }

}
