package com.edupulse.app.ui.auth.verify;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.edupulse.app.R;
import com.edupulse.app.databinding.FragmentVerifyEmailBinding;
import com.edupulse.app.ui.auth.AuthViewModel;
import com.google.android.material.snackbar.Snackbar;

public class VerifyEmailFragment extends Fragment {

    private FragmentVerifyEmailBinding binding;
    private AuthViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentVerifyEmailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);

        setupClickListeners(view);
        observeAuthState(view);
    }

    private void setupClickListeners(View rootView) {
        binding.btnBack.setOnClickListener(v -> {
            Navigation.findNavController(rootView).navigateUp();
        });

        binding.tvBackToLogin.setOnClickListener(v -> {
            Navigation.findNavController(rootView).navigateUp();
        });

        binding.btnSendEmail.setOnClickListener(v -> {
            String email = binding.etPrimaryEmail.getText().toString().trim();
            viewModel.sendEmailVerification(email);
        });
    }

    private void observeAuthState(View rootView) {
        viewModel.getAuthState().observe(getViewLifecycleOwner(), state -> {
            if (state == null || binding == null) return;

            binding.btnSendEmail.setEnabled(!state.isLoading());
            if (state.isLoading()) {
                binding.btnSendEmail.setText("Sending Link & Code...");
            } else {
                binding.btnSendEmail.setText(R.string.btn_send_email);
            }

            if (state.isError()) {
                Snackbar.make(binding.getRoot(), state.getErrorMessage(), Snackbar.LENGTH_LONG)
                        .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.error_red))
                        .setTextColor(ContextCompat.getColor(requireContext(), R.color.text_primary))
                        .show();
            } else if (state.isSuccess()) {
                viewModel.resetState();
                Navigation.findNavController(rootView).navigate(R.id.action_verifyEmail_to_verifyOtp);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
