package id.ac.astra.polytechnic.internak.ui.cage;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import id.ac.astra.polytechnic.internak.MainActivity;
import id.ac.astra.polytechnic.internak.R;
import id.ac.astra.polytechnic.internak.databinding.FragmentCreateCageBinding;
import id.ac.astra.polytechnic.internak.databinding.FragmentProfileBinding;
import id.ac.astra.polytechnic.internak.ui.notification.NotificationFragment;
import id.ac.astra.polytechnic.internak.ui.profile.ProfileViewModel;

public class CreateCage extends Fragment {
    private FragmentCreateCageBinding binding;
    private OnCreateCageBackClickListener createlistener;

    public interface OnCreateCageBackClickListener {
        void OnCreateCageBackClickListener();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CreateViewModel profileViewModel =
                new ViewModelProvider(this).get(CreateViewModel.class);

        binding = FragmentCreateCageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) getActivity();
            setOnCreateCageBackClickListener(mainActivity);
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
