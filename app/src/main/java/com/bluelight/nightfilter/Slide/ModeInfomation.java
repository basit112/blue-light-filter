package com.bluelight.nightfilter.Slide;

import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bluelight.nightfilter.R;
import com.github.paolorotolo.appintro.AppIntro;


public class ModeInfomation extends AppIntro {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(SampleSlide.newInstance(R.layout.slide_1));
      //  addSlide(AppIntroFragment.newInstance(getResources().getString(R.string.advice), getResources().getString(R.string.advice_description), R.drawable.ic_glasses_with_circular_lenses, getResources().getColor(R.color.cardview_dark_background)));

        showSkipButton(false);
        setFlowAnimation();

    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
    }
}
