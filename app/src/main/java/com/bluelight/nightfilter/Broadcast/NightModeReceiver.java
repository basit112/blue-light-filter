package com.bluelight.nightfilter.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.bluelight.nightfilter.Service.NightShiftService;
import com.bluelight.nightfilter.Utils.Const;
import com.bluelight.nightfilter.Utils.SharePreferencesController;

public class NightModeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("son.pt", "broadcast time");
        if (intent.getIntExtra("BẬT", 0) == 1) {
            if (NightShiftService.view.getWindowToken() == null) {
                Intent intent1 = new Intent(Const.ACTION_NIGHT_MODE);
                context.sendBroadcast(intent1);
                SharePreferencesController.getInstance(context).putInt(Const.ALARM_COUNT,1);
            }
        } else {
            if (NightShiftService.view.getWindowToken() != null) {
                Intent intent1 = new Intent(Const.ACTION_NIGHT_MODE);
                context.sendBroadcast(intent1);
                SharePreferencesController.getInstance(context).putInt(Const.ALARM_COUNT,1);
            }
        }

    }
}
