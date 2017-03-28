package com.yx.service;

import com.yx.model.Person;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
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
}
