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
import java.util.List;

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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        PreguntasActivity pA= (PreguntasActivity) getActivity();
       List<Preguntas> listaPreguntas =  pA.obtenerPreguntas();

        View vista = inflater.inflate(R.layout.fragment_blank_preguntas, container, false);
        recyclerViewPreguntas = recyclerViewPreguntas.findViewById(R.id.recyclerViewPREGUNTA);



        recyclerViewPreguntas.setLayoutManager((new LinearLayoutManager(getContext())));
        preguntasAdapter = new PreguntasAdapter(getContext() , listaPreguntas);
        recyclerViewPreguntas.setAdapter(preguntasAdapter);

        return vista;
    }
}