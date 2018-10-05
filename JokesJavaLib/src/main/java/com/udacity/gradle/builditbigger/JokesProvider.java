package com.udacity.gradle.builditbigger;

import java.util.ArrayList;
import java.util.Random;

public class JokesProvider {
    private Random mRandom;
    private ArrayList<String> mJokes = new ArrayList<>();

    public JokesProvider() {
        mJokes.add("Anton, do you think I’m a bad mother?\n" +
                "\n" +
                "My name is Paul.\n" +
                "\n");
        mJokes.add("My dog used to chase people on a bike a lot. It got so bad, finally I had to take his bike away.");
        mJokes.add("What is the difference between a snowman and a snowwoman?\n" +
                "\n" +
                "Snowballs.\n");
        mJokes.add("Mom, where do tampons go?\n" +
                "\n" +
                "Where the babies come from, darling.\n" +
                "\n" +
                "In the stork?\n");
        mJokes.add("My wife suffers from a drinking problem.\n" +
                "\n" +
                "Oh is she an alcoholic?\n" +
                "\n" +
                "No, I am, but she’s the one who suffers.\n");
        mRandom = new Random();
    }

    public String getAJoke() {
        return mJokes.get(mRandom.nextInt(mJokes.size()));
    }

}
