package id.ac.astra.polytechnic.internak.ui.cage;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import id.ac.astra.polytechnic.internak.MainActivity;
import id.ac.astra.polytechnic.internak.R;
import id.ac.astra.polytechnic.internak.databinding.FragmentCreateCageBinding;
import id.ac.astra.polytechnic.internak.databinding.FragmentIotBinding;

public class CreteIot extends Fragment {

    private FragmentIotBinding binding;
    private OnCreateIoTBackClickListener listener;

    public interface OnCreateIoTBackClickListener {
        void OnCreateIoTBackClickListener();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentIotBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView textView = binding.nama; // Pastikan ID sesuai dengan yang ada di layout XML
        String text = "Nama Perangkat";
        textView.setText(text);

        TextView kodetextView = binding.kode; // Pastikan ID sesuai dengan yang ada di layout XML
        String jenis = "Kode Perangkat";
        kodetextView.setText(jenis);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imageView = view.findViewById(R.id.button_back_detail);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.OnCreateIoTBackClickListener();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) getActivity();
            setOnCreateIoTBackClickListener(mainActivity);
        }
    }

    public void setOnCreateIoTBackClickListener(OnCreateIoTBackClickListener listener) {
        this.listener = listener;
    }

}
