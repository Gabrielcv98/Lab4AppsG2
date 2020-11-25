package pe.pucp.dduu.tel306;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Registro extends Fragment {

    public Registro() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    View vista;
    RequestQueue rq;
    JsonRequest jrq;
    Button btnCancelar, btnRegistro;
    EditText txtUser, txtPwd, txtNames;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_registro, container, false);
        txtUser = (EditText) vista.findViewById(R.id.emailRegistro);
        txtPwd = (EditText) vista.findViewById(R.id.contraRegistro);
        txtNames= (EditText) vista.findViewById(R.id.nombre);
        //rq = Volley.newRequestQueue(getContext());

        btnCancelar = (Button) vista.findViewById(R.id.buttonCancelar);
        btnRegistro = (Button) vista.findViewById(R.id.buttonAceptarRegistro);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciar_sesion();
            }
        });

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = txtUser.getText().toString();
                String contra = txtPwd.getText().toString();
                String nombre = txtNames.getText().toString();
                MainActivity m2 = (MainActivity) getActivity();
                m2.datosRegistro(nombre, mail, contra);
            }
        });
        return vista;
    }

    /*void registrarUsuario(){
        String url = "http://34.236.191.118:3000/api/v1/users/login";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getContext(), "No Se pudó registrar el usuario " + error.toString()+ txtUser.getText().toString(), Toast.LENGTH_LONG).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "No Se encontró el usuario " +error.toString()+ txtUser.getText().toString(), Toast.LENGTH_LONG).show();
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> parametros = new HashMap<>();

                parametros.put("name",txtNames.getText().toString());
                parametros.put("email", txtUser.getText().toString());
                parametros.put("password", txtPwd.getText().toString());
                return parametros;
            }
        };
        rq.add(stringRequest);
    } */


    void iniciar_sesion() {
        IniciarSesion fr=new IniciarSesion();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.esc_reutilizable,fr)
                .addToBackStack(null)
                .commit();
    }
}