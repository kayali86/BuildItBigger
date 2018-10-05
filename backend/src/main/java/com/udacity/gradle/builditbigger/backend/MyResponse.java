package com.udacity.gradle.builditbigger.backend;

/**
 * The object model for the data we are sending through endpoints
 */
class MyResponse {
    private String mJoke;

    public String getJoke(){
        return mJoke;
    }

    void setJoke(String joke) {
        mJoke = joke;
    }
}