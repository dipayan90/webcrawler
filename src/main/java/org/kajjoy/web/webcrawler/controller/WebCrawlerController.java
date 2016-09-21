package org.kajjoy.web.webcrawler.controller;


import org.kajjoy.web.webcrawler.service.analytics.counter.WebSiteFrequencyCounter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

@RestController
public class WebCrawlerController {

    @Resource
    private WebSiteFrequencyCounter webSiteFrequencyCounter;

    @RequestMapping("/crawl")
    public Map<String,Long> getWordFrequencyCount(@RequestParam String url) throws IOException {
        return webSiteFrequencyCounter.getFrequency(url);
    }

}
