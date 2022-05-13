package fr.polytech.ihm.td4menu.ratatouille.recipe.create.custom;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.File;
import fr.polytech.ihm.td4menu.ratatouille.R;
import fr.polytech.ihm.td4menu.ratatouille.datas.factory.CustomRecipeFactory;
import fr.polytech.ihm.td4menu.ratatouille.datas.recipe.Recipe;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipes;
import fr.polytech.ihm.td4menu.ratatouille.recipe.create.custom.MVC.Controller_CustomRecipe;
import fr.polytech.ihm.td4menu.ratatouille.recipe.create.custom.MVC.Model_CustomRecipe;
import fr.polytech.ihm.td4menu.ratatouille.recipe.create.custom.MVC.View_CustomRecipe;

public class CreateCustomRecipe extends AppCompatActivity {
    private Model_CustomRecipe model_customRecipe;
    private View_CustomRecipe view_customRecipe;
    private Controller_CustomRecipe controller_customRecipe;

    private static final float TRANSPARENT = 0.3f;
    private static final float OPAQUE = 1;
    private static final String PICTURE_NAME = "_test.jpg";
    private final String TAG = "info";
    private Bitmap picture = null;

    private File internalDirectory;
    //private File externalPrimaryDirectory;
    //private File externalSecondaryDirectory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_custom_recipe);

        // Midi ou Soir ?
        int moment = getIntent().getIntExtra("moment",-1);
        Log.d("info","moment dans create : " + moment);
        Recipes.setMoment(moment);

        //create VIEW with XML layout
        this.view_customRecipe = new View_CustomRecipe(this.controller_customRecipe , this);
        this.model_customRecipe = new Model_CustomRecipe(null);    //controller not still created so the controller reference will be sent later
        this.model_customRecipe.addObserver(this.view_customRecipe);    //MODEL is observable from VIEW

        this.controller_customRecipe = new Controller_CustomRecipe(this.model_customRecipe, this.view_customRecipe);
        this.model_customRecipe.setController(this.controller_customRecipe);
        this.view_customRecipe.setListener(this.controller_customRecipe);


