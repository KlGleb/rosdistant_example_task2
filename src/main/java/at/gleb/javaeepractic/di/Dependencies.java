package at.gleb.javaeepractic.di;


import at.gleb.javaeepractic.data.AnimalsGetter;
import at.gleb.javaeepractic.data.AnimalsTypeGetter;

public final class Dependencies {
    private static Dependencies instance;



    private final AnimalsGetter animalsGetter;



    private final AnimalsTypeGetter animalsTypeGetter;


    private Dependencies(AnimalsGetter animalsGetter, AnimalsTypeGetter animalsTypeGetter) {
        this.animalsGetter = animalsGetter;
        this.animalsTypeGetter = animalsTypeGetter;
    }

    public static Dependencies getInstance() {
        if (instance == null) {
            final DependencyFabric fabric = new DependencyFabric();
            instance = new Dependencies(fabric.animalsGetter(), fabric.typeGetter());
        }
        return instance;
    }


    public AnimalsGetter getAnimalsGetter() {
        return animalsGetter;
    }
    public AnimalsTypeGetter getAnimalsTypeGetter() {
        return animalsTypeGetter;
    }
}
