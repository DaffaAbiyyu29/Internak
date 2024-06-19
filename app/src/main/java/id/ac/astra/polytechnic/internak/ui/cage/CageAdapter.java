package id.ac.astra.polytechnic.internak.ui.cage;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.astra.polytechnic.internak.R;
import id.ac.astra.polytechnic.internak.api.ApiClient;
import id.ac.astra.polytechnic.internak.api.ApiResponse;
import id.ac.astra.polytechnic.internak.api.ApiService;
import id.ac.astra.polytechnic.internak.api.SingleObjectApiResponse;
import id.ac.astra.polytechnic.internak.model.Cage;
import id.ac.astra.polytechnic.internak.model.City;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CageAdapter extends RecyclerView.Adapter<CageAdapter.CageViewHolder> {
    private List<Cage> cageItemList;
    private ApiService apiService = ApiClient.getClient().create(ApiService.class);

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

        // Tambahkan log untuk memastikan posisi dan item cage benar
        Log.d("CageAdapter", "Cage Id: " + cageItem.getCtyId() + " Cage Name : "+cageItem.getCagName());

        // Memanggil API untuk mendapatkan data kota berdasarkan id kota dari cageItem
        Call<SingleObjectApiResponse<City>> call = apiService.getCityById(cageItem.getCtyId());
        call.enqueue(new Callback<SingleObjectApiResponse<City>>() {
            @Override
            public void onResponse(Call<SingleObjectApiResponse<City>> call, Response<SingleObjectApiResponse<City>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    City city = response.body().getData();
                    Log.d("CageAdapter", "sukses: " + city.getCty_name());
                    holder.cageLocation.setText(city.getCty_name());
                } else {
                    // Tangani respons tidak berhasil
                    Log.e("CageAdapter", "Response not successful or body is null");
                }
            }

            @Override
            public void onFailure(Call<SingleObjectApiResponse<City>> call, Throwable t) {
                Log.e("CageAdapter", "Error: " + t.getMessage());
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
