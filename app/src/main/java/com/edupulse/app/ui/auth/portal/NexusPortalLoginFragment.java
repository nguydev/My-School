package com.edupulse.app.ui.auth.portal;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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
import com.edupulse.app.data.model.AuthState;
import com.edupulse.app.databinding.FragmentNexusPortalLoginBinding;
import com.edupulse.app.ui.auth.AuthViewModel;
import com.edupulse.app.ui.common.ViewAnimationUtils;
import com.google.android.material.snackbar.Snackbar;

public class NexusPortalLoginFragment extends Fragment {

    private FragmentNexusPortalLoginBinding binding;
    private AuthViewModel viewModel;
    private boolean isPasswordVisible = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentNexusPortalLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);

        ViewAnimationUtils.applyBounceEffect(
                binding.btnSignIn,
                binding.tvSignUp,
                binding.tvForgotPassword,
                binding.btnTogglePassword,
                binding.btnBack,
                binding.btnHelp
        );

        setupPasswordToggle();
        setupClickListeners(view);
        observeAuthState(view);
    }

    private void setupPasswordToggle() {
        binding.btnTogglePassword.setOnClickListener(v -> {
            isPasswordVisible = !isPasswordVisible;
            if (isPasswordVisible) {
                binding.etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                binding.btnTogglePassword.setImageResource(R.drawable.ic_eye_off);
            } else {
                binding.etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                binding.btnTogglePassword.setImageResource(R.drawable.ic_eye);
            }
            binding.etPassword.setSelection(binding.etPassword.getText().length());
        });
    }

    private void setupClickListeners(View rootView) {
        // Sign In Action -> Takes student straight to Home Page!
        binding.btnSignIn.setOnClickListener(v -> {
            String email = binding.etEmail.getText().toString().trim();
            String password = binding.etPassword.getText().toString().trim();
            boolean rememberMe = binding.cbRememberMe.isChecked();
            viewModel.loginWithCredentials(email, password, rememberMe);
        });

        // Forgot Password -> Navigate to Verify Email Screen
        binding.tvForgotPassword.setOnClickListener(v -> {
            Navigation.findNavController(rootView).navigate(R.id.action_nexusLogin_to_verifyEmail);
        });

        // Sign Up -> Navigate to Registration Screen
        binding.tvSignUp.setOnClickListener(v -> {
            Navigation.findNavController(rootView).navigate(R.id.action_nexusLogin_to_registerStep2);
        });

        // Switch to EduPulse Alternate Sign-In View
        binding.btnHelp.setOnClickListener(v -> {
            Navigation.findNavController(rootView).navigate(R.id.action_nexusLogin_to_edupulseSignIn);
        });

        // Mockup Simulation Buttons
        binding.btnSimulateError.setOnClickListener(v -> {
            viewModel.simulateState(AuthState.Status.ERROR, "SSO Error: Student account locked due to security policy.");
        });

        binding.btnSimulateSuccess.setOnClickListener(v -> {
            viewModel.simulateState(AuthState.Status.SUCCESS, null);
        });
    }

    private void observeAuthState(View rootView) {
        viewModel.getAuthState().observe(getViewLifecycleOwner(), state -> {
            if (state == null || binding == null) return;

            binding.btnSignIn.setEnabled(!state.isLoading());
            if (state.isLoading()) {
                binding.btnSignIn.setText("Authenticating...");
            } else {
                binding.btnSignIn.setText(R.string.btn_sign_in);
            }

            if (state.isError()) {
                Snackbar.make(binding.getRoot(), state.getErrorMessage(), Snackbar.LENGTH_LONG)
                        .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.error_red))
                        .setTextColor(ContextCompat.getColor(requireContext(), R.color.text_primary))
                        .show();
            } else if (state.isSuccess()) {
                Toast.makeText(requireContext(), "Welcome Back, Scholar!", Toast.LENGTH_SHORT).show();
                viewModel.resetState();
                // Direct natural project flow: Sign In -> Home Page!
                Navigation.findNavController(rootView).navigate(R.id.action_nexusLogin_to_homeFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
