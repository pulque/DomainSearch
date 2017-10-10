package com.lizheblogs.domainsearch.bean;

import com.lizheblogs.domainsearch.bean.db.Whois;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by LiZhe on 2017-09-29.
 * com.lizheblogs.domainsearch
 * 接口返回部分信息模板，详细参见
 * https://help.aliyun.com/document_detail/42888.html?spm=5176.doc42886.6.593.3RbxGh
 */

public class WhoisInfo {
    @XStreamAlias("DomainName")
    private String DomainName;//域名
    @XStreamAlias("Registrar")
    private String Registrar;//注册商

    @XStreamAlias("CreationDate")
    private String CreationDate;//注册日期
    @XStreamAlias("ExpirationDate")
    private String ExpirationDate;//到期日期
    @XStreamAlias("UpdatedDate")
    private String UpdatedDate;//更新日期

    @XStreamAlias("RegistrantName")
    private String RegistrantName;//注册人姓名
    @XStreamAlias("RegistrantCountry")
    private String RegistrantCountry;//注册人国家
    @XStreamAlias("RegistrantPhone")
    private String RegistrantPhone;//注册人电话

    @XStreamAlias("AdminName")
    private String AdminName;//管理联系人姓名
    @XStreamAlias("AdminPhone")
    private String AdminPhone;//管理联系人电话

    @XStreamAlias("TechName")
    private String TechName;//技术联系人姓名
    @XStreamAlias("TechPhone")
    private String TechPhone;//技术联系人电话

    @XStreamAlias("OriginalInfo")
    private String OriginalInfo;//原始信息

    public static WhoisInfo copy(Whois mWhois) {
        WhoisInfo mWhoisInfo = new WhoisInfo();
        mWhoisInfo.DomainName = mWhois.getDomain_name();
        mWhoisInfo.Registrar = mWhois.getRegistrar();
        mWhoisInfo.CreationDate = mWhois.getCreation_date();
        mWhoisInfo.ExpirationDate = mWhois.getExpiration_date();
        mWhoisInfo.UpdatedDate = mWhois.getUpdated_date();
        mWhoisInfo.RegistrantName = mWhois.getRegistrant_name();
        mWhoisInfo.RegistrantCountry = mWhois.getRegistrant_country();
        mWhoisInfo.RegistrantPhone = mWhois.getRegistrant_phone();
        mWhoisInfo.AdminName = mWhois.getAdmin_name();
        mWhoisInfo.AdminPhone = mWhois.getAdmin_phone();
        mWhoisInfo.TechName = mWhois.getTech_name();
        mWhoisInfo.TechPhone = mWhois.getTech_phone();
        mWhoisInfo.OriginalInfo = mWhois.getOriginal_info();
        return mWhoisInfo;
    }

    public String getDomainName() {
        return DomainName;
    }

    public void setDomainName(String domainName) {
        DomainName = domainName;
    }

    public String getRegistrar() {
        return Registrar;
    }

    public void setRegistrar(String registrar) {
        Registrar = registrar;
    }

    public String getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(String creationDate) {
        CreationDate = creationDate;
    }

    public String getExpirationDate() {
        return ExpirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        ExpirationDate = expirationDate;
    }

    public String getUpdatedDate() {
        return UpdatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        UpdatedDate = updatedDate;
    }

    public String getRegistrantName() {
        return RegistrantName;
    }

    public void setRegistrantName(String registrantName) {
        RegistrantName = registrantName;
    }

    public String getRegistrantCountry() {
        return RegistrantCountry;
    }

    public void setRegistrantCountry(String registrantCountry) {
        RegistrantCountry = registrantCountry;
    }

    public String getRegistrantPhone() {
        return RegistrantPhone;
    }

    public void setRegistrantPhone(String registrantPhone) {
        RegistrantPhone = registrantPhone;
    }

    public String getAdminName() {
        return AdminName;
    }

    public void setAdminName(String adminName) {
        AdminName = adminName;
    }

    public String getAdminPhone() {
        return AdminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        AdminPhone = adminPhone;
    }

    public String getTechName() {
        return TechName;
    }

    public void setTechName(String techName) {
        TechName = techName;
    }

    public String getTechPhone() {
        return TechPhone;
    }

    public void setTechPhone(String techPhone) {
        TechPhone = techPhone;
    }

    public String getOriginalInfo() {
        return OriginalInfo;
    }

    public void setOriginalInfo(String originalInfo) {
        OriginalInfo = originalInfo;
    }

    @Override
    public String toString() {
        return "WhoisInfo{" +
                "DomainName='" + DomainName + '\'' +
                ", Registrar='" + Registrar + '\'' +
                ", CreationDate='" + CreationDate + '\'' +
                ", ExpirationDate='" + ExpirationDate + '\'' +
                ", UpdatedDate='" + UpdatedDate + '\'' +
                ", RegistrantName='" + RegistrantName + '\'' +
                ", RegistrantCountry='" + RegistrantCountry + '\'' +
                ", RegistrantPhone='" + RegistrantPhone + '\'' +
                ", AdminName='" + AdminName + '\'' +
                ", AdminPhone='" + AdminPhone + '\'' +
                ", TechName='" + TechName + '\'' +
                ", TechPhone='" + TechPhone + '\'' +
                ", OriginalInfo='" + OriginalInfo + '\'' +
                '}';
    }
}
