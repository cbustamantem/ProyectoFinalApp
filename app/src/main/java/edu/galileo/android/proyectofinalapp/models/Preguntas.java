package edu.galileo.android.proyectofinalapp.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

// [START post_class]
@IgnoreExtraProperties
public class Preguntas {

    public String uid;
    public String author;
    public String title;
    public String body;
    public int nroPregunta=0;
    public int starCount = 0;
    public Map<String, Boolean> stars = new HashMap<>();

    public Preguntas() {
        // Default constructor required for calls to DataSnapshot.getValue(Preguntas.class)
    }

    public Preguntas(String uid, String author, String title, String body) {
        this.uid = uid;
        this.author = author;
        this.title = title;
        this.body = body;
    }

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("author", author);
        result.put("title", title);
        result.put("body", body);
        result.put("starCount", starCount);
        result.put("stars", stars);
        result.put("nroPregunta", nroPregunta);

        return result;
    }
    // [END post_to_map]

}
// [END post_class]
