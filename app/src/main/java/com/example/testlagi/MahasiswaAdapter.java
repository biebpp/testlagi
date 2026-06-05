package com.example.testlagi;

import android.content.*;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.*;

import java.util.List;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.ViewHolder> {
    private List<Mahasiswa> listMahasiswa;
    private Context context;

    public MahasiswaAdapter(Context context, List<Mahasiswa> list) {
        this.context = context;
        this.listMahasiswa = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mahasiswa_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Mahasiswa mhs = listMahasiswa.get(position);
        holder.tvNama.setText(mhs.getNama());
        holder.tvNIM.setText("NIM : " + mhs.getNIM());
        holder.tvProdi.setText(mhs.getProdi());
        holder.tvIPK.setText("IPK : " + mhs.getIpk());
    }

    @Override
    public int getItemCount() { return listMahasiswa.size(); }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvNIM, tvProdi, tvIPK;
        public ViewHolder(View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tvNamaMahasiswa);
            tvNIM = itemView.findViewById(R.id.tvNIMMahasiswa);
            tvProdi = itemView.findViewById(R.id.tvProdiMahasiswa);
            tvIPK = itemView.findViewById(R.id.tvIPKMahasiswa);
        }
    }
}
