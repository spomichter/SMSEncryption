package tk.greatdysonia.smsencryption;

// If you tryna buy the half-o, you don't want the piggy to know.
import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.*;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

private static final int PERMISSIONS_SEND_SMS = 1;
    private String recipientNumber = "";
    private String msg = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendClicked(View v)
    {
        EditText number = (EditText) findViewById(R.id.txtRecipient);
        System.out.println(number.getText());
        //if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
        //    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PERMISSIONS_SEND_SMS);
        //}
        //else {
        //   SmsManager.getDefault().sendTextMessage(recipientNumber, null, msg, null,null);
        }
    }
