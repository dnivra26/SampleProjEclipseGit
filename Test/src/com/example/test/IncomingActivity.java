package com.example.test;

import java.lang.reflect.Method;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.internal.telephony.ITelephony;

public class IncomingActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        setContentView(R.layout.activity_incoming);
        
//        //GETTING THE PHONE NUMBER
        Bundle bundle = getIntent().getExtras();
//
//        //Extract the data…
        String numb = bundle.getString("numb");
        
                TextView e1=(TextView)findViewById(R.id.textView2);
       e1.setText(numb);
        
       
   
        
        //TRYING TO ANSWER A CALL...
        Button btnDialog = (Button) findViewById(R.id.button1);
        btnDialog.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
               
            	Intent answer = new Intent(Intent.ACTION_MEDIA_BUTTON);
            	answer.putExtra(Intent.EXTRA_KEY_EVENT, new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_HEADSETHOOK));
            	sendOrderedBroadcast(answer, null);
            	
            }
        });
        

        //end try
        
        //TRYING TO REJECT A CALL...
        Button btnDialog2 = (Button) findViewById(R.id.button2);
        btnDialog2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	
            	//TRYING TELEPHONY
                
                TelephonyManager tm = (TelephonyManager) getApplicationContext()
                        .getSystemService(Context.TELEPHONY_SERVICE);
                try {
                    // Java reflection to gain access to TelephonyManager's
                    // ITelephony getter
                  
                    Class c = Class.forName(tm.getClass().getName());
                    Method m = c.getDeclaredMethod("getITelephony");
                    m.setAccessible(true);
                    com.android.internal.telephony.ITelephony telephonyService = (ITelephony) m.invoke(tm);
                    telephonyService.endCall();
                } catch (Exception e) {
                    e.printStackTrace();
                   
                }
                
                //END TELEPHONEY
            }
        });
        

        //end try
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_incoming, menu);
        return true;
    }
}
