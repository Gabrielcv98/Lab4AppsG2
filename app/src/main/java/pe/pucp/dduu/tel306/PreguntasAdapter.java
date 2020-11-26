package pe.pucp.dduu.tel306;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pe.pucp.dduu.tel306.Entidades.Preguntas;

public class PreguntasAdapter extends RecyclerView.Adapter<PreguntasAdapter.ViewHolder> {

    LayoutInflater inflater;
    List<Preguntas> model;

    public  PreguntasAdapter(Context context, List<Preguntas> model){
        this.inflater = LayoutInflater.from(context);
        this.model = model;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.lista_preguntas,parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String pregunta = model.get(position).getQuestionText();
        String fechaPregunta = model.get(position).getQuestionDate();
        int idPregunta = model.get(position).getId();
        holder.preguntas.setText(pregunta);
        holder.fechaPregunta.setText(fechaPregunta);
        holder.id.setText(idPregunta);

    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView preguntas , fechaPregunta , id;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            preguntas = itemView.findViewById(R.id.pregunta);
            fechaPregunta = itemView.findViewById(R.id.fechaPregunta);
            id = itemView.findViewById(R.id.idPregunta);

        }
    }
}
