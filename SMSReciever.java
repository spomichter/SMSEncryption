package stashpomichter.smsencryption.MainPackage;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.Manifest;
import android.content.pm.PackageManager;
import android.opengl.EGLSurface;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.*;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import android.telephony.SmsMessage;
import android.widget.TextView;
import android.widget.Toast;

import javax.crypto.SealedObject;


/**
 * Created by stashpomichter on 11/11/16.
 */


public class SMSReciever extends BroadcastReceiver{

     public static String messageBody = "";

    public void onReceive(Context context, Intent intent)
    {
        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED"))
        {
            Bundle intentExtras = intent.getExtras();

            if(intentExtras != null)
            {
                Object[] sms = (Object[]) intentExtras.get("pdus");

                for(int i = 0; i < sms.length; i++)
                {
                    SmsMessage message = SmsMessage.createFromPdu((byte[]) sms[i]);

                    messageBody = message.getMessageBody();
                    String address = message.getOriginatingAddress();
                }
            }
        }

        try{
            String decryptedMessage = Encrypt.DecryptMessage();
            Toast.makeText(context, decryptedMessage, Toast.LENGTH_LONG).show();

        }catch(Exception error)
        {
            System.out.println(error);
        }
    }
}
