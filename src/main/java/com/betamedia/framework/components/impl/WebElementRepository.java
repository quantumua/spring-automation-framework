package com.betamedia.framework.components.impl;

import com.betamedia.framework.web.pages.common.entities.PageElementLocation;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mbelyaev on 2/16/17.
 */
@Repository
public class WebElementRepository {

    private Map<String, Map<String, String>> locations = new HashMap<>();

    @PostConstruct
    public void populateRepository() {
        HeaderColumnNameMappingStrategy<PageElementLocation> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(PageElementLocation.class);
        CsvToBean<PageElementLocation> csvToBean = new CsvToBean<>();
        try (InputStream resourceInputStream = new ClassPathResource("/pageElementLocations.csv").getInputStream();) {
            csvToBean.parse(strategy, new CSVReader(new InputStreamReader(resourceInputStream))).forEach(el -> {
                        locations.putIfAbsent(el.getPageObjectName(), new HashMap<>());
                        locations.get(el.getPageObjectName()).put(el.getElementId(), el.getId());
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize web elements IDs store");
        }
    }

    public Map<String, String> getLocations(String pageObject) {
        return locations.getOrDefault(pageObject, null);
    }

}
