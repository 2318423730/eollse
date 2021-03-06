package com.eollse.activity.zwfw.wggl;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.bumptech.glide.Glide;
import com.eollse.R;
import com.eollse.activity.BaseActivity;
import com.eollse.activity.MainActivity;
import com.eollse.app.MyApplication;
import com.eollse.utils.Constants;
import com.eollse.utils.DateTimePickDialogUtil;
import com.eollse.utils.MyLeftLinearLayout;
import com.eollse.utils.MyToast;
import com.eollse.utils.UploadPicHelper;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 考勤打卡
 */
public class KqdkActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_backHome)
    TextView tvBackHome;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_hour)
    TextView tvHour;
    @BindView(R.id.tv_min)
    TextView tvMin;
    @BindView(R.id.tv_second)
    TextView tvSecond;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_getLoction)
    TextView tvGetLoction;

    @BindView(R.id.et_kq_content)
    EditText etKqContent;
    @BindView(R.id.btn_kq_commit)
    Button btnKqCommit;
    @BindView(R.id.et_qj_content)
    EditText etQjContent;
    @BindView(R.id.btn_qj_commit)
    Button btnQjCommit;
    @BindView(R.id.iv_choose)
    ImageView ivChoose;
    @BindView(R.id.iv_info)
    ImageView ivInfo;
    @BindView(R.id.tv_addressNotice)
    TextView tvAddressNotice;
    @BindView(R.id.tv_qj_type)
    TextView tvQjType;
    @BindView(R.id.tv_qj_leader)
    TextView tvQjLeader;
    @BindView(R.id.tv_qj_startTime)
    TextView tvQjStartTime;
    @BindView(R.id.tv_qj_endTime)
    TextView tvQjEndTime;
    @BindView(R.id.ll_myLeftLinearLayout)
    MyLeftLinearLayout llMyLeftLinearLayout;
    @BindView(R.id.mapView)
    com.baidu.mapapi.map.MapView mapView;


    private int mYear;
    private int mMonth;
    private int mDay;
    private Date date;
    private int mHour;
    private int mMinuts;
    private int mSecond;
    private SimpleDateFormat dfTime;
    private SimpleDateFormat dfDate;

    private static final int BAIDU_PERMESSION = 100;
    private static final int CAMERA = 101;
    public LocationClient mLocationClient = null;
    private LocationClientOption option;
    public BDLocationListener myListener;
    private BaiduMap baiduMap;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constants.HANDLER_REFRESH_TIME:
                    setTime();
                    handler.postDelayed(runnable, 998);
                    break;
                case Constants.HANDLER_LOCATION_GET:
                    //接受msg传递过来的参数
                    double longitude = msg.getData().getDouble("longitude");
                    double latitude = msg.getData().getDouble("latitude");
                    String dizhi = msg.getData().getString("dizhi");//接受msg传递过来的参数
                    tvAddress.setText(dizhi);

                    //移动地图到定位到的位置
                    LatLng lng = new LatLng(latitude, longitude);
                    goLocation(lng);

                    break;
                case Constants.HANDLER_NO_NENTWORK:
                    tvAddress.setText("定位失败");
                    MyToast.showToast(getApplicationContext(),"网络不通导致定位失败，请检查网络是否通畅");
                    break;
            }
        }
    };
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.sendEmptyMessage(Constants.HANDLER_REFRESH_TIME);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_kqdk);
        ButterKnife.bind(this);
        tvTitle.setText("考勤打卡");

        dfTime = new SimpleDateFormat("HH:mm:ss");
        dfDate = new SimpleDateFormat("yyyy-MM-dd");

        setDate();
        setTime();

        setListeners();
        handler.postDelayed(runnable, 998);

        baiduMap = mapView.getMap();

        mapView.showZoomControls(false);//隐藏缩放按钮

        // 开启定位图层
        baiduMap.setMyLocationEnabled(true);
        //baiduMap.setIndoorEnable(true); // 打开室内图
        //普通地图
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        //卫星地图
        // baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
        //开启交通图
        //baiduMap.setTrafficEnabled(true);

        myListener = new MyLocationListener();
        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
        option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(true);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(true);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);

        getMyLocation();
    }

    private void setListeners() {
        llMyLeftLinearLayout.setBackZwfwActivity(this);
        //回首页
        tvBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KqdkActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                MyApplication.clearAllActivitiesWithOutMainActivity();
            }
        });
        //返回
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvGetLoction.setOnClickListener(this);

        ivChoose.setOnClickListener(this);
        btnKqCommit.setOnClickListener(this);
        btnQjCommit.setOnClickListener(this);

        tvQjStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                        KqdkActivity.this, null);
                date = new Date();
                dateTimePicKDialog.dateTimePicKDialog(tvQjStartTime);
            }
        });
        tvQjEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                        KqdkActivity.this, null);

                dateTimePicKDialog.dateTimePicKDialog(tvQjEndTime);
            }
        });
    }


    private class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {


            try {
                // location 位置信息
                //4.9E-324:定位代码正确没有得到坐标
                //1,在原生的模拟器
                //2,真机上信号不好

                if (location != null) {
                    //经度
                    double longitude = location.getLongitude();
                    //纬度
                    double latitude = location.getLatitude();
                    String dizhi = location.getAddrStr();
                    //String s = location.getLocationDescribe();

                    // Address address = location.getAddress();

                    //Toast.makeText(LocationActivity.this, "精度="+a+"  纬度="+b, Toast.LENGTH_SHORT).show();
                    //Log.e("MyTAG", "AddrStr=" + dizhi);
                    // Log.e("MyTAG", "LocationDescribe=" + s);
                    // Log.e("MyTAG", "城市=" + address.city);
                    Log.e("MyTAG", "经度=" + longitude + "  纬度=" + latitude);
                    if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                        //Log.e("MyTAG", "GPS定位结果   经度:" + longitude + "  纬度:" + latitude);
                    } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                        //Log.e("MyTAG", "网络定位结果   经度:" + longitude + "  纬度:" + latitude);
                    } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                        //Log.e("MyTAG", "离线定位结果   经度:" + longitude + "  纬度:" + latitude);
                    } else if (location.getLocType() == BDLocation.TypeNetWorkException) {//网络不通导致定位失败，请检查网络是否通畅
                        //Log.e("MyTAG", "网络不通导致定位失败，请检查网络是否通畅");
                        handler.sendEmptyMessage(Constants.HANDLER_NO_NENTWORK);
                        return;
                    }




