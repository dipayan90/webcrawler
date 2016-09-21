package org.kajjoy.web.webcrawler.vo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class SiteElementFrequency {

    @ManyToOne
    @JoinColumn(name = "site_info_id", insertable = false, updatable = false)
    @JsonIgnore
    private SiteInfo siteInfo;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String key;
    private Long value;
    private String type;

    public SiteElementFrequency(){

    }

    public SiteElementFrequency(String key,Long value,String type){
        this.key = key;
        this.value = value;
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format(
                "SiteElementFrequency[site_id=%d, key='%s', value='%s', type='%s']",
                siteInfo.getId(), key, value,type);
    }

}
