package com.edupulse.app.ui.alerts;

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
import com.edupulse.app.databinding.FragmentAlertsBinding;
import com.edupulse.app.ui.common.ViewAnimationUtils;

public class AlertsFragment extends Fragment {

    private FragmentAlertsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentAlertsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewAnimationUtils.applyBounceEffect(
                binding.btnSearchAlerts,
                binding.btnMarkRead,
                binding.cardMockTestLive,
                binding.btnTakeTest,
                binding.cardHigherMathSheet,
                binding.btnViewNotes,
                binding.btnDownloadNotes,
                binding.cardDoubtReply,
                binding.btnViewFullReply,
                binding.cardScheduleFinalized,
                binding.btnDownloadRoutine,
                binding.cardMilestoneUnlocked,
                binding.cardManageAlerts,
                binding.navItemHome,
                binding.navItemCourses,
                binding.navItemSaved,
                binding.navItemAlerts,
                binding.navItemProfile
        );

        setupClickListeners(view);
    }

    private void setupClickListeners(View rootView) {
        // Mark all as read
        binding.btnMarkRead.setOnClickListener(v -> {
            binding.tvTodayNewBadge.setVisibility(View.GONE);
            Toast.makeText(requireContext(), "All notifications marked as read! ✔", Toast.LENGTH_SHORT).show();
        });

        // Search in alerts
        binding.btnSearchAlerts.setOnClickListener(v ->
                Toast.makeText(requireContext(), "Search alerts & notices", Toast.LENGTH_SHORT).show()
        );

        // Card 1: Mock test live -> Navigate to Chapter Video / Mock Test
        binding.btnTakeTest.setOnClickListener(v ->
                Navigation.findNavController(rootView).navigate(R.id.action_alerts_to_videoPlaylist)
        );
        binding.cardMockTestLive.setOnClickListener(v ->
                Navigation.findNavController(rootView).navigate(R.id.action_alerts_to_videoPlaylist)
        );

        // Card 2: Higher Math Formula Sheet -> PDF Viewer
        binding.btnViewNotes.setOnClickListener(v ->
                Navigation.findNavController(rootView).navigate(R.id.action_alerts_to_pdfViewer)
        );
        binding.btnDownloadNotes.setOnClickListener(v ->
                Toast.makeText(requireContext(), "Downloading Higher Math Ch 3 Formula Sheet...", Toast.LENGTH_SHORT).show()
        );

        // Card 3: Faculty Doubt Reply
        binding.btnViewFullReply.setOnClickListener(v ->
                Toast.makeText(requireContext(), "Dr. Tahmid Hasan: Momentum Conservation thread opened", Toast.LENGTH_LONG).show()
        );

        // Card 4: Routine PDF Download
        binding.btnDownloadRoutine.setOnClickListener(v ->
                Toast.makeText(requireContext(), "Downloading SSC_2027_Term1_Routine.pdf", Toast.LENGTH_SHORT).show()
        );

        // Card 6: Manage Alerts settings
        binding.cardManageAlerts.setOnClickListener(v ->
                Toast.makeText(requireContext(), "Alert notification preferences opened", Toast.LENGTH_SHORT).show()
        );

        // Bottom Navigation Bar
        binding.navItemHome.setOnClickListener(v ->
                Navigation.findNavController(rootView).navigate(R.id.action_alerts_to_home)
        );
        binding.navItemCourses.setOnClickListener(v ->
                Navigation.findNavController(rootView).navigate(R.id.action_alerts_to_courseDetail)
        );
        binding.navItemSaved.setOnClickListener(v ->
                Navigation.findNavController(rootView).navigate(R.id.action_alerts_to_saved)
        );
        binding.navItemAlerts.setOnClickListener(v ->
                Toast.makeText(requireContext(), "You are already on Alerts", Toast.LENGTH_SHORT).show()
        );
        binding.navItemProfile.setOnClickListener(v ->
                Navigation.findNavController(rootView).navigate(R.id.action_alerts_to_profile)
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