//                    if (!(dizhi == null)) {
//                       // Log.e("MyTAG", "定位成功!!!");
//                        tvAddress.setText(dizhi);
//                    } else {
//                       // Log.e("MyTAG", "定位失败!!!");
//                        tvAddress.setText("定位失败!!!");
//                    }
                    if(dizhi==null){
                        dizhi="定位失败!!!";
                    }

                    Message message = Message.obtain();
                    Bundle bundle = new Bundle();
                    //往Bundle中存放数据
                    bundle.putString("dizhi", dizhi);
                    bundle.putDouble("longitude", longitude);
                    bundle.putDouble("latitude", latitude);
                    message.setData(bundle);
                    message.what=Constants.HANDLER_LOCATION_GET;
                    handler.sendMessage(message);

                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                //Log.e("MyTAG", "" + e);
            }
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }


    }

    /**
     * 定位时先获取权限
     */
    private void getMyLocation() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//6.0以上
            if (this.checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") != PackageManager.PERMISSION_GRANTED) {
                // 申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义)
                requestPermissions(new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.READ_PHONE_STATE"}, BAIDU_PERMESSION);

                return;
            } else {
                //   开始定位
                mLocationClient.start();

            }


        } else {//6.0以下
            //   开始定位
            mLocationClient.start();


        }


    }

    //移动位置，并且添加marker
    public void goLocation(LatLng lng) {
        // TODO Auto-generated method stub

        float zoom = 19;//值越大,地图范围越小
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(lng, zoom);
        baiduMap.animateMapStatus(mapStatusUpdate);

        //在定位的位置添加marker
        //先清除marker
        baiduMap.clear();
        MarkerOptions markerOptions = new MarkerOptions();

        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.icon_gcoding);
        markerOptions.icon(bitmapDescriptor);
        markerOptions.position(lng);
        baiduMap.addOverlay(markerOptions);
    }

    private void setDate() {
        date = new Date();
        String d = dfDate.format(date);
        // 截取字符串(传入的格式 ：   2017-03-21)
        mYear = Integer.parseInt(d.split("-")[0]);//年
        mMonth = Integer.parseInt(d.split("-")[1]);//月
        mDay = Integer.parseInt(d.split("-")[2]);//日
        tvDate.setText(mYear + "年" + mMonth + "月" + mDay + "日");
    }

    private void setTime() {

        date = new Date();
        String time = dfTime.format(date);
        // 截取字符串(传入的格式 ：   01:02:1)
        mHour = Integer.parseInt(time.split(":")[0]);//小时
        mMinuts = Integer.parseInt(time.split(":")[1]);//分钟
        mSecond = Integer.parseInt(time.split(":")[2]);//秒
        //小时

        if (mHour < 10) {
            tvHour.setText("0" + mHour);
        } else {
            tvHour.setText("" + mHour);
        }
        //分
        if (mMinuts < 10) {
            tvMin.setText("0" + mMinuts);
        } else {
            tvMin.setText("" + mMinuts);
        }
        //秒
        if (mSecond < 10) {
            tvSecond.setText("0" + mSecond);
        } else {
            tvSecond.setText("" + mSecond);
        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case BAIDU_PERMESSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //获取到权限ACCESS_COARSE_LOCATION
                } else {
                    //没有获取到权限
                    MyToast.showToast(getApplicationContext(), "没有获得ACCESS_COARSE_LOCATION");
                }

                if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    //获取到权限ACCESS_FINE_LOCATION
                } else {
                    //没有获取到权限
                    MyToast.showToast(getApplicationContext(), "没有获得ACCESS_FINE_LOCATION");
                }

                if (grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                    //获取到权限READ_PHONE_STATE
                } else {
                    //没有获取到权限
                    MyToast.showToast(getApplicationContext(), "没有获得READ_PHONE_STATE");
                }
                break;
            case CAMERA:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //获取到摄像机权限
                    Log.e("MyTAG", "获取到摄像机权限");
                } else {
                    //没有获取到权限
                    MyToast.showToast(getApplicationContext(), "没有获得摄像机权限");
                }

                if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    //获取到摄像机权限
                    Log.e("MyTAG", "获取到SD卡读权限");
                } else {
                    //没有获取到权限
                    MyToast.showToast(getApplicationContext(), "没有获取到SD卡读权限");
                }

                if (grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                    //获取到摄像机权限
                    Log.e("MyTAG", "获取到SD卡写权限");
                } else {
                    //没有获取到权限
                    MyToast.showToast(getApplicationContext(), "没有获取到SD卡写权限");
                }
                break;

        }
        mLocationClient.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_getLoction://手动重新定位:
                tvAddress.setText("定位中...");
                getMyLocation();
                break;

            case R.id.btn_kq_commit://考勤提交
                kaoqiCommit();
                break;
            case R.id.btn_qj_commit://请假提交
                qingjiaCommit();
                break;
            case R.id.iv_choose:
                openCamera();

                break;

        }
    }

    private void openCamera() {
        //获取权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//6.0以上
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA);
        }

        UploadPicHelper.showPicDialog(KqdkActivity.this);


    }


    /**
     * 考勤提交
     */
    private void kaoqiCommit() {

        if ("".equals(tvAddress.getText().toString())) {
            MyToast.showToast(getApplicationContext(), "需重新定位");
            return;
        }
        if ("".equals(etKqContent.getText().toString())) {
            MyToast.showToast(getApplicationContext(), "考勤备注不能为空");
            return;
        }

        MyToast.showToast(getApplicationContext(), "考勤提交成功");


    }

    /**
     * 请假提交
     */
    private void qingjiaCommit() {

        if ("".equals(tvQjType.getText().toString())) {
            MyToast.showToast(getApplicationContext(), "请选择请假类型");
            return;
        }
        if ("".equals(tvQjLeader.getText().toString())) {
            MyToast.showToast(getApplicationContext(), "请选择审批领导");
            return;
        }
        if ("".equals(tvQjStartTime.getText().toString())) {
            MyToast.showToast(getApplicationContext(), "请选择请假开始时间");
            return;
        }
        if ("".equals(tvQjEndTime.getText().toString())) {
            MyToast.showToast(getApplicationContext(), "请选择请假结束时间");
            return;
        }
        if ("".equals(tvAddress.getText().toString())) {
            MyToast.showToast(getApplicationContext(), "需重新定位");
            return;
        }
        if ("".equals(etQjContent.getText().toString())) {
            MyToast.showToast(getApplicationContext(), "请假详情不能为空");
            return;
        }
        MyToast.showToast(getApplicationContext(), "请假提交成功");


    }

    //private Uri imgUri;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case UploadPicHelper.TAKEPHOTO_REQUESTCODE:

                if (resultCode == -1) {
                    File file1 = new File(UploadPicHelper.takePicturePath);
                    tvAddressNotice.setVisibility(View.GONE);
                    //UploadPicHelper.startPhotoZoom(Uri.fromFile(file1), this);

                    Glide.with(KqdkActivity.this).load(file1).override(200, 200).into(ivInfo);
//                    BitmapFactory.Options options = new BitmapFactory.Options();
//                    BitmapFactory.decodeFile(file1.getAbsolutePath(), options);
//                    options.inJustDecodeBounds = true;
//                    int size = BitmapUtil.calculateInSampleSize(options, 200, 200);
//                    options.inSampleSize = size;
//                    options.inJustDecodeBounds = false;
//                    Log.e("MyTAG", "" + size);
//                    Bitmap b = null;
//                    try {
//
//                        b = BitmapFactory.decodeFile(file1.getAbsolutePath(), options);
//                        ivInfo.setImageBitmap(b);
//                        tvAddressNotice.setVisibility(View.GONE);
//                        //Log.e("MyTAG", "" + ivInfo.getMeasuredWidth());
//                        //Log.e("MyTAG", "" + ivInfo.getMeasuredHeight());
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }

