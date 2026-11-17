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
import com.edupulse.app.databinding.FragmentVideoPlaylistBinding;
import com.edupulse.app.ui.common.ViewAnimationUtils;

public class VideoPlaylistFragment extends Fragment {

    private FragmentVideoPlaylistBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentVideoPlaylistBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Apply bounce animations to interactive buttons & cards
        ViewAnimationUtils.applyBounceEffect(
                binding.btnBack,
                binding.btnWatchLesson,
                binding.btnNotesAndPdf,
                binding.cardPlayingNow,
                binding.cardLesson31,
                binding.cardLesson32,
                binding.cardLesson34,
                binding.cardLesson35,
                binding.cardQuiz
        );

        binding.btnBack.setOnClickListener(v -> Navigation.findNavController(v).navigateUp());

        // Watch Lesson (Hero Button) -> Video Player Screen
        binding.btnWatchLesson.setOnClickListener(v -> 
            Navigation.findNavController(v).navigate(R.id.action_videoPlaylist_to_videoPlayer)
        );

        // Banner Click -> Video Player Screen
        binding.bannerVideo33.setOnClickListener(v -> 
            Navigation.findNavController(v).navigate(R.id.action_videoPlaylist_to_videoPlayer)
        );

        // Notes & PDF -> PDF Viewer Screen
        binding.btnNotesAndPdf.setOnClickListener(v -> 
            Navigation.findNavController(v).navigate(R.id.action_videoPlaylist_to_pdfViewer)
        );

        // Lesson 3.1 & 3.2 (Watched)
        binding.cardLesson31.setOnClickListener(v -> {
            Toast.makeText(requireContext(), "Replaying Lesson 3.1: Newton's First Law & Inertia", Toast.LENGTH_SHORT).show();
            Navigation.findNavController(v).navigate(R.id.action_videoPlaylist_to_videoPlayer);
        });

        binding.cardLesson32.setOnClickListener(v -> {
            Toast.makeText(requireContext(), "Replaying Lesson 3.2: Momentum & Newton's Second Law", Toast.LENGTH_SHORT).show();
            Navigation.findNavController(v).navigate(R.id.action_videoPlaylist_to_videoPlayer);
        });

        // Locked Lessons
        binding.cardLesson34.setOnClickListener(v -> 
            Toast.makeText(requireContext(), "Lesson 3.4 will unlock after finishing Lesson 3.3!", Toast.LENGTH_SHORT).show()
        );

        binding.cardLesson35.setOnClickListener(v -> 
            Toast.makeText(requireContext(), "Lesson 3.5 will unlock after finishing Lesson 3.4!", Toast.LENGTH_SHORT).show()
        );

        binding.cardQuiz.setOnClickListener(v -> 
            Toast.makeText(requireContext(), "Assessment Quiz unlocks after all 5 lessons!", Toast.LENGTH_SHORT).show()
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
