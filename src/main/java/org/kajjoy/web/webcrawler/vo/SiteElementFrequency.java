package org.kajjoy.web.webcrawler.vo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class SiteElementFrequency {

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

}
