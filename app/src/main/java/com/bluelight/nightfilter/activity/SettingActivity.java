package com.bluelight.nightfilter.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Switch;
import android.widget.TextView;

import com.bluelight.nightfilter.BuildConfig;
import com.bluelight.nightfilter.Slide.ModeInfomation;
import com.bluelight.nightfilter.Utils.Commom;
import com.bluelight.nightfilter.Utils.Const;
import com.bluelight.nightfilter.Utils.SharePreferencesController;
import com.bluelight.nightfilter.R;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class SettingActivity extends AppCompatActivity {

    private final String PRIVACY_LINK = "https://www.google.com";


    private Switch  switchNoti;
    private Switch switchTime;
    private AdView adView;

    private InterstitialAd mInterstitialAd;
    private static final String TAG = "SettingActivity";
    private TextView tvOnOffNoti , help ,rate_us,policy , share_app ,more_apps;
//    private LinearLayout llTurnOnTime;
//    private TextView tvTurnOffTime;

//    private PendingIntent pendingIntentOn, pendingIntentOff;
//    private IntentFilter intentFilter = new IntentFilter();
 //   private BroadcastNightShift broadcastNightShift = new BroadcastNightShift();
//    class BroadcastNightShift extends BroadcastReceiver {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            Log.d("son.pt", "mainactivity receiver");
//            boolean isOnePending = pendingIntentOff == null || pendingIntentOn == null;
//            int count = SharePreferencesController.getInstance(context).getInt(Const.ALARM_COUNT, 0);
//            if (isOnePending && count == 1) {
//                SharePreferencesController.getInstance(context).putInt(Const.ALARM_COUNT, 0);
//                switchTime.setChecked(false);
//                SharePreferencesController.getInstance(SettingActivity.this).putBoolean(Const.ALARM_APP, false);
//            }
//
//            if (count == 2) {
//                SharePreferencesController.getInstance(context).putInt(Const.ALARM_COUNT, 0);
//                switchTime.setChecked(false);
//                SharePreferencesController.getInstance(SettingActivity.this).putBoolean(Const.ALARM_APP, false);
//            }
//
//            if (NightShiftService.isCheckedSwitch) {
//               // aSwitch.setChecked(false);
//            } else {
//               // aSwitch.setChecked(true);
//            }
//        }
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        switchNoti = findViewById(R.id.switchNotification);
        tvOnOffNoti = findViewById(R.id.tv_on_off);
        help = findViewById(R.id.help_setting);
        rate_us = findViewById(R.id.rate_us);
        policy = findViewById(R.id.privacy);
        share_app = findViewById(R.id.share_app);
        more_apps = findViewById(R.id.more_apps);
        MobileAds.initialize(this, initializationStatus -> {
        });

        loadInterstitialAd();
        adView = findViewById(R.id.setting_adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        help.setOnClickListener(v->{
            startActivity(new Intent(SettingActivity.this, ModeInfomation.class));

        });
        rate_us.setOnClickListener(v->{
            try {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID)));
                finish();
            } catch (ActivityNotFoundException e) {
                //
            }

        });
        policy.setOnClickListener(v->{
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(PRIVACY_LINK));
            startActivity(browserIntent);

        });
        share_app.setOnClickListener(v->{
            try {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Blue Light Filter");
                String shareMessage = "\nLet me recommend you this application.This is the best Blue Light Filter Application Check it out \n\n";
                shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, "choose one"));
                Log.d("setting", "1"+ BuildConfig.APPLICATION_ID);
            } catch (Exception e) {
                //e.toString();
            }

        });
        more_apps.setOnClickListener(v->{

            try {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID)));
                finish();
                Log.d("setting", "2 "+ BuildConfig.APPLICATION_ID);
            } catch (ActivityNotFoundException e) {
                //
            }
        });
       // switchTime = findViewById(R.id.switchTime);
//        tvTurnOffTime = findViewById(R.id.tv_turn_off_time);
//        llTurnOnTime = findViewById(R.id.ll_turn_on_time);



//        if (SharePreferencesController.getInstance(SettingActivity.this).getBoolean(Const.ALARM_APP, false)) {
//            switchTime.setChecked(true);
//            llTurnOnTime.setVisibility(View.VISIBLE);
//            tvTurnOffTime.setVisibility(View.INVISIBLE);
//        } else {
//            switchTime.setChecked(false);
//            llTurnOnTime.setVisibility(View.INVISIBLE);
//            tvTurnOffTime.setVisibility(View.VISIBLE);
//        }
//
//        switchTime.setOnCheckedChangeListener((buttonView, isChecked) -> {
//            if (isChecked) {
//                llTurnOnTime.setVisibility(View.VISIBLE);
//                tvTurnOffTime.setVisibility(View.INVISIBLE);
//            } else {
//                if (pendingIntentOn != null) {
//                    NightShiftService.alarmManager.cancel(pendingIntentOn);
//                }
//                if (pendingIntentOff != null) {
//                    NightShiftService.alarmManager.cancel(pendingIntentOff);
//                }
//                SharePreferencesController.getInstance(SettingActivity.this).putInt(Const.ALARM_COUNT, 0);
//                SharePreferencesController.getInstance(SettingActivity.this).putBoolean(Const.ALARM_APP, false);
//                llTurnOnTime.setVisibility(View.INVISIBLE);
//                tvTurnOffTime.setVisibility(View.VISIBLE);
//            }
//        });


        if (SharePreferencesController.getInstance(this).getBoolean(Const.MY_SHOW_NOTIFICATION, true)) {
            switchNoti.setChecked(true);
            tvOnOffNoti.setText(getResources().getString(R.string.status_on));
        } else {
            switchNoti.setChecked(false);
            tvOnOffNoti.setText(getResources().getString(R.string.status_off));
        }
        if (SharePreferencesController.getInstance(this).getBoolean(Const.MY_SHOW_NOTIFICATION, true)) {
            switchNoti.setChecked(true);
            tvOnOffNoti.setText(getResources().getString(R.string.status_on));
        } else {
            switchNoti.setChecked(false);
            tvOnOffNoti.setText(getResources().getString(R.string.status_off));
        }

        switchNoti.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (mInterstitialAd != null) {
                mInterstitialAd.show(this);
                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent();
                        if (isChecked) {
                            tvOnOffNoti.setText(getResources().getString(R.string.status_on));
                            SharePreferencesController.getInstance(SettingActivity.this).putBoolean(Const.MY_SHOW_NOTIFICATION, true);
                            Commom.iShowNotification.onShowNotification();
                        } else {
                            tvOnOffNoti.setText(getResources().getString(R.string.status_off));
                            SharePreferencesController.getInstance(SettingActivity.this).putBoolean(Const.MY_SHOW_NOTIFICATION, false);
                            Commom.iShowNotification.onShowNotification();
                        }
                    }
                });
            }else{
                loadInterstitialAd();
                if (isChecked) {
                    tvOnOffNoti.setText(getResources().getString(R.string.status_on));
                    SharePreferencesController.getInstance(SettingActivity.this).putBoolean(Const.MY_SHOW_NOTIFICATION, true);
                    Commom.iShowNotification.onShowNotification();
                } else {
                    tvOnOffNoti.setText(getResources().getString(R.string.status_off));
                    SharePreferencesController.getInstance(SettingActivity.this).putBoolean(Const.MY_SHOW_NOTIFICATION, false);
                    Commom.iShowNotification.onShowNotification();
                }
            }

        });

    }

    private void loadInterstitialAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(
                this,
                getResources().getString(R.string.interstitials_ads),
                adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.

                        SettingActivity.this.mInterstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                        //Toast.makeText(MainActivity.this, "onAdLoaded()", Toast.LENGTH_SHORT).show();
                        interstitialAd.setFullScreenContentCallback(
                                new FullScreenContentCallback() {
                                    @Override
                                    public void onAdDismissedFullScreenContent() {
                                        SettingActivity.this.mInterstitialAd = null;
                                        Log.d("TAG", "The ad was dismissed.");
                                    }

                                    @Override
                                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                                        // Called when fullscreen content failed to show.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        SettingActivity.this.mInterstitialAd = null;
                                        Log.d("TAG", "The ad failed to show.");
                                    }

                                    @Override
                                    public void onAdShowedFullScreenContent() {
                                        // Called when fullscreen content is shown.
                                        Log.d("TAG", "The ad was shown.");
                                    }
                                });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i(TAG, loadAdError.getMessage());
                        mInterstitialAd = null;
//                        String error = String.format(
//                                        "domain: %s, code: %d, message: %s",
//                                        loadAdError.getDomain(), loadAdError.getCode(), loadAdError.getMessage());
//                        Toast.makeText(
//                                        MainActivity.this, "onAdFailedToLoad() with error: " + error, Toast.LENGTH_SHORT)
//                                .show();
                    }
                });
    }
}