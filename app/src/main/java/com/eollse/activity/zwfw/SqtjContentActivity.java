package com.eollse.activity.zwfw;

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
import com.eollse.activity.BaseActivity;
import com.eollse.activity.MainActivity;
import com.eollse.app.MyApplication;
import com.eollse.entity.Dept;
import com.eollse.entity.Type;
import com.eollse.utils.Constants;
import com.eollse.utils.HttpCallBack;
import com.eollse.utils.MyLeftLinearLayout;
import com.eollse.utils.MyToast;
import com.eollse.utils.SharedPreUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 诉求提交
 */
public class SqtjContentActivity extends BaseActivity {

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

    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.activity_bszn)
    LinearLayout activityBszn;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_dept)
    TextView tvDept;
    @BindView(R.id.ll_myLeftLinearLayout)
    MyLeftLinearLayout llMyLeftLinearLayout;

    private View myView;
    private PopupWindow popupWindow;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constants.HANDLER_TYPE_RECEIVED:
                    showPopup(myView, "type");
                    break;
                case Constants.HANDLER_DEPT_RECEIVED:
                    showPopup(myView, "dept");
                    break;
                case Constants.HANDLER_SQTJ_FAIL:
                    MyToast.showToast(getApplicationContext(), "诉求提交失败");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqtj_content);
        ButterKnife.bind(this);
        tvTitle.setText("诉求提交");


        //设置监听器
        setListeners();


        //通过布局注入器，注入布局给View对象
        myView = getLayoutInflater().inflate(R.layout.popup_yjtjcontent_type, null);
        //通过view 和宽·高，构造PopopWindow
        popupWindow = null;
        popupWindow = new PopupWindow(myView, 300, 400, true);
        // popupWindow.setBackgroundDrawable(getResources().getDrawable(
        //此处为popwindow 设置背景，同事做到点击外部区域，popwindow消失
        // R.drawable.diaolog_bg));
        //设置焦点为可点击
        //popupWindow.setFocusable(true);//可以试试设为false的结果
        //将window视图显示在etType下面

    }

    private List<String> typeList = new ArrayList<>();
    private List<String> deptList = new ArrayList<>();


    private void setListeners() {
        llMyLeftLinearLayout.setBackZwfwActivity(this);
        //回首页
        tvBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SqtjContentActivity.this, MainActivity.class);
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


        //类型选择
        tvType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getType();//查询类型


            }


        });
        tvDept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDept();
            }
        });

        //确定
        btnTongyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("".equals(etName.getText().toString())) {
                    MyToast.showToast(getApplicationContext(), "姓名不能为空");
                    return;
                } else if ("".equals(etPhone.getText().toString())) {
                    MyToast.showToast(getApplicationContext(), "电话不能为空");
                    return;
                } else if ("".equals(tvType.getText().toString())) {
                    MyToast.showToast(getApplicationContext(), "类型不能为空");
                    return;
                } else if ("".equals(tvDept.getText().toString())) {
                    MyToast.showToast(getApplicationContext(), "部门不能为空");
                    return;
                } else if ("".equals(etTitle.getText().toString())) {
                    MyToast.showToast(getApplicationContext(), "标题不能为空");
                    return;
                } else if ("".equals(etContent.getText().toString())) {
                    MyToast.showToast(getApplicationContext(), "内容不能为空");
                    return;
                }

                commitData();
            }
        });
    }

    /**
     * 意见提交
     */
    private void commitData() {
        //http://oa.ybqtw.org.cn/api/APP1.0.aspx?&TVInfoId=19&method=Opinionclass&Key=21218CCA77804D2BA1922C33E0151105
        Map<String, String> params = new HashMap<>();
        params.put("method", "OpinionAdd");
        params.put("TVInfoId", SharedPreUtil.getValue(this, "userinfo", "TVInfoId", ""));
        params.put("Key", SharedPreUtil.getValue(this, "userinfo", "Key", ""));
        params.put("UserName", etName.getText().toString());
        params.put("MobileNo", etPhone.getText().toString());
        params.put("OpinionClassId", tvTitle.getText().toString());
        params.put("deptId", tvDept.getText().toString());
        params.put("Title", etTitle.getText().toString());
        params.put("Content", etContent.getText().toString());
        MyApplication.okHttpUtil.post(Constants.BASE_URL, params, new HttpCallBack() {
            @Override
            public void OnSuccess(String jsonStr) {
                if (!"0".equals(JSON.parseObject(jsonStr).get("Status"))) {
                    startActivity(new Intent(getApplicationContext(), SqtjSuccessActivity.class));
                    finish();
                }

            }

            @Override
            public void OnError(String jsonStr) {
                handler.sendEmptyMessage(Constants.HANDLER_SQTJ_FAIL);
            }
        });
    }

    /**
     * 获取类型
     */
    private void getType() {
        Map<String, String> map = new HashMap<>();
        //http://oa.ybqtw.org.cn/api/APP1.0.aspx?&TVInfoId=19&method=Opinionclass&Key=21218CCA77804D2BA1922C33E0151105
        map.put("TVInfoId", SharedPreUtil.getValue(this, "userinfo", "TVInfoId", ""));
        map.put("method", "Opinionclass");
        map.put("Key", SharedPreUtil.getValue(this, "userinfo", "Key", ""));

        MyApplication.okHttpUtil.post(Constants.BASE_URL, map, new HttpCallBack() {
            @Override
            public void OnSuccess(String jsonStr) {
                Type type = JSON.parseObject(jsonStr, Type.class);
                if (type.getStatus() == 1) {
                    typeList.clear();
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

    /**
     * 获取部门
     */
    private void getDept() {
        Map<String, String> map = new HashMap<>();
        //http://oa.ybqtw.org.cn/api/APP1.0.aspx?&TVInfoId=19&method=Opinionclass&Key=21218CCA77804D2BA1922C33E0151105
        map.put("TVInfoId", SharedPreUtil.getValue(this, "userinfo", "TVInfoId", ""));
        map.put("method", "DeptList");
        map.put("Key", SharedPreUtil.getValue(this, "userinfo", "Key", ""));
        MyApplication.okHttpUtil.post(Constants.BASE_URL, map, new HttpCallBack() {
            @Override
            public void OnSuccess(String jsonStr) {
                Dept dept = JSON.parseObject(jsonStr, Dept.class);
                if (dept.getStatus() == 1) {
                    deptList.clear();
                    for (int i = 0; i < dept.getData().size(); i++) {
                        deptList.add(dept.getData().get(i).getDeptName());
                    }
                    handler.sendEmptyMessage(Constants.HANDLER_DEPT_RECEIVED);
                }
            }

            @Override
            public void OnError(String jsonStr) {

            }
        });
    }

    private void showPopup(View myView, final String t) {
        // popupWindow.showAsDropDown(etType);
        popupWindow.showAtLocation(activityBszn, Gravity.CENTER, 0, 0);

        ListView listView = (ListView) myView.findViewById(R.id.lv_type);
        if ("type".equals(t)) {
            setPopupAdapter(listView, typeList);
        } else {
            setPopupAdapter(listView, deptList);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                popupWindow.dismiss();
                if ("type".equals(t)) {
                    tvType.setText(typeList.get(position));
                } else {
                    tvDept.setText(deptList.get(position));
                }
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });
    }

    private void setPopupAdapter(ListView listView, List<String> list) {
        if (adapter == null) {
            listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.popup_sqtjcontent_type, list));
        } else {
            adapter.notifyDataSetChanged();
            listView.smoothScrollToPosition(0);
        }

    }

    private ArrayAdapter<String> adapter;

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
