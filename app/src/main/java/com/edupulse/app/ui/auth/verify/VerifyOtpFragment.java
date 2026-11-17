package com.edupulse.app.ui.auth.verify;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.edupulse.app.R;
import com.edupulse.app.databinding.FragmentVerifyOtpBinding;
import com.edupulse.app.ui.auth.AuthViewModel;
import com.google.android.material.snackbar.Snackbar;

public class VerifyOtpFragment extends Fragment {

    private FragmentVerifyOtpBinding binding;
    private AuthViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentVerifyOtpBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);

        setupOtpInputProgression();
        setupClickListeners(view);
        observeAuthState(view);
    }

    /**
     * Auto-focus advance across the 6 OTP boxes.
     */
    private void setupOtpInputProgression() {
        EditText[] otpFields = {
                binding.etOtp1, binding.etOtp2, binding.etOtp3,
                binding.etOtp4, binding.etOtp5, binding.etOtp6
        };

        for (int i = 0; i < otpFields.length; i++) {
            final int index = i;
            otpFields[i].addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.length() == 1 && index < otpFields.length - 1) {
                        otpFields[index + 1].requestFocus();
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {}
            });
        }
    }

    private String getEnteredOtp() {
        return binding.etOtp1.getText().toString().trim() +
                binding.etOtp2.getText().toString().trim() +
                binding.etOtp3.getText().toString().trim() +
                binding.etOtp4.getText().toString().trim() +
                binding.etOtp5.getText().toString().trim() +
                binding.etOtp6.getText().toString().trim();
    }

    private void setupClickListeners(View rootView) {
        binding.btnBack.setOnClickListener(v -> {
            Navigation.findNavController(rootView).navigateUp();
        });

        // Change Email / Phone
        binding.tvChangeEmail.setOnClickListener(v -> {
            Navigation.findNavController(rootView).navigate(R.id.action_verifyOtp_to_verifyEmail);
        });

        // Resend OTP
        binding.tvResendOtp.setOnClickListener(v -> {
            Toast.makeText(requireContext(), "New 6-digit code sent to your email & SMS.", Toast.LENGTH_SHORT).show();
            binding.pillTimerExpired.setText("⏱ Resend code in 00:59");
        });

        // Auto-Fill Active Badge Simulation
        binding.badgeAutoFill.setOnClickListener(v -> {
            binding.etOtp1.setText("4");
            binding.etOtp2.setText("8");
            binding.etOtp3.setText("2");
            binding.etOtp4.setText("9");
            binding.etOtp5.setText("1");
            binding.etOtp6.setText("0");
            Toast.makeText(requireContext(), "Auto-filled SMS OTP: 482910", Toast.LENGTH_SHORT).show();
        });

        // Verify & Activate Account
        binding.btnVerifyActivate.setOnClickListener(v -> {
            String otp = getEnteredOtp();
            if (otp.length() < 6) {
                binding.etOtp6.setText("0");
                otp = getEnteredOtp();
            }
            viewModel.verifyOtp(otp);
        });

        // Faculty Helpdesk
        binding.tvContactHelpdesk.setOnClickListener(v -> {
            new AlertDialog.Builder(requireContext())
                    .setTitle("Faculty Helpdesk")
                    .setMessage("Live Support:\n• Email: support@edupulse.campus.edu\n• Hotlines: +1 (800) 555-CAMPUS\n• Operating Hours: 24/7 Academic Support")
                    .setPositiveButton("Close", null)
                    .show();
        });

        binding.tvTryAlternate.setOnClickListener(v -> {
            Toast.makeText(requireContext(), "Switched to hardware security key / backup code.", Toast.LENGTH_SHORT).show();
        });
    }

    private void observeAuthState(View rootView) {
        viewModel.getCurrentVerifiedEmail().observe(getViewLifecycleOwner(), email -> {
            if (email != null && email.contains("@") && binding != null) {
                int atIndex = email.indexOf("@");
                String prefix = email.substring(0, Math.min(3, atIndex));
                binding.tvMaskedEmail.setText(prefix + "***" + email.substring(atIndex));
            }
        });

        viewModel.getAuthState().observe(getViewLifecycleOwner(), state -> {
            if (state == null || binding == null) return;

            binding.btnVerifyActivate.setEnabled(!state.isLoading());
            if (state.isLoading()) {
                binding.btnVerifyActivate.setText("Verifying Passcode...");
            } else {
                binding.btnVerifyActivate.setText(R.string.btn_verify_activate);
            }

            if (state.isError()) {
                Snackbar.make(binding.getRoot(), state.getErrorMessage(), Snackbar.LENGTH_LONG)
                        .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.error_red))
                        .setTextColor(ContextCompat.getColor(requireContext(), R.color.text_primary))
                        .show();
            } else if (state.isSuccess()) {
                viewModel.resetState();
                Navigation.findNavController(rootView).navigate(R.id.action_verifyOtp_to_resetPassword);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
