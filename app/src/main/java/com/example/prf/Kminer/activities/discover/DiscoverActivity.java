package com.example.prf.Kminer.activities.discover;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.prf.Kminer.R;

public class DiscoverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);

        Button mineBtn = findViewById(R.id.activity_discover_btn_mine);
        final TextView tableTxt = findViewById(R.id.activity_discover_txt_table);
        final TextView clustersTxt = findViewById(R.id.activity_discover_txt_clusters);

        mineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FetchDiscoverDataAsyncTask task =new FetchDiscoverDataAsyncTask(getApplicationContext());

                task.execute(tableTxt.getText().toString(), clustersTxt.getText().toString());
            }
        });




    }
}
