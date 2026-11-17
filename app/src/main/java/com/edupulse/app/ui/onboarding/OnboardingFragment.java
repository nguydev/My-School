package com.edupulse.app.ui.onboarding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.edupulse.app.R;
import com.edupulse.app.databinding.FragmentOnboardingBinding;
import com.edupulse.app.ui.common.ViewAnimationUtils;

public class OnboardingFragment extends Fragment {

    private FragmentOnboardingBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewAnimationUtils.applyBounceEffect(
                binding.btnStartLearningNow,
                binding.tvSkipOnboarding,
                binding.tvSignInLink
        );

        // Start Learning Now -> Go to Home
        binding.btnStartLearningNow.setOnClickListener(v ->
                Navigation.findNavController(view).navigate(R.id.action_onboarding_to_home)
        );

        // Skip -> Go to Home
        binding.tvSkipOnboarding.setOnClickListener(v ->
                Navigation.findNavController(view).navigate(R.id.action_onboarding_to_home)
        );

        // Already a student? Sign In -> Go to Login
        binding.tvSignInLink.setOnClickListener(v ->
                Navigation.findNavController(view).navigate(R.id.action_onboarding_to_login)
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
