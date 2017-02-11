package com.nexuslink.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nexuslink.R;
import com.nexuslink.model.data.FriendInfo;
import com.nexuslink.ui.view.FriendInfoView;
import com.nexuslink.util.BlurDrawable;
import com.nexuslink.util.CircleImageView;
import com.nexuslink.util.ImageUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import okhttp3.OkHttpClient;

/**
 * Created by ASUS-NB on 2017/2/7.
 */

public class FriendInfoActivity extends SwipeBackActivity implements FriendInfoView {

    @BindView(R.id.first)
    LinearLayout first;
    @BindView(R.id.friend_head_background)
    RelativeLayout friendHeadBackground;
    //辅助变量,用于被模糊的背景
    ImageView imageViewBackground;
    @BindView(R.id.friend_head_image)
    CircleImageView friendHeadImage;
    @BindView(R.id.turn_back)
    ImageView turnBack;
    //用户获取头像文件的输入流
    private OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_friendinfo);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        imageViewBackground = new ImageView(this);
        doBlur();
    }

    /**
     * 获取头像
     */
    private void getHeadImage(String url, ImageView imageView) {
        ImageUtil.imageDisplay(url, imageView);
    }

    /**
     * 以头像作为背景 并做模糊化处理
     */
    private void doBlur() {
        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.headimage);
        final BlurDrawable blurDrawable = new BlurDrawable(this, getResources(), bitmap);
        blurDrawable.setBlur(200);
        imageViewBackground.setImageDrawable(blurDrawable.getBlurDrawable());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            friendHeadBackground.setBackground(imageViewBackground.getDrawable());
        }
    }

    @Override
    public void showFriendInfo(FriendInfo friendInfo) {

    }

    /**
     * 获取头像的输入流
     */
    private void getImageInputStream() {

    }

    @OnClick(R.id.turn_back)
    public void onClick(View v) {
        if(v.getId()==R.id.turn_back){
            onBackPressed();
        }
    }
}