package id.ac.astra.polytechnic.internak.ui.cage;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import id.ac.astra.polytechnic.internak.MainActivity;
import id.ac.astra.polytechnic.internak.R;
import id.ac.astra.polytechnic.internak.api.ApiService;
import id.ac.astra.polytechnic.internak.databinding.FragmentCreateCageBinding;
import id.ac.astra.polytechnic.internak.model.Cage;
import id.ac.astra.polytechnic.internak.model.City;
import id.ac.astra.polytechnic.internak.model.Province;

public class CreateCage extends Fragment {
    private FragmentCreateCageBinding binding;
    private ProvinceAdapter provinceAdapter;
    private ApiService apiService;
    private OnCreateCageBackClickListener createlistener;
    private int ctyId;
    private int prvId;

    public interface OnCreateCageBackClickListener {
        void OnCreateCageBackClickListener();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ProvinceViewModel provinceViewModel;
        CageViewModel cageViewModel = new ViewModelProvider(this).get(CageViewModel.class);

        CreateViewModel profileViewModel =
                new ViewModelProvider(this).get(CreateViewModel.class);

        binding = FragmentCreateCageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Spinner jenisKandang = root.findViewById(R.id.jenis_kandang);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.jenis_kandang_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jenisKandang.setAdapter(adapter);

        TextView textView = binding.nama; // Pastikan ID sesuai dengan yang ada di layout XML
        String text = "Nama Kandang *";
        SpannableString spannableString = new SpannableString(text);
        ForegroundColorSpan redColorSpan = new ForegroundColorSpan(Color.RED);
        spannableString.setSpan(redColorSpan, text.indexOf("*"), text.indexOf("*") + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);

        TextView jenistextView = binding.jenis; // Pastikan ID sesuai dengan yang ada di layout XML
        String jenis = "Jenis Kandang *";
        SpannableString jenisS = new SpannableString(jenis);
        jenisS.setSpan(redColorSpan, jenis.indexOf("*"), jenis.indexOf("*") + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        jenistextView.setText(jenisS);

        TextView lokasitextView = binding.lokasi;
        String lokasi = "Lokasi *";
        SpannableString lokasiS = new SpannableString(lokasi);
        lokasiS.setSpan(redColorSpan, lokasi.indexOf("*"), lokasi.indexOf("*") + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        lokasitextView.setText(lokasiS);

        TextView provinsitextView = binding.provinsi;
        String provinsi = "Provinsi *";
        SpannableString provinsiS = new SpannableString(provinsi);
        provinsiS.setSpan(redColorSpan, provinsi.indexOf("*"), provinsi.indexOf("*") + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        provinsitextView.setText(provinsiS);

        TextView kotatextView = binding.kota;
        String kota = "Kota *";
        SpannableString kotasS = new SpannableString(kota);
        kotasS.setSpan(redColorSpan, kota.indexOf("*"), kota.indexOf("*") + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        kotatextView.setText(kotasS);

        TextView kapasitastextView = binding.kapasitas;
        String kapasitas = "Kapasitas Hewan(ekor) *";
        SpannableString kapasitasS = new SpannableString(kapasitas);
        kapasitasS.setSpan(redColorSpan, kapasitas.indexOf("*"), kapasitas.indexOf("*") + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        kapasitastextView.setText(kapasitasS);

        EditText kapasitasHewan = binding.kapasitasHewan; // Ensure ID matches layout XML
        kapasitasHewan.setText("1"); // Set initial value

        kapasitasHewan.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (kapasitasHewan.getRight() - kapasitasHewan.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        int currentValue = Integer.parseInt(kapasitasHewan.getText().toString());
                        int halfHeight = kapasitasHewan.getHeight() / 2;
                        if (event.getY() <= halfHeight) {
                            // Clicked on the upper half, increment the value
                            if (currentValue < 100) {
                                kapasitasHewan.setText(String.valueOf(currentValue + 1));
                            } else {
                                Toast.makeText(getContext(), "Maximum value reached", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Clicked on the lower half, decrement the value
                            if (currentValue > 1) {
                                kapasitasHewan.setText(String.valueOf(currentValue - 1));
                            } else {
                                Toast.makeText(getContext(), "Minimum value reached", Toast.LENGTH_SHORT).show();
                            }
                        }
                        return true;
                    }
                }
                return false;
            }
        });

        Spinner provinsiKandang = root.findViewById(R.id.provinsi_kandang);
        Spinner kotaKandang = root.findViewById(R.id.kota_kandang);

        provinceViewModel = new ViewModelProvider(this).get(ProvinceViewModel.class);
        provinceViewModel.getProvinces().observe(getViewLifecycleOwner(), provinces -> {
            if (provinces != null) {
                List<Province> provinceList = new ArrayList<>(provinces);

                // Create an ArrayAdapter with Province objects
                ArrayAdapter<Province> adapter2 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, provinceList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // Set the adapter to provinsiKandang Spinner
                provinsiKandang.setAdapter(adapter2);
            }
        });

        provinsiKandang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Province selectedProvince = (Province) parent.getItemAtPosition(position);
                prvId = selectedProvince.getPrvId();
                Log.d("PPP", String.valueOf(selectedProvince.getPrvId()));
                provinceViewModel.getCityByProvince(selectedProvince.getPrvId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle case where nothing is selected
            }
        });

        provinceViewModel.getCityByProvinces().observe(getViewLifecycleOwner(), cities -> {
            if (cities != null) {
                List<City> cityList = new ArrayList<>(cities);

                // Create an ArrayAdapter with Province objects
                ArrayAdapter<City> adapter2 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, cityList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // Set the adapter to provinsiKandang Spinner
                kotaKandang.setAdapter(adapter2);
            }
        });

        kotaKandang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                City selectedCity = (City) parent.getItemAtPosition(position);
                ctyId = selectedCity.getCty_id();
                Log.d("PPP2", String.valueOf(selectedCity.getCty_id()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle case where nothing is selected
            }
        });

