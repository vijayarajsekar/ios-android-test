package com.ingram.test.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ingram.test.Model.PlayersDetail;
import com.ingram.test.R;

import java.util.List;

/**
 * Created by vijayarajsekar on 19/2/16.
 */
public class PlayerAdapter extends BaseAdapter {

    private Context mContext;

    private ViewHolder holder;
    private LayoutInflater l_Inflater;
    private List<PlayersDetail> mPlayerList = null;

    public PlayerAdapter(Context context, List<PlayersDetail> mList) {

        this.mContext = context;
        l_Inflater = LayoutInflater.from(mContext);
        this.mPlayerList = mList;
    }

    @Override
    public int getCount() {
        return this.mPlayerList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        if (convertView == null) {

            holder = new ViewHolder();

            convertView = l_Inflater.inflate(R.layout.players_row_items, parent, false);

            holder = new ViewHolder();

            holder.mPlayerID = (TextView) convertView.findViewById(R.id.text_p_id);
            holder.mPlayerName = (TextView) convertView.findViewById(R.id.text_p_name);
            holder.mPlayerNationality = (TextView) convertView.findViewById(R.id.text_p_nationality);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mPlayerID.setText(mPlayerList.get(position).getPlayerId());
        holder.mPlayerName.setText(mPlayerList.get(position).getPlayerName());
        holder.mPlayerNationality.setText(mPlayerList.get(position).getPlayerNationality());

        return convertView;
    }

    private static class ViewHolder {
        private TextView mPlayerID;
        private TextView mPlayerName;
        private TextView mPlayerNationality;
    }
}