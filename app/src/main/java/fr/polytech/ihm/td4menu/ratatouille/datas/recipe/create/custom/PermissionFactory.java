package fr.polytech.ihm.td4menu.ratatouille.datas.recipe.create.custom;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionFactory {
    public static final int REQUEST_ID_IMAGE_CAPTURE = 100;
    public static final int PERMISSIONS_REQUEST_READ_MEDIA = 1000;
    private static final String TAG = "frallo_"+"PermissionFactory";

    /**
     * check if permission is still granted
     * if it is not, request permissions and the result will come back to callbackActivity
     *
     * @param callbackActivity is the Activity where result is sent
     * @param permission is the permission we would like to be granted
     * @param arg is an argument... the arg can be use to remember the action witch involved to ask the permission
     * @return true if permission is still granted, false else
     */
    public static boolean buildAndCheck(Activity callbackActivity, String permission, String arg){
        int permissionCheck = ContextCompat.checkSelfPermission(callbackActivity, permission);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG,permission + " => permission NOT granted");
            ActivityCompat.requestPermissions(callbackActivity, new String[]{permission,arg}, PERMISSIONS_REQUEST_READ_MEDIA);
            return false;
        }
        Log.d(TAG,permission + " => permission granted");
        return true ;
    }
}
