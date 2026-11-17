package com.edupulse.app.ui.auth.register;

import android.os.Bundle;
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
import com.edupulse.app.data.model.StudentRegistrationRequest;
import com.edupulse.app.databinding.FragmentRegisterStep2Binding;
import com.edupulse.app.ui.auth.AuthViewModel;
import com.google.android.material.snackbar.Snackbar;

public class RegisterStep2Fragment extends Fragment {

    private FragmentRegisterStep2Binding binding;
    private AuthViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentRegisterStep2Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);

        setupClassSelector();
        setupClickListeners(view);
        observeAuthState(view);
    }

    private void setupClassSelector() {
        String[] grades = {"Class 8", "Class 9", "Class 10", "Class 11 - Science", "Class 12 - Science"};
        binding.spinnerGradeClass.setOnClickListener(v -> {
            new AlertDialog.Builder(requireContext())
                    .setTitle("Select Grade / Class")
                    .setItems(grades, (dialog, which) -> {
                        binding.tvSelectedGrade.setText(grades[which]);
                    })
                    .show();
        });
    }

    private void setupClickListeners(View rootView) {
        binding.btnBack.setOnClickListener(v -> {
            Navigation.findNavController(rootView).navigateUp();
        });

        // Photo Picker simulation
        binding.layoutPhotoUpload.setOnClickListener(v -> {
            Toast.makeText(requireContext(), "Opening photo gallery / camera...", Toast.LENGTH_SHORT).show();
        });

        // Complete Registration
        binding.btnCompleteRegistration.setOnClickListener(v -> {
            String subject = binding.etSubject.getText().toString().trim();
            String grade = binding.tvSelectedGrade.getText().toString().trim();
            String phone = binding.etMobileNumber.getText().toString().trim();
            boolean acceptedTerms = binding.cbTerms.isChecked();

            StudentRegistrationRequest request = new StudentRegistrationRequest(
                    "Alex Parker",
                    "Apex International Academy",
                    subject,
                    grade,
                    phone,
                    acceptedTerms
            );

            viewModel.registerStudent(request);
        });

        // Already have an account -> Sign In
        binding.tvSignIn.setOnClickListener(v -> {
            Navigation.findNavController(rootView).navigate(R.id.action_registerStep2_to_nexusLogin);
        });
    }

    private void observeAuthState(View rootView) {
        viewModel.getAuthState().observe(getViewLifecycleOwner(), state -> {
            if (state == null || binding == null) return;

            binding.btnCompleteRegistration.setEnabled(!state.isLoading());
            if (state.isLoading()) {
                binding.btnCompleteRegistration.setText("Registering Student Record...");
            } else {
                binding.btnCompleteRegistration.setText(R.string.btn_complete_registration);
            }

            if (state.isError()) {
                Snackbar.make(binding.getRoot(), state.getErrorMessage(), Snackbar.LENGTH_LONG)
                        .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.error_red))
                        .setTextColor(ContextCompat.getColor(requireContext(), R.color.text_primary))
                        .show();
            } else if (state.isSuccess()) {
                viewModel.resetState();
                Navigation.findNavController(rootView).navigate(R.id.action_registerStep2_to_verifyOtp);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
