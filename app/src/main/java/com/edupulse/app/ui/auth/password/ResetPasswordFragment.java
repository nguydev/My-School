package com.edupulse.app.ui.auth.password;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.edupulse.app.R;
import com.edupulse.app.data.model.ResetPasswordRequest;
import com.edupulse.app.databinding.FragmentResetPasswordBinding;
import com.edupulse.app.ui.auth.AuthViewModel;
import com.google.android.material.snackbar.Snackbar;

public class ResetPasswordFragment extends Fragment {

    private FragmentResetPasswordBinding binding;
    private AuthViewModel viewModel;

    private boolean isNewPasswordVisible = false;
    private boolean isConfirmPasswordVisible = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentResetPasswordBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);

        setupPasswordToggles();
        setupPasswordMatchingWatcher();
        setupClickListeners(view);
        observeAuthState(view);
    }

    private void setupPasswordToggles() {
        binding.btnToggleNewPassword.setOnClickListener(v -> {
            isNewPasswordVisible = !isNewPasswordVisible;
            binding.etNewPassword.setTransformationMethod(
                    isNewPasswordVisible ? HideReturnsTransformationMethod.getInstance()
                            : PasswordTransformationMethod.getInstance());
            binding.btnToggleNewPassword.setImageResource(
                    isNewPasswordVisible ? R.drawable.ic_eye_off : R.drawable.ic_eye);
            binding.etNewPassword.setSelection(binding.etNewPassword.getText().length());
        });

        binding.btnToggleConfirmPassword.setOnClickListener(v -> {
            isConfirmPasswordVisible = !isConfirmPasswordVisible;
            binding.etConfirmPassword.setTransformationMethod(
                    isConfirmPasswordVisible ? HideReturnsTransformationMethod.getInstance()
                            : PasswordTransformationMethod.getInstance());
            binding.btnToggleConfirmPassword.setImageResource(
                    isConfirmPasswordVisible ? R.drawable.ic_eye_off : R.drawable.ic_eye);
            binding.etConfirmPassword.setSelection(binding.etConfirmPassword.getText().length());
        });
    }

    private void setupPasswordMatchingWatcher() {
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String p1 = binding.etNewPassword.getText().toString();
                String p2 = binding.etConfirmPassword.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };

        binding.etNewPassword.addTextChangedListener(watcher);
        binding.etConfirmPassword.addTextChangedListener(watcher);
    }

    private void setupClickListeners(View rootView) {
        binding.btnBack.setOnClickListener(v -> {
            Navigation.findNavController(rootView).navigateUp();
        });

        binding.tvBackToStudentSignIn.setOnClickListener(v -> {
            Navigation.findNavController(rootView).navigate(R.id.action_resetPassword_to_nexusLogin);
        });

        // Reset Password & Sign In
        binding.btnResetPasswordSignIn.setOnClickListener(v -> {
            String identifier = binding.etResetEmail.getText().toString().trim();
            String newPassword = binding.etNewPassword.getText().toString();
            String confirmPassword = binding.etConfirmPassword.getText().toString();

            ResetPasswordRequest request = new ResetPasswordRequest(identifier, newPassword, confirmPassword);
            viewModel.resetPassword(request);
        });

        // School IT Helpdesk Dialog
        binding.tvContactSchoolIt.setOnClickListener(v -> {
            new AlertDialog.Builder(requireContext())
                    .setTitle("School IT Helpdesk")
                    .setMessage("Institutional Account Services:\n• Email: it-desk@campus.edu\n• Portal: https://help.edupulse.edu\n• Office: Administrative Block Room 104")
                    .setPositiveButton("OK", null)
                    .show();
        });
    }

    private void observeAuthState(View rootView) {
        viewModel.getAuthState().observe(getViewLifecycleOwner(), state -> {
            if (state == null || binding == null) return;

            binding.btnResetPasswordSignIn.setEnabled(!state.isLoading());
            if (state.isLoading()) {
                binding.btnResetPasswordSignIn.setText("Updating Credentials...");
            } else {
                binding.btnResetPasswordSignIn.setText(R.string.btn_reset_password_sign_in);
            }

            if (state.isError()) {
                Snackbar.make(binding.getRoot(), state.getErrorMessage(), Snackbar.LENGTH_LONG)
                        .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.error_red))
                        .setTextColor(ContextCompat.getColor(requireContext(), R.color.text_primary))
                        .show();
            } else if (state.isSuccess()) {
                Toast.makeText(requireContext(), "Password reset successfully! Please log in.", Toast.LENGTH_LONG).show();
                viewModel.resetState();
                Navigation.findNavController(rootView).navigate(R.id.action_resetPassword_to_nexusLogin);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
