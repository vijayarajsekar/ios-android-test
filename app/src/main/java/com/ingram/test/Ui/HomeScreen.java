package com.ingram.test.Ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.ingram.test.Adapter.FootbalAdapter;
import com.ingram.test.Interfaces.API_Request;
import com.ingram.test.Interfaces.API_Response;
import com.ingram.test.Model.TeamDetails;
import com.ingram.test.Preferences.AppPreference;
import com.ingram.test.R;
import com.ingram.test.Utils.ApiConstants;
import com.ingram.test.Utils.InternetConnection;
import com.ingram.test.Utils.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vijayarajsekar on 19/2/16.
 */

public class HomeScreen extends AppCompatActivity implements ApiConstants {

    private String TAG = HomeScreen.class.getSimpleName();

    private Toolbar mToolbar;

    private RecyclerView mTeamsList;
    private RecyclerView.LayoutManager mLayoutManager;

    private FootbalAdapter mFootbalAdapter;

    private JSONObject mResult;
    private JSONObject mSingleObject;
    private JSONArray mMatchResult;

    private AppPreference mPref;

    private List<TeamDetails> mDetails;

    private String mSelfhref;
    private String mSoccerseasonhref;

    private String mHomeTeamName;
    private String mAwayTeamName;

    private String mHomeTeamhref;
    private String mAwayTeamhref;

    private String mDate;
    private String mStatus;
    private String mMatchday;

    private String mHomeTeamGoal;
    private String mAwayTeamGoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPref = new AppPreference(this);

        mTeamsList = (RecyclerView) findViewById(R.id.listView);
        mTeamsList.setHasFixedSize(true);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.str_match_details));

        setSupportActionBar(mToolbar);

        mDetails = new ArrayList<TeamDetails>();

        mLayoutManager = new LinearLayoutManager(this);
        mTeamsList.setLayoutManager(mLayoutManager);
        mTeamsList.setItemAnimator(new DefaultItemAnimator());

        if (!mPref.getIsReadOnline()) {

            if (new InternetConnection(this).isConnectingToInternet()) {

                API_Request.instance().GetMatchDetails(new API_Response() {

                    @Override
                    public void HandleResponse(int type, String Response) {
                        ParseResponse(type, Response);
                    }
                });

            } else {
                Logger.NoInternet(this);
            }

        } else {

            ParseResponse(ApiConstants.REQ_DATA_URL, mPref.getMatchDetail());
        }
    }

    private void ParseResponse(int type, String response) {

        if (type == ApiConstants.REQ_DATA_URL) {

            try {

                mResult = new JSONObject(response);

                if (mResult != null && mResult.length() != 0) {

                    mPref.setIsReadOnline(true);
                    mPref.setMatchDetails(mResult.toString());

                    mMatchResult = mResult.getJSONArray(FIXTURES);

                    for (int x = 0; x < mMatchResult.length(); x++) {

                        mSingleObject = mMatchResult.getJSONObject(x);

                        mSelfhref = mSingleObject.getJSONObject(LINKS).getJSONObject(LINKS_SELF).getString(LINKS_HREF);
                        mSoccerseasonhref = mSingleObject.getJSONObject(LINKS).getJSONObject(LINKS_SOCCERSEASON).getString(LINKS_HREF);

                        mHomeTeamhref = mSingleObject.getJSONObject(LINKS).getJSONObject(LINKS_HOMETEAM).getString(LINKS_HREF);
                        mAwayTeamhref = mSingleObject.getJSONObject(LINKS).getJSONObject(LINKS_AWAYTEAM).getString(LINKS_HREF);

                        mDate = mSingleObject.getString(DATE);
                        mStatus = mSingleObject.getString(STATUS);
                        mMatchday = mSingleObject.getString(MATCHDAY);

                        mHomeTeamName = mSingleObject.getString(HOMETEAM_NAME);
                        mAwayTeamName = mSingleObject.getString(AWAYTEAM_NAME);

                        mHomeTeamGoal = mSingleObject.getJSONObject(RESULT).getString(RESULT_GOALS_HOMETEAM);
                        mAwayTeamGoal = mSingleObject.getJSONObject(RESULT).getString(RESULT_GOALS_AWAYTEAM);

                        mDetails.add(new TeamDetails(mSelfhref, mSoccerseasonhref, mHomeTeamhref, mAwayTeamhref, mDate, mStatus, mMatchday, mHomeTeamName, mAwayTeamName,
                                mHomeTeamGoal, mAwayTeamGoal));
                    }

                    Logger.Print(TAG, "" + mDetails.size());

                    if (mDetails.size() != 0) {
                        mFootbalAdapter = new FootbalAdapter(this, mDetails);
                        mTeamsList.setAdapter(mFootbalAdapter);
                        mFootbalAdapter.notifyDataSetChanged();
                    }

                } else {
                    Logger.ShowToast(getResources().getString(R.string.str_no_data));
                }

            } catch (JSONException ex) {
                Logger.Print(TAG, ex.getMessage().toString());
            }

        } else {
            Logger.ShowToast(getResources().getString(R.string.str_invalidreqs));
        }
    }
}
