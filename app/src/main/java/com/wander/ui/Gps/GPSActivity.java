package com.wander.ui.Gps;

import android.content.Context;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.wander.by.R;
import com.wander.view.ColorArcProgressBar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class GPSActivity extends AppCompatActivity implements GpsStatus.Listener {

    private LocationManager locationManager;
    private final String TAG = "GPS";
    private TextView lat_long, tv_altitude, tv_bearing, tv_accuracy, tv_speed, tv_time, tv_count;
    private LocationListener locationListener;
    private CheckedTextView check;
    private ColorArcProgressBar speedBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        initView();
        initGPS();
    }

    private void initView() {
        lat_long = (TextView) findViewById(R.id.long_lat);
        tv_accuracy = (TextView) findViewById(R.id.accuracy);
        tv_altitude = (TextView) findViewById(R.id.altitude);
        tv_speed = (TextView) findViewById(R.id.speed);
        tv_bearing = (TextView) findViewById(R.id.bearing);
        tv_count = (TextView) findViewById(R.id.count);
        tv_time = (TextView) findViewById(R.id.time);
        speedBar = (ColorArcProgressBar) findViewById(R.id.stepBar);
        check = (CheckedTextView) findViewById(R.id.check);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check.toggle();

            }
        });

    }

    private void initGPS() {
        //获取定位服务
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        //判断是否已经打开GPS模块
        if (locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
            //GPS模块打开，可以定位操作
        }
        // 通过GPS定位
        String LocateType = LocationManager.GPS_PROVIDER;
        Location location = locationManager.getLastKnownLocation(LocateType);
        // 设置监听器，设置自动更新间隔这里设置1000ms，移动距离：0米。
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                float speed = location.getSpeed();
                long time = location.getTime();
                double longitude = location.getLongitude();
                double latitude = location.getLatitude();
                //海拔
                double altitude = location.getAltitude();
                //方向
                float bearing = location.getBearing();
                //精度
                float accuracy = location.getAccuracy();

                speedBar.setCurrentValues(speed);
                lat_long.setText(String.format("精度%s \n纬度%s", longitude, latitude));
                tv_accuracy.setText(String.format("精度： %s", accuracy));
                tv_bearing.setText(String.format("方向：%s", bearing));
                tv_altitude.setText(String.format("海拔：%s", altitude));
                tv_speed.setText(String.format("速度：%s", speed));
                tv_time.setText(String.format("当前时间：%s", SimpleDateFormat.getInstance().format(new Date(time))));
                Log.e(TAG, "speed:" + speed + "\ttime=" + SimpleDateFormat.getInstance().format(new Date(time)) + "\tlongitude==" + longitude + " latitude" + latitude);

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.e(TAG + "StatusChange", provider + "  " + status + extras.toString());
            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
        // 设置状态监听回调函数。statusListener是监听的回调函数。
        locationManager.addGpsStatusListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationManager.removeUpdates(locationListener);
    }

    @Override
    public void onGpsStatusChanged(int event) {
        // GPS状态变化时的回调，获取当前状态
        GpsStatus status = locationManager.getGpsStatus(null);
        GetGPSStatus(event, status);
    }

    private List<GpsSatellite> numSatelliteList = new LinkedList<>();

    private void GetGPSStatus(int event, GpsStatus status) {
        Log.d(TAG, "enter the updateGpsStatus()");
        if (status == null) {
        } else {
            if (event == GpsStatus.GPS_EVENT_SATELLITE_STATUS) {
                //获取最大的卫星数（这个只是一个预设值）
                int maxSatellites = status.getMaxSatellites();
                Iterator<GpsSatellite> it = status.getSatellites().iterator();
                numSatelliteList.clear();
                //记录实际的卫星数目
                int count = 0;
                while (it.hasNext() && count <= maxSatellites) {
                    //保存卫星的数据到一个队列，用于刷新界面
                    GpsSatellite s = it.next();
                    numSatelliteList.add(s);
                    count++;
                }
                int mSatelliteNum = numSatelliteList.size();
                tv_count.setText(String.format("卫星数量：%s", mSatelliteNum));
            } else if (event == GpsStatus.GPS_EVENT_STARTED) {
                //定位启动
            } else if (event == GpsStatus.GPS_EVENT_STOPPED) {
                //定位结束
            }
        }

    }
}
