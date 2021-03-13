package com.vaibhav.fitnessapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.net.URI;

public class VideoCallActivityDoctor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_call);

        /*WebView webview = (WebView)findViewById(R.id.webview);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("https://stormy-tor-69508.herokuapp.com/19157");*/

        Intent il = getIntent();
        String url = il.getStringExtra("link");
        if(url == null) {
            Toast.makeText(VideoCallActivityDoctor.this, "Link not created yet", Toast.LENGTH_SHORT).show();
        }

        //String url = "https://stormy-tor-69508.herokuapp.com/19157";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ResolveInfo resolveInfo = getApplicationContext().getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        String packageName = resolveInfo.activityInfo.packageName;

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setData(Uri.parse(url));
        i.setPackage(packageName);
        getApplicationContext().startActivity(i);
    }


}