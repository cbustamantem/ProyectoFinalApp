package edu.galileo.android.proyectofinalapp.preguntas.repository;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

import edu.galileo.android.proyectofinalapp.models.Opciones;

/**
 * Created by cbustamante on 18/07/16.
 */

public interface SurveyRepository {
    void onCreate();
    void loadDataPreguntas(Integer preguntaNro, TextView tvTitulo,  ViewGroup vg, RadioGroup pRadioGroup,Context context);
    void addRadioButtons(List<Opciones> listaOpciones, ViewGroup vg, RadioGroup rg,Context context);
    void onDestroy();

}
