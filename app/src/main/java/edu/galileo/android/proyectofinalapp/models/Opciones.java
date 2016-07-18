package edu.galileo.android.proyectofinalapp.models;

import com.google.firebase.database.IgnoreExtraProperties;

// [START comment_class]
@IgnoreExtraProperties
public class Opciones {

    public String uid;
    public String author;
    public String text;
    public int nroOpcion;

    public Opciones() {
        // Default constructor required for calls to DataSnapshot.getValue(Comment.class)
    }

    public Opciones(String uid, String author, String text) {
        this.uid = uid;
        this.author = author;
        this.text = text;
    }

}
// [END comment_class]
