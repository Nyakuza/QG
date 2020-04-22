package com.example.qg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Example {


    private Integer responseCode;
    private List<Result> results;
    private Map<String, Object> additionalProperties;

    public Example(Integer responseCode, List<Result> results, Map<String, Object> additionalProperties) {
        this.responseCode = responseCode;
        this.results = results;
        this.additionalProperties = additionalProperties;
    }


    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
