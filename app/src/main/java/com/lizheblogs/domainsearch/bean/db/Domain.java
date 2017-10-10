package com.lizheblogs.domainsearch.bean.db;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "DOMAIN".
 */
public class Domain {

    private Long id;
    private String domain_name;
    private String original;
    private String code;
    private Long update_time;
    private String whois_id;

    public Domain() {
    }

    public Domain(String domain_name) {
        this.domain_name = domain_name;
    }

    public Domain(Long id, String domain_name, String original, String code, Long update_time, String whois_id) {
        this.id = id;
        this.domain_name = domain_name;
        this.original = original;
        this.code = code;
        this.update_time = update_time;
        this.whois_id = whois_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDomain_name() {
        return domain_name;
    }

    public void setDomain_name(String domain_name) {
        this.domain_name = domain_name;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Long update_time) {
        this.update_time = update_time;
    }

    public String getWhois_id() {
        return whois_id;
    }

    public void setWhois_id(String whois_id) {
        this.whois_id = whois_id;
    }

}
