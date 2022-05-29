package com.example.tugas419030013;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.core.app.ShareCompat;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText phoneNumber;
    private EditText websiteUri;
    private EditText locationUri;
    private EditText textShare;
    Button buttonWebsite;
    Button buttonLocation;
    Button buttonText;
    Button buttonPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phoneNumber = findViewById(R.id.nohp);
        websiteUri = findViewById(R.id.url);
        locationUri = findViewById(R.id.lokasi);
        textShare = findViewById(R.id.teks);
        buttonPhone = findViewById(R.id.buttonhp);
        buttonPhone.setOnClickListener(this);
        buttonWebsite = findViewById(R.id.buttonurl);
        buttonWebsite.setOnClickListener(this);
        buttonLocation = findViewById(R.id.buttonlokasi);
        buttonLocation.setOnClickListener(this);
        buttonText = findViewById(R.id.buttonteks);
        buttonText.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonhp:
                if (phoneNumber.getText().toString()==null || phoneNumber.getText().toString().trim().equals("")){
                    Toast.makeText(getBaseContext(),"Tolong Isi NO-HP Terlebih Dahulu", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent dialPhone = new Intent(Intent.ACTION_DIAL,
                            Uri.parse("tel:" + phoneNumber.getText().toString()));
                    startActivity(dialPhone);
                }
                break;
            case R.id.buttonurl:
                if (websiteUri.getText().toString()==null || websiteUri.getText().toString().trim().equals("")){
                    Toast.makeText(getBaseContext(),"Tolong Isi URL Terlebih Dahulu", Toast.LENGTH_LONG).show();
                }
                else {
                    String url = websiteUri.getText().toString();
                    if (!url.startsWith("http://") && !url.startsWith("https://")) {
                        url = "http://" + url;
                        Intent openWebsite = new
                                Intent(Intent.ACTION_VIEW, Uri.parse
                                (url));
                        startActivity(openWebsite);
                        break;
                    }
                }
            case R.id.buttonlokasi:
                if (locationUri.getText().toString()==null || locationUri.getText().toString().trim().equals("")){
                    Toast.makeText(getBaseContext(),"Tolong Isi Lokasi Terlebih Dahulu", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent openLocation = new
                            Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" +
                            locationUri.getText().toString()));
                    startActivity(openLocation);
                }
                break;
            case R.id.buttonteks:
                if (textShare.getText().toString()==null || textShare.getText().toString().trim().equals("")){
                    Toast.makeText(getBaseContext(),"Tolong Isi Text Terlebih Dahulu", Toast.LENGTH_LONG).show();
                }
                else {
                    ShareCompat.IntentBuilder
                            .from(this)
                            .setType("text/plan")
                            .setChooserTitle("Buka Teks Dengan : "
                            )
                            .setText(textShare.getText().toString()).startChooser();
                }
                break;
        }
    }
}