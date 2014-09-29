package eu.mok.mokeulol.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import eu.m0k.lol.api.model.Champion;
import eu.m0k.lol.api.model.ChampionList;
import eu.mok.mokeulol.view.ChampionListItem;

/**
 * Created by Don on 25.09.2014.
 */
public class ChampionAdapter extends BaseAdapter {
    private ChampionList mChampionList = new ChampionList();

    /**
     * replaces the current champion list
     *
     * @param championList the new champion list
     */
    public void setChampionList(ChampionList championList) {
        if (championList != null) {
            this.mChampionList.clear();
            this.mChampionList.addAll(championList);
            this.notifyDataSetChanged();
        }
    }

    /**
     * removes all champions from the list
     */
    public void clear() {
        this.mChampionList.clear();
        this.notifyDataSetChanged();
    }

    /**
     * adds a champion to the list
     *
     * @param champion the champion to be added
     */
    public void addChampion(Champion champion) {
        if (champion != null) {
            this.mChampionList.add(champion);
            this.notifyDataSetChanged();
        }
    }

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
        ChampionListItem item = null;
        if (convertView != null && convertView instanceof TextView) {
            item = (ChampionListItem) convertView;
        } else {
            item = new ChampionListItem(parent.getContext());
        }
        item.setChampion(this.getItem(position));
        return item;
    }
}
