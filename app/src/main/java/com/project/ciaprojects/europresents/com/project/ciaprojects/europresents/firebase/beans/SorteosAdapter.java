package com.project.ciaprojects.europresents.com.project.ciaprojects.europresents.firebase.beans;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import com.project.ciaprojects.europresents.R;

import java.util.List;

/**
 * Created by masdi on 26/01/2018.
 */

public class SorteosAdapter extends RecyclerView.Adapter<SorteosAdapter.SorteosViewHolder>{

    List<Sorteo> sorteos;

    public SorteosAdapter(List<Sorteo> sorteos) {
        this.sorteos = sorteos;
    }

    @Override
    public SorteosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler, parent, false);
        SorteosViewHolder holder = new SorteosViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(SorteosViewHolder holder, int position) {
        Sorteo sorteo = sorteos.get(position);
        holder.textViewProducto.setText(sorteo.getProducto());
        holder.textViewPartTotales.setText(String.valueOf(sorteo.getParticipacionesTotales()));
        holder.textViewPartActuales.setText(String.valueOf(sorteo.getParticipacionesActuales()));
    }

    @Override
    public int getItemCount() {
        return sorteos.size();
    }

    public static class SorteosViewHolder extends RecyclerView.ViewHolder {

        TextView textViewProducto, textViewPartTotales, textViewPartActuales;

        public SorteosViewHolder(View view){
            super(view);

            textViewProducto = (TextView) view.findViewById(R.id.txtview_producto);
            textViewPartTotales = (TextView) view.findViewById(R.id.txtview_partTotales);
            textViewPartActuales = (TextView) view.findViewById(R.id.txtview_partActuales);
        }
    }
}
