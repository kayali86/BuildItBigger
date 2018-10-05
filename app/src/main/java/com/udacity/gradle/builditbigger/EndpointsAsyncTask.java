package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import com.udacity.gradle.builditbigger.jokesdisplayandroidlib.JokeActivity;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<Pair<Context, ProgressBar>, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    private ProgressBar loading_indicator;
    private static final String JOKE_KEY = "joke_key";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (loading_indicator != null) {
            loading_indicator.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected String doInBackground(Pair<Context, ProgressBar>... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }

        context = params[0].first;
        loading_indicator = params[0].second;

        try {
            return myApiService.getJoke().execute().getJoke();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (loading_indicator != null) {
            loading_indicator.setVisibility(View.GONE);
        }
        if (result != null){
            Intent jokeIntent = new Intent(context, JokeActivity.class);
            jokeIntent.putExtra(JOKE_KEY, result);
            if (jokeIntent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(jokeIntent);
            }
        }else{
            Log.e(EndpointsAsyncTask.class.getSimpleName(), "Result is null");
        }
    }
}