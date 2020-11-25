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
import android.widget.Button;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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



    public void recibirSesion (String data){
        if(isInternetAvailable()){
            final String datos = data;
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String url = "http://34.236.191.118:3000/api/v1/users/login";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                Log.d("respuesta",response);
                                JSONObject objres=new JSONObject(response);
                                String logueo = "Se inició sesión exitosamente";
                                Toast.makeText(getApplicationContext(),logueo,Toast.LENGTH_LONG).show();
                                String id = objres.get("id").toString();
                                Bundle parametros = new Bundle();
                                parametros.putString("id", id);

                                try (FileOutputStream fileOutputStream = openFileOutput("archivo.json",Context.MODE_PRIVATE);
                                     FileWriter fileWriter = new FileWriter(fileOutputStream.getFD());)
                                {
                                    fileWriter.write(objres.toString());
                                    Intent i = new Intent(getApplicationContext(), MainActivity2.class);
                                    i.putExtras(parametros);
                                    startActivity(i);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            } catch (JSONException e) {
                                Toast.makeText(getApplicationContext(),"Server Error",Toast.LENGTH_LONG).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "no se logueo", Toast.LENGTH_SHORT).show();
                }
            })
            {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return datos == null ? null : datos.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        return null;
                    }
                }

            };

            requestQueue.add(stringRequest);

        }
    }

    public void datosRegistro (final String data){

        if(isInternetAvailable()){
            final String savedata= data;
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String url = "http://34.236.191.118:3000/api/v1/users/new";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getApplicationContext(), "Registro finalizado exitosamente", Toast.LENGTH_SHORT).show();
                            Log.d("respuesta",response);
                            Log.d("datos mandados", data);
                            try {
                                JSONObject objres=new JSONObject(data);
                                reformarDatosParaLogin(objres);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }



                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "No se pudo realizar el registro", Toast.LENGTH_SHORT).show();
                }
            })
            {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return savedata == null ? null : savedata.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        //Log.v("Unsupported Encoding while trying to get the bytes", data);
                        return null;
                    }
                }

            };

            requestQueue.add(stringRequest);

        }
    }

    public void reformarDatosParaLogin(JSONObject data) throws JSONException {

        String email = data.get("email").toString();
        String contra = data.get("password").toString();

        String datos = "{"+
                "\"email\":" + "\"" + email + "\","+
                "\"password\":" + "\"" + contra + "\""+
                "}";
        recibirSesion (datos);
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