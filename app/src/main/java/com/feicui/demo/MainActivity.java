package com.feicui.demo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mButton;
    private ImageView mImageView;
    private Bitmap mBitmap;
    private static final int    LOAD_PIC = 1;
    private static final int    LOAD_PIC_fail = 2;

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case LOAD_PIC:
                    mImageView.setImageBitmap(mBitmap);
                    Toast.makeText(getApplicationContext(), "图片获取成功", Toast.LENGTH_SHORT).show();
                    break;
                case LOAD_PIC_fail:
                    mImageView.setImageResource(R.drawable.downloadfail);
                    Toast.makeText(MainActivity.this, "图片连接超时,请检查地址是否正确", Toast.LENGTH_SHORT).show();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.btn_getimage);
        mImageView = (ImageView) findViewById(R.id.iv_image);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                    //   String path=mEditText.getText().toString();
                        String path="http://pic2.sc.chinaz.com/files/pic/pic9/201605/apic20874.jpg";

                        try {
                            byte[]data=ImageService.getImages(path);
                            mBitmap= BitmapFactory.decodeByteArray(data,0,data.length);
                            mHandler.sendEmptyMessage(LOAD_PIC);
                        } catch (Exception e) {
                            mHandler.sendEmptyMessage(LOAD_PIC_fail);
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
    }
}
