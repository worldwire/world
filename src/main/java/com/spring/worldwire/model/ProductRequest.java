package com.spring.worldwire.model;

import com.spring.worldwire.enums.LanguageEnum;
import com.spring.worldwire.enums.ProductRequestStatusEnum;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

@SuppressWarnings("unused")
public class ProductRequest implements Serializable {

    private static final long serialVersionUID = 2648973495617278934L;

    private Long id;

    private Integer requestType;

    private String keyWord;

    private String title;

    private String content;

    private String mobile;

    private String email;

    private String phone;

    private String website;

    private String wechat;

    private String linkedIn;

    private LanguageEnum languageType;

    private List<LanguageLevel> languageLevel;

    private Long languageId;

    private ProductRequestStatusEnum status;

    private Integer userType;

    private Long userId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRequestType() {
        return requestType;
    }

    public void setRequestType(Integer requestType) {
        this.requestType = requestType;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord == null ? null : keyWord.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    public LanguageEnum getLanguageType() {
        return languageType;
    }

    public void setLanguageType(LanguageEnum languageType) {
        this.languageType = languageType;
    }

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    public ProductRequestStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ProductRequestStatusEnum status) {
        this.status = status;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<LanguageLevel> getLanguageLevel() {
        return languageLevel;
    }

    public void setLanguageLevel(List<LanguageLevel> languageLevel) {
        this.languageLevel = languageLevel;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    /**
     * 克隆一个新语种的翻译
     * @param languageType 转变成的语言类型
     * @return 返回一个新的对象 缺少ID 和需要翻译的对象
     */
    public ProductRequest converFromProduct(LanguageEnum languageType){
        ProductRequest fromProductRequest = new ProductRequest();
        fromProductRequest.mobile = this.mobile;
        fromProductRequest.email = this.email;
        fromProductRequest.languageType = languageType;
        fromProductRequest.phone = this.phone;
        fromProductRequest.status = ProductRequestStatusEnum.INIT;
        fromProductRequest.website = this.website;
        fromProductRequest.userType = this.userType;
        fromProductRequest.requestType = this.requestType;
        fromProductRequest.createTime = new Date();
        fromProductRequest.userId = this.userId;
        fromProductRequest.website = this.website;
        fromProductRequest.wechat = this.wechat;
        fromProductRequest.linkedIn = this.linkedIn;
        fromProductRequest.languageLevel = this.languageLevel;
        if(this.languageId==null){
            fromProductRequest.languageId = this.id;
        }else{
            fromProductRequest.languageId = this.languageId;
        }
        return fromProductRequest;
    }

    @Override
    public String toString() {
        return "ProductRequest{" +
                "id=" + id +
                ", requestType=" + requestType +
                ", keyWord='" + keyWord + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                ", languageType=" + languageType +
                ", languageId=" + languageId +
                ", status=" + status +
                ", userType=" + userType +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }


    public String[] getKeyWordsVeiw(){
        if(keyWord==null){
            return null;
        }
        return keyWord.split(",");

    }

}