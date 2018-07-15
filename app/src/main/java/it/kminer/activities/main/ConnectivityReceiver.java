package it.kminer.activities.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import it.kminer.R;

public class ConnectivityReceiver extends BroadcastReceiver {

    private View rootView;

    public ConnectivityReceiver(View rootView) {
        this.rootView = rootView;
    }

    private static final String TAG = "ConnectivityReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();



        if (extras != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            Log.d( TAG,  "info:" + info);


            if( info != null && info.isConnectedOrConnecting()){
                Snackbar.make(rootView,
                        "Connection established",
                        Snackbar.LENGTH_SHORT).show();

            }
            else {

                final Snackbar snBar = Snackbar.make(rootView,
                        R.string.error_noNetwork,
                        Snackbar.LENGTH_INDEFINITE);
                snBar.setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        snBar.dismiss();
                    }
                });
                snBar.show();
            }


        }
    }
}
