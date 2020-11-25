package pe.pucp.dduu.tel306;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragmentPreguntas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragmentPreguntas extends Fragment {




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
        return inflater.inflate(R.layout.fragment_blank_preguntas, container, false);
    }
}