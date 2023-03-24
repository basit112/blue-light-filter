//package com.example.nightshift.Broadcast;
//
//import android.app.PendingIntent;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.util.Log;
//
//import NightShiftService;
//import Const;
//import SharePreferencesController;
//import MainActivity;
//
//class BroadcastNightShift extends BroadcastReceiver {
//
//    private PendingIntent pendingIntentOn, pendingIntentOff;
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        Log.d("son.pt", "mainactivity receiver");
//        boolean isOnePending = pendingIntentOff == null || pendingIntentOn == null;
//        int count = SharePreferencesController.getInstance(context).getInt(Const.ALARM_COUNT, 0);
//        if (isOnePending && count == 1) {
//            SharePreferencesController.getInstance(context).putInt(Const.ALARM_COUNT, 0);
//            switchTime.setChecked(false);
//            SharePreferencesController.getInstance(context).putBoolean(Const.ALARM_APP, false);
//        }
//
//        if (count == 2) {
//            SharePreferencesController.getInstance(context).putInt(Const.ALARM_COUNT, 0);
//            switchTime.setChecked(false);
//            SharePreferencesController.getInstance(MainActivity.this).putBoolean(Const.ALARM_APP, false);
//        }
//
//        if (NightShiftService.isCheckedSwitch) {
//            aSwitch.setChecked(false);
//        } else {
//            aSwitch.setChecked(true);
//        }
//    }
//}