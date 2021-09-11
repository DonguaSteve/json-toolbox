package com.dg.jsontools.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import com.dg.jsontools.R;
import com.dg.jsontools.common.activity.BaseActivity;
import android.widget.PopupMenu.OnMenuItemClickListener;

public class SettingActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        this.setTitle("设置");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

//        Intent intent = new Intent("com.android.dg.touga.MAIN");
//
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//        startActivity(intent);
    }
 
}
