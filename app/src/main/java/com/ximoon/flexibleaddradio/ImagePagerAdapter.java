package com.ximoon.flexibleaddradio;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by XImoon on 15/10/31.
 */
public class ImagePagerAdapter extends PagerAdapter {

    private Context mContext;
    private ArrayList<Integer> mImages = new ArrayList<Integer>();

    public ImagePagerAdapter(ArrayList<Integer> images, Context mContext) {
        this.mImages = images;
        this.mContext = mContext;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public int getCount() {
        return mImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setImageResource(mImages.get(position));
        // 设置刷新标志
//        imageView.setTag(position);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public int getItemPosition(Object object) {
        View view = (View) object;
        // 设置刷新状态（POSITION_NONE表示会销毁掉view然后被remove，重新加载，POSITION_UNCHANGED表示不会刷新加载）
        if (mImages.size() > 0) {
            return POSITION_NONE;
        }
        // 为了保证性能，可添加是否刷新的tag标志,tag用来确保当前的view需要被刷新，其他的view不需要刷新 mVp.getCurrentItem() == (Integer)view.getTag()   -->  return  POSITION_NONE; return POSITION_UNCHANGED;
        return super.getItemPosition(object);
    }

    public void refresh(ArrayList<Integer> images) {
        this.mImages = images;
        notifyDataSetChanged();
    }


}
