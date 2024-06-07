package id.ac.astra.polytechnic.internak.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.ac.astra.polytechnic.internak.R;
import id.ac.astra.polytechnic.internak.databinding.FragmentHomeBinding;
import id.ac.astra.polytechnic.internak.ui.cage.Cage;
import id.ac.astra.polytechnic.internak.ui.cage.CageAdapter;
import id.ac.astra.polytechnic.internak.ui.article.Article;
import id.ac.astra.polytechnic.internak.ui.article.ArticleAdapter;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private OnNotificationClickListener listener;
    private ArticleAdapter articleAdapter;
    private List<Article> articleList;
    private List<Cage> cageItemList;
    private static final String TAG = "HomeFragment";

    // Antarmuka untuk memberi tahu MainActivity saat gambar diklik
    public interface OnNotificationClickListener {
        void onNotificationClicked();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Log.d(TAG, "onCreateView: setupRecyclerView akan dipanggil");
        setupRecyclerView();

        return root;
    }

    private void setupRecyclerView() {
        articleList = new ArrayList<>();
        articleList.add(new Article("Perbedaan Daging Sapi...", "4 April 2024"));
        articleList.add(new Article("Perbedaan Daging Sapi...", "4 April 2024"));
        articleList.add(new Article("Perbedaan Daging Sapi...", "4 April 2024"));
        articleList.add(new Article("Perbedaan Daging Sapi...", "4 April 2024"));
        articleList.add(new Article("Perbedaan Daging Sapi...", "4 April 2024"));
        articleList.add(new Article("Cara Merawat Ayam...", "5 April 2024"));
        // Tambahkan artikel lainnya

        Log.d(TAG, "setupRecyclerView: articleList size = " + articleList.size());

        articleAdapter = new ArticleAdapter(articleList);
        RecyclerView recyclerView = binding.recyclerViewArticles;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(articleAdapter);

        Log.d(TAG, "setupRecyclerView: RecyclerView setup selesai");

        cageItemList = new ArrayList<>();
        cageItemList.add(new Cage("HARVEST MOON", "4 April 2024", "Bandung", 200, 25, 20));
        cageItemList.add(new Cage("HARVEST MOON", "4 April 2024", "Bandung", 200, 25, 20));
        cageItemList.add(new Cage("HARVEST MOON", "4 April 2024", "Bandung", 200, 25, 20));
        cageItemList.add(new Cage("HARVEST MOON", "4 April 2024", "Bandung", 200, 25, 20));

        CageAdapter cageAdapter = new CageAdapter(cageItemList);
        RecyclerView recyclerViewCages = binding.recyclerViewCages;
        recyclerViewCages.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCages.setAdapter(cageAdapter);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // Menghubungkan antarmuka ke MainActivity saat fragment terhubung
        if (context instanceof OnNotificationClickListener) {
            listener = (OnNotificationClickListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement HomeFragment.OnNotificationClickListener");
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Panggil moveToNotificationFragment saat gambar diklik
        ImageView imageView = view.findViewById(R.id.buttonNotification);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onNotificationClicked();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
