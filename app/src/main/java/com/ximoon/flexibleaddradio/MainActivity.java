package com.ximoon.flexibleaddradio;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RadioGroup rg;
    private ViewPager vp;
    private EditText et;
    private Button bt;
    private ImagePagerAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp = (ViewPager) findViewById(R.id.vp);
        vp.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        rg = (RadioGroup) findViewById(R.id.rg);
        et = (EditText) findViewById(R.id.et);
        bt = (Button) findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {

            public void setData(){
                int num = 10;
                try {
                    num = Integer.parseInt(et.getText().toString());
                    if (num < 0 ){
                        num = 5;
                    }
                }catch (Exception e){
                    num = 5;
                    e.printStackTrace();
                }
                initData(num);
            }

            @Override
            public void onClick(View v) {
                setData();
            }
        });
        initData(5);
    }

    /**
     * 初始化数据
     * @param num   数据个数
     */
    public void initData(int num){
        addRadioButton(num);
        setImagePager(num);
        setCustomerOption();
    }


    /**
     * 动态添加RadionButton
     * @param num
     */
    public void addRadioButton(int num){
        RadioButton rb = null;
        rg.removeAllViews();
        for (int i = 0; i < num; i++){
            rb = (RadioButton) getLayoutInflater().inflate(R.layout.content_main,null);
            rg.addView(rb);
        }
        if (num > 0){
            rg.check(rg.getChildAt(0).getId());
        }
    }

    /**
     * 设置viewpager适配器并动态刷新
     * @param num
     */
    public void setImagePager(int num){
        ArrayList<Integer> images = new ArrayList<Integer>();
        for (int i = 0; i < num; i++){
            images.add(ConstantUtils.IMAGES[i]);
        }
        if (imageAdapter == null){
            imageAdapter = new ImagePagerAdapter(images,this);
            vp.setAdapter(imageAdapter);
        }else{

            imageAdapter.refresh(images);
        }
    }


    public void setCustomerOption(){
        // 设置viewpager的滑动事件
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                rg.check(rg.getChildAt(position).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //设置radiobutton的点击事件
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int position = 0; position < group.getChildCount(); position++){
                    if (checkedId == group.getChildAt(position).getId()){
                        vp.setCurrentItem(position);
                        break;
                    }
                }
            }
        });
    }

}
