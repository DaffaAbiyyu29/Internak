package id.ac.astra.polytechnic.internak.ui.cage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.astra.polytechnic.internak.R;
import id.ac.astra.polytechnic.internak.model.Cage;

public class CageAdapterC extends RecyclerView.Adapter<CageAdapterC.CageCViewHolder> {
    private List<Cage> cageItemList;

    public CageAdapterC(List<Cage> cageItemList) {
        this.cageItemList = cageItemList;
    }

    @NonNull
    @Override
    public CageAdapterC.CageCViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_create, parent, false);
        return new CageAdapterC.CageCViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CageAdapterC.CageCViewHolder holder, int position) {
        Cage cageItem = cageItemList.get(position);
        // Atur data ke dalam tampilan holder
        holder.cageName.setText(cageItem.getCagName());
        holder.cageLocation.setText(String.valueOf(cageItem.getCtyId()));
        holder.cagekapasitas.setText(String.valueOf(cageItem.getCagCapacity()) + " Ekor");
//        holder.cagesuhu.setText(String.valueOf(cageItem.getTemperature()) + " °C");
//        holder.cageanomia.setText(String.valueOf(cageItem.getAmonia()) + " ppm");
        // Atur data lain sesuai kebutuhan
    }


    @Override
    public int getItemCount() {
        return cageItemList.size();
    }

    public void updateData(List<Cage> newCages) {
        this.cageItemList = newCages;
        notifyDataSetChanged();
    }

    public static class CageCViewHolder extends RecyclerView.ViewHolder {
        public TextView cageName;
        public TextView cageLocation;
        public TextView cagekapasitas;
        public TextView cagesuhu;
        public TextView cageanomia;

        public CageCViewHolder(@NonNull View itemView) {
            super(itemView);
            cageName = itemView.findViewById(R.id.text_card_cage_titleC);
            cageLocation = itemView.findViewById(R.id.lokasi);
            cagekapasitas = itemView.findViewById(R.id.total_kapasitas);
            cagesuhu = itemView.findViewById(R.id.total_suhu);
            cageanomia = itemView.findViewById(R.id.total_anomia);
            // Inisialisasi tampilan lain sesuai dengan yang ada di cardview_cage.xml
        }
    }
}
