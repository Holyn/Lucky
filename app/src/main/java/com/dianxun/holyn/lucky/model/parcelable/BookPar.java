package com.dianxun.holyn.lucky.model.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by holyn on 2016/1/25.
 */
public class BookPar implements Parcelable{

    /**
     * id : 1
     * companyName : 测试公司
     * companyTel : 13630015207
     * count : 3799
     * show : Apple ipad min4 64G wifI版*1
     * companyId : 1
     * roomId : 0
     * name : rrr
     * tel : 13670617582
     * address : fd
     * demo :
     * rand : 0
     * time : 2015-12-16 05:18:17
     * status : 0
     * userName : 13670617582
     * admin : 0
     */

    private String id;
    private String companyName;
    private String companyTel;
    private String count;
    private String show;
    private String companyId;
    private String roomId;
    private String name;
    private String tel;
    private String address;
    private String demo;
    private String rand;
    private String time;
    private String status;
    private String userName;
    private String admin;

    public void setId(String id) {
        this.id = id;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setRand(String rand) {
        this.rand = rand;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public String getCount() {
        return count;
    }

    public String getShow() {
        return show;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getName() {
        return name;
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

    public String getRand() {
        return rand;
    }

    public String getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }

    public String getUserName() {
        return userName;
    }

    public String getAdmin() {
        return admin;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.companyName);
        dest.writeString(this.companyTel);
        dest.writeString(this.count);
        dest.writeString(this.show);
        dest.writeString(this.companyId);
        dest.writeString(this.roomId);
        dest.writeString(this.name);
        dest.writeString(this.tel);
        dest.writeString(this.address);
        dest.writeString(this.demo);
        dest.writeString(this.rand);
        dest.writeString(this.time);
        dest.writeString(this.status);
        dest.writeString(this.userName);
        dest.writeString(this.admin);
    }

    public BookPar() {
    }

    protected BookPar(Parcel in) {
        this.id = in.readString();
        this.companyName = in.readString();
        this.companyTel = in.readString();
        this.count = in.readString();
        this.show = in.readString();
        this.companyId = in.readString();
        this.roomId = in.readString();
        this.name = in.readString();
        this.tel = in.readString();
        this.address = in.readString();
        this.demo = in.readString();
        this.rand = in.readString();
        this.time = in.readString();
        this.status = in.readString();
        this.userName = in.readString();
        this.admin = in.readString();
    }

    public static final Creator<BookPar> CREATOR = new Creator<BookPar>() {
        public BookPar createFromParcel(Parcel source) {
            return new BookPar(source);
        }

        public BookPar[] newArray(int size) {
            return new BookPar[size];
        }
    };
}
