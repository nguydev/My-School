package com.edupulse.app.ui.book;

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
import com.edupulse.app.databinding.FragmentChapterRoadmapBinding;
import com.edupulse.app.ui.common.ViewAnimationUtils;

public class ChapterRoadmapFragment extends Fragment {

    private FragmentChapterRoadmapBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentChapterRoadmapBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Apply interactive animation
        ViewAnimationUtils.applyBounceEffect(
                binding.btnBack,
                binding.btnContinueLesson,
                binding.cardActiveChapter3,
                binding.btnJoinDoubt,
                binding.cardDoubtClearing
        );

        binding.btnBack.setOnClickListener(v -> Navigation.findNavController(v).navigateUp());

        // Continue Lesson -> Navigates to Video Playlist!
        binding.btnContinueLesson.setOnClickListener(v -> 
            Navigation.findNavController(v).navigate(R.id.action_chapterRoadmap_to_videoPlaylist)
        );

        // Card Active Chapter 3 -> Navigates to Video Playlist!
        binding.cardActiveChapter3.setOnClickListener(v -> 
            Navigation.findNavController(v).navigate(R.id.action_chapterRoadmap_to_videoPlaylist)
        );

        binding.btnJoinDoubt.setOnClickListener(v -> {
            Toast.makeText(requireContext(), "Joining Doubt Clearing Hour with Dr. Tahmid Hasan...", Toast.LENGTH_LONG).show();
        });

        binding.cardDoubtClearing.setOnClickListener(v -> {
            Toast.makeText(requireContext(), "Live Q&A on Chapter 03 tonight at 8:00 PM", Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
