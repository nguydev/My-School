package com.edupulse.app.ui.saved;

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
import com.edupulse.app.databinding.FragmentSavedBinding;
import com.edupulse.app.ui.common.ViewAnimationUtils;

public class SavedFragment extends Fragment {

    private FragmentSavedBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSavedBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewAnimationUtils.applyBounceEffect(
                binding.btnSearchTop,
                binding.tabAllSaved,
                binding.tabSavedVideos,
                binding.tabOfflinePdfs,
                binding.btnDownloadAll,
                binding.btnSortVideos,
                binding.cardVideo1,
                binding.cardVideo2,
                binding.btnDownloadVid2,
                binding.cardVideo3,
                binding.cardPdf1,
                binding.btnReadNow1,
                binding.cardPdf2,
                binding.btnReadNow2,
                binding.btnSharePdf2,
                binding.cardPdf3,
                binding.btnReadNow3,
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
        binding.swipeRefreshSaved.setColorSchemeResources(R.color.cyan_neon, R.color.blue_electric);
        binding.swipeRefreshSaved.setProgressBackgroundColorSchemeResource(R.color.bg_card);
        binding.swipeRefreshSaved.setOnRefreshListener(() -> {
            binding.swipeRefreshSaved.postDelayed(() -> {
                if (binding != null) {
                    binding.swipeRefreshSaved.setRefreshing(false);
                    Toast.makeText(requireContext(), "Saved storage verified! 1.2 GB used", Toast.LENGTH_SHORT).show();
                }
            }, 800);
        });
    }

    private void setupClickListeners(View rootView) {
        // Tab Filters
        binding.tabAllSaved.setOnClickListener(v -> {
            binding.cardVideo1.setVisibility(View.VISIBLE);
            binding.cardVideo2.setVisibility(View.VISIBLE);
            binding.cardVideo3.setVisibility(View.VISIBLE);
            binding.cardPdf1.setVisibility(View.VISIBLE);
            binding.cardPdf2.setVisibility(View.VISIBLE);
            binding.cardPdf3.setVisibility(View.VISIBLE);
            Toast.makeText(requireContext(), "Showing All 8 Saved Items", Toast.LENGTH_SHORT).show();
        });

        binding.tabSavedVideos.setOnClickListener(v -> {
            binding.cardVideo1.setVisibility(View.VISIBLE);
            binding.cardVideo2.setVisibility(View.VISIBLE);
            binding.cardVideo3.setVisibility(View.VISIBLE);
            binding.cardPdf1.setVisibility(View.GONE);
            binding.cardPdf2.setVisibility(View.GONE);
            binding.cardPdf3.setVisibility(View.GONE);
            Toast.makeText(requireContext(), "Showing 5 Saved Videos", Toast.LENGTH_SHORT).show();
        });

        binding.tabOfflinePdfs.setOnClickListener(v -> {
            binding.cardVideo1.setVisibility(View.GONE);
            binding.cardVideo2.setVisibility(View.GONE);
            binding.cardVideo3.setVisibility(View.GONE);
            binding.cardPdf1.setVisibility(View.VISIBLE);
            binding.cardPdf2.setVisibility(View.VISIBLE);
            binding.cardPdf3.setVisibility(View.VISIBLE);
            Toast.makeText(requireContext(), "Showing 3 Offline PDFs", Toast.LENGTH_SHORT).show();
        });

        binding.btnDownloadAll.setOnClickListener(v ->
                Toast.makeText(requireContext(), "Starting download of pending files...", Toast.LENGTH_SHORT).show()
        );

        // Video item 1 -> Open Video Player
        binding.cardVideo1.setOnClickListener(v ->
                Navigation.findNavController(rootView).navigate(R.id.action_saved_to_videoPlayer)
        );

        // Video item 2 -> Open Video Player
        binding.cardVideo2.setOnClickListener(v ->
                Navigation.findNavController(rootView).navigate(R.id.action_saved_to_videoPlayer)
        );
        binding.btnDownloadVid2.setOnClickListener(v ->
                Toast.makeText(requireContext(), "Downloading Coordinate Geometry (22:15)...", Toast.LENGTH_SHORT).show()
        );

        // Video item 3 -> Open Video Player
        binding.cardVideo3.setOnClickListener(v ->
                Navigation.findNavController(rootView).navigate(R.id.action_saved_to_videoPlayer)
        );

        // PDF Items -> Open PDF Viewer
        binding.btnReadNow1.setOnClickListener(v ->
                Navigation.findNavController(rootView).navigate(R.id.action_saved_to_pdfViewer)
        );
        binding.cardPdf1.setOnClickListener(v ->
                Navigation.findNavController(rootView).navigate(R.id.action_saved_to_pdfViewer)
        );

        binding.btnReadNow2.setOnClickListener(v ->
                Navigation.findNavController(rootView).navigate(R.id.action_saved_to_pdfViewer)
        );
        binding.cardPdf2.setOnClickListener(v ->
                Navigation.findNavController(rootView).navigate(R.id.action_saved_to_pdfViewer)
        );
        binding.btnSharePdf2.setOnClickListener(v ->
                Toast.makeText(requireContext(), "Sharing Higher Mathematics Full notes link...", Toast.LENGTH_SHORT).show()
        );

        binding.btnReadNow3.setOnClickListener(v ->
                Navigation.findNavController(rootView).navigate(R.id.action_saved_to_pdfViewer)
        );
        binding.cardPdf3.setOnClickListener(v ->
                Navigation.findNavController(rootView).navigate(R.id.action_saved_to_pdfViewer)
        );

        // Bottom Navigation Bar
        binding.navItemHome.setOnClickListener(v ->
                Navigation.findNavController(rootView).navigate(R.id.action_saved_to_home)
        );
        binding.navItemCourses.setOnClickListener(v ->
                Navigation.findNavController(rootView).navigate(R.id.action_saved_to_courseDetail)
        );
        binding.navItemSaved.setOnClickListener(v ->
                Toast.makeText(requireContext(), "You are already on Saved", Toast.LENGTH_SHORT).show()
        );
        binding.navItemAlerts.setOnClickListener(v ->
                Navigation.findNavController(rootView).navigate(R.id.action_saved_to_alerts)
        );
        binding.navItemProfile.setOnClickListener(v ->
                Navigation.findNavController(rootView).navigate(R.id.action_saved_to_profile)
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
