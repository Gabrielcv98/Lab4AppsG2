package pe.pucp.dduu.tel306;

import
        androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.fragment.app.FragmentTransaction;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;

import android.widget.Toast;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import pe.pucp.dduu.tel306.Entidades.Preguntas;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import pe.pucp.dduu.tel306.Entidades.Preguntas;
import pe.pucp.dduu.tel306.Entidades.Respuestas;

public class PreguntasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);
    }


    public void obtenerPreguntas(View view) {
        if (isInternetAvailable()) {

            final int[] arrayId = new int[0];
            final String[] arrayQuestionDate = new String[0];
            final String[] arrayQuestionText = new String[0];



            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String url = "http://34.236.191.118:3000/api/v1/questions/";

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.d("infoWS", response);

                            Gson gson = new Gson();
                            Preguntas[] preguntasarray = gson.fromJson(response, Preguntas[].class);

                            int index = 0;
                            for (Preguntas preguntas : preguntasarray) {
                                arrayId[index] = preguntas.getId();
                                arrayQuestionDate[index] = preguntas.getQuestionDate();
                                arrayQuestionText[index] = preguntas.getQuestionText();
                                index++;
                            }


                            Bundle bundle = new Bundle();
                            bundle.putStringArray("questionDate", arrayQuestionDate);
                            bundle.putIntArray("questionId", arrayId);
                            bundle.putStringArray("questionText", arrayQuestionText);

                            BlankFragmentPreguntas fragmentPreguntas = new BlankFragmentPreguntas();
                            fragmentPreguntas.setArguments(bundle);
                            FragmentManager fm = getSupportFragmentManager();
                            FragmentTransaction ft = fm.beginTransaction();
                            ft.add(R.id.containerPreguntas, fragmentPreguntas, null);
                            ft.commit();

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {

            };

            requestQueue.add(stringRequest);

        }

    }

    public void obtenerRespuestas(String id){

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://34.236.191.118:3000/api/v1/questions/" + id;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                Preguntas pregunta = gson.fromJson(response, Preguntas.class);
                List<Respuestas> listarespuestas = Arrays.asList(pregunta.getAnswers());

                if (listarespuestas.isEmpty()){
                    Toast.makeText(PreguntasActivity.this, "no hay respuestas", Toast.LENGTH_SHORT).show();
                }else {
                    FragmentManager fm = getSupportFragmentManager();
                    Fragment fragment = fm.findFragmentById(R.id.fragment2);
                    if (fragment != null){
                        fm.beginTransaction().remove(fragment).commit();
                    }
                    FragmentRespuestas fragmentRespuestas = FragmentRespuestas.newInstance(listarespuestas);

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

        };
        requestQueue.add(stringRequest);


                            /*Respuestas[] respuestas = pregunta.getAnswers();
                            ListaOpcionesAdapter adapter = new ListaOpcionesAdapter(pregunta.getAnswers(),
                                    PreguntasActivity.this);*/


    }


    public boolean isInternetAvailable() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null)
            return false;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network networks = connectivityManager.getActiveNetwork();
            if (networks == null)
                return false;

            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(networks);
            if (networkCapabilities == null)
                return false;

            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
                return true;
            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
                return true;
            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
                return true;
            return false;
        } else {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null)
                return false;
            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI)
                return true;
            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE)
                return true;
            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_ETHERNET)
                return true;
            return false;

        }

    }



}