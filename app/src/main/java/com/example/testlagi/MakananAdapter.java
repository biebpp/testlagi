package com.example.testlagi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MakananAdapter extends RecyclerView.Adapter<MakananAdapter.ViewHolder> {
    private List<Makanan> listMakanan;
    private Context context;

    public MakananAdapter(Context context, List<Makanan> list) {
        this.context = context;
        this.listMakanan = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.makanan_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Makanan mkn = listMakanan.get(position);
        holder.tvNama.setText(mkn.getMakanan());
        holder.tvDesc.setText(mkn.getDesc());
        holder.tvHarga.setText("Rp. " + mkn.getHarga());
    }

    @Override
    public int getItemCount() { return listMakanan.size(); }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvDesc, tvHarga;
        public ViewHolder(View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tvNama);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            tvHarga = itemView.findViewById(R.id.tvHarga);
        }
    }
}
