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
import id.ac.astra.polytechnic.internak.api.ApiService;
import id.ac.astra.polytechnic.internak.api.SingleObjectApiResponse;
import id.ac.astra.polytechnic.internak.model.City;
import id.ac.astra.polytechnic.internak.model.Province;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProvinceAdapter {
    private List<Province> provinceList;
    private ApiService apiService;

    public ProvinceAdapter(List<Province> provinceList) {
        this.provinceList = provinceList;
        this.apiService = ApiClient.getClient().create(ApiService.class); // Inisialisasi ApiService
    }

//    @NonNull
//    @Override
//    public ProvinceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_province, parent, false); // Pastikan layout sesuai
//        return new ProvinceViewHolder(view);
//    }

//    @Override
//    public void onBindViewHolder(@NonNull final ProvinceViewHolder holder, int position) {
//        final Province province = provinceList.get(position);
//
//        // Tambahkan log untuk memastikan posisi dan item province benar
//        Log.d("ProvinceAdapter", "Province Id: " + province.getPrvId() + " Province Name: " + province.getPrvName());
//
//        // Memanggil API untuk mendapatkan data kota berdasarkan id kota dari province
//        Call<SingleObjectApiResponse<City>> call = apiService.getCityById(province.getPrvId());
//        call.enqueue(new Callback<SingleObjectApiResponse<City>>() {
//            @Override
//            public void onResponse(Call<SingleObjectApiResponse<City>> call, Response<SingleObjectApiResponse<City>> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    City city = response.body().getData();
//                    Log.d("ProvinceAdapter", "City retrieved: " + city.getCty_name());
//                    holder.cityName.setText(city.getCty_name());
//                } else {
//                    // Tangani respons tidak berhasil
//                    Log.e("ProvinceAdapter", "Response not successful or body is null");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SingleObjectApiResponse<City>> call, Throwable t) {
//                Log.e("ProvinceAdapter", "Error: " + t.getMessage());
//            }
//        });
//
//        holder.provinceName.setText(province.getPrvName());
//    }
//
//    @Override
//    public int getItemCount() {
//        return provinceList.size();
//    }

//    public void updateData(List<Province> newProvinces) {
//        this.provinceList = newProvinces;
//        notifyDataSetChanged();
//    }
//
//    public static class ProvinceViewHolder extends RecyclerView.ViewHolder {
//        TextView provinceName;
//        TextView cityName;
//
//        public ProvinceViewHolder(@NonNull View itemView) {
//            super(itemView);
//            provinceName = itemView.findViewById(R.id.text_card_province_title); // Pastikan ID sesuai
//            cityName = itemView.findViewById(R.id.text_card_city_name); // Pastikan ID sesuai
//        }
//    }
}
