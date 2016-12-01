package edu.csumb.moli9479.applicationpocketcs;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by josephmolina on 9/28/16.
 */

public class PocketCSApp extends Application {
    @Override
    public void onCreate(){
        FacebookSdk.sdkInitialize(getApplicationContext());
        Log.i("PocketCSAPP", "Inside PocketCSAPP class");
        super.onCreate();
          try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "edu.csumb.moli9479.applicationpocketcs",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

        //FacebookSdk.sdkInitialize(getApplicationContext());
       // AppEventsLogger.activateApp(this);
    }
}
