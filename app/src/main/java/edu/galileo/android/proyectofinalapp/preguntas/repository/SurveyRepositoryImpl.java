package edu.galileo.android.proyectofinalapp.preguntas.repository;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import edu.galileo.android.proyectofinalapp.models.Opciones;
import edu.galileo.android.proyectofinalapp .models.Preguntas;

/**
 * Created by cbustamante on 18/07/16.
 */

public class SurveyRepositoryImpl implements SurveyRepository{
    private DatabaseReference mDatabase;
    private String TAG="SurveyRepository";
    @Override
    public void onCreate() {
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    @Override
    public void loadDataPreguntas(final Integer preguntaNro, final TextView tvTitulo,final ViewGroup vg, final RadioGroup pRadioGroup, final Context context) {

        Query q1  = mDatabase.child("user-posts").child(this.getUid())
                .orderByChild("nroPregunta");

        if (null != q1) {

            q1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    Log.e("Count " ,""+snapshot.getChildrenCount());
                    for (DataSnapshot postSnapshot: snapshot.getChildren()) {

                        Preguntas post = postSnapshot.getValue(Preguntas.class);
                        Log.e("Get Data", post.title);
                        Log.e("Get Key", postSnapshot.getKey());
                        if (post.nroPregunta == preguntaNro)
                        {
                            tvTitulo.setText(post.title);

                            Query q2  = mDatabase.child("post-comments").child(postSnapshot.getKey());
                            if (null != q2) {

                                q2.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot snapshot) {
                                        Log.e("Count " ,""+snapshot.getChildrenCount());
                                        List<Opciones> listaOpciones = new ArrayList<Opciones>();
                                        for (DataSnapshot postSnapshot: snapshot.getChildren()) {

                                            Opciones post = postSnapshot.getValue(Opciones.class);
                                            Log.d(TAG,"Opciones Data "+  post.text);
                                            listaOpciones.add(post);
                                        }
                                        addRadioButtons(listaOpciones, vg, pRadioGroup,context);
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }

                                });
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else {
            Log.d(TAG, "No query result for pregunta:" + preguntaNro);
        }

    }

    @Override
    public void addRadioButtons(List<Opciones> listaOpciones,ViewGroup vg, RadioGroup rg,Context context) {

//        vg.removeAllViews();
        rg.removeAllViews();
        rg.setOrientation(LinearLayout.VERTICAL);
        int contador=0;
        for (Opciones opcion: listaOpciones) {
            //RadioButton rdbtn= new RadioButton(this,  null, R.attr.radioButtonStyle);
            RadioButton rdbtn= new RadioButton(context);
            rdbtn.setId(contador++);
            rdbtn.setText(opcion.text);
            rg.addView(rdbtn);

        }
        //vg.addView(rg);
        //((ViewGroup) rg.findViewById())

    }
    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }
    @Override
    public void onDestroy() {

    }
}
