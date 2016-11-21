package stashpomichter.smsencryption.MainPackage;

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

import javax.crypto.SealedObject;

public class MainActivity extends AppCompatActivity {

    private static MainActivity activity;
    private static final int PERMISSIONS_SEND_SMS = 1;
    private static final int PERMISSIONS_READ_SMS = 1;
    private static final int PERMISSIONS_RECEIVE_SMS = 1;
    private static final int PERMISSIONS_WRITE_SMS = 1;
    private String recipientNumber = "";
    private String msg = "";
    private SealedObject encryptedMsg;

    public static MainActivity test()
    {
        return activity;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        activity = this;

        // Generate public/private key
        try {
            Encrypt.GenerateKey();
        }catch(Exception error){
            System.out.print(error);
        }

        // 'Recieve' permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, PERMISSIONS_RECEIVE_SMS);
        }

        // 'Read' permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, PERMISSIONS_READ_SMS);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void sendClicked(View v) {

        EditText number = (EditText) findViewById(R.id.txtRecipient);
        recipientNumber = number.getText().toString();
        EditText message = (EditText) findViewById(R.id.txtMessage);
        msg = message.getText().toString();

        // Encrypt message
        try{
            encryptedMsg = Encrypt.EncryptMessage(msg);
        } catch(Exception error){
            System.out.print(error);
        }

        // SEND permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PERMISSIONS_SEND_SMS);
        } else {
            SmsManager.getDefault().sendTextMessage(recipientNumber, null, Integer.toString(encryptedMsg.hashCode()), null, null);
        }

    }

}
