package com.example.hocvien.lab02may06;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONObject;

public class WeatherDetail extends AppCompatActivity {
    private String ID;
    private ImageView ivicon;
    private TextView tvtemp,tvname,tvdes,tvtmin,tvtmax,tvpres,tvhumid,tvspeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);
        initview();
        getdatafromintent();
        getJSONdata();
    }

    private void getJSONdata() {
        Ion.with(this)
                .load("http://api.openweathermap.org/data/2.5/weather?id="+ID+"&appid=c0269134cfe1d389366cc3aa47f54818")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if(result!= null && !result.equals("")){
                            JSONObject jsonObject = null;
                            try{
                                jsonObject=new JSONObject(result);
                                JSONObject weatherobj = jsonObject.getJSONObject("weather");
                                JSONObject mainobj = jsonObject.getJSONObject("main");
                                JSONObject windobj=jsonObject.getJSONObject("wind");
                                String icon = weatherobj.getString("icon");
                                String temp = mainobj.getString("temp");
                                String description = weatherobj.getString("description");
                                String temp_min = mainobj.getString("temp_min");
                                String temp_max = mainobj.getString("temp_max");
                                String pressure = mainobj.getString("pressure");
                                String humidity = mainobj.getString("humidity");
                                String speed = windobj.getString("speed");
                                String name = jsonObject.getString("name");


                                Ion.with(ivicon)
                                        .placeholder(R.mipmap.ic_launcher)
                                        .error(R.mipmap.ic_launcher)
                                        .load("http://openweathermap.org/img/w/"+icon+".png");
                                tvtemp.setText(temp);
                                tvname.setText(name);
                                tvdes.setText(description);
                                tvtmin.setText(temp_min);
                                tvtmax.setText(temp_max);
                                tvpres.setText(pressure);
                                tvhumid.setText(humidity);
                                tvspeed.setText(speed);
                            }catch (Exception ex){
                                ex.printStackTrace();
                            }
                        }
                    }
                });
    }

    private void getdatafromintent() {
        ID=getIntent().getStringExtra("ID");
    }

    private void initview() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.ic_app);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        ivicon = (ImageView)findViewById(R.id.iv_icon);
        tvtemp = (TextView)findViewById(R.id.tv_temp);
        tvname = (TextView)findViewById(R.id.tv_name);
        tvdes = (TextView)findViewById(R.id.tv_des);
        tvtmin = (TextView)findViewById(R.id.tv_tmin);
        tvtmax = (TextView)findViewById(R.id.tv_tmax);
        tvpres = (TextView)findViewById(R.id.tv_pressure);
        tvhumid = (TextView)findViewById(R.id.tv_humidity);
        tvspeed = (TextView)findViewById(R.id.tv_windspeed);
    }
}
