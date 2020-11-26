package pe.pucp.dduu.tel306;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import pe.pucp.dduu.tel306.Entidades.Preguntas;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragmentPreguntas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragmentPreguntas extends Fragment {
    PreguntasAdapter preguntasAdapter;
    RecyclerView recyclerViewPreguntas;
    ArrayList<Preguntas> listaPreguntas;
    private String[] questionDate;
    private int[] id ;
    private String[] questionText;

    public BlankFragmentPreguntas() {
        // Required empty public constructor
    }

    public static BlankFragmentPreguntas newInstance() {
        BlankFragmentPreguntas fragment = new BlankFragmentPreguntas();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getIntArray("questionId");
            questionDate = getArguments().getStringArray("questionDate");
            questionText = getArguments().getStringArray("questionText");


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_blank_preguntas, container, false);
        recyclerViewPreguntas = recyclerViewPreguntas.findViewById(R.id.recyclerViewPreg);

        listaPreguntas.add(new Preguntas(id[0] , questionDate[0] , questionText[0]));
        listaPreguntas.add(new Preguntas(id[1] , questionDate[1] , questionText[1]));

        recyclerViewPreguntas.setLayoutManager((new LinearLayoutManager(getContext())));
        preguntasAdapter = new PreguntasAdapter(getContext() , listaPreguntas);
        recyclerViewPreguntas.setAdapter(preguntasAdapter);
        return vista;
    }
}