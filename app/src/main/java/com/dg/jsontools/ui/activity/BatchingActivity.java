package com.dg.jsontools.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import com.dg.jsontools.R;
import com.dg.jsontools.common.activity.BaseActivity;

public class BatchingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("批量处理");
        setContentView(R.layout.activity_batching);
    }
}
