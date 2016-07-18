package edu.galileo.android.proyectofinalapp.preguntas.presenter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by cbustamante on 18/07/16.
 */

public interface SurveyPresenter {
    void onCreate();
    void onDestroy();
    void loadDataPreguntas(Integer preguntaNro, TextView tvTitulo, ViewGroup vg, RadioGroup pRadioGroup,Context context);


}
