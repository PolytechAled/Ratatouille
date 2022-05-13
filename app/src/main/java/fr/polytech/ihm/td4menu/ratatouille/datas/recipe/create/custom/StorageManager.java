package fr.polytech.ihm.td4menu.ratatouille.datas.recipe.create.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class StorageManager {
    private static final String TAG = "frallo_"+"StorageManager";

    /**
     * return an Array of files inside a directory
     * @param directory
     * @return
     */
    public static boolean isEmptyDirectory(File directory) {
        if (directory==null || directory.listFiles()==null) return true;
        return directory.listFiles().length==0;
    }

    /**
     * return an Array of files inside a directory
     * @param directory
     * @return
     */
    public static File[] listFiles(File directory) {
        return directory.listFiles();
    }

    /**
     * save bitmap in a file. The file is in External Storage (depending context)
     * @param context
     * @param bitmapImage
     * @param file
     */
    public static void saveBitmapToStorage(Context context, Bitmap bitmapImage, File file){
        Log.d(TAG,"file name = "+file);
        FileOutputStream fileStream = null;
        try {
            fileStream = new FileOutputStream(file);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 80, fileStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(fileStream!=null) {
                try {
                    fileStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),file.getAbsolutePath(),file.getName(),file.getName());
            Log.d(TAG, "New picture saved");
        } catch (FileNotFoundException e) {
            Log.d(TAG, "ERROR: picture not saved");
            e.printStackTrace();
        }
    }

    /**
     * load bitmap file into a ImageView
     * @param file
     * @param img
     */
    public static void loadBitmapFromStorage(File file, ImageView img) {
        try {
            Bitmap picture = BitmapFactory.decodeStream(new FileInputStream(file));
            img.setImageBitmap(picture);
            Log.d(TAG, "load " + file);
        }
        catch (FileNotFoundException e)  {
            e.printStackTrace();
            Log.d(TAG, "ERROR: picture not loaded");
        }
    }
}
