package com.ingram.test.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by vijayarajsekar on 19/2/16.
 */
public class PlayersDetail implements Parcelable {

    private String id;
    private String name;
    private String nationality;


    public PlayersDetail(String _id, String _name, String _nationality) {

        this.id = _id;
        this.name = _name;
        this.nationality = _nationality;
    }

    public String getPlayerId() {
        return id;
    }

    public String getPlayerName() {
        return name;
    }

    public String getPlayerNationality() {
        return nationality;
    }


    public PlayersDetail(Parcel in) {

        String[] data = new String[3];

        in.readStringArray(data);

        this.id = data[0];
        this.name = data[1];
        this.nationality = data[2];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.id, this.name, this.nationality
        });
    }

    public static final Creator CREATOR = new Creator() {
        public PlayersDetail createFromParcel(Parcel in) {
            return new PlayersDetail(in);
        }

        public PlayersDetail[] newArray(int size) {
            return new PlayersDetail[size];
        }
    };
}
