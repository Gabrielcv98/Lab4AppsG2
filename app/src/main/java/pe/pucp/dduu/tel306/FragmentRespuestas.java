package pe.pucp.dduu.tel306;

import android.content.Context;
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
import pe.pucp.dduu.tel306.Entidades.Respuestas;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentRespuestas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentRespuestas extends Fragment {

    private static List<Respuestas> lista;
   private static Context context;



    public FragmentRespuestas() {
        // Required empty public constructor
    }

    public static FragmentRespuestas newInstance(List<Respuestas> listarespuestas) {
        FragmentRespuestas fragment = new FragmentRespuestas();
        lista=listarespuestas;
        //fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    ListaOpcionesAdapter listaOpcionesAdapter;
    RecyclerView recyclerViewRespuestas;
    ArrayList<Respuestas> listaRespuestas;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_respuestas, container, false);
        recyclerViewRespuestas = view.findViewById(R.id.recyclerViewRespuestas);
        recyclerViewRespuestas.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        listaRespuestas = new ArrayList<>();
        llenarLista();

        return view;
    }

    private void llenarLista() {
        listaRespuestas.add((Respuestas) lista);
    }

    public void cargarLista(){
        listaRespuestas.add(new Respuestas());
    }
}