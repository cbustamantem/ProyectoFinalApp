package edu.galileo.android.proyectofinalapp.preguntas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.galileo.android.proyectofinalapp.R;
import edu.galileo.android.proyectofinalapp.preguntas.presenter.SurveyPresenter;
import edu.galileo.android.proyectofinalapp.preguntas.presenter.SurveyPresenterImpl;

public class SurveyActivity extends AppCompatActivity {


    private String TAG ="Survey";
    private int preguntaNro;

    private SurveyPresenter presenter;

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

    public ViewGroup vg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        ButterKnife.bind(this);
        vg = (ViewGroup) findViewById(R.id.surveyRadioGroup);
        presenter = new SurveyPresenterImpl();
        presenter.onCreate();
        preguntaNro=1;

        presenter.loadDataPreguntas(preguntaNro,titulo,vg,radioGroup,this);



    }
    @OnClick(R.id.btnSiguiente)
    public void siguientePregunta()
    {
        preguntaNro++;
        presenter.loadDataPreguntas(preguntaNro,titulo,vg,radioGroup,this);

    }
    @OnClick(R.id.btnAnterior)
    public void anteriorPregunta()
    {
        if (preguntaNro >1 )
        {
            preguntaNro--;
            presenter.loadDataPreguntas(preguntaNro,titulo,vg,radioGroup,this);
        }

    }


}
