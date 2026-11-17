package com.edupulse.app.ui.course;

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
import com.edupulse.app.databinding.FragmentCourseDetailBinding;
import com.edupulse.app.ui.common.ViewAnimationUtils;

public class CourseDetailFragment extends Fragment {

    private FragmentCourseDetailBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentCourseDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        ViewAnimationUtils.applyBounceEffect(
                binding.btnBack,
                binding.btnDownloadPdf,
                binding.cardScienceTrack,
                binding.btnOpenScienceModules,
                binding.cardBusinessTrack,
                binding.cardHumanitiesTrack,
                binding.btnConsult
        );

        setupClickListeners(view);
    }

    private void setupClickListeners(View rootView) {
        binding.btnBack.setOnClickListener(v -> 
            Navigation.findNavController(rootView).navigateUp()
        );

        binding.btnDownloadPdf.setOnClickListener(v -> 
            Toast.makeText(requireContext(), "Downloading Official SSC 2027 Blueprint (4.2 MB)...", Toast.LENGTH_SHORT).show()
        );

        // Clicking Science Division goes to Stream Selection
        binding.cardScienceTrack.setOnClickListener(v -> 
            Navigation.findNavController(rootView).navigate(R.id.action_courseDetail_to_streamSelection)
        );
        binding.btnOpenScienceModules.setOnClickListener(v -> 
            Navigation.findNavController(rootView).navigate(R.id.action_courseDetail_to_streamSelection)
        );

        // Business Track
        binding.cardBusinessTrack.setOnClickListener(v -> 
            Navigation.findNavController(rootView).navigate(R.id.action_courseDetail_to_streamSelection)
        );
        binding.btnSwitchBusiness.setOnClickListener(v -> 
            Navigation.findNavController(rootView).navigate(R.id.action_courseDetail_to_streamSelection)
        );

        // Humanities Track
        binding.cardHumanitiesTrack.setOnClickListener(v -> 
            Navigation.findNavController(rootView).navigate(R.id.action_courseDetail_to_streamSelection)
        );
        binding.btnSwitchHumanities.setOnClickListener(v -> 
            Navigation.findNavController(rootView).navigate(R.id.action_courseDetail_to_streamSelection)
        );

        // All Mentors
        binding.tvAllMentors.setOnClickListener(v -> 
            Toast.makeText(requireContext(), "Opening Top Subject Mentors Directory", Toast.LENGTH_SHORT).show()
        );

        // Counselor Call
        binding.btnConsult.setOnClickListener(v -> 
            Toast.makeText(requireContext(), "Scheduling 10-minute counselor call with Academic Advisor", Toast.LENGTH_SHORT).show()
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
