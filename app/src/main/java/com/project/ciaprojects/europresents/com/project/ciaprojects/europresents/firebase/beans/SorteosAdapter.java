package com.project.ciaprojects.europresents.com.project.ciaprojects.europresents.firebase.beans;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.project.ciaprojects.europresents.Main2Activity;
import com.project.ciaprojects.europresents.R;

import java.util.List;

/**
 * Created by masdi on 26/01/2018.
 */

public class SorteosAdapter extends RecyclerView.Adapter<SorteosAdapter.SorteosViewHolder>{

    List<Sorteo> sorteos;
    View v;

    public SorteosAdapter(List<Sorteo> sorteos) {
        this.sorteos = sorteos;
    }

    @Override
    public SorteosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler, parent, false);
        SorteosViewHolder holder = new SorteosViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(SorteosViewHolder holder, int position) {
        Sorteo sorteo = sorteos.get(position);
        Main2Activity activity = (Main2Activity) v.getContext();
        StorageReference ref = activity.storage.getReferenceFromUrl(sorteo.getUriPhoto());
        holder.textViewProducto.setText(sorteo.getProducto());
        holder.textViewProgreso.setText(String.valueOf(sorteo.getParticipacionesTotales() + "/" + sorteo.getParticipacionesActuales()));
        Glide.with(v.getContext())
                .using(new FirebaseImageLoader())
                .load(ref)
                .into(holder.imageViewProducto);
        int progress = (int) Math.round(Double.parseDouble(String.valueOf(sorteo.getParticipacionesActuales()))
                /Double.parseDouble(String.valueOf(sorteo.getParticipacionesTotales()))*100);
        holder.progressBar.setProgress(progress);
    }

    @Override
    public int getItemCount() {
        return sorteos.size();
    }

    public static class SorteosViewHolder extends RecyclerView.ViewHolder {

        TextView textViewProducto, textViewProgreso;
        ImageView imageViewProducto;
        ProgressBar progressBar;

        public SorteosViewHolder(View view){
            super(view);

            textViewProducto = (TextView) view.findViewById(R.id.txtview_producto);
            textViewProgreso = (TextView) view.findViewById(R.id.txtview_progreso);
            imageViewProducto = (ImageView) view.findViewById(R.id.image_view_producto);
            progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        }
    }
}
