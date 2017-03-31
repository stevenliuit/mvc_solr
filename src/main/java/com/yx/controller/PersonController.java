package com.yx.controller;

import com.yx.model.Person;
import com.yx.service.PersonService;
import com.yx.utils.GenResult;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "person")
public class PersonController {

    @Resource
    private PersonService personService;

    private static Logger log = Logger.getLogger(PersonController.class);

    @RequestMapping(value = "queryById")
    @ResponseBody
    public Map<String, Object> queryById(String id) {
        try {
            List<Person> persons = personService.getById(id);
            return GenResult.SUCCESS.genResult(persons);
        } catch (Exception e) {
            log.error(e, e);
            return GenResult.FAILED.genResult();
        }
    }

    @RequestMapping(value = "queryByName")
    @ResponseBody
    public Map<String, Object> queryByName(String name) {
        try {
            List<Person> persons = personService.getByName(name);
            return GenResult.SUCCESS.genResult(persons);
        } catch (Exception e) {
            log.error(e, e);
            return GenResult.FAILED.genResult();
        }
    }

    @RequestMapping(value = "getCloudById")
    @ResponseBody
    public Map<String, Object> getCloudById(String id) {
        try {
            List<Person> persons = personService.getCloudById(id);
            return GenResult.SUCCESS.genResult(persons);
        } catch (Exception e) {
            log.error(e, e);
            return GenResult.FAILED.genResult();
        }
    }

    @RequestMapping(value = "getCloudByName")
    @ResponseBody
    public Map<String, Object> getCloudByName(String name) {
        try {
            List<Person> persons = personService.getCloudByName(name);
            return GenResult.SUCCESS.genResult(persons);
        } catch (Exception e) {
            log.error(e, e);
            return GenResult.FAILED.genResult();
        }
    }

    //多条件，日期范围
    @RequestMapping(value = "getFilterQuery")
    @ResponseBody
    public Map<String, Object> getFilterQuery() {
        try {
            List<Person> persons = personService.getFilterQuery();
            return GenResult.SUCCESS.genResult(persons);
        } catch (Exception e) {
            log.error(e, e);
            return GenResult.FAILED.genResult();
        }
    }
}
