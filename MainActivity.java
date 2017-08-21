package com.example.hocvien.lab02may06;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> namelist = new ArrayList<String>();
    private ListView listView;
    private WeatherAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        namelist.add("Thành phố Hà Nội");
        namelist.add("Thành phố Hồ Chí Minh");
        initview();
    }

    private void initview() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.ic_app);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        listView = (ListView) findViewById(R.id.lv_listview);
        adapter = new WeatherAdapter(this,namelist);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,WeatherDetail.class);
                if(i==0)
                    intent.putExtra("ID","1581130");
                else
                    intent.putExtra("ID","1566083");
                startActivity(intent);
            }
        });
    }
}
