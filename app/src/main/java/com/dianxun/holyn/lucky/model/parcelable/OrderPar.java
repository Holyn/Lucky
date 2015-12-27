package com.dianxun.holyn.lucky.model.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by holyn on 2015/12/27.
 * 晒单
 */
public class OrderPar implements Parcelable {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
    }

    public OrderPar() {
    }

    protected OrderPar(Parcel in) {
        this.title = in.readString();
    }

    public static final Creator<OrderPar> CREATOR = new Creator<OrderPar>() {
        public OrderPar createFromParcel(Parcel source) {
            return new OrderPar(source);
        }

        public OrderPar[] newArray(int size) {
            return new OrderPar[size];
        }
    };
}
