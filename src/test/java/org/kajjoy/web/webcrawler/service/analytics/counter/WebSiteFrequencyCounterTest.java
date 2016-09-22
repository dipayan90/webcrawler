package org.kajjoy.web.webcrawler.service.analytics.counter;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kajjoy.web.webcrawler.repository.SiteInfoRepository;
import org.kajjoy.web.webcrawler.vo.SiteInfo;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class WebSiteFrequencyCounterTest {

    @Mock
    SiteInfoRepository siteInfoRepository;

    @InjectMocks
    WebSiteFrequencyCounter frequencyCounter;

    @Test
    public void testTextToLines(){
        String s = "line1 \n line2 \n line3";
        List<String> lines = frequencyCounter.textToLines(s);
        Assert.assertEquals(3,lines.size());
    }

    public void testCounter(){
        String s1 = "cnn has good news";
        String s2 = "bbc also has good news";
        List<String> lines  = new ArrayList<>();
        lines.add(s1);
        lines.add(s2);
        Map<String,Long> frequency = frequencyCounter.counter(lines);
        Assert.assertEquals(new Long(2),frequency.get("good"));
        Assert.assertEquals(new Long(1),frequency.get("cnn"));
    }

    @Test
    public void testSaveToDB() throws IOException {
        Mockito.when(siteInfoRepository.findByUrl(Mockito.anyString())).thenReturn(null);
        frequencyCounter.getFrequency("http://www.google.com","news");
        Mockito.verify(siteInfoRepository,Mockito.times(1)).save(Mockito.any(SiteInfo.class));
    }

    @Test
    public void testGetFromDB() throws IOException {
        Mockito.when(siteInfoRepository.findByUrl(Mockito.anyString())).thenReturn(new SiteInfo());
        frequencyCounter.getFrequency("http://www.google.com","news");
        Mockito.verify(siteInfoRepository,Mockito.times(0)).save(Mockito.any(SiteInfo.class));
    }

}
