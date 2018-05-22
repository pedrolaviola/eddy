package com.pedrolaviola.user.eddy.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pedrolaviola.user.eddy.R;
import com.pedrolaviola.user.eddy.models.Feed;

import java.util.List;

/**
 * Created by User on 30/10/2017.
 */

public class FeedAdapter extends BaseAdapter {
    private Context context;
    private List<Feed> lista;

    public FeedAdapter(Context context, List<Feed> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.item_list,null);
        TextView titulo = (TextView) v.findViewById(R.id.txr_title_feedID);
        TextView data = (TextView) v.findViewById(R.id.txt_mes_feedID);

        titulo.setText(lista.get(i).getTitulo());
        data.setText(lista.get(i).getData());

        v.setTag(lista.get(i).getId());
        return v;
    }
}
