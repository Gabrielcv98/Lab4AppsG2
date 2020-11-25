package pe.pucp.dduu.tel306;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle parametros = this.getIntent().getExtras();
        String id = parametros.getString("id");
        Log.d("id",id);

        TextView textoprueba = (TextView) findViewById(R.id.textView1);
        textoprueba.setText(id);

    }






}