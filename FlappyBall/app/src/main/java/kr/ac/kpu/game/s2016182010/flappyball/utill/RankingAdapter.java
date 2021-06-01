package kr.ac.kpu.game.s2016182010.flappyball.utill;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import kr.ac.kpu.game.s2016182010.flappyball.R;

public class RankingAdapter extends BaseAdapter {

    ArrayList<Pair<String, Integer>> values;
    public void setData(ArrayList<Pair<String, Integer>> values) {
        this.values = values;
    }


    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public Object getItem(int i) {
        return values.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Context context = viewGroup.getContext();
        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.ranking_item, viewGroup, false);
        }

        TextView one = view.findViewById(R.id.lv_r_name);
        TextView two = view.findViewById(R.id.lv_r_value);
        Pair<String, Integer> result = values.get(i);
        one.setText((i + 1) + ". " + result.first);
        two.setText(result.second.toString());

        return view;
    }
}
