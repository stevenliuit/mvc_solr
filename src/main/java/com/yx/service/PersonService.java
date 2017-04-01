package com.yx.service;

import com.yx.model.Person;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PersonService {

    @Resource
    private HttpSolrServer httpSolrServer;

    @Resource
    private CloudSolrServer cloudSolrServer;

    public List<Person> getById(String id) throws SolrServerException {
        SolrQuery query = new SolrQuery();
        query.setQuery("id:" + id);
        QueryResponse queryResponse = httpSolrServer.query(query);
        return queryResponse.getBeans(Person.class);
    }

    public List<Person> getByName(String name) throws SolrServerException {
        SolrQuery query = new SolrQuery();
        query.setQuery("name:" + name);
        QueryResponse queryResponse = httpSolrServer.query(query);
        return queryResponse.getBeans(Person.class);
    }

    public List<Person> getCloudById(String id) throws SolrServerException {
        SolrQuery query = new SolrQuery();
        query.setQuery("id:" + id);
        QueryResponse queryResponse = cloudSolrServer.query(query);
        return queryResponse.getBeans(Person.class);
    }

    public List<Person> getCloudByName(String name) throws SolrServerException {
        SolrQuery query = new SolrQuery();
        query.setQuery("name:" + name);
        QueryResponse queryResponse = cloudSolrServer.query(query);
        return queryResponse.getBeans(Person.class);
    }

    /**
     * solr的date日期格式为GMT：2017-03-30T17:33:18Z
     *
     * @return
     * @throws SolrServerException
     */
    public List<Person> getFilterQuery() throws SolrServerException {
        SolrQuery query = new SolrQuery();
//        query.setQuery("last_modified:[NOW/DAY-3DAY TO *]");日期计算
//        query.setQuery("last_modified:[* TO NOW/DAY]");
        query.setQuery("last_modified:[NOW/DAY TO *]");
        query.setQuery("UPDATE_TIME:[20170330172445 TO *]");//字符串范围
        query.setQuery("ljggfwpt:认真学习");
        QueryResponse queryResponse = httpSolrServer.query(query);
        SolrDocumentList documentList = queryResponse.getResults();
        for (SolrDocument document : documentList) {
            System.out.println(document);
        }
        return queryResponse.getBeans(Person.class);
    }
}
