package id.ac.astra.polytechnic.internak.ui.cage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.astra.polytechnic.internak.R;
import id.ac.astra.polytechnic.internak.model.Cage;

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
        holder.cageName.setText(cageItem.getCagName());
//        holder.cageType.setText(cageItem.getCagType());
        holder.cageLocation.setText(String.valueOf(cageItem.getCtyId()));
        holder.cageQty.setText(String.valueOf(cageItem.getCagCapacity()) + " Ekor");
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

    public static class CageViewHolder extends RecyclerView.ViewHolder {
        TextView cageName;
        TextView cageType;
        TextView cageLocation;
        TextView cageQty;

        public CageViewHolder(@NonNull View itemView) {
            super(itemView);
            cageName = itemView.findViewById(R.id.text_card_cage_title);
            cageType = itemView.findViewById(R.id.text_card_cage_date);
            cageLocation = itemView.findViewById(R.id.text_card_cage_location);
            cageQty = itemView.findViewById(R.id.text_card_cage_qty);
            // Inisialisasi tampilan lain sesuai dengan yang ada di cardview_cage.xml
        }
    }
}
