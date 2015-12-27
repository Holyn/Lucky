package com.dianxun.holyn.lucky.model.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by holyn on 2015/12/27.
 * 最新揭晓
 */
public class NewAnnouncePar implements Parcelable {
    private String term;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.term);
        dest.writeString(this.name);
    }

    public NewAnnouncePar() {
    }

    protected NewAnnouncePar(Parcel in) {
        this.term = in.readString();
        this.name = in.readString();
    }

    public static final Creator<NewAnnouncePar> CREATOR = new Creator<NewAnnouncePar>() {
        public NewAnnouncePar createFromParcel(Parcel source) {
            return new NewAnnouncePar(source);
        }

        public NewAnnouncePar[] newArray(int size) {
            return new NewAnnouncePar[size];
        }
    };
}