        findViewById(R.id.takeAPicture).setOnClickListener( click -> {
            Log.d(TAG,"want to take a picture");
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);   // Create an implicit intent, for image capture
            startActivityForResult(intent, PermissionFactory.REQUEST_ID_IMAGE_CAPTURE);      // Start camera and wait for the results.
        });

        findViewById(R.id.customRecipeButtonRetour).setOnClickListener(clic -> {
            finish();
        });

        findViewById(R.id.customRecipeButtonValider).setOnClickListener(clic -> {
            EditText name = findViewById(R.id.customRecipeName);
            EditText time = findViewById(R.id.customRecipeTime);
            EditText ingredients = findViewById(R.id.customRecipeIngredients);
            EditText step = findViewById(R.id.customRecipeEtape);

            String nameText = name.getText().toString();
            int timeVal = Integer.parseInt(time.getText().toString());
            String ingredientsText = ingredients.getText().toString();
            String stepText = step.getText().toString();

            //Recipe newRecipe = new Recipe(55, nameText,timeVal,ingredientsText,stepText);
            Recipe recipe = new CustomRecipeFactory(nameText,"Fait maison",timeVal,ingredientsText,stepText).instantiate();
            Recipes.getDay().setRecipe(moment, recipe);
            Recipes.setNewRecipe(recipe);
            //Recipes.getDay().setFirstRecipe(newRecipe);
            //Recipes.add(newRecipe);
            //Recipes.setNewRecipe(newRecipe);
            //getParent().recreate();
            finish();
        });
    }
    /**
     * load an Image then allow save buttons
     * @param file
     * @param imageView
     */
    private void loadImage(File file, ImageView imageView){
        StorageManager.loadBitmapFromStorage(file, imageView);
        picture = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        //findViewById(R.id.button_save_internal).setAlpha( picture!=null && internalDirectory!=null ? OPAQUE : TRANSPARENT) ;
        //findViewById(R.id.button_save_external).setAlpha( picture!=null && externalPrimaryDirectory!=null ? OPAQUE : TRANSPARENT) ;
        //findViewById(R.id.button_save_sd).setAlpha( picture!=null && externalSecondaryDirectory!=null ? OPAQUE : TRANSPARENT) ;
    }

    /**
     * what user wanted to do before permission to access external storage was granted
     * @param action: possible values are WRITE_EXTERNAL_STORAGE and READ_EXTERNAL_STORAGE
     *//*
    private void actionAfterCallbackIfPermissionGranted(String action, String folder){
        File directory = new File (folder);
        Log.d(TAG,"directory = "+directory);
        switch (action) {
            case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                Log.d(TAG,"action when permission granted ==> save");
                StorageManager.saveBitmapToStorage(getApplicationContext(), picture, new File(directory, PICTURE_NAME));
                findViewById(R.id.button_load_external).setAlpha( StorageManager.listFiles(directory).length>0 ? OPAQUE : TRANSPARENT) ;
                Toast.makeText(getApplicationContext(), "file saved in "+directory, Toast.LENGTH_LONG).show();
                break;

            case Manifest.permission.READ_EXTERNAL_STORAGE:
                Log.d(TAG,"action when permission granted ==> load");
                loadImage(new File(directory, PICTURE_NAME), findViewById(R.id.imageView));
                Toast.makeText(getApplicationContext(), "file load from "+directory, Toast.LENGTH_LONG).show();
                break;
        }
    }*/


    /**
     * callback from PermissionFactory builAndCheck
     * @param requestCode
     * @param result
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String result[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, result, grantResults);
        Log.d(TAG,"onRequestPermissionsResult : requestCode="+requestCode+"    grantResults.length="+grantResults.length);
        switch (requestCode) {
            case PermissionFactory.PERMISSIONS_REQUEST_READ_MEDIA:
                if ((grantResults.length >0 ) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {  //length==2 when (permission + arg) received
                    //actionAfterCallbackIfPermissionGranted(result[0], result[1]);
                    //Log.d(TAG,"onRequestPermissionsResult : permission granted");
                }
                else { //not granted
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);  //"this" and not getApplicationContext()... bug?
                    builder.setTitle("Permisions");
                    builder.setMessage("You don't have granted permission... " +
                            "It's your choice! Note that is necessary to load and save pictures into your device.\n" +
                            "So, please reconsider issues :)");
                    builder.setNeutralButton("OK", null);
                    builder.show();
                    Log.d(TAG,"onRequestPermissionsResult : permission not granted");
                }
                break;
            default:        //other permission
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Permisions");
                builder.setMessage("Not sur you need this permission... ");
                builder.setNeutralButton("OK", null);
                builder.show();
                Log.d(TAG,"onRequestPermissionsResult : odd permission!");
                break;
        }
    }


    /**
     * callback from camera activity
     * The Android Camera application encodes the photo in the return Intent delivered to onActivityResult()
     * as a small Bitmap in the extras, under the key "data"  ---> BEURK!!!  no constant!
     * https://developer.android.com/training/camera/photobasics
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PermissionFactory.REQUEST_ID_IMAGE_CAPTURE) {
            switch (resultCode) {
                case RESULT_OK:
                    picture = (Bitmap) data.getExtras().get("data");
                    this.view_customRecipe.onClickTakeAPhoto(picture);
                    //((ImageView)findViewById(R.id.customRecipeImage)).setImageBitmap(picture);
                    Log.d(TAG,"camera result: RESULT_OK");
                    //findViewById(R.id.button_save_internal).setAlpha(internalDirectory!=null ? OPAQUE : TRANSPARENT);
                    //findViewById(R.id.button_save_external).setAlpha(externalPrimaryDirectory!=null ? OPAQUE : TRANSPARENT);
                    //findViewById(R.id.button_save_sd).setAlpha(externalSecondaryDirectory!=null ? OPAQUE : TRANSPARENT);
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(getApplicationContext(), "Action canceled", Toast.LENGTH_LONG).show();
                    Log.d(TAG,"camera result: RESULT_CANCELED");
                    //findViewById(R.id.button_save_internal).setAlpha(TRANSPARENT);
                    //findViewById(R.id.button_save_external).setAlpha(TRANSPARENT);
                    //findViewById(R.id.button_save_sd).setAlpha(TRANSPARENT);
                    //TODO: ask why it is important to grant permission to this app
                    break;
                default:                Toast.makeText(getApplicationContext(), "camera result: Action Failed", Toast.LENGTH_LONG).show();
                    break;
            }//end switch
        }
    }

}