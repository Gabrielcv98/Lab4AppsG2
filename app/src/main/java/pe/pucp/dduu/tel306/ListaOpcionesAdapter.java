package pe.pucp.dduu.tel306;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pe.pucp.dduu.tel306.Entidades.Preguntas;
import pe.pucp.dduu.tel306.Entidades.Respuestas;

public class ListaOpcionesAdapter extends RecyclerView.Adapter<ListaOpcionesAdapter.OpcionesViewHolder>
implements View.OnClickListener{

    private Respuestas[] listarespuestas;
    private Context context;

    public ListaOpcionesAdapter (Respuestas[] listarespuestas, Context context){
        this.listarespuestas = listarespuestas;
        this.context = context;
    }

    @NonNull
    @Override
    public OpcionesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(context).inflate(R.layout.respuestas_rv, parent, false);
        OpcionesViewHolder opcionesViewHolder = new OpcionesViewHolder(itemview);
        return opcionesViewHolder;
    }

    @Override
    public void onBindViewHolder(OpcionesViewHolder holder, int position) {

        Respuestas respuestas = listarespuestas[position];
        String data = respuestas.getAnswerText();
        holder.asignarDatos(data);

    }

    @Override
    public int getItemCount() {
        return listarespuestas.length;
    }

    private View.OnClickListener listener;

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null){
            listener.onClick(v);
        }
    }

    public static class OpcionesViewHolder extends RecyclerView.ViewHolder{

        TextView opciones;
        public OpcionesViewHolder(@NonNull View itemView) {
            super(itemView);
            opciones = itemView.findViewById(R.id.textViewOpciones);
        }

        public void asignarDatos(String data) {
            opciones.setText(data);
        }
    }
}
