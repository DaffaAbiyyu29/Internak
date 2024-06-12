package id.ac.astra.polytechnic.internak.ui.cage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.astra.polytechnic.internak.R;
import id.ac.astra.polytechnic.internak.api.ApiResponse;
import id.ac.astra.polytechnic.internak.api.ApiService;
import id.ac.astra.polytechnic.internak.model.Cage;
import id.ac.astra.polytechnic.internak.model.City;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CageAdapter extends RecyclerView.Adapter<CageAdapter.CageViewHolder> {
    private List<Cage> cageItemList;
    private ApiService apiService; // Tambahkan atribut ApiService

    public CageAdapter(List<Cage> cageItemList, ApiService apiService) {
        this.cageItemList = cageItemList;
        this.apiService = apiService; // Inisialisasi ApiService
    }

    @NonNull
    @Override
    public CageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_cage, parent, false);
        return new CageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CageViewHolder holder, int position) {
        final Cage cageItem = cageItemList.get(position);

        // Panggil metode getCityById untuk mendapatkan data kota berdasarkan ID
        apiService.getCityById(cageItem.getCtyId()).enqueue(new Callback<ApiResponse<City>>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse<City>> call, @NonNull Response<ApiResponse<City>> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getData() != null) {
                    City city = (City) response.body().getData();
                    holder.cageLocation.setText(city.getCty_name()); // Atur teks berdasarkan nama kota
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse<City>> call, @NonNull Throwable t) {
                // Tangani kegagalan jika terjadi
            }
        });

        holder.cageName.setText(cageItem.getCagName());
        holder.cageQty.setText(String.valueOf(cageItem.getCagCapacity()) + " Ekor");
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
        TextView cageLocation;
        TextView cageQty;

        public CageViewHolder(@NonNull View itemView) {
            super(itemView);
            cageName = itemView.findViewById(R.id.text_card_cage_title);
            cageLocation = itemView.findViewById(R.id.text_card_cage_location);
            cageQty = itemView.findViewById(R.id.text_card_cage_qty);
        }
    }
}
