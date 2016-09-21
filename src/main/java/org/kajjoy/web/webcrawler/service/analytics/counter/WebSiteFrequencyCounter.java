package org.kajjoy.web.webcrawler.service.analytics.counter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Service
public class WebSiteFrequencyCounter {

    private static final Logger logger = LoggerFactory.getLogger(WebSiteFrequencyCounter.class);

    private static final String HTTP_STRING = "http://";

    public Map<String,Integer> getFrequency(String url) throws IOException {
        Assert.notNull(url);
        Document websiteDoc;
        if(!url.contains(HTTP_STRING)){
            websiteDoc  = Jsoup.connect(HTTP_STRING + url).get();
        }else{
            websiteDoc = Jsoup.connect(url).get();
        }
        String text = websiteDoc.text();
        logger.info("Text is: " + text);

        List<String> lines =  textToLines(text);

        for(String line: lines){
            String[] words = line.split("\\W+");
            Map<String, Long> collect = Arrays.asList(words).stream().collect(groupingBy(Function.identity(), counting()));
            collect.entrySet().stream().forEach(e -> logger.info("Key: "+ e.getKey() + "Value : "+ e.getValue() ));
        }

        return null;
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
