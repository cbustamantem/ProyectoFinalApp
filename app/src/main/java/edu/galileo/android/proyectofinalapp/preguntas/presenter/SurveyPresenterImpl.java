package edu.galileo.android.proyectofinalapp.preguntas.presenter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import edu.galileo.android.proyectofinalapp.preguntas.repository.SurveyRepository;
import edu.galileo.android.proyectofinalapp.preguntas.repository.SurveyRepositoryImpl;

/**
 * Created by cbustamante on 18/07/16.
 */

public class SurveyPresenterImpl implements SurveyPresenter {
    private SurveyRepository repository;

    @Override
    public void onCreate() {
        repository = new SurveyRepositoryImpl();
        repository.onCreate();

    }

    @Override
    public void onDestroy() {
        repository.onDestroy();

    }

    @Override
    public void loadDataPreguntas(Integer nroPregunta, TextView tvTitulo, ViewGroup vg, RadioGroup pRadioGroup,Context context) {
        repository.loadDataPreguntas(nroPregunta,tvTitulo,vg,pRadioGroup,context);
    }

}
