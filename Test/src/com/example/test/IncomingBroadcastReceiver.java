package com.example.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;


public class IncomingBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {

      
    	Bundle extras = intent.getExtras();

       
        final String incomingNumber = extras.getString("incoming_number");
        
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
       
        if (state.equals(TelephonyManager.EXTRA_STATE_RINGING))
        {   
        	
        	  Log.i("ARVIND", "i am getting called");
        	 
        	Toast.makeText(context, "MAY BE YOU ARE SMART!!", Toast.LENGTH_LONG).show();
        	
        	Intent i = new Intent(context, IncomingActivity.class);
            i.putExtras(intent);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            try {
        		Thread.sleep(1000);
        	} catch (InterruptedException e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	}
            Bundle bundle =new Bundle();
            bundle.putString("numb", incomingNumber);
            i.putExtras(bundle);
            context.startActivity(i);
            
        	
        	 
        }

    }

}
