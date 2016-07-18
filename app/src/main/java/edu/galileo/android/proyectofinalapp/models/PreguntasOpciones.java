package edu.galileo.android.proyectofinalapp.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cbustamante on 18/07/16.
 */

public class PreguntasOpciones extends Preguntas{
    private List<Opciones> opciones;

    public PreguntasOpciones() {
        this.opciones = new ArrayList<Opciones>();
    }

    public List<Opciones> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<Opciones> opciones) {
        this.opciones = opciones;
    }
}
