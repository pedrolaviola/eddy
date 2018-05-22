package com.pedrolaviola.user.eddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pedrolaviola.user.eddy.models.Feed;

public class FeedDetailActivity extends AppCompatActivity {
    private TextView titulo,data,desc;
    private Feed feed;
    private Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_detail);
        feed =(Feed) getIntent().getSerializableExtra("Feed");
        titulo = (TextView)findViewById(R.id.text_feed_titleID);
        btn_back = (Button)findViewById(R.id.btn_backID);
        data = (TextView)findViewById(R.id.text_feed_dataID);
        desc = (TextView)findViewById(R.id.text_feed_descricaoID);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        titulo.setText(feed.getTitulo());
        data.setText(feed.getData());
        desc.setText(feed.getDescricao());
    }
}
