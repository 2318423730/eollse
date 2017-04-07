package com.eollse.activity.zwfw.pasq;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.activity.BaseActivity;
import com.eollse.activity.MainActivity;
import com.eollse.adapter.pasq.LdrkLeftAdapter;
import com.eollse.app.MyApplication;
import com.eollse.utils.MyLeftLinearLayout;
import com.eollse.utils.MyToast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * 流动人口
 */
public class LdrkActivity extends BaseActivity {

    @BindView(R.id.ll_myLeftLinearLayout)
    MyLeftLinearLayout llMyLeftLinearLayout;
    @BindView(R.id.tv_backHome)
    TextView tvBackHome;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.lv_leftInfo)
    ListView lvLeftInfo;
    @BindView(R.id.et_sqName)
    EditText etSqName;
    @BindView(R.id.et_userName)
    EditText etUserName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_reset)
    Button btnReset;
    @BindView(R.id.ll_login)
    LinearLayout llLogin;
    @BindView(R.id.line_chart)
    LineChartView lineChart;

    private LdrkLeftAdapter leftAdapter;
    private List<String> monthList = new ArrayList<>();

    Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
    int day=aCalendar.getActualMaximum(Calendar.DATE);

    private Random random;
    String[] date3 = {"3月1日", "3月3日", "3月5日", "3月7日", "3月9日", "3月11日", "3月13日", "3月15日", "3月17日", "3月19日", "3月21日", "3月23日", "3月25日", "3月27日", "3月29日","3月31日"};//X轴的标注
    String[] date4 = {"4月1日", "4月3日", "4月5日", "4月7日", "4月9日", "4月11日", "4月13日", "4月15日", "4月17日", "4月19日", "4月21日", "4月23日", "4月25日", "4月27日", "4月29日","4月30日"};//X轴的标注
    String[] date5 = {"5月1日", "5月3日", "5月5日", "5月7日", "5月9日", "5月11日", "5月13日", "5月15日", "5月17日", "5月19日", "5月21日", "5月23日", "5月25日", "5月27日", "5月29日", "5月31日"};//X轴的标注
    List<Integer> listNum = new ArrayList<>();//图表的数据点
    private List<PointValue> mPointValues = new ArrayList<PointValue>();
    private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ldrk);
        ButterKnife.bind(this);

        tvTitle.setText("流动人口");

        monthList.add("2017-3");
        monthList.add("2017-4");
        monthList.add("2017-5");

        //arrayAdapter=new ArrayAdapter<String>(LdrkActivity.this,R.layout.item_ldrk_month,monthList);
        leftAdapter = new LdrkLeftAdapter(LdrkActivity.this, monthList);
        lvLeftInfo.setAdapter(leftAdapter);

        setLsiteners();

        //设置图表数据

        getAxisXLables(3);//获取x轴的标注
        getAxisPoints();//获取坐标点
        initLineChart();//初始化

       // Log.e("MyTAG","天数="+day);
    }

    private void setLsiteners() {
        lvLeftInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                leftAdapter.setSelectIndex(position);
                leftAdapter.notifyDataSetChanged();
                int month=3;
                if(position==0){
                    month=3;
                }else if(position==1){
                    month=4;
                }else if(position==2){
                    month=5;
                }
                getAxisXLables(month);
                getAxisPoints();//获取坐标点
                initLineChart();//初始化
            }
        });
    }

    /**
     * 设置X 轴的显示
     */
    private void getAxisXLables(int month) {
        mAxisXValues.clear();
        if(month==3){
            for (int i = 0; i < date3.length; i++) {
                mAxisXValues.add(new AxisValue(i).setLabel(date3[i]));
            }
        }else if(month==4){
            for (int i = 0; i < date4.length; i++) {
                mAxisXValues.add(new AxisValue(i).setLabel(date4[i]));
            }
        }else if(month==5){
            for (int i = 0; i < date5.length; i++) {
                mAxisXValues.add(new AxisValue(i).setLabel(date5[i]));
            }
        }

    }

    /**
     * 图表的每个点的显示
     */
    private void getAxisPoints() {
        mPointValues.clear();

        int max=50;
        int min=10;
        random=new Random();
        for(int i=0;i<16;i++){
            int s = random.nextInt(max)%(max-min+1) + min;
            listNum.add(s);
        }

        for (int i = 0; i < listNum.size(); i++) {
            mPointValues.add(new PointValue(i, listNum.get(i)));
        }
    }

    private void initLineChart(){
        Line line = new Line(mPointValues).setColor(Color.BLUE);  //折线的颜色（蓝色）
        List<Line> lines = new ArrayList<Line>();
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
        line.setCubic(false);//曲线是否平滑，即是曲线还是折线
        line.setFilled(false);//是否填充曲线的面积
        line.setHasLabels(true);//曲线的数据坐标是否加上备注
//      line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(true);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextColor(Color.WHITE);  //设置字体颜色
        //axisX.setName("date");  //表格名称
        axisX.setTextSize(10);//设置字体大小
        axisX.setMaxLabelChars(7); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
        axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部
        //data.setAxisXTop(axisX);  //x 轴在顶部
        axisX.setHasLines(true); //x 轴分割线

        // Y轴是根据数据的大小自动设置Y轴上限(在下面我会给出固定Y轴数据个数的解决方案)
        Axis axisY = new Axis();  //Y轴
        axisY.setName("");//y轴标注
        axisY.setTextSize(10);//设置字体大小
        data.setAxisYLeft(axisY);  //Y轴设置在左边
        //data.setAxisYRight(axisY);  //y轴设置在右边


        //设置行为属性，支持缩放、滑动以及平移
        lineChart.setInteractive(true);
        lineChart.setZoomType(ZoomType.HORIZONTAL);
        lineChart.setMaxZoom((float) 2);//最大方法比例
        lineChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lineChart.setLineChartData(data);
        lineChart.setVisibility(View.VISIBLE);
        /**注：下面的7，10只是代表一个数字去类比而已
         * 当时是为了解决X轴固定数据个数。见（http://forum.xda-developers.com/tools/programming/library-hellocharts-charting-library-t2904456/page2）;
         */
        Viewport v = new Viewport(lineChart.getMaximumViewport());
        v.left = 0;
        v.right= 7;
        lineChart.setCurrentViewport(v);
    }
    @OnClick({R.id.tv_backHome, R.id.tv_back, R.id.btn_login, R.id.btn_reset})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_backHome:
                Intent intent = new Intent(LdrkActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                MyApplication.clearAllActivitiesWithOutMainActivity();
                break;
            case R.id.tv_back:
                finish();
                break;
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_reset:
                etSqName.setText("");
                etUserName.setText("");
                etPassword.setText("");
                break;
        }
    }

    private void login() {
        if("".equals(etSqName.getText().toString())){
            MyToast.showToast(getApplicationContext(),"社区名不能为空");
            return;
        }
        if("".equals(etUserName.getText().toString())){
            MyToast.showToast(getApplicationContext(),"账号不能为空");
            return;
        }
        if("".equals(etPassword.getText().toString())){
            MyToast.showToast(getApplicationContext(),"密码不能为空");
            return;
        }

        MyToast.showToast(getApplicationContext(),"登录成功!!!");
    }
}
