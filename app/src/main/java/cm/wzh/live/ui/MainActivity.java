package cm.wzh.live.ui;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import cm.wzh.live.R;
import cm.wzh.live.adapter.MainFragmentPageAdapter;
import cm.wzh.live.view.MyVideoView;

public class MainActivity extends FragmentActivity{
    private ViewPager vp ;
    private ArrayList<Fragment> fragments ;
    private ChatFragment chatFragment ;
    private NoFragment noFragment ;
    private MyVideoView videoView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.view_pager);
        // 设置viewPager的适配器
        fragments = new ArrayList<Fragment>();
        chatFragment = new ChatFragment();
        noFragment = new NoFragment();
        fragments.add(noFragment);
        fragments.add(chatFragment);
        MainFragmentPageAdapter myFragmentPagerAdapter = new MainFragmentPageAdapter(getSupportFragmentManager(), fragments);
        vp.setAdapter(myFragmentPagerAdapter);
        vp.setCurrentItem(1);
        vp.addOnPageChangeListener(new MyOnPageChangeListener());
        videoView = (MyVideoView) findViewById(R.id.video);
        final String uri = ("android.resource://" + this.getPackageName() + "/raw/vedio");
        videoView.setVideoURI(Uri.parse(uri));
        videoView.start();
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        videoView.setVideoURI(Uri.parse(uri));
                        videoView.start();
                    }
                });

    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }
        @Override
        public void onPageSelected(int arg0) {
        }
    }
}
