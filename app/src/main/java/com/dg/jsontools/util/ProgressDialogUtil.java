package com.dg.jsontools.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import com.dg.jsontools.R;
import android.widget.ProgressBar;

public class ProgressDialogUtil {

    private static final int PRO = 0;
    private Handler handler;
    public int progress=0;

    ProgressBar progressBar;
    ProgressDialog progressDialog;

    OnRun onRun0;

    public ProgressDialogUtil(Activity activity, String title, String message, int max) {
        handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {

                super.handleMessage(msg);

                switch (msg.what) {
                    case PRO:

                        if (progress >= progressDialog.getMax()) {
                            onRun0.OnFinish();
                            progress = 0;                            
                            progressDialog.dismiss();
                        } else {
                            progressDialog.incrementProgressBy(1);
                            onRun0.OnRun();
                            progress++;
                            handler.sendEmptyMessageDelayed(PRO, 1);
                        }
                        break;
                    default:
                }

            }
        };

        progressDialog = new ProgressDialog(activity);
        progressDialog.setIcon(R.drawable.ic_launcher);
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setView(progressBar);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.setMax(max);
        progress = (progress > 0) ?progress: 0;
        progressDialog.setProgress(progress);
        handler.sendEmptyMessage(PRO);
    }

    public void Show() {
        progressDialog.show();
    }

    public void SetOnRun(OnRun onRun) {
        onRun0 = onRun;
    }

    public interface OnRun {
        void OnRun();
        void OnFinish();
    }

}
