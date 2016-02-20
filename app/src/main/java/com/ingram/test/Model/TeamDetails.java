package com.ingram.test.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by vijayarajsekar on 19/2/16.
 */
public class TeamDetails implements Parcelable {

    private String mSelfhref;
    private String mSoccerseasonhref;

    private String mHomeTeamhref;
    private String mAwayTeamhref;

    private String mDate;
    private String mStatus;
    private String mMatchday;

    private String mHomeTeamName;
    private String mAwayTeamName;

    private String mHomeTeamGoal;
    private String mAwayTeamGoal;

    public TeamDetails(String _selfhref, String _socfhref, String _homehref, String _awayhref, String _date, String _status, String _matchday,
                       String _hometeamname, String _awayteamname, String _htgoal, String _awgoal) {

        this.mSelfhref = _selfhref;
        this.mSoccerseasonhref = _socfhref;

        this.mHomeTeamhref = _homehref;
        this.mAwayTeamhref = _awayhref;

        this.mDate = _date;
        this.mStatus = _status;
        this.mMatchday = _matchday;

        this.mHomeTeamName = _hometeamname;
        this.mAwayTeamName = _awayteamname;

        this.mHomeTeamGoal = _htgoal;
        this.mAwayTeamGoal = _awgoal;
    }

    public String getSelfhref() {
        return mSelfhref;
    }

    public String getSoccerseasonhref() {
        return mSoccerseasonhref;
    }

    public String getHomeTeamhref() {
        return mHomeTeamhref;
    }

    public String getAwayTeamhref() {
        return mAwayTeamhref;
    }

    public String getDate() {
        return mDate;
    }

    public String getStatus() {
        return mStatus;
    }

    public String getMatchday() {
        return mMatchday;
    }

    public String getHomeTeamName() {
        return mHomeTeamName;
    }

    public String getAwayTeamName() {
        return mAwayTeamName;
    }

    public String getHomeTeamGoal() {
        return mHomeTeamGoal;
    }

    public String getAwayTeamGoal() {
        return mAwayTeamGoal;
    }

    public TeamDetails(Parcel in) {

        String[] data = new String[10];

        in.readStringArray(data);

        this.mSelfhref = data[0];
        this.mSoccerseasonhref = data[1];

        this.mHomeTeamhref = data[2];
        this.mAwayTeamhref = data[3];

        this.mDate = data[4];
        this.mStatus = data[5];
        this.mMatchday = data[6];

        this.mHomeTeamName = data[7];
        this.mAwayTeamName = data[8];

        this.mHomeTeamGoal = data[9];
        this.mAwayTeamGoal = data[10];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.mSelfhref, this.mSoccerseasonhref, this.mHomeTeamhref, this.mAwayTeamhref, this.mDate, this.mStatus, this.mMatchday,
                this.mHomeTeamName, this.mAwayTeamName, this.mHomeTeamGoal, this.mAwayTeamGoal
        });
    }

    public static final Creator CREATOR = new Creator() {
        public TeamDetails createFromParcel(Parcel in) {
            return new TeamDetails(in);
        }

        public TeamDetails[] newArray(int size) {
            return new TeamDetails[size];
        }
    };
}
