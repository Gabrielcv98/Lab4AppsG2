package pe.pucp.dduu.tel306;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;

import android.view.View;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.esc_reutilizable, new IniciarSesion()).commit();


    }






    }






    public void recibirSesion (final String mail, final String contra){
        final String mailSesion = mail;
        String contraSesion = contra;

        if(isInternetAvailable()){
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String url = "http://34.236.191.118:3000/api/v1/users/login";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Intent intent = new Intent(MainActivity.this, PreguntasActivity.class);
                            startActivity(intent);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Intent intent = new Intent(MainActivity.this, PreguntasActivity.class);
                            startActivity(intent);
                        }
                    })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> parametros = new HashMap<>();

                    parametros.put("username", mail);
                    parametros.put("password", contra);
                    return parametros;
                }
            };

            requestQueue.add(stringRequest);

        }
    }

    public void datosRegistro (final String nombre, final String mail, final String contra){

        if(isInternetAvailable()){
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String url = "http://34.236.191.118:3000/api/v1/users/new";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            //startActivity(intent);
                            Log.d("respuesta", response);
                            Toast.makeText(getApplicationContext(), "Se registró usuario correctamente", Toast.LENGTH_SHORT).show();

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            startActivity(intent);
                            //Log.d("error", error.getLocalizedMessage());
                            Toast.makeText(getApplicationContext(), "No se pudo registrar el usuario ", Toast.LENGTH_LONG).show();
                        }
                    })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> parametros = new HashMap<>();
                    parametros.put("name", nombre);
                    parametros.put("email", mail);
                    parametros.put("password", contra);
                    return parametros;
                }

                /* @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }
                 */
            };

            requestQueue.add(stringRequest);

        }
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