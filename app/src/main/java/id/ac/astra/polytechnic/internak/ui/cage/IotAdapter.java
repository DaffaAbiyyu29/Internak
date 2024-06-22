package id.ac.astra.polytechnic.internak.ui.cage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.astra.polytechnic.internak.R;
import id.ac.astra.polytechnic.internak.api.ApiService;
import id.ac.astra.polytechnic.internak.model.Censor;

public class IotAdapter extends RecyclerView.Adapter<IotAdapter.CensorViewHolder> {
    private List<Censor> censorItemList;
    private ApiService apiService;
    private int selectedPosition = -1;

    public IotAdapter(List<Censor> censorItemList, ApiService apiService) {
        this.censorItemList = censorItemList;
        this.apiService = apiService; // Inisialisasi ApiService
    }

    public void updateData(List<Censor> newData) {
        this.censorItemList = newData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CensorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_iot, parent, false);
        return new CensorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CensorViewHolder holder, int position) {
        final Censor censor = censorItemList.get(position);
        holder.nameTextView.setText(censor.getCns_name());
        holder.descriptionTextView.setText(censor.getCns_description());
        // Set other views here if needed
        holder.radioButton.setOnCheckedChangeListener(null);
        holder.radioButton.setChecked(position == selectedPosition);

        holder.radioButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedPosition = position;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return censorItemList.size();
    }

    public static class CensorViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView descriptionTextView;
        public RadioButton radioButton;

        public CensorViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.censor_name);
            descriptionTextView = itemView.findViewById(R.id.censor_description);
            radioButton = itemView.findViewById(R.id.radio_Max);
        }
    }
}
