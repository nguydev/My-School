package com.edupulse.app.ui.player;

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
import com.edupulse.app.databinding.FragmentVideoPlayerBinding;
import com.edupulse.app.ui.common.ViewAnimationUtils;

public class VideoPlayerFragment extends Fragment {

    private FragmentVideoPlayerBinding binding;
    private boolean isPlaying = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentVideoPlayerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Apply tactile bounce animations
        ViewAnimationUtils.applyBounceEffect(
                binding.btnBack,
                binding.btnPlayPause,
                binding.btnPrevLesson,
                binding.btnNextLesson,
                binding.btnAskDoubt,
                binding.btnOpenChapterPdf,
                binding.btnCast,
                binding.btnBookmark,
                binding.btnQuality,
                binding.btnSpeed,
                binding.btnFullscreen
        );

        binding.btnBack.setOnClickListener(v -> Navigation.findNavController(v).navigateUp());

        // Play/Pause Toggle
        binding.btnPlayPause.setOnClickListener(v -> {
            isPlaying = !isPlaying;
            if (isPlaying) {
                binding.tvPlayPauseIcon.setText("⏸");
                Toast.makeText(requireContext(), "Resumed: 3.3 Action-Reaction & Momentum", Toast.LENGTH_SHORT).show();
            } else {
                binding.tvPlayPauseIcon.setText("▶");
                Toast.makeText(requireContext(), "Paused", Toast.LENGTH_SHORT).show();
            }
        });

        // Navigation between lessons
        binding.btnPrevLesson.setOnClickListener(v -> 
            Toast.makeText(requireContext(), "Loading Previous Lesson: 3.2 Newton's 2nd Law...", Toast.LENGTH_SHORT).show()
        );

        binding.btnNextLesson.setOnClickListener(v -> 
            Toast.makeText(requireContext(), "Loading Next Lesson: 3.4 Friction & Drag...", Toast.LENGTH_SHORT).show()
        );

        // Ask Doubt
        binding.btnAskDoubt.setOnClickListener(v -> 
            Toast.makeText(requireContext(), "Connecting with Dr. Tariq Rahman for Doubt Clearing...", Toast.LENGTH_LONG).show()
        );

        // Open Chapter PDF -> Navigates to PDF Viewer
        binding.btnOpenChapterPdf.setOnClickListener(v -> 
            Navigation.findNavController(v).navigate(R.id.action_videoPlayer_to_pdfViewer)
        );

        // Video Settings Controls
        binding.btnQuality.setOnClickListener(v -> 
            Toast.makeText(requireContext(), "Quality: 1080p HD (60fps)", Toast.LENGTH_SHORT).show()
        );

        binding.btnSpeed.setOnClickListener(v -> 
            Toast.makeText(requireContext(), "Playback Speed: 1.25x", Toast.LENGTH_SHORT).show()
        );

        binding.btnFullscreen.setOnClickListener(v -> 
            Toast.makeText(requireContext(), "Entering Fullscreen Mode", Toast.LENGTH_SHORT).show()
        );

        binding.btnCast.setOnClickListener(v -> 
            Toast.makeText(requireContext(), "Searching for Google Cast / Smart TVs...", Toast.LENGTH_SHORT).show()
        );

        binding.btnBookmark.setOnClickListener(v -> 
            Toast.makeText(requireContext(), "Lesson 3.3 saved to your Bookmarks!", Toast.LENGTH_SHORT).show()
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
