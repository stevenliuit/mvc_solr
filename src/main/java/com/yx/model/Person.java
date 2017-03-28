package com.yx.model;

import org.apache.solr.client.solrj.beans.Field;

public class Person {

    @Field
    private String id;
    @Field
    private String name;

    @Field
    private String text_ik;

    public Person() {
    }

    public Person(String id, String name, String text_ik) {
        this.id = id;
        this.name = name;
        this.text_ik = text_ik;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText_ik() {
        return text_ik;
    }

    public void setText_ik(String text_ik) {
        this.text_ik = text_ik;
    }
}
