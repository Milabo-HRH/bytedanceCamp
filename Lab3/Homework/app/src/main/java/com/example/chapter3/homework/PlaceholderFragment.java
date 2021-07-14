package com.example.chapter3.homework;


import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ViewAnimator;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;

public class PlaceholderFragment extends Fragment {
    //private View mRootView;

    private int i;
    PlaceholderFragment(int in){
        i = in;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        //mRootView


        //ListView listView = getView().findViewById(R.id.list_view);
        //listView.setAlpha(0);
        //listView.setAdapter(new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1, data));
        return  inflater.inflate(R.layout.fragment_placeholder, container, false);

    }
    //private Resources resource = getResources();
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                //ListView listView = mRootView.findViewById(R.id.list_view);
                //AlphaAnimation hide = new AlphaAnimation(1f, 0f);
               // hide.setDuration(500);
                //AlphaAnimation show = new AlphaAnimation(0f,1f);
                //show.setDuration(500);
                //LottieAnimationView loAniView = mRootView.findViewById(R.id.animation_view);
                //listView.startAnimation(show);
               // loAniView.startAnimation(hide);
                // 这里会在 5s 后执行
                ListView listView = getView().findViewById(R.id.list_view);

                String[] data = myDataSet.getData(i);
                listView.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, data));

                LottieAnimationView loAniView =getView().findViewById(R.id.animation_view);
                listView.animate()
                         .alpha(1f)
                         .setDuration(500)
                         .setListener(null);
                loAniView.animate()
                        .alpha(0f)
                        .setDuration(500)
                        .setListener(null);
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
            }
        }, 5000);
    }
}
