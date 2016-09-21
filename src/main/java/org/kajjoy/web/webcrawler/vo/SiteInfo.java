package org.kajjoy.web.webcrawler.vo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "site_info")
@Data
public class SiteInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String url;
    private String category;
    @OneToMany(mappedBy = "bookCategory", cascade = CascadeType.ALL)
    private Set<SiteElementFrequency> siteElementFrequencies;

    protected SiteInfo(){};

    protected SiteInfo(String url,String category){
        this.url = url;
        this.category = category;
    }

    @Override
    public String toString() {
        String result = String.format(
                "Category[id=%d, url='%s', category='%s']%n",
                id, url,category);
        if (siteElementFrequencies != null) {
            for(SiteElementFrequency frequency : siteElementFrequencies) {
                result += String.format(
                        "SiteElementFrequency[site_id=%d, key='%s', value='%s', type='%s']",
                        frequency.getSite_id(), frequency.getKey(), frequency.getValue(),frequency.getType());
            }
        }

        return result;
    }

}
