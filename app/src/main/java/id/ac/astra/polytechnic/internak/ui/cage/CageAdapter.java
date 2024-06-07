package id.ac.astra.polytechnic.internak.ui.cage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.astra.polytechnic.internak.R;

public class CageAdapter extends RecyclerView.Adapter<CageAdapter.CageViewHolder> {
    private List<Cage> cageItemList;

    public CageAdapter(List<Cage> cageItemList) {
        this.cageItemList = cageItemList;
    }

    @NonNull
    @Override
    public CageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_cage, parent, false);
        return new CageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CageViewHolder holder, int position) {
        Cage cageItem = cageItemList.get(position);
        // Atur data ke dalam tampilan holder
    }

    @Override
    public int getItemCount() {
        return cageItemList.size();
    }

    public static class CageViewHolder extends RecyclerView.ViewHolder {
        // Deklarasikan tampilan yang akan digunakan di dalam layout item
        public CageViewHolder(@NonNull View itemView) {
            super(itemView);
            // Inisialisasi tampilan
        }
    }
}
