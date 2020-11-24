package pe.pucp.dduu.tel306;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragmentRegistro#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragmentRegistro extends Fragment {

    View vista;

    public BlankFragmentRegistro() {
        // Required empty public constructor
    }


    public static BlankFragmentRegistro newInstance() {
        BlankFragmentRegistro fragment = new BlankFragmentRegistro();

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


        vista =inflater.inflate(R.layout.fragment_blank_registro, container, false);
        TextView btnIniciarSesion = (TextView) vista.findViewById(R.id.inicioSesionID);

        btnIniciarSesion.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "FUNCIONA", Toast.LENGTH_SHORT).show();

            }
        });
        return vista;
    }



}