package com.jungle.twiter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class Intro_viewPagerAdapter extends PagerAdapter {

    Context mcontext;
    List<ScreenItem> mListScreen;

    public Intro_viewPagerAdapter(Context mcontext, List<ScreenItem> mListScreen) {
        this.mcontext = mcontext;
        this.mListScreen = mListScreen;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen= inflater.inflate(R.layout.screen,null);

        ImageView screenImage=layoutScreen.findViewById(R.id.screen_Img);
        TextView title=layoutScreen.findViewById(R.id.intro_title);
        TextView descrption=layoutScreen.findViewById(R.id.intro_description);

        screenImage.setImageResource(mListScreen.get(position).getScreenImage());
        title.setText(mListScreen.get(position).getTitle());
        descrption.setText(mListScreen.get(position).getDescription());

        container.addView(layoutScreen);
        return layoutScreen;
    }

    @Override
    public int getCount() {
        return mListScreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
