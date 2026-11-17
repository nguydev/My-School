package com.edupulse.app.ui.pdf;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.edupulse.app.databinding.FragmentPdfViewerBinding;
import com.edupulse.app.ui.common.ViewAnimationUtils;

public class PdfViewerFragment extends Fragment {

    private FragmentPdfViewerBinding binding;
    private int currentPage = 14;
    private final int totalPages = 48;
    private int currentZoom = 100;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPdfViewerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Apply bounce animations to interactive buttons
        ViewAnimationUtils.applyBounceEffect(
                binding.btnBack,
                binding.btnPrevPage,
                binding.btnNextPage,
                binding.btnExpandFull,
                binding.btnDownloadPdf,
                binding.btnBookmarkPdf,
                binding.btnZoomIn,
                binding.btnZoomOut,
                binding.btnFitScreen,
                binding.btnGridPages,
                binding.btnSearchDoc
        );

        binding.btnBack.setOnClickListener(v -> Navigation.findNavController(v).navigateUp());

        // Previous Page
        binding.btnPrevPage.setOnClickListener(v -> {
            if (currentPage > 1) {
                currentPage--;
                updatePageInfo();
            } else {
                Toast.makeText(requireContext(), "You are on the first page", Toast.LENGTH_SHORT).show();
            }
        });

        // Next Page
        binding.btnNextPage.setOnClickListener(v -> {
            if (currentPage < totalPages) {
                currentPage++;
                updatePageInfo();
            } else {
                Toast.makeText(requireContext(), "You have reached the last page", Toast.LENGTH_SHORT).show();
            }
        });

        // Zoom In & Out
        binding.btnZoomIn.setOnClickListener(v -> {
            if (currentZoom < 200) {
                currentZoom += 25;
                binding.tvZoomLevel.setText(currentZoom + "%");
                binding.pdfScrollView.animate().scaleX(currentZoom / 100f).scaleY(currentZoom / 100f).setDuration(150).start();
            }
        });

        binding.btnZoomOut.setOnClickListener(v -> {
            if (currentZoom > 75) {
                currentZoom -= 25;
                binding.tvZoomLevel.setText(currentZoom + "%");
                binding.pdfScrollView.animate().scaleX(currentZoom / 100f).scaleY(currentZoom / 100f).setDuration(150).start();
            }
        });

        binding.btnDownloadPdf.setOnClickListener(v -> 
            Toast.makeText(requireContext(), "Saving 'SSC Physics – Chapter 03.pdf' to device storage...", Toast.LENGTH_SHORT).show()
        );

        binding.btnBookmarkPdf.setOnClickListener(v -> 
            Toast.makeText(requireContext(), "Page " + currentPage + " bookmarked!", Toast.LENGTH_SHORT).show()
        );

        binding.btnExpandFull.setOnClickListener(v -> 
            Toast.makeText(requireContext(), "Toggled Fullscreen Reading Mode", Toast.LENGTH_SHORT).show()
        );

        binding.btnSearchDoc.setOnClickListener(v -> 
            Toast.makeText(requireContext(), "Search in document...", Toast.LENGTH_SHORT).show()
        );

        binding.btnGridPages.setOnClickListener(v -> 
            Toast.makeText(requireContext(), "Opening page thumbnail grid...", Toast.LENGTH_SHORT).show()
        );
    }

    private void updatePageInfo() {
        binding.tvProgressionFraction.setText(currentPage + " / " + totalPages);
        binding.tvCurrentPagePill.setText("🔵 Page " + currentPage + " of " + totalPages);
        binding.progressPdf.setProgress(currentPage);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
