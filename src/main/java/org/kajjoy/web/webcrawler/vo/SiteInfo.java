package org.kajjoy.web.webcrawler.vo;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@ToString
public class SiteInfo extends AbstractTimeStamp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String url;
    private String category;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = SiteElementFrequency.class)
    private Set<SiteElementFrequency> siteElementFrequencySet;

    public SiteInfo(){

    }

    public SiteInfo(String url, String category, Set<SiteElementFrequency> siteElementFrequencySet){
        this.url = url;
        this.category = category;
        this.siteElementFrequencySet = siteElementFrequencySet;
    }

}
