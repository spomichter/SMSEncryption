package stashpomichter.smsencryption.MainPackage;

/**
 * Created by stashpomichter on 11/11/16.
 */

import android.widget.TextView;
import android.view.View;
import java.io.IOException;
import java.security.*;
import javax.crypto.*;

public class Encrypt extends MainActivity{

    private static PublicKey publicKey;
    private static PrivateKey privateKey;
    private static Cipher cipher;
    private static String myMessage;
    private static SealedObject encryptedMessage;
    private static String decryptedMessage;

    // Constructor
    public Encrypt()
    {
    }

    public static SealedObject EncryptMessage(String message) throws InvalidKeyException, IllegalBlockSizeException, IOException
    {
        myMessage = message;
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        encryptedMessage = new SealedObject(message, cipher);

        return encryptedMessage;

    }
    public static String DecryptMessage() throws InvalidKeyException, ClassNotFoundException, IllegalBlockSizeException, BadPaddingException, IOException
    {
        String myMesssage;

        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        myMesssage = (String) encryptedMessage.getObject(cipher);

        return myMesssage;
    }

    public static void GenerateKey() throws NoSuchAlgorithmException, NoSuchPaddingException {
        boolean keyGenerated = false;

        if(!keyGenerated) {
            keyGenerated = true;

            cipher = Cipher.getInstance("RSA");

            // Generates public/private keys - 2048 bit RSA asymmetric encryption
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(2048);
            KeyPair kp = kpg.genKeyPair();

            publicKey = kp.getPublic();
            privateKey = kp.getPrivate();

            System.out.println("Public key:  " + publicKey);
            System.out.println();
            System.out.println("Private key:  " + privateKey);
        }
    }

}

