package com.bawei.disanfangdenglu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.image);
        tv = (TextView) findViewById(R.id.textview);
    }

    //BUTTON的监听
    public void btn(View view) {
        //访问第三方
        shard();
    }

    public void shard() {

        // mShareAPI.getPlatformInfo(UserinfoActivity.this, SHARE_MEDIA.SINA, umAuthListener);

        UMShareAPI.get(MainActivity.this).getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                Toast.makeText(MainActivity.this, "开始登陆", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                String uid = map.get("uid");
                //名字
                String name = map.get("name");
                //性别
                String gender = map.get("gender");
                //头像
                String iconurl = map.get("iconurl");

                System.out.println("gender" + gender);
                Toast.makeText(MainActivity.this, "gender" + gender, Toast.LENGTH_SHORT).show();
                ImageLoader.getInstance().displayImage(iconurl, imageView);
                tv.setText(name);

            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                Toast.makeText(MainActivity.this, "登录取消", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(MainActivity.this).onActivityResult(requestCode, resultCode, data);
    }
}
