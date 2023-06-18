package at.gleb.javaeepractic.di;


import at.gleb.javaeepractic.data.AnimalsGetter;

import java.sql.Connection;

public final class Dependencies {
    private static Dependencies instance;



    private final AnimalsGetter animalsGetter;


    private Dependencies(AnimalsGetter animalsGetter) {
        this.animalsGetter = animalsGetter;
    }

    public static Dependencies getInstance() {
        if (instance == null) {
            final DependencyFabric fabric = new DependencyFabric();
            instance = new Dependencies(fabric.animalsGetter());
        }
        return instance;
    }


    public AnimalsGetter getAnimalsGetter() {
        return animalsGetter;
    }
}
