package edu.galileo.android.proyectofinalapp.preguntas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import java.util.Map;
import java.util.TreeMap;

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
    private Map<Integer,Preguntas> mapaPreguntas;
    private Map<Integer,List<Opciones>> mapaOpciones;
    private Query mref;
    private ValueEventListener mPostListener;
    private String mPostKey;
    private String TAG ="Survey";
    private int preguntaNro;

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
        mDatabase = FirebaseDatabase.getInstance().getReference();
        preguntaNro=1;
        //Carga de información de los mapas de preguntas
        this.mapaPreguntas = this.loadData();
        addRadioButtons(this.cargarOpciones());
        mPostKey = this.getCurrentObjectKey();
        Log.d(TAG, "SurveyActivity key:" + mPostKey);

    }
    @OnClick(R.id.btnSiguiente)
    public void siguientePregunta()
    {
        preguntaNro++;

        Log.d(TAG, "SurveyActivity key:" + mPostKey);
    }
    @OnClick(R.id.btnAnterior)
    public void anteriorPregunta()
    {
        preguntaNro++;
        mPostKey = this.getCurrentObjectKey();
        Log.d(TAG, "SurveyActivity key:" + mPostKey);
    }

    private Map<Integer,Preguntas> loadDataPreguntas()
    {
        Map<Integer, Preguntas> lista = new TreeMap<Integer,Preguntas>();
        /*Query myTopPostsQuery = mDatabase.child("user-posts").child(myUserId)
                .orderByChild("starCount");*/
        /*mref = FirebaseDatabase.getInstance().getReference()
                .child("posts").equalTo("nroPregunta",String.valueOf(this.preguntaNro));*/
         mref  = mDatabase.child("user-posts").child(this.getUid())
                 .orderByChild("nroPregunta");

      /*  mref = FirebaseDatabase.getInstance().getReference()
                .child("posts").orderByChild("nroPregunta");*/

        if (null != mref) {
            mapaPreguntas = new TreeMap<>();
            mref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    Log.e("Count " ,""+snapshot.getChildrenCount());
                    for (DataSnapshot postSnapshot: snapshot.getChildren()) {

                        Preguntas post = postSnapshot.getValue(Preguntas.class);
                        lista.put(post.nroPregunta,post);
                        Log.e("Get Data", post.title);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });
           // mAdapter
            //Log.d(TAG, "SurveyActivity registros:" + mAdapter.getItemCount());

            Log.d(TAG, "SurveyActivity key:" + mref.getRef());
            return lista ;
        }
        return lista;

    }
    public void addRadioButtons(List<Opciones> listaOpciones) {

         RadioGroup radioGroup= new RadioGroup(this);
        radioGroup.setOrientation(LinearLayout.VERTICAL);


        for (Opciones opcion: listaOpciones) {
            //RadioButton rdbtn= new RadioButton(this,  null, R.attr.radioButtonStyle);
            RadioButton rdbtn= new RadioButton(this);
            rdbtn.setId(opcion.nroOpcion);
            rdbtn.setText(opcion.text);
            radioGroup.addView(rdbtn);
        }
        ((ViewGroup) findViewById(R.id.radiogroup)).addView(radioGroup);

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
