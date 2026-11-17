package com.edupulse.app.ui.auth.signin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.edupulse.app.R;
import com.edupulse.app.databinding.FragmentEdupulseSignInBinding;
import com.edupulse.app.ui.auth.AuthViewModel;
import com.edupulse.app.ui.common.ViewAnimationUtils;
import com.google.android.material.snackbar.Snackbar;

public class EduPulseSignInFragment extends Fragment {

    private FragmentEdupulseSignInBinding binding;
    private AuthViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentEdupulseSignInBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);

        ViewAnimationUtils.applyBounceEffect(
                binding.btnBack,
                binding.btnSignInPortal,
                binding.tvRegisterHere,
                binding.btnGoogleClassroom,
                binding.btnLanguage,
                binding.btnHelp
        );

        setupClickListeners(view);
        observeAuthState(view);
    }

    private void setupClickListeners(View rootView) {
        binding.btnBack.setOnClickListener(v -> {
            Navigation.findNavController(rootView).navigateUp();
        });

        // Sign In to Portal
        binding.btnSignInPortal.setOnClickListener(v -> {
            String fullName = binding.etFullName.getText().toString().trim();
            String schoolName = binding.etSchoolName.getText().toString().trim();
            boolean rememberDevice = binding.switchRememberDevice.isChecked();

            viewModel.loginWithSchoolPortal(fullName, schoolName, rememberDevice);
        });

        // Google Classroom Account SSO
        binding.btnGoogleClassroom.setOnClickListener(v -> {
            Toast.makeText(requireContext(), "Connecting to Google Classroom SSO...", Toast.LENGTH_SHORT).show();
            viewModel.loginWithSchoolPortal("Alex Johnson (Google)", "Apex International Academy", true);
        });

        // Register Link
        binding.tvRegisterHere.setOnClickListener(v -> {
            Navigation.findNavController(rootView).navigate(R.id.action_edupulseSignIn_to_registerStep2);
        });
    }

    private void observeAuthState(View rootView) {
        viewModel.getAuthState().observe(getViewLifecycleOwner(), state -> {
            if (state == null || binding == null) return;

            binding.btnSignInPortal.setEnabled(!state.isLoading());
            if (state.isLoading()) {
                binding.btnSignInPortal.setText("Connecting to Portal...");
            } else {
                binding.btnSignInPortal.setText(R.string.btn_sign_in_portal);
            }

            if (state.isError()) {
                Snackbar.make(binding.getRoot(), state.getErrorMessage(), Snackbar.LENGTH_LONG)
                        .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.error_red))
                        .setTextColor(ContextCompat.getColor(requireContext(), R.color.text_primary))
                        .show();
            } else if (state.isSuccess()) {
                Toast.makeText(requireContext(), "Welcome " + state.getUser().getFullName(), Toast.LENGTH_SHORT).show();
                viewModel.resetState();
                Navigation.findNavController(rootView).navigate(R.id.action_edupulseSignIn_to_registerStep2);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
