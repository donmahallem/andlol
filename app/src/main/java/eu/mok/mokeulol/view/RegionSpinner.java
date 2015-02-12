/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import eu.m0k.lol.api.model.Region;

public class RegionSpinner extends Spinner {

    private OnItemSelectedListener ITEM_SELECT = new OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    private BaseAdapter ADAPTER = new BaseAdapter() {

        private final Region[] mRegions = new Region[]{
                Region.BR,
                Region.EUNE,
                Region.EUW,
                Region.KR,
                Region.LAN,
                Region.LAS,
                Region.OCE,
                Region.RU,
                Region.TR
        };

        @Override
        public int getCount() {
            return this.mRegions.length;
        }

        @Override
        public Region getItem(int position) {
            return this.mRegions[position];
        }

        @Override
        public long getItemId(int position) {
            return this.getItem(position).ordinal();
        }

        @Override
        public View getView(final int position, final View convertView, final ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_spinner_item, parent, false);
            TextView txtView = (TextView) view.findViewById(android.R.id.text1);
            txtView.setText(getItem(position).name());
            return view;
        }
    };

    public RegionSpinner(Context context) {
        super(context);
        init();
    }

    public RegionSpinner(Context context, int mode) {
        super(context, mode);
        init();
    }

    public RegionSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RegionSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public RegionSpinner(Context context, AttributeSet attrs, int defStyleAttr, int mode) {
        super(context, attrs, defStyleAttr, mode);
        init();
    }

    public RegionSpinner(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, int mode) {
        super(context, attrs, defStyleAttr, defStyleRes, mode);
        init();
    }

    private void init() {
        super.setOnItemSelectedListener(this.ITEM_SELECT);
        this.setAdapter(this.ADAPTER);
    }

    @Override
    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        //super.setOnItemSelectedListener(onItemSelectedListener);
    }

    public static interface OnRegionSelectedListener {
        public void onRegionSelected(Region region);
    }
}