//                    BitmapFactory.Options options = new BitmapFactory.Options();
//                    Bitmap b = null;
//                    b = BitmapFactory.decodeFile(file1.getAbsolutePath(), options);
//                    //压缩
//                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                    b.compress(Bitmap.CompressFormat.JPEG, 50, stream);//10 是压缩率，表示压缩90%; 如果不压缩是100，表示压缩率为0
//                    byte[] bytes = stream.toByteArray();
//                    try {
//                        stream.flush();
//                        stream.close();
//                    } catch (IOException e) {
//
//
//                    }
//                    Glide.with(this).load(bytes).into(ivInfo);
//                    Log.e("MyTAG",""+ivInfo.getMeasuredWidth());
//                    Log.e("MyTAG",""+ivInfo.getMeasuredHeight());
                }

                break;
//            case UploadPicHelper.CROP:
//                Bundle bundle = data.getExtras();
//                if (data != null) {
//
//                    Bitmap bitmap = null;
//                    if (bundle != null) {
//                        bitmap = bundle.getParcelable("data");
//                        //将Uri图片转换为Bitmap
//                           /* try {
//                                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imgUri));
//                            } catch (FileNotFoundException e) {
//                                e.printStackTrace();
//                            }*/
//
//                        //圆形图片
//                        //bitmap = BitmapUtil.toRoundBitmap(bitmap);
//                        //压缩
//                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                        bitmap.compress(Bitmap.CompressFormat.JPEG, 10, stream);//10 是压缩率，表示压缩90%; 如果不压缩是100，表示压缩率为0
//                        byte[] bytes = stream.toByteArray();
//                        try {
//                            stream.flush();
//                            stream.close();
//                        } catch (IOException e) {
//
//                        }
//                        //转换图片
//                        String s = Base64.encodeToString(bytes, Base64.NO_WRAP);
//                        // ivInfo.setImageBitmap(bitmap);
//                        Glide.with(this).load(bitmap).into(ivInfo);
//                    }
//
//                }
//                break;
        }


    }


    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        // 关闭定位图层
        baiduMap.setMyLocationEnabled(false);
        mLocationClient.stop();   //添加这句就行了
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mapView.onPause();
    }
}

