package com.edupulse.app.ui.book;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.edupulse.app.R;
import com.edupulse.app.databinding.FragmentBookDetailBinding;

import com.edupulse.app.ui.common.ViewAnimationUtils;

public class BookDetailFragment extends Fragment {

    private FragmentBookDetailBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentBookDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        // Touch feedback animation
        ViewAnimationUtils.applyBounceEffect(
                binding.btnBack,
                binding.btnBrowseChapters,
                binding.cardVideoMode,
                binding.itemChapter1,
                binding.itemChapter2,
                binding.btnViewPdf,
                binding.btnDownloadBook
        );

        setupClickListeners(view);
    }

    private void setupClickListeners(View rootView) {
        binding.btnBack.setOnClickListener(v -> 
            Navigation.findNavController(rootView).navigateUp()
        );

        // Browse Chapters & Videos Button -> Navigates to Chapter Roadmap!
        binding.btnBrowseChapters.setOnClickListener(v -> 
            Navigation.findNavController(rootView).navigate(R.id.action_bookDetail_to_chapterRoadmap)
        );

        binding.cardVideoMode.setOnClickListener(v -> 
            Navigation.findNavController(rootView).navigate(R.id.action_bookDetail_to_chapterRoadmap)
        );

        binding.itemChapter1.setOnClickListener(v -> 
            Navigation.findNavController(rootView).navigate(R.id.action_bookDetail_to_chapterRoadmap)
        );

        binding.itemChapter2.setOnClickListener(v -> 
            Navigation.findNavController(rootView).navigate(R.id.action_bookDetail_to_chapterRoadmap)
        );

        binding.tvViewFullSyllabus.setOnClickListener(v -> 
            Navigation.findNavController(rootView).navigate(R.id.action_bookDetail_to_chapterRoadmap)
        );

        // View PDF Button -> Navigates directly to PDF Viewer Screen!
        binding.btnViewPdf.setOnClickListener(v -> 
            Navigation.findNavController(rootView).navigate(R.id.action_bookDetail_to_pdfViewer)
        );

        binding.btnDownloadBook.setOnClickListener(v -> 
            Toast.makeText(requireContext(), "Downloading SSC Physics Textbook (48.6 MB)...", Toast.LENGTH_SHORT).show()
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
