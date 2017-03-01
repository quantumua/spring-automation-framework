package com.betamedia.framework.web.controllers;

import com.betamedia.framework.business.RunTestHandler;
import com.betamedia.framework.components.SUTPropertiesHolder;
import com.betamedia.framework.entities.web.RunTestParams;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/28/17.
 */
@RestController
@RequestMapping("/run")
public class RunTestController {

    @Autowired
    private RunTestHandler runTestHandler;

//    @Autowired
//    private SUTPropertiesHolder sutPropertiesHolder;

    @Autowired
    private BeanFactory beanFactory;

    @RequestMapping(method = GET)
    public ResponseEntity<String> run(@Valid RunTestParams params) {
        try {
            SUTPropertiesHolder sutPropertiesHolder = beanFactory.getBean(SUTPropertiesHolder.class,getProperties(params.getSut()));
//            sutPropertiesHolder.set(getProperties(params.getSut()));
            runTestHandler.handle(params);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Properties getProperties(String fileName) throws IOException {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = this.getClass().getClassLoader().getResourceAsStream(fileName);
            if (input == null) {
                throw new RuntimeException("Sorry, unable to find " + fileName);
            }

            //load a properties file from class path, inside static method
            prop.load(input);
            return prop;

        } catch (IOException ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
