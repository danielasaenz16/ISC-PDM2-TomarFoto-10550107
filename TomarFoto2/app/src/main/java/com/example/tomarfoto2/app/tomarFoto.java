package com.example.tomarfoto2.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class tomarFoto extends ActionBarActivity {
   private final String ruta_fotos = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/misfotos/";
       private File file = new File(ruta_fotos);
       private Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tomar_foto);
        boton = (Button) findViewById(R.id.btnTomaFoto);
        //Si no existe crea la carpeta donde se guardaran las fotos
        file.mkdirs();
        //accion para el boton
        boton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String file = ruta_fotos + getCode() + ".jpg";
                File mi_foto = new File(file);
                try {
                    mi_foto.createNewFile();
                } catch (IOException ex) {
                    Log.e("ERROR ", "Error:" + ex);
                }
                //
                Uri uri = Uri.fromFile( mi_foto );
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(cameraIntent, 0);
            }
        });
    }
    @SuppressLint("SimpleDateFormat")
      private String getCode()
      {
           SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
           String date = dateFormat.format(new Date() );
           String photoCode = "pic_" + date;
           return photoCode;
          }
   
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tomar_foto, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
