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
import com.edupulse.app.databinding.FragmentBookViewerBinding;
import com.edupulse.app.ui.common.ViewAnimationUtils;

public class BookViewerFragment extends Fragment {

    private FragmentBookViewerBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentBookViewerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        // Touch bounce feedback
        ViewAnimationUtils.applyBounceEffect(
                binding.btnBack,
                binding.cardBookPhysics,
                binding.btnOpenBookPhysics,
                binding.btnFullPdfPhysics,
                binding.cardBookChemistry,
                binding.btnOpenBookChem,
                binding.cardBookMath,
                binding.btnOpenBookMath,
                binding.chipAllBooks,
                binding.chipPhysics,
                binding.chipChemistry
        );

        setupClickListeners(view);
    }

    private void setupClickListeners(View rootView) {
        binding.btnBack.setOnClickListener(v -> 
            Navigation.findNavController(rootView).navigateUp()
        );

        // Open Book Physics -> Navigates to Book Detail Screen!
        binding.cardBookPhysics.setOnClickListener(v -> 
            Navigation.findNavController(rootView).navigate(R.id.action_bookViewer_to_bookDetail)
        );
        binding.btnOpenBookPhysics.setOnClickListener(v -> 
            Navigation.findNavController(rootView).navigate(R.id.action_bookViewer_to_bookDetail)
        );

        binding.btnFullPdfPhysics.setOnClickListener(v -> 
            Toast.makeText(requireContext(), "Downloading SSC Physics Full PDF (48 MB)...", Toast.LENGTH_SHORT).show()
        );

        // Chemistry & Math Book Clicks
        binding.btnOpenBookChem.setOnClickListener(v -> 
            Navigation.findNavController(rootView).navigate(R.id.action_bookViewer_to_bookDetail)
        );
        binding.btnOpenBookMath.setOnClickListener(v -> 
            Navigation.findNavController(rootView).navigate(R.id.action_bookViewer_to_bookDetail)
        );

        // Chips
        binding.chipAllBooks.setOnClickListener(v -> 
            Toast.makeText(requireContext(), "Showing All Books (12)", Toast.LENGTH_SHORT).show()
        );
        binding.chipPhysics.setOnClickListener(v -> 
            Toast.makeText(requireContext(), "Filtered: Physics Textbooks", Toast.LENGTH_SHORT).show()
        );
        binding.chipChemistry.setOnClickListener(v -> 
            Toast.makeText(requireContext(), "Filtered: Chemistry Textbooks", Toast.LENGTH_SHORT).show()
        );

        // Download All
        binding.cardDownloadAll.setOnClickListener(v -> 
            Toast.makeText(requireContext(), "Starting Offline Package Download (All PDF Notes)...", Toast.LENGTH_SHORT).show()
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
