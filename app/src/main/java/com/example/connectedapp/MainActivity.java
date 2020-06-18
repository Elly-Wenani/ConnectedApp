package com.example.connectedapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ProgressBar mProgressBar;
    TextView textViewErrorLoadingData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = findViewById(R.id.mProgressBar);
        textViewErrorLoadingData = findViewById(R.id.tvErrorLoadingData);

        try {
            URL bookUrl = ApisUtil.buildUrl("cooking");
            new BookQueryTask().execute(bookUrl);

        } catch (Exception e) {
            Log.d("Error", e.getMessage());
        }
    }

    public class BookQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL searchURL = urls[0];
            String result = null;

            try {
                result = ApisUtil.getJson(searchURL);
            } catch (IOException e) {
                Log.e("Error", e.getMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            TextView tvResult = findViewById(R.id.tvResponse);
            mProgressBar.setVisibility(View.INVISIBLE);
            tvResult.setText(result);

            if (result == null) {
                tvResult.setVisibility(View.INVISIBLE);
                textViewErrorLoadingData.setVisibility(View.VISIBLE);
            } else {
                tvResult.setVisibility(View.VISIBLE);
                textViewErrorLoadingData.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }
}