package com.example.gabriele.play4me;

import java.io.Serializable;

/**
 * Created by Gabriele on 13/02/2017.
 */

public class Factory implements Serializable {
    public static Person nuovoUtente() {
        return new Person("Rocco", "Rocky", "Brock", 24, 7, "Rockenburg", "rocco.png", "rocco2.png");
    }
}
