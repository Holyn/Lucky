package com.dianxun.holyn.lucky.model.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by holyn on 2016/1/7.
 */
public class CompanyTypePar implements Parcelable {

    /**
     * id : 3
     * name : 10元专区
     * pic : 1449219535.png
     * isTop : 10
     */

    private String id;
    private String name;
    private String pic;
    private String isTop;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
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

    public String getIsTop() {
        return isTop;
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
        dest.writeString(this.isTop);
    }

    public CompanyTypePar() {
    }

    protected CompanyTypePar(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.pic = in.readString();
        this.isTop = in.readString();
    }

    public static final Creator<CompanyTypePar> CREATOR = new Creator<CompanyTypePar>() {
        public CompanyTypePar createFromParcel(Parcel source) {
            return new CompanyTypePar(source);
        }

        public CompanyTypePar[] newArray(int size) {
            return new CompanyTypePar[size];
        }
    };
}
