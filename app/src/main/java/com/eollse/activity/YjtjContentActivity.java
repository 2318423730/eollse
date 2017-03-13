package com.eollse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.adapter.YjtjTypeAdapter;
import com.eollse.app.MyApplication;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YjtjContentActivity extends BaseActivity {

    @BindView(R.id.tv_backHome)
    TextView tvBackHome;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.btn_tongyi)
    Button btnTongyi;
    @BindView(R.id.et_type)
    EditText etType;
    @BindView(R.id.et_dept)
    EditText etDept;
    @BindView(R.id.tv_content)
    TextView tvContent;


    PopupWindow popupWindow;
    private List<String> typeList=new ArrayList<>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yjtj_content);
        ButterKnife.bind(this);
        tvTitle.setText("意见提交");

        //获取类型
        getType();
        //设置监听器
        setListeners();
    }

    private void getType() {
        for(int i=0;i<20;i++){
            typeList.add("类型"+i);
        }
    }

    private void setListeners() {
        //回首页
        tvBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(YjtjContentActivity.this, MainActivity.class);
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
        //确定
        btnTongyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //类型选择
        etType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //通过布局注入器，注入布局给View对象
                View myView=getLayoutInflater().inflate(R.layout.popup_yjtjcontent_type,null);
                //通过view 和宽·高，构造PopopWindow
                popupWindow=new PopupWindow(myView,300,500,true);
               // popupWindow.setBackgroundDrawable(getResources().getDrawable(
                        //此处为popwindow 设置背景，同事做到点击外部区域，popwindow消失
                //        R.drawable.diaolog_bg));
                //设置焦点为可点击
                //popupWindow.setFocusable(true);//可以试试设为false的结果
                //将window视图显示在etType下面
                popupWindow.showAsDropDown(etType);

                ListView listView= (ListView) myView.findViewById(R.id.lv_type);
                listView.setAdapter(new YjtjTypeAdapter(getApplicationContext(),typeList));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        etType.setText(typeList.get(position));
                        popupWindow.dismiss();
                    }
                });
            }
        });
    }

}
