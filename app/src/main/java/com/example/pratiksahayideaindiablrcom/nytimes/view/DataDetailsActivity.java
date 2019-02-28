package com.example.pratiksahayideaindiablrcom.nytimes.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pratiksahayideaindiablrcom.nytimes.R;
import com.example.pratiksahayideaindiablrcom.nytimes.model.DataItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DataDetailsActivity extends AppCompatActivity {

    @BindView(R.id.img_cover)
    ImageView img_cover;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.tv_abstract)
    TextView tv_abstract;

    @BindView(R.id.tv_author)
    TextView tv_author;

    @BindView(R.id.tv_date)
    TextView tv_date;

    @BindView(R.id.tv_link)
    TextView tv_link;

    DataItem dataItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_details);
        ButterKnife.bind(this);

        dataItem = (DataItem) getIntent().getSerializableExtra("LIST");
        if (dataItem.getMultimedia().size()>0)
        {
            for (int i=0;i<dataItem.getMultimedia().size();i++)
            {
                if (dataItem.getMultimedia().get(i).getFormat().equals("mediumThreeByTwo210"))
                {
                    Picasso.with(DataDetailsActivity.this).load(dataItem.getMultimedia().get(i).getUrl()).into(img_cover);
                    break;
                }
            }
        }
        tv_title.setText(dataItem.getTitle());
        tv_abstract.setText(dataItem.getJsonMemberAbstract());
        tv_author.setText(dataItem.getByline());
        tv_date.setText(dataItem.getPublishedDate());
        tv_link.setText(dataItem.getUrl());
    }
}
