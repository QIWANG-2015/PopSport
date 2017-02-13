package com.nexuslink.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.nexuslink.R;
import com.nexuslink.config.Constants;
import com.nexuslink.util.ApiUtil;
import com.nexuslink.util.ToastUtil;

import java.io.File;
import java.util.List;

import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


import static com.nexuslink.config.Constants.MULTIPART_FORM_DATA;


public class ImageActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "图片上传测试";
    Button choose;
    Button load;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        choose = (Button) findViewById(R.id.choose);
        load = (Button) findViewById(R.id.get);
        choose.setOnClickListener(this);
        load.setOnClickListener(this);
        imageView = (ImageView) findViewById(R.id.image_view);
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.choose:
                GalleryFinal.openGalleryMuti(0, 10, new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                        PhotoInfo info = resultList.get(0);
                        //进行选择图片的上传
                        //封装图片
                        File file = new File(info.getPhotoPath());
                        RequestBody requestFile =
                                RequestBody.create(MULTIPART_FORM_DATA,file);
                        //构造MultiPartBody
                        MultipartBody.Part body =
                                MultipartBody.Part.createFormData("uImg",file.getName(),requestFile);
                        ToastUtil.showToast(ImageActivity.this,"准备上传");
                        ApiUtil.getInstance(Constants.BASE_URL).changUserImage(15,requestFile)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<String>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                ToastUtil.showToast(ImageActivity.this,"出错");
                                Log.e(TAG,"出错");
                                e.printStackTrace();
                            }

                            @Override
                            public void onNext(String s) {
                                Log.i(TAG,s);
                                ToastUtil.showToast(ImageActivity.this,"成功");;
                            }
                        });
                    }

                    @Override
                    public void onHanlderFailure(int requestCode, String errorMsg) {
                            Log.e("ImageViewTest",errorMsg);
                    }
                });
                break;
            case R.id.get:

                break;
        }
    }
}
