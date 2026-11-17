package com.edupulse.app.ui.home;

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
import com.edupulse.app.databinding.FragmentHomeBinding;
import com.edupulse.app.ui.common.ViewAnimationUtils;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        ViewAnimationUtils.applyBounceEffect(
                binding.btnNotification,
                binding.ivProfileAvatar,
                binding.cardResume,
                binding.btnResumePlay,
                binding.btnExploreCourse1,
                binding.btnExploreCourse2,
                binding.cardBookStoreBanner,
                binding.btnViewWelcome,
                binding.navItemHome,
                binding.navItemCourses,
                binding.navItemSaved,
                binding.navItemAlerts,
                binding.navItemProfile
        );

        setupClickListeners(view);
        setupPullToRefresh();
    }

    private void setupPullToRefresh() {
        binding.swipeRefreshHome.setColorSchemeResources(R.color.cyan_neon, R.color.blue_electric);
        binding.swipeRefreshHome.setProgressBackgroundColorSchemeResource(R.color.bg_card);
        binding.swipeRefreshHome.setOnRefreshListener(() -> {
            binding.swipeRefreshHome.postDelayed(() -> {
                if (binding != null) {
                    binding.swipeRefreshHome.setRefreshing(false);
                    Toast.makeText(requireContext(), "Home Feed Updated! ✨", Toast.LENGTH_SHORT).show();
                }
            }, 1000);
        });
    }

    private void setupClickListeners(View rootView) {
        // Notification Bell -> Navigate to Alerts Page
        binding.btnNotification.setOnClickListener(v -> 
            Navigation.findNavController(rootView).navigate(R.id.action_home_to_alerts)
        );

        // Profile Avatar -> Navigate to Profile Page
        binding.ivProfileAvatar.setOnClickListener(v -> 
            Navigation.findNavController(rootView).navigate(R.id.action_home_to_profile)
        );

        // Resume Higher Math Lecture
        binding.cardResume.setOnClickListener(v -> 
            Navigation.findNavController(rootView).navigate(R.id.action_home_to_courseDetail)
        );
        binding.btnResumePlay.setOnClickListener(v -> 
            Navigation.findNavController(rootView).navigate(R.id.action_home_to_courseDetail)
        );

        // View All Courses -> Navigate to Course Detail
        binding.tvViewAllCourses.setOnClickListener(v -> 
            Navigation.findNavController(rootView).navigate(R.id.action_home_to_courseDetail)
        );

        // Explore Course 1 (SSC Preparation 2027) -> Navigate to Course Detail Page!
        binding.cardCourse1.setOnClickListener(v -> 
            Navigation.findNavController(rootView).navigate(R.id.action_home_to_courseDetail)
        );
        binding.btnExploreCourse1.setOnClickListener(v -> 
            Navigation.findNavController(rootView).navigate(R.id.action_home_to_courseDetail)
        );

        // Explore Course 2 (HSC Foundation) -> Navigate to Stream Selection
        binding.cardCourse2.setOnClickListener(v -> 
            Navigation.findNavController(rootView).navigate(R.id.action_home_to_streamSelection)
        );
        binding.btnExploreCourse2.setOnClickListener(v -> 
            Navigation.findNavController(rootView).navigate(R.id.action_home_to_streamSelection)
        );

        // Book Store Orders Banner -> Go to BookStore
        binding.cardBookStoreBanner.setOnClickListener(v -> 
            Navigation.findNavController(rootView).navigate(R.id.action_home_to_bookStore)
        );

        // Welcome card view
        binding.btnViewWelcome.setOnClickListener(v -> 
            Toast.makeText(requireContext(), "Viewing Welcome Onboarding Card #7842", Toast.LENGTH_SHORT).show()
        );

        // Bottom Navigation Bar Items
        binding.navItemHome.setOnClickListener(v -> 
            Toast.makeText(requireContext(), "You are on Home Feed", Toast.LENGTH_SHORT).show()
        );
        binding.navItemCourses.setOnClickListener(v -> 
            Navigation.findNavController(rootView).navigate(R.id.action_home_to_courseDetail)
        );
        binding.navItemSaved.setOnClickListener(v -> 
            Navigation.findNavController(rootView).navigate(R.id.action_home_to_saved)
        );
        binding.navItemAlerts.setOnClickListener(v -> 
            Navigation.findNavController(rootView).navigate(R.id.action_home_to_alerts)
        );
        binding.navItemProfile.setOnClickListener(v -> 
            Navigation.findNavController(rootView).navigate(R.id.action_home_to_profile)
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
