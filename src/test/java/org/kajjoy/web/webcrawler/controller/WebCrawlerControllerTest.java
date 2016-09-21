package org.kajjoy.web.webcrawler.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kajjoy.web.webcrawler.service.analytics.counter.WebSiteFrequencyCounter;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class WebCrawlerControllerTest {

    @Mock
    WebSiteFrequencyCounter webSiteFrequencyCounter;

    @InjectMocks
    WebCrawlerController crawlerController;

    @Test
    public void testServiceCalledFromController() throws IOException {
        crawlerController.getWordFrequencyCount(Mockito.anyString(),Mockito.anyString());
        Mockito.verify(webSiteFrequencyCounter,Mockito.times(1)).getFrequency(Mockito.anyString(),Mockito.anyString());
    }

}
