package org.kajjoy.web.webcrawler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class WebCrawler {

    private static final Logger logger = LoggerFactory.getLogger(WebCrawler.class);


    public static void main(String[] args) {
        logger.info("Starting the web-crawler application");
        SpringApplication.run(WebCrawler.class, args);
    }

}