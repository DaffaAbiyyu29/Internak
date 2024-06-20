package id.ac.astra.polytechnic.internak.ui.cage;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.astra.polytechnic.internak.R;
import id.ac.astra.polytechnic.internak.api.ApiClient;
import id.ac.astra.polytechnic.internak.api.ApiService;
import id.ac.astra.polytechnic.internak.api.SingleObjectApiResponse;
import id.ac.astra.polytechnic.internak.model.Cage;
import id.ac.astra.polytechnic.internak.model.City;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CageAdapterC extends RecyclerView.Adapter<CageAdapterC.CageCViewHolder> {
    private List<Cage> cageItemList;
    private ApiService apiService = ApiClient.getClient().create(ApiService.class);

    public CageAdapterC(List<Cage> cageItemList, ApiService apiService) {
        this.cageItemList = cageItemList;
        this.apiService = apiService;
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
        holder.cagekapasitas.setText(String.valueOf(cageItem.getCagCapacity()) + " Ekor");
//        holder.cagesuhu.setText(String.valueOf(cageItem.getTemperature()) + " °C");
//        holder.cageanomia.setText(String.valueOf(cageItem.getAmonia()) + " ppm");
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
        }
    }
}
