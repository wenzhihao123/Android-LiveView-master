package cm.wzh.live.adapter;

/**
 * author：Administrator on 2016/12/26 15:00
 * description:文件说明
 * version:版本
 */
import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.GridView;

public class GiftViewPagerAdapter extends PagerAdapter{
    private List<View> array;

    public GiftViewPagerAdapter(Context context, List<View> array) {
        this.array=array;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return array.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return arg0 == arg1;
    }
    @Override
    public Object instantiateItem(View arg0, int arg1)
    {
        ((ViewPager) arg0).addView(array.get(arg1));
        return array.get(arg1);
    }

    @Override
    public void destroyItem(View arg0, int arg1, Object arg2)
    {
        ((ViewPager) arg0).removeView((View) arg2);
    }

}
