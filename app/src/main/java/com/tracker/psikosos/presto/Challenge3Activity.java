package com.tracker.psikosos.presto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.Random;


public class Challenge3Activity extends ActionBarActivity {
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

    private Uri fileUri;
    private ImageView imageView;

    private final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Presto/";
    private final int page = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge3);
        randomKategori1();

        TextView challenge_content = (TextView) findViewById(R.id.your_challenge_content_3);
        challenge_content.setText(getResources().getText(R.string.tantangan_3));

        imageView = (ImageView) findViewById(R.id.photo_result3);

        File newDir = new File(dir);
        newDir.mkdirs();

        Bitmap bitmap = BitmapFactory.decodeFile(dir + page + ".jpg");
        if(bitmap != null)
        {
            Log.d("Setting Bitmap", "Setting Bitmap");
            imageView.setImageBitmap(bitmap);
        }
    }

    private void randomKategori1()
    {
        TextView your_id_content = (TextView) findViewById(R.id.your_id_content_3);
        String[] array = getResources().getStringArray(R.array.kategori3);
        String randomStr = array[new Random().nextInt(array.length)];
        your_id_content.setText(randomStr);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_challenge, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void takePicture(View view) {
        String file = dir + page + ".jpg";
        File newFile = new File(file);
        try
        {
            newFile.createNewFile();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        Uri outputFileURI = Uri.fromFile(newFile);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileURI);

        startActivityForResult(cameraIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK)
        {
            finish();
            startActivity(getIntent());
        }
    }
}
