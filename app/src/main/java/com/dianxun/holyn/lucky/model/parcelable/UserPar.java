package com.dianxun.holyn.lucky.model.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by holyn on 2015/12/28.
 */
public class UserPar implements Parcelable {

    /**
     * id : 41
     * name : 66
     * pic : 0
     * password : e10adc3949ba59abbe56e057f20f883e
     * tel : 13590604841
     * address : 0
     * demo :
     * userName :
     * companyIds :
     * foodIds :
     * car :
     * companyId : 0
     * buildId : 0
     * buildareaId : 0
     * buildType : 0
     * buildPass : 0
     * buildduty : 0
     * buildDutyTime : 0000-00-00 00:00:00
     * money : 0
     * vote : 0
     * credit : 0
     * createtime : 2015-12-03 20:41:08
     * admin : 0
     * location :
     */

    private String id;
    private String name;
    private String pic;
    private String password;
    private String tel;
    private String address;
    private String demo;
    private String userName;
    private String companyIds;
    private String foodIds;
    private String car;
    private String companyId;
    private String buildId;
    private String buildareaId;
    private String buildType;
    private String buildPass;
    private String buildduty;
    private String buildDutyTime;
    private String money;
    private String vote;
    private String credit;
    private String createtime;
    private String admin;
    private String location;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDemo(String demo) {
        this.demo = demo;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCompanyIds(String companyIds) {
        this.companyIds = companyIds;
    }

    public void setFoodIds(String foodIds) {
        this.foodIds = foodIds;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setBuildId(String buildId) {
        this.buildId = buildId;
    }

    public void setBuildareaId(String buildareaId) {
        this.buildareaId = buildareaId;
    }

    public void setBuildType(String buildType) {
        this.buildType = buildType;
    }

    public void setBuildPass(String buildPass) {
        this.buildPass = buildPass;
    }

    public void setBuildduty(String buildduty) {
        this.buildduty = buildduty;
    }

    public void setBuildDutyTime(String buildDutyTime) {
        this.buildDutyTime = buildDutyTime;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPic() {
        return pic;
    }

    public String getPassword() {
        return password;
    }

    public String getTel() {
        return tel;
    }

    public String getAddress() {
        return address;
    }

    public String getDemo() {
        return demo;
    }

    public String getUserName() {
        return userName;
    }

    public String getCompanyIds() {
        return companyIds;
    }

    public String getFoodIds() {
        return foodIds;
    }

    public String getCar() {
        return car;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getBuildId() {
        return buildId;
    }

    public String getBuildareaId() {
        return buildareaId;
    }

    public String getBuildType() {
        return buildType;
    }

    public String getBuildPass() {
        return buildPass;
    }

    public String getBuildduty() {
        return buildduty;
    }

    public String getBuildDutyTime() {
        return buildDutyTime;
    }

    public String getMoney() {
        return money;
    }

    public String getVote() {
        return vote;
    }

    public String getCredit() {
        return credit;
    }

    public String getCreatetime() {
        return createtime;
    }

    public String getAdmin() {
        return admin;
    }

    public String getLocation() {
        return location;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.pic);
        dest.writeString(this.password);
        dest.writeString(this.tel);
        dest.writeString(this.address);
        dest.writeString(this.demo);
        dest.writeString(this.userName);
        dest.writeString(this.companyIds);
        dest.writeString(this.foodIds);
        dest.writeString(this.car);
        dest.writeString(this.companyId);
        dest.writeString(this.buildId);
        dest.writeString(this.buildareaId);
        dest.writeString(this.buildType);
        dest.writeString(this.buildPass);
        dest.writeString(this.buildduty);
        dest.writeString(this.buildDutyTime);
        dest.writeString(this.money);
        dest.writeString(this.vote);
        dest.writeString(this.credit);
        dest.writeString(this.createtime);
        dest.writeString(this.admin);
        dest.writeString(this.location);
    }

    public UserPar() {
    }

    protected UserPar(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.pic = in.readString();
        this.password = in.readString();
        this.tel = in.readString();
        this.address = in.readString();
        this.demo = in.readString();
        this.userName = in.readString();
        this.companyIds = in.readString();
        this.foodIds = in.readString();
        this.car = in.readString();
        this.companyId = in.readString();
        this.buildId = in.readString();
        this.buildareaId = in.readString();
        this.buildType = in.readString();
        this.buildPass = in.readString();
        this.buildduty = in.readString();
        this.buildDutyTime = in.readString();
        this.money = in.readString();
        this.vote = in.readString();
        this.credit = in.readString();
        this.createtime = in.readString();
        this.admin = in.readString();
        this.location = in.readString();
    }

    public static final Creator<UserPar> CREATOR = new Creator<UserPar>() {
        public UserPar createFromParcel(Parcel source) {
            return new UserPar(source);
        }

        public UserPar[] newArray(int size) {
            return new UserPar[size];
        }
    };
}
