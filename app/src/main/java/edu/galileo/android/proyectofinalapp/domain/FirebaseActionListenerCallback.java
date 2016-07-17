package edu.galileo.android.proyectofinalapp.domain;

//import com.firebase.client.FirebaseError;

/**
 * Created by ykro.
 */
public interface FirebaseActionListenerCallback {
    void onSuccess();
    void onError(Error error);
}
