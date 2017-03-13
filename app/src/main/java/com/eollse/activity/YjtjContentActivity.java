package com.eollse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.eollse.R;
import com.eollse.app.MyApplication;
import com.eollse.entity.Type;
import com.eollse.utils.Constants;
import com.eollse.utils.HttpCallBack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 意见提交内容
 */
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
    @BindView(R.id.activity_bszn)
    LinearLayout activityBszn;


    private PopupWindow popupWindow;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constants.HANDLER_TYPE_RECEIVED:
                    showPopup();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yjtj_content);
        ButterKnife.bind(this);
        tvTitle.setText("意见提交");


        //设置监听器
        setListeners();
    }

    private List<String> typeList;

    private void getType() {
        Map<String, String> map = new HashMap<>();
        //http://oa.ybqtw.org.cn/api/APP1.0.aspx?&TVInfoId=19&method=Opinionclass&Key=21218CCA77804D2BA1922C33E0151105
        map.put("TVInfoId", "19");
        map.put("method", "Opinionclass");
        map.put("Key", "21218CCA77804D2BA1922C33E0151105");

        MyApplication.okHttpUtil.post(Constants.BASE_URL, map, new HttpCallBack() {
            @Override
            public void OnSuccess(String jsonStr) {
                Type type = JSON.parseObject(jsonStr, Type.class);
                if (type.getStatus() == 1) {
                    typeList = new ArrayList<String>();
                    for (int i = 0; i < type.getData().size(); i++) {
                        typeList.add(type.getData().get(i).getOpinionClassName());
                    }
                    handler.sendEmptyMessage(Constants.HANDLER_TYPE_RECEIVED);
                } else {
                    //加载失败
                }
            }

            @Override
            public void OnError(String jsonStr) {

            }
        });

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
                getType();//查询类型


            }


        });

    }

    private void showPopup() {
        //通过布局注入器，注入布局给View对象
        View myView = getLayoutInflater().inflate(R.layout.popup_yjtjcontent_type, null);
        //通过view 和宽·高，构造PopopWindow
        popupWindow = new PopupWindow(myView, 500, 500, true);
        // popupWindow.setBackgroundDrawable(getResources().getDrawable(
        //此处为popwindow 设置背景，同事做到点击外部区域，popwindow消失
        // R.drawable.diaolog_bg));
        //设置焦点为可点击
        //popupWindow.setFocusable(true);//可以试试设为false的结果
        //将window视图显示在etType下面
       // popupWindow.showAsDropDown(etType);
        popupWindow.showAtLocation(activityBszn, Gravity.CENTER,0,0);

        ListView listView = (ListView) myView.findViewById(R.id.lv_type);
        listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.item_popup_yjtjcontent_type, typeList));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                etType.setText(typeList.get(position));
                popupWindow.dismiss();
            }
        });
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
