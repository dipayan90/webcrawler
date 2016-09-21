package org.kajjoy.web.webcrawler.vo;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
public class SiteElementFrequency {

    @ManyToOne
    @JoinColumn(name = "site_info_id")
    private Long site_id;
    @Id
    private Long id;
    private String key;
    private Long value;
    private String type;

    public SiteElementFrequency(){};

    public SiteElementFrequency(String key,Long value,String type){
        this.key = key;
        this.value = value;
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format(
                "SiteElementFrequency[site_id=%d, key='%s', value='%s', type='%s']",
                site_id, key, value,type);
    }

}
