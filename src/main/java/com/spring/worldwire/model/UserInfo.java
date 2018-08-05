package com.spring.worldwire.model;

import com.spring.worldwire.enums.UserTypeEnum;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("unused")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 4091704691107509862L;

    private Long id;

    private Long loginId;

    private UserTypeEnum type;

    private Integer isForeign;

    private String showImg;

    private Date birthday;

    private String nickName;

    private List<LanguageLevel> language;

    private String briNationality;

    private String nowNationality;

    private String localAddr;

    private String occupation;

    private String email;

    private String mobile;

    private String website;

    private String publicPlatform;

    private String wechatQrcode;

    private String wechat;

    private String facebook;

    private String whatsapp;

    private String linkedin;

    private List<Communication> communication;

    private String idcardOnImg;

    private String idcardDownImg;

    private String idcardHandImg;

    private String realName;

    private String idcard;

    private String companyName;

    private Date companyTime;

    private String companyNum;

    private String companyAddr;

    private String companyTel;

    private String companyMobile;

    private String companyPostCode;

    private String companyFax;

    private String companyEmail;

    private String companyMainBusiness;

    private String companyMainContry;

    private String businessLicenseImg;

    private String businessSpecialLicenseImg;

    private String businessBank;

    private String businessBankName;

    private String businessBankAddr;

    private String businessBankImg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    public UserTypeEnum getType() {
        return type;
    }

    public void setType(UserTypeEnum type) {
        this.type = type;
    }

    public Integer getIsForeign() {
        return isForeign;
    }

    public void setIsForeign(Integer isForeign) {
        this.isForeign = isForeign;
    }

    public String getShowImg() {
        return showImg;
    }

    public void setShowImg(String showImg) {
        this.showImg = showImg == null ? null : showImg.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public List<LanguageLevel> getLanguage() {
        return language;
    }

    public void setLanguage(List<LanguageLevel> language) {
        this.language = language;
    }

    public String getBriNationality() {
        return briNationality;
    }

    public void setBriNationality(String briNationality) {
        this.briNationality = briNationality == null ? null : briNationality.trim();
    }

    public String getNowNationality() {
        return nowNationality;
    }

    public void setNowNationality(String nowNationality) {
        this.nowNationality = nowNationality == null ? null : nowNationality.trim();
    }

    public String getLocalAddr() {
        return localAddr;
    }

    public void setLocalAddr(String localAddr) {
        this.localAddr = localAddr == null ? null : localAddr.trim();
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation == null ? null : occupation.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    public String getPublicPlatform() {
        return publicPlatform;
    }

    public void setPublicPlatform(String publicPlatform) {
        this.publicPlatform = publicPlatform == null ? null : publicPlatform.trim();
    }

    public String getWechatQrcode() {
        return wechatQrcode;
    }

    public void setWechatQrcode(String wechatQrcode) {
        this.wechatQrcode = wechatQrcode == null ? null : wechatQrcode.trim();
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat == null ? null : wechat.trim();
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook == null ? null : facebook.trim();
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp == null ? null : whatsapp.trim();
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin == null ? null : linkedin.trim();
    }

    public String getIdcardOnImg() {
        return idcardOnImg;
    }

    public void setIdcardOnImg(String idcardOnImg) {
        this.idcardOnImg = idcardOnImg == null ? null : idcardOnImg.trim();
    }

    public String getIdcardDownImg() {
        return idcardDownImg;
    }

    public void setIdcardDownImg(String idcardDownImg) {
        this.idcardDownImg = idcardDownImg == null ? null : idcardDownImg.trim();
    }

    public String getIdcardHandImg() {
        return idcardHandImg;
    }

    public void setIdcardHandImg(String idcardHandImg) {
        this.idcardHandImg = idcardHandImg == null ? null : idcardHandImg.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public Date getCompanyTime() {
        return companyTime;
    }

    public void setCompanyTime(Date companyTime) {
        this.companyTime = companyTime;
    }

    public String getCompanyNum() {
        return companyNum;
    }

    public void setCompanyNum(String companyNum) {
        this.companyNum = companyNum == null ? null : companyNum.trim();
    }

    public String getCompanyAddr() {
        return companyAddr;
    }

    public void setCompanyAddr(String companyAddr) {
        this.companyAddr = companyAddr == null ? null : companyAddr.trim();
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel == null ? null : companyTel.trim();
    }

    public String getCompanyMobile() {
        return companyMobile;
    }

    public void setCompanyMobile(String companyMobile) {
        this.companyMobile = companyMobile == null ? null : companyMobile.trim();
    }

    public String getCompanyPostCode() {
        return companyPostCode;
    }

    public void setCompanyPostCode(String companyPostCode) {
        this.companyPostCode = companyPostCode == null ? null : companyPostCode.trim();
    }

    public String getCompanyFax() {
        return companyFax;
    }

    public void setCompanyFax(String companyFax) {
        this.companyFax = companyFax == null ? null : companyFax.trim();
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail == null ? null : companyEmail.trim();
    }

    public String getCompanyMainBusiness() {
        return companyMainBusiness;
    }

    public void setCompanyMainBusiness(String companyMainBusiness) {
        this.companyMainBusiness = companyMainBusiness == null ? null : companyMainBusiness.trim();
    }

    public String getCompanyMainContry() {
        return companyMainContry;
    }

    public void setCompanyMainContry(String companyMainContry) {
        this.companyMainContry = companyMainContry == null ? null : companyMainContry.trim();
    }

    public String getBusinessLicenseImg() {
        return businessLicenseImg;
    }

    public void setBusinessLicenseImg(String businessLicenseImg) {
        this.businessLicenseImg = businessLicenseImg == null ? null : businessLicenseImg.trim();
    }

    public String getBusinessSpecialLicenseImg() {
        return businessSpecialLicenseImg;
    }

    public void setBusinessSpecialLicenseImg(String businessSpecialLicenseImg) {
        this.businessSpecialLicenseImg = businessSpecialLicenseImg == null ? null : businessSpecialLicenseImg.trim();
    }

    public String getBusinessBank() {
        return businessBank;
    }

    public void setBusinessBank(String businessBank) {
        this.businessBank = businessBank == null ? null : businessBank.trim();
    }

    public String getBusinessBankName() {
        return businessBankName;
    }

    public void setBusinessBankName(String businessBankName) {
        this.businessBankName = businessBankName == null ? null : businessBankName.trim();
    }

    public String getBusinessBankAddr() {
        return businessBankAddr;
    }

    public void setBusinessBankAddr(String businessBankAddr) {
        this.businessBankAddr = businessBankAddr == null ? null : businessBankAddr.trim();
    }

    public String getBusinessBankImg() {
        return businessBankImg;
    }

    public void setBusinessBankImg(String businessBankImg) {
        this.businessBankImg = businessBankImg == null ? null : businessBankImg.trim();
    }

    public List<Communication> getCommunication() {
        return communication;
    }

    public void setCommunication(List<Communication> communication) {
        this.communication = communication;
    }

    @Override
    public String toString() {
        return "UserInfo [id=" + id + ", loginId=" + loginId + ", type=" + type + ", isForeign=" + isForeign
                + ", showImg=" + showImg + ", birthday=" + birthday + ", nickName=" + nickName + ", language="
                + language + ", briNationality=" + briNationality + ", nowNationality=" + nowNationality
                + ", localAddr=" + localAddr + ", occupation=" + occupation + ", email=" + email + ", mobile=" + mobile
                + ", website=" + website + ", publicPlatform=" + publicPlatform + ", wechatQrcode=" + wechatQrcode
                + ", wechat=" + wechat + ", facebook=" + facebook + ", whatsapp=" + whatsapp + ", linkedin=" + linkedin
                + ", communication=" + communication + ", idcardOnImg=" + idcardOnImg + ", idcardDownImg="
                + idcardDownImg + ", idcardHandImg=" + idcardHandImg + ", realName=" + realName + ", idcard=" + idcard
                + ", companyName=" + companyName + ", companyTime=" + companyTime + ", companyNum=" + companyNum
                + ", companyAddr=" + companyAddr + ", companyTel=" + companyTel + ", companyMobile=" + companyMobile
                + ", companyPostCode=" + companyPostCode + ", companyFax=" + companyFax + ", companyEmail="
                + companyEmail + ", companyMainBusiness=" + companyMainBusiness + ", companyMainContry="
                + companyMainContry + ", businessLicenseImg=" + businessLicenseImg + ", businessSpecialLicenseImg="
                + businessSpecialLicenseImg + ", businessBank=" + businessBank + ", businessBankName="
                + businessBankName + ", businessBankAddr=" + businessBankAddr + ", businessBankImg=" + businessBankImg
                + "]";
    }

    private static String BLANK_STRING = "null";


    public String cookiesValue() {

        return String.join("$$", id.toString(), type != null ? type.getCode()+"" : BLANK_STRING, StringUtils.isNotEmpty(nickName) ? nickName : BLANK_STRING, StringUtils.isNotEmpty(showImg)  ? showImg : BLANK_STRING);
    }

    public void analysisCookiesValue(String cookiesValue) {
        if (cookiesValue.contains("$$")) {
            String[] split = cookiesValue.split("\\$\\$");
            setId(Long.parseLong(split[0]));
            if (!BLANK_STRING.equals(split[1])) {
                setType(UserTypeEnum.getNameByCode(Integer.parseInt(split[1])));
            }
            if (!BLANK_STRING.equals(split[2])) {
                setNickName(split[2]);
            }
            if (!BLANK_STRING.equals(split[3])) {
                setShowImg(split[3]);
            }

        }
    }

}