package com.dianxun.holyn.lucky.model.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by holyn on 2015/12/24.
 */
public class BidRecordPar implements Parcelable {


    /**
     * id : 44
     * name : holyn
     * pic : 1449847686.jpg
     * pics :
     * address : 广东省佛山市
     * count : 4
     * ip : 192.168.1.1
     * time : 2015-12-24 20:52:04
     */

    private String id;
    private String name;
    private String pic;
    private String pics;
    private String address;
    private String count;
    private String ip;
    private String time;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getPics() {
        return pics;
    }

    public String getAddress() {
        return address;
    }

    public String getCount() {
        return count;
    }

    public String getIp() {
        return ip;
    }

    public String getTime() {
        return time;
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
        dest.writeString(this.pics);
        dest.writeString(this.address);
        dest.writeString(this.count);
        dest.writeString(this.ip);
        dest.writeString(this.time);
    }

    public BidRecordPar() {
    }

    protected BidRecordPar(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.pic = in.readString();
        this.pics = in.readString();
        this.address = in.readString();
        this.count = in.readString();
        this.ip = in.readString();
        this.time = in.readString();
    }

    public static final Creator<BidRecordPar> CREATOR = new Creator<BidRecordPar>() {
        public BidRecordPar createFromParcel(Parcel source) {
            return new BidRecordPar(source);
        }

        public BidRecordPar[] newArray(int size) {
            return new BidRecordPar[size];
        }
    };
}
