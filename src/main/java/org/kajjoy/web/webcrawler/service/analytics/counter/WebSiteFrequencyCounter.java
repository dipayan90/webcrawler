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
import java.net.URL;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Service
public class WebSiteFrequencyCounter {

    private static final Logger logger = LoggerFactory.getLogger(WebSiteFrequencyCounter.class);

    private static final String ELEMENT_TYPE = "WORD";

    @Resource
    SiteInfoRepository siteInfoRepository;

    public SiteInfo getFrequency(String url,String category) throws IOException {
        Assert.notNull(url);
        // Validate if its a correct url if not throw exception
        URL validUrl = new URL(url);
        // find if data exists in DB
        SiteInfo siteStats = siteInfoRepository.findByUrl(url);
        if(siteStats == null){

            Map<String,Long> wordCounts = getWordCounts(url);
            Set<SiteElementFrequency> frequencySet = wordCounts.entrySet()
                    .stream()
                    .map(entry -> new SiteElementFrequency(entry.getKey(),entry.getValue(),ELEMENT_TYPE))
                    .collect(Collectors.toSet());

            siteStats = new SiteInfo(url,category,frequencySet);
            siteInfoRepository.save(siteStats);
        }

        return siteStats;
    }

    protected Map<String,Long> getWordCounts(String url) throws IOException {
        Document websiteDoc;
        websiteDoc = Jsoup.connect(url).get();
        String text = websiteDoc.text();
        List<String> lines =  textToLines(text);
        return counter(lines);
    }

    protected Map<String,Long> counter(List<String> lines){
        Map<String,Long> result = new HashMap<>();
        for(String line: lines){
            String[] words = line.split("\\W+");
            Map<String, Long> collect = Arrays.asList(words).stream().collect(groupingBy(Function.identity(), counting()));
            result.putAll(collect);
        }
        return result;
    }


    protected List<String> textToLines(String text) {
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
