package org.kajjoy.web.webcrawler.service.analytics.counter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.kajjoy.web.webcrawler.repository.SiteInfoRepository;
import org.kajjoy.web.webcrawler.vo.SiteElementFrequency;
import org.kajjoy.web.webcrawler.vo.SiteInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Service
public class WebSiteFrequencyCounter {

    private static final Logger logger = LoggerFactory.getLogger(WebSiteFrequencyCounter.class);

    private static final String HTTP_STRING = "http://";

    @Resource
    SiteInfoRepository siteInfoRepository;

    public SiteInfo getFrequency(String url) throws IOException {
        SiteInfo siteStats = siteInfoRepository.findByUrl(url);
        if(siteStats == null){

            Map<String,Long> wordCounts = getWordCounts(url);
            Set<SiteElementFrequency> frequencySet = wordCounts.entrySet()
                    .stream()
                    .map(entry -> new SiteElementFrequency(entry.getKey(),entry.getValue(),"WORD"))
                    .collect(Collectors.toSet());

            siteStats = new SiteInfo(url,"News",frequencySet);
            siteInfoRepository.save(siteStats);
        }

        return siteStats;
    }

    private Map<String,Long> getWordCounts(String url) throws IOException {
        Assert.notNull(url);
        Document websiteDoc;
        if(!url.contains(HTTP_STRING)){
            websiteDoc  = Jsoup.connect(HTTP_STRING + url).get();
        }else{
            websiteDoc = Jsoup.connect(url).get();
        }
        String text = websiteDoc.text();
        List<String> lines =  textToLines(text);

        Map<String,Long> result = new HashMap<>();

        for(String line: lines){
            String[] words = line.split("\\W+");
            Map<String, Long> collect = Arrays.asList(words).stream().collect(groupingBy(Function.identity(), counting()));
            result.putAll(collect);
        }
        return result;
    }


    private static List<String> textToLines(String text) {
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new StringReader(text))) {
            String line = reader.readLine();
            while (line != null) {
                result.add(line);
                line = reader.readLine();
            }
        } catch (IOException exc) {
            logger.error(exc.getMessage());
        }
        return result;
    }
}
