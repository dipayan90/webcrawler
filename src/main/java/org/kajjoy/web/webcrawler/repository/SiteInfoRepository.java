package org.kajjoy.web.webcrawler.repository;

import org.kajjoy.web.webcrawler.vo.SiteInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteInfoRepository extends JpaRepository<SiteInfo,Integer> {
    SiteInfo findByUrl(String url);
}
