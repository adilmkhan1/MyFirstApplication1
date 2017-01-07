package com.example.amk.myfirstapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

import static android.R.attr.description;


public class Disclaimer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclaimer);


        // Text View

        TextView txtView = (TextView) findViewById(R.id.text_id);
        String formattedText = "<h><b><font color =\"red\">Please Note</font></b></h><p>This <i>is</i> just <b> for </b>testing</p>";
        txtView.setText(Html.fromHtml(formattedText));

    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
