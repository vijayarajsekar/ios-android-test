package com.ingram.test.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ingram.test.Interfaces.API_Request;
import com.ingram.test.Interfaces.API_Response;
import com.ingram.test.Model.PlayersDetail;
import com.ingram.test.Model.TeamDetails;
import com.ingram.test.R;
import com.ingram.test.Utils.ApiConstants;
import com.ingram.test.Utils.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by vijayarajsekar on 19/2/16.
 */

public class FootbalAdapter extends RecyclerView.Adapter<FootbalAdapter.MatchViewHolder> {

    private String TAG = FootbalAdapter.class.getSimpleName();

    private List<TeamDetails> mData = Collections.emptyList();

    private LayoutInflater inflater;
    private Context context;

    private DetailsDialoug mDialog;

    public FootbalAdapter(Context ctx, List<TeamDetails> data) {
        this.context = ctx;
        inflater = LayoutInflater.from(context);
        this.mData = data;
    }

    public FootbalAdapter(Context ctx) {
        this.context = ctx;
    }

    @Override
    public MatchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_row_items, parent, false);
        MatchViewHolder holder = new MatchViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MatchViewHolder holder, int position) {

        final TeamDetails mDetails = mData.get(position);

        holder.mHomeTeamName.setText(mDetails.getHomeTeamName());
        holder.mAwayTeamName.setText(mDetails.getAwayTeamName());

        holder.mStatus.setText(mDetails.getStatus());
        holder.mDate.setText(mDetails.getDate());

        holder.mHomeTeamGoal.setText(mDetails.getHomeTeamGoal());
        holder.mAwayTeamGoal.setText(mDetails.getAwayTeamGoal());

        holder.mHomeTeamName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.ShowToast(mDetails.getHomeTeamhref());
                ShowDetailDialoug(mDetails.getHomeTeamhref());
            }
        });

        holder.mAwayTeamName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.ShowToast(mDetails.getAwayTeamhref());
                ShowDetailDialoug(mDetails.getAwayTeamhref());
            }
        });

        holder.mHomeTeamTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.ShowToast(mDetails.getHomeTeamhref());
                ShowDetailDialoug(mDetails.getHomeTeamhref());
            }
        });

        holder.mAwayTeamTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.ShowToast(mDetails.getAwayTeamhref());
                ShowDetailDialoug(mDetails.getAwayTeamhref());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MatchViewHolder extends RecyclerView.ViewHolder {

        private TextView mHomeTeamTitle;
        private TextView mAwayTeamTitle;

        private TextView mHomeTeamName;
        private TextView mAwayTeamName;

        private TextView mStatus;
        private TextView mDate;

        private TextView mHomeTeamGoal;
        private TextView mAwayTeamGoal;

        public MatchViewHolder(View itemView) {

            super(itemView);

            mHomeTeamTitle = (TextView) itemView.findViewById(R.id.title_htname);
            mAwayTeamTitle = (TextView) itemView.findViewById(R.id.title_atname);

            mHomeTeamName = (TextView) itemView.findViewById(R.id.text_htname);
            mAwayTeamName = (TextView) itemView.findViewById(R.id.text_atname);

            mStatus = (TextView) itemView.findViewById(R.id.text_status);
            mDate = (TextView) itemView.findViewById(R.id.text_date);

            mHomeTeamGoal = (TextView) itemView.findViewById(R.id.text_ht_goal);
            mAwayTeamGoal = (TextView) itemView.findViewById(R.id.text_aw_goal);
        }
    }


    private void ShowDetailDialoug(String m_url) {

        mDialog = new DetailsDialoug(context, android.R.style.Theme, m_url);
        mDialog.show();

    }

    public class DetailsDialoug extends Dialog implements ApiConstants {

        private String TAG = DetailsDialoug.class.getSimpleName();

        public Context mContext;

        private TextView mTeamName;
        private TextView mTeamCode;
        private TextView mTeamShortName;
        private TextView mTeamValue;

        private ImageView mTeamLogo;
        private ListView mPlayersList;

        private PlayerAdapter mPlayerAdapter;

        private LinearLayout mProgressLayout;

        private String mTeamUrl;

        private JSONObject mResult;

        private JSONArray mPlayersArray;

        private List<PlayersDetail> mPlayersDetails;

        private String mSelfhref;
        private String mFixtureshref;
        private String mPlayershref;

        private String mName;
        private String mCode;
        private String mShortName;
        private String mMarketValue;
        private String mCrestUrl;

        public DetailsDialoug(Context _ctx, int b, String _url) {
            super(_ctx, b);

            this.mContext = _ctx;
            this.mTeamUrl = _url;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.dialog_details);

            mTeamName = (TextView) findViewById(R.id.text_name);
            mTeamCode = (TextView) findViewById(R.id.text_code);
            mTeamShortName = (TextView) findViewById(R.id.text_short_name);
            mTeamValue = (TextView) findViewById(R.id.text_value);

            mTeamLogo = (ImageView) findViewById(R.id.team_logo);
            mPlayersList = (ListView) findViewById(R.id.list_players);

            mProgressLayout = (LinearLayout) findViewById(R.id.layout_progress);

            mPlayersDetails = new ArrayList<PlayersDetail>();

            API_Request.instance().GetHomeTeam(new API_Response() {
                @Override
                public void HandleResponse(int type, String Response) {
                    ParseResponse(type, Response);
                }
            }, mTeamUrl);
        }

        private void ParseResponse(int type, String response) {

            if (type == ApiConstants.REQ_TEAM_DETAILS) {

                try {

                    mResult = new JSONObject(response);

                    if (mResult != null && mResult.length() != 0) {

                        mSelfhref = mResult.getJSONObject(LINKS).getJSONObject(LINKS_SELF).getString(LINKS_HREF);
                        mFixtureshref = mResult.getJSONObject(LINKS).getJSONObject(FIXTURES).getString(LINKS_HREF);
                        mPlayershref = mResult.getJSONObject(LINKS).getJSONObject(LINKS_PLAYERS).getString(LINKS_HREF);

                        mName = mResult.getString(TEAM_DETAILS_NAME);
                        mCode = mResult.getString(TEAM_DETAILS_CODE);
                        mShortName = mResult.getString(TEAM_DETAILS_SHORTNAME);
                        mMarketValue = mResult.getString(TEAM_DETAILS_SQUADMARKETVALUE);
                        mCrestUrl = mResult.getString(TEAM_DETAILS_CRESTURL);

                        Logger.Print(TAG, mSelfhref + mFixtureshref + mPlayershref + mName + mCode + mShortName + mMarketValue + mCrestUrl);

                        mTeamName.setText(mName);
                        mTeamCode.setText(mCode);
                        mTeamShortName.setText(mShortName);
                        mTeamValue.setText(mMarketValue);

                        // Requesting Player Details //
                        API_Request.instance().GetPlayerDetails(new API_Response() {
                            @Override
                            public void HandleResponse(int type, String Response) {
                                ParseResponse(type, Response);
                            }
                        }, mPlayershref);

                    } else {
                        Logger.ShowToast(mContext.getString(R.string.str_no_data));
                    }

                } catch (JSONException ex) {
                    Logger.Print(TAG, ex.getMessage().toString());
                }

            } else if (type == ApiConstants.REQ_PLAYER_DETAILS) {

                try {

                    mResult = new JSONObject(response);

                    if (mResult != null && mResult.length() != 0) {

                        mPlayersArray = mResult.getJSONArray(LINKS_PLAYERS);

                        for (int i = 0; i < mPlayersArray.length(); i++) {

                            mPlayersDetails.add(new PlayersDetail(mPlayersArray.getJSONObject(i).getString(PLAYER_ID), mPlayersArray.getJSONObject(i).getString(PLAYER_NAME),
                                    mPlayersArray.getJSONObject(i).getString(PLAYER_NATIONALITY)));
                        }

                        if (mPlayersDetails != null && mPlayersDetails.size() != 0) {
                            mPlayerAdapter = new PlayerAdapter(mContext, mPlayersDetails);
                            mPlayersList.setAdapter(mPlayerAdapter);
                            mPlayerAdapter.notifyDataSetChanged();
                        }

                        mProgressLayout.setVisibility(View.GONE);

                    } else {
                        Logger.ShowToast(mContext.getString(R.string.str_no_data));
                    }

                } catch (JSONException ex) {
                    Logger.Print(TAG, ex.getMessage().toString());
                }

            } else {
                Logger.ShowToast(mContext.getString(R.string.str_invalidreqs));
            }
        }
    }
}