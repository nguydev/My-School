package com.edupulse.app.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.edupulse.app.R;
import com.edupulse.app.databinding.FragmentProfileBinding;
import com.edupulse.app.ui.common.ViewAnimationUtils;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewAnimationUtils.applyBounceEffect(
                binding.btnSettingsTop,
                binding.ivShareTop,
                binding.btnEditProfile,
                binding.btnQrCode,
                binding.btnViewSyllabus,
                binding.btnClassGroup,
                binding.itemPersonalDetails,
                binding.itemOfflineStorage,
                binding.itemStudyRoutine,
                binding.itemFeeReceipts,
                binding.itemFacultySupport,
                binding.cardLogout,
                binding.navItemHome,
                binding.navItemCourses,
                binding.navItemSaved,
                binding.navItemAlerts,
                binding.navItemProfile
        );

        setupClickListeners(view);
    }

    private void setupClickListeners(View rootView) {
        // Edit Profile
        binding.btnEditProfile.setOnClickListener(v ->
                Toast.makeText(requireContext(), "Edit Profile: Alex Rahman (SSC '27)", Toast.LENGTH_SHORT).show()
        );

        // QR Code
        binding.btnQrCode.setOnClickListener(v ->
                Toast.makeText(requireContext(), "Student ID QR: APX-841920", Toast.LENGTH_LONG).show()
        );

        // Settings Top
        binding.btnSettingsTop.setOnClickListener(v ->
                Toast.makeText(requireContext(), "Opening Application Settings", Toast.LENGTH_SHORT).show()
        );

        // Share Profile
        binding.ivShareTop.setOnClickListener(v ->
                Toast.makeText(requireContext(), "Share Profile Badge & Stats Link Copied", Toast.LENGTH_SHORT).show()
        );

        // View Syllabus PDF
        binding.btnViewSyllabus.setOnClickListener(v ->
                Navigation.findNavController(rootView).navigate(R.id.action_profile_to_pdfViewer)
        );

        // Class Group
        binding.btnClassGroup.setOnClickListener(v ->
                Toast.makeText(requireContext(), "Opening Batch Alpha-2 Community Discussion", Toast.LENGTH_SHORT).show()
        );

        // Preference items
        binding.itemPersonalDetails.setOnClickListener(v ->
                Toast.makeText(requireContext(), "Personal & Guardian Details", Toast.LENGTH_SHORT).show()
        );

        binding.itemOfflineStorage.setOnClickListener(v ->
                Navigation.findNavController(rootView).navigate(R.id.action_profile_to_saved)
        );

        binding.itemStudyRoutine.setOnClickListener(v ->
                Toast.makeText(requireContext(), "Study Routine & Notifications Configured", Toast.LENGTH_SHORT).show()
        );

        binding.itemFeeReceipts.setOnClickListener(v ->
                Toast.makeText(requireContext(), "Subscription: All Terms Fully Paid (Active)", Toast.LENGTH_SHORT).show()
        );

        binding.itemFacultySupport.setOnClickListener(v ->
                Toast.makeText(requireContext(), "Connecting with Lead Mentor Engr. Salman Kabir...", Toast.LENGTH_SHORT).show()
        );

        // Dark Theme Switch
        binding.switchDarkTheme.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String themeMode = isChecked ? "Cyber Slate Dark Mode Enabled" : "Light Mode Toggled";
            Toast.makeText(requireContext(), themeMode, Toast.LENGTH_SHORT).show();
        });

        // Logout
        binding.cardLogout.setOnClickListener(v -> {
            new AlertDialog.Builder(requireContext())
                    .setTitle("Log Out")
                    .setMessage("Are you sure you want to log out from EduPulse Apex?")
                    .setPositiveButton("Log Out", (dialog, which) -> {
                        Toast.makeText(requireContext(), "Logged out successfully", Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(rootView).navigate(R.id.action_profile_to_nexusLogin);
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });

        // Bottom Navigation Bar
        binding.navItemHome.setOnClickListener(v ->
                Navigation.findNavController(rootView).navigate(R.id.action_profile_to_home)
        );
        binding.navItemCourses.setOnClickListener(v ->
                Navigation.findNavController(rootView).navigate(R.id.action_profile_to_courseDetail)
        );
        binding.navItemSaved.setOnClickListener(v ->
                Navigation.findNavController(rootView).navigate(R.id.action_profile_to_saved)
        );
        binding.navItemAlerts.setOnClickListener(v ->
                Navigation.findNavController(rootView).navigate(R.id.action_profile_to_alerts)
        );
        binding.navItemProfile.setOnClickListener(v ->
                Toast.makeText(requireContext(), "You are already on Profile", Toast.LENGTH_SHORT).show()
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
