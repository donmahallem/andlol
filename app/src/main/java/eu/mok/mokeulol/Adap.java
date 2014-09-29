package eu.mok.mokeulol;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import eu.m0k.lol.api.model.Champion;

/**
 * Created by Don on 25.09.2014.
 */
public class Adap extends BaseAdapter {
    private List<Champion> mChampionList = new ArrayList<Champion>();

    @Override
    public int getCount() {
        return this.mChampionList.size();
    }

    @Override
    public Champion getItem(int position) {
        return this.mChampionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView text = null;
        if (convertView != null && convertView instanceof TextView) {
            text = (TextView) convertView;
        } else {
            text = new TextView(parent.getContext());
        }
        text.setText(this.getItem(position).getTitle());
        return text;
    }
}
