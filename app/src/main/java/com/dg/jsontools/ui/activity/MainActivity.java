package com.dg.jsontools.ui.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.EditText;
import com.dg.jsontools.R;
import com.dg.jsontools.common.activity.BaseActivity;
import com.dg.jsontools.util.AlertDialogUtils;
import com.dg.jsontools.util.JsonUtils;
import com.dg.jsontools.util.ProgressDialogUtil;

public class MainActivity extends BaseActivity { 
    private EditText EditText1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("JSON工具箱");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionmenu, menu);

        SubMenu zcd1=menu.addSubMenu("格式");

        zcd1.add(1, 1, 0, "格式化...");
        zcd1.add(1, 2, 0, "转义...");
        zcd1.add(1, 3, 0, "压缩...");

        SubMenu zcd2=menu.addSubMenu("检查");

        zcd2.add(2, 1, 0, "校验...");

        //menu.add(3, 1, 0, "批量处理...");

        //menu.add(4, 1, 0, "可视化...");

        menu.add(5, 1, 0, "设置");

        menu.add(6, 1, 0, "关于");

        menu.add(7, 1, 0, "退出");

        menu.add(8, 1, 0, "复制").setIcon(R.drawable.ic_content_copy).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        menu.add(9, 1, 0, "粘贴").setIcon(R.drawable.ic_content_paste).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        EditText1 = findViewById(R.id.EditText1);
        final String JSONstring=EditText1.getText().toString();
        int GroupId=item.getGroupId();
        switch (GroupId) {
            case 1:
                switch (item.getItemId()) {
                    case 1:                   
                        {
							ProgressDialogUtil myProgressDialogUtil=new ProgressDialogUtil(this, "正在格式化...", "", 2);
                            myProgressDialogUtil.Show();
							myProgressDialogUtil.SetOnRun(new ProgressDialogUtil.OnRun(){

									@Override
									public void OnRun() {       
									}

									@Override
									public void OnFinish() {
                                        JsonUtils myJsonUtils=new JsonUtils <>();
                                        myJsonUtils.JSONimput(JSONstring);
                                        myJsonUtils.setOnJSONSyntaxException(new JsonUtils.onJSONSyntaxException(){

                                                @Override
                                                public void onSyntaxException() {
                                                    AlertDialogUtils.show("格式化失败,因为该JSON表达存在语法错误", "格式化...", "知道了", MainActivity.this);
                                                }
                                            });
                                        myJsonUtils.setOnJSONExport(new JsonUtils.onJSONExport(){

                                                @Override
                                                public void onExportSucceed() {
                                                    AlertDialogUtils.show("格式化成功", "格式化...", "知道了", MainActivity.this);
                                                }
                                            });
										EditText1.setText(myJsonUtils.handle("format").JSONexport());    
									}
								});
                              
                        }
                        break;
                    case 2:
                        {
							ProgressDialogUtil myProgressDialogUtil=new ProgressDialogUtil(this, "正在转义...", "", 2);
                            myProgressDialogUtil.Show();
							myProgressDialogUtil.SetOnRun(new ProgressDialogUtil.OnRun(){

									@Override
									public void OnRun() {
									}

									@Override
									public void OnFinish() {
                                        JsonUtils myJsonUtils=new JsonUtils <>();
                                        myJsonUtils.JSONimput(JSONstring);
                                        myJsonUtils.setOnJSONSyntaxException(new JsonUtils.onJSONSyntaxException(){

                                                @Override
                                                public void onSyntaxException() {
                                                    AlertDialogUtils.show("转义失败,因为该JSON表达存在语法错误", "转义...", "知道了", MainActivity.this);
                                                }
                                            });
                                        myJsonUtils.setOnJSONExport(new JsonUtils.onJSONExport(){

                                                @Override
                                                public void onExportSucceed() {
                                                    AlertDialogUtils.show("转义成功", "转义...", "知道了", MainActivity.this);
                                                }
                                            });
										EditText1.setText(myJsonUtils.handle("escape").JSONexport());     
									}
								});
                        }
                        break;
                    case 3:
                        {
							ProgressDialogUtil myProgressDialogUtil=new ProgressDialogUtil(this, "正在压缩...", "", 2);
                            myProgressDialogUtil.Show();
							myProgressDialogUtil.SetOnRun(new ProgressDialogUtil.OnRun(){

									@Override
									public void OnRun() {
									}

									@Override
									public void OnFinish() {
                                        JsonUtils myJsonUtils=new JsonUtils <>();
                                        myJsonUtils.JSONimput(JSONstring);
                                        myJsonUtils.setOnJSONSyntaxException(new JsonUtils.onJSONSyntaxException(){

                                                @Override
                                                public void onSyntaxException() {
                                                    AlertDialogUtils.show("压缩失败,因为该JSON表达存在语法错误", "压缩...", "知道了", MainActivity.this);
                                                }
                                            });
                                        myJsonUtils.setOnJSONExport(new JsonUtils.onJSONExport(){

                                                @Override
                                                public void onExportSucceed() {
                                                    AlertDialogUtils.show("压缩成功", "压缩...", "知道了", MainActivity.this);
                                                }
                                            });
										EditText1.setText(myJsonUtils.handle("zip").JSONexport());        
									}
								}); 
                        }
                        break;
                }
                break;
            case 2:
                switch (item.getItemId()) {
                    case 1:
                        EditText1 = findViewById(R.id.EditText1);

                        AlertDialogUtils.show(JsonUtils.isJSON(EditText1.getText().toString()) ? "该JSON表达无语法错误": "该JSON表达存在语法错误", "校验", "知道了", this);

                        break;
                }
                break;
            case 3:
                {
                    Intent intent = new Intent("com.android.dg.touga.BATCHING");

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(intent);
                }
                break;
            case 4:

                break;                
            case 5:
                {
                    Intent intent = new Intent("com.android.dg.touga.SETTING");

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(intent);
                }
                break;
            case 6:
                AlertDialogUtils.show("作者:冬瓜\n\nQQ:551420874", "关于", "关闭", this);
                break;
            case 7:
                System.exit(0);
                break;
            case 8:
                {
                    ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

                    cm.setPrimaryClip(ClipData.newPlainText(getPackageName(), JSONstring));
                }
                break;
            case 9:
                {
                    ClipboardManager clipboardManager = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                    if (null != clipboardManager) {
                        ClipData clipData = clipboardManager.getPrimaryClip();
                        if (null != clipData && clipData.getItemCount() > 0) {
                            ClipData.Item item0 = clipData.getItemAt(0);
                            if (null != item) {
                                String content = item0.getText().toString();         
                                EditText1.setText(content);
                            }
                        }
                    }                    
                }
                break;
            default:
        }
        return super.onOptionsItemSelected(item);

    }

}
