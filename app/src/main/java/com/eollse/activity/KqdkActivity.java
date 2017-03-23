package com.eollse.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.eollse.R;
import com.eollse.app.MyApplication;
import com.eollse.utils.BitmapUtil;
import com.eollse.utils.Constants;
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
    @BindView(R.id.et_userName)
    EditText etUserName;
    @BindView(R.id.et_usrNum)
    EditText etUsrNum;
    @BindView(R.id.btn_login)
    Button btnLogin;
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
    @BindView(R.id.et_qj_type)
    EditText etQjType;
    @BindView(R.id.et_qj_leader)
    EditText etQjLeader;
    @BindView(R.id.et_qj_startTime)
    EditText etQjStartTime;
    @BindView(R.id.et_qj_endTime)
    EditText etQjEndTime;

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

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constants.HANDLER_REFRESH_TIME:
                    setTime();
                    handler.postDelayed(runnable, 998);
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
        setContentView(R.layout.activity_kqdk);
        ButterKnife.bind(this);
        tvTitle.setText("考勤打卡");

        dfTime = new SimpleDateFormat("HH:mm:ss");
        dfDate = new SimpleDateFormat("yyyy-MM-dd");

        setDate();
        setTime();

        setListeners();
        handler.postDelayed(runnable, 998);

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
        btnLogin.setOnClickListener(this);
        ivChoose.setOnClickListener(this);
        btnKqCommit.setOnClickListener(this);
        btnQjCommit.setOnClickListener(this);
    }


    private class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果

            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果

            } else if (location.getLocType() == BDLocation.TypeServerError) {

                //服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                //网络不同导致定位失败，请检查网络是否通畅
                MyToast.showToast(getApplicationContext(), "请检查网络");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                //无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机
            }


            try {
                // location 位置信息
                //4.9E-324:定位代码正确没有得到坐标
                //1,在原生的模拟器
                //2,真机上信号不好

                if (location != null) {
                    //经度
                    double longitude = location.getLongitude();
                    //纬度
                    double Latitude = location.getLatitude();


                    if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                        Log.e("MyTAG", "GPS定位结果   经度:" + longitude + "  纬度:" + Latitude);
                    } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                        Log.e("MyTAG", "网络定位结果   经度:" + longitude + "  纬度:" + Latitude);
                    } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                        Log.e("MyTAG", "离线定位结果   经度:" + longitude + "  纬度:" + Latitude);
                    } else if (location.getLocType() == BDLocation.TypeNetWorkException) {//网络不通导致定位失败，请检查网络是否通畅
                        Log.e("MyTAG", "网络不通导致定位失败，请检查网络是否通畅");
                    }


                    double a = location.getLongitude();//精度
                    double b = location.getLatitude();//纬度
                    //String s = location.getLocationDescribe();
                    String dizhi = location.getAddrStr();
                    // Address address = location.getAddress();

                    //Toast.makeText(LocationActivity.this, "精度="+a+"  纬度="+b, Toast.LENGTH_SHORT).show();
                    //Log.e("MyTAG", "AddrStr=" + dizhi);
                    // Log.e("MyTAG", "LocationDescribe=" + s);
                    // Log.e("MyTAG", "城市=" + address.city);
                    //Log.e("MyTAG", "经度=" + a + "  纬度=" + b);
                    if (!(dizhi == null)) {
                        tvAddress.setText(dizhi);
                    } else {
                        tvAddress.setText("定位失败!!!");
                    }


                    mLocationClient.stop();
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
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


    private void setDate() {
        date = new Date();
        String d = dfDate.format(date);
        // 截取字符串(传入的格式 ：   2017-03-21)
        mYear = Integer.parseInt(d.split("-")[0]);//小时
        mMonth = Integer.parseInt(d.split("-")[1]);//分钟
        mDay = Integer.parseInt(d.split("-")[2]);//秒
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
            case R.id.btn_login://登录
                login();
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


    private void login() {
        if ("".equals(etUserName.getText().toString())) {
            MyToast.showToast(getApplicationContext(), "姓名不能为空");
            return;
        }
        if ("".equals(etUsrNum.getText().toString())) {
            MyToast.showToast(getApplicationContext(), "工号不能为空");
            return;
        }
        MyToast.showToast(getApplicationContext(), "登陆成功");
        isLogin = true;
    }

    private boolean isLogin;

    /**
     * 考勤提交
     */
    private void kaoqiCommit() {
        if (isLogin) {
            if ("".equals(tvAddress.getText().toString())) {
                MyToast.showToast(getApplicationContext(), "需重新定位");
                return;
            }
            if ("".equals(etKqContent.getText().toString())) {
                MyToast.showToast(getApplicationContext(), "考勤备注不能为空");
                return;
            }

            MyToast.showToast(getApplicationContext(), "考勤提交成功");

        } else {
            MyToast.showToast(getApplicationContext(), "请先登录");
            return;
        }

    }

    /**
     * 请假提交
     */
    private void qingjiaCommit() {
        if (isLogin) {
            if ("".equals(etQjType.getText().toString())) {
                MyToast.showToast(getApplicationContext(), "请选择请假类型");
                return;
            }
            if ("".equals(etQjLeader.getText().toString())) {
                MyToast.showToast(getApplicationContext(), "请选择审批领导");
                return;
            }
            if ("".equals(etQjStartTime.getText().toString())) {
                MyToast.showToast(getApplicationContext(), "请选择请假开始时间");
                return;
            }
            if ("".equals(etQjEndTime.getText().toString())) {
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
        } else {
            MyToast.showToast(getApplicationContext(), "请先登录");

        }

    }

    //private Uri imgUri;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case UploadPicHelper.TAKEPHOTO_REQUESTCODE:

                if (resultCode == -1) {
                    File file1 = new File(UploadPicHelper.takePicturePath);
                    //UploadPicHelper.startPhotoZoom(Uri.fromFile(file1), this);


                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    int size = BitmapUtil.calculateInSampleSize(options, 200, 200);
                    options.inSampleSize = size;
                    options.inJustDecodeBounds = false;
                    Log.e("MyTAG", "" + size);
                    Bitmap b = null;
                    try {

                        b = BitmapFactory.decodeFile(file1.getAbsolutePath(), options);
                        ivInfo.setImageBitmap(b);
                        tvAddressNotice.setVisibility(View.GONE);
                        Log.e("MyTAG", "" + ivInfo.getMeasuredWidth());
                        Log.e("MyTAG", "" + ivInfo.getMeasuredHeight());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

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
//                }

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
        super.onDestroy();
    }
}