        cageViewModel.getCreateCageSuccess().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean success) {
                if (success) {
                    Toast.makeText(getContext(), "Kandang berhasil dibuat", Toast.LENGTH_SHORT).show();
                    // Navigasi kembali ke CageFragment
                    createlistener.OnCreateCageBackClickListener();
                } else {
                    Toast.makeText(getContext(), "Gagal membuat kandang", Toast.LENGTH_SHORT).show();
                }
            }
        });

        MaterialButton submitButton = root.findViewById(R.id.button_simpan_kandang);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nama_kandang = root.findViewById(R.id.nama_kandang);
                EditText lokasi_kandang = root.findViewById(R.id.lokasi_kandang);
                EditText kapasitas_hewan = root.findViewById(R.id.kapasitas_hewan);

                Cage cage = new Cage();
                cage.setCagName(nama_kandang.getText().toString());
                cage.setCagType(jenisKandang.getSelectedItem().toString());
                cage.setCagLocation(lokasi_kandang.getText().toString());
                cage.setPrvId(prvId);
                cage.setCtyId(ctyId);
                cage.setCagCapacity(Integer.parseInt(kapasitas_hewan.getText().toString()));
                cage.setCagStatus(1);
                Log.d("PPP3", String.valueOf(cage.getPrvId()));

                cageViewModel.createCage(cage);
            }
        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imageView = view.findViewById(R.id.button_back_cage);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (createlistener != null) {
                    createlistener.OnCreateCageBackClickListener();
                }
            }
        });
    }

    private void setupObservers(ProvinceViewModel provinceViewModel) {
        provinceViewModel.getProvinces().observe(getViewLifecycleOwner(), new Observer<List<id.ac.astra.polytechnic.internak.model.Province>>() {
            @Override
            public void onChanged(List<Province> provinces) {
                if (provinces != null) {
                    Log.d("ProvinceViewModel", "Province updated: " + provinces.size());
//                    provinceAdapter.updateData(provinces);
                }
            }
        });
    }

    public void navigateToCageFragment() {
        // Metode ini akan dipanggil dari ViewModel jika pembuatan kandang berhasil
        // Misalnya:
        Toast.makeText(getContext(), "Kandang berhasil dibuat", Toast.LENGTH_SHORT).show();
        createlistener.OnCreateCageBackClickListener();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) getActivity();
            setOnCreateCageBackClickListener((OnCreateCageBackClickListener) mainActivity);
        }
    }

    public void setOnCreateCageBackClickListener(OnCreateCageBackClickListener listener) {
        this.createlistener = listener;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
