package edu.galileo.android.proyectofinalapp.preguntas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.galileo.android.proyectofinalapp.R;
import edu.galileo.android.proyectofinalapp.models.Opciones;
import edu.galileo.android.proyectofinalapp.models.Preguntas;
import edu.galileo.android.proyectofinalapp.preguntas.viewholder.PostViewHolder;

public class SurveyActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseRecyclerAdapter<Preguntas, PostViewHolder> mAdapter;
    private Query mref;
    private ValueEventListener mPostListener;
    private String mPostKey;
    private String TAG ="Survey";
    private int preguntaNro;

    @Bind(R.id.surveyReciclerView)
    public RelativeLayout rView;

    @Bind(R.id.surveyRadioGroup)
    public RadioGroup radioGroup;


    @Bind(R.id.post_title)
    public TextView titulo;
    @Bind(R.id.btnSiguiente)
    public Button btnSiguiente;
    @Bind(R.id.btnAnterior)
    public Button btnAnterior;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        ButterKnife.bind(this);
        radioGroup= new RadioGroup(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        preguntaNro=1;
        this.loadDataPreguntas(rView,radioGroup);

        Log.d(TAG, "SurveyActivity key:" + mPostKey);

    }
    @OnClick(R.id.btnSiguiente)
    public void siguientePregunta()
    {
        preguntaNro++;
        this.loadDataPreguntas(rView,radioGroup);

        Log.d(TAG, "SurveyActivity key:" + mPostKey);
    }
    @OnClick(R.id.btnAnterior)
    public void anteriorPregunta()
    {
        if (preguntaNro >1 )
        {
            preguntaNro--;
            this.loadDataPreguntas(rView,radioGroup);
            Log.d(TAG, "SurveyActivity key:" + mPostKey);
        }

    }

    public void assignVC()
    {

    }

    private void loadDataPreguntas(final RelativeLayout pReciclerView, final RadioGroup pRadioGroup)
    {


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
                            titulo.setText(post.title);

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
                                        addRadioButtons(listaOpciones,pReciclerView, pRadioGroup);
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
            Log.d(TAG, "No query result for pregunta:" + this.preguntaNro);
        }



    }
    public void addRadioButtons(List<Opciones> listaOpciones, RelativeLayout rView, RadioGroup rg) {

        //rView.get

        ((ViewGroup) findViewById(R.id.surveyRadioGroup)).removeAllViews();
        rg.removeAllViews();
        rg.setOrientation(LinearLayout.VERTICAL);
        int contador=0;
        for (Opciones opcion: listaOpciones) {
            //RadioButton rdbtn= new RadioButton(this,  null, R.attr.radioButtonStyle);
            RadioButton rdbtn= new RadioButton(this);
            rdbtn.setId(contador++);
            rdbtn.setText(opcion.text);
            rg.addView(rdbtn);

        }
        rView.addView(rg);

    }
    private List<Opciones> cargarOpciones(){
        List<Opciones> lista = new ArrayList<Opciones>();
        Opciones opcion1 = new Opciones();
        opcion1.nroOpcion=1;
        opcion1.text="Opcion 1";
        lista.add(opcion1);

        Opciones opcion2 = new Opciones();
        opcion2.nroOpcion=2;
        opcion2.text="Opcion 2";
        lista.add(opcion2);

        return lista;
    }
    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

}
