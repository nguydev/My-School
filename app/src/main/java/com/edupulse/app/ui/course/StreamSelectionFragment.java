package com.edupulse.app.ui.course;

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
import com.edupulse.app.databinding.FragmentStreamSelectionBinding;
import com.edupulse.app.ui.common.BottomNavHelper;
import com.edupulse.app.ui.common.ViewAnimationUtils;

public class StreamSelectionFragment extends Fragment {

    private FragmentStreamSelectionBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentStreamSelectionBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        ViewAnimationUtils.applyBounceEffect(
                binding.btnBack,
                binding.cardScienceStream,
                binding.btnEnterScienceStream,
                binding.cardCommerceStream,
                binding.btnEnterCommerceStream,
                binding.cardHumanitiesStream,
                binding.btnEnterHumanitiesStream,
                binding.ivUserProfileTop
        );

        setupClickListeners(view);
        BottomNavHelper.setupBottomNav(view, BottomNavHelper.ActiveTab.COURSES);
    }

    private void setupClickListeners(View rootView) {
        binding.btnBack.setOnClickListener(v -> 
            Navigation.findNavController(rootView).navigateUp()
        );
        binding.ivUserProfileTop.setOnClickListener(v ->
            Navigation.findNavController(rootView).navigate(R.id.profileFragment)
        );

        // Science Stream -> Navigates to BookViewerFragment!
        View.OnClickListener scienceClick = v -> 
            Navigation.findNavController(rootView).navigate(R.id.action_streamSelection_to_bookViewer);
        binding.cardScienceStream.setOnClickListener(scienceClick);
        binding.btnEnterScienceStream.setOnClickListener(scienceClick);

        // Commerce Stream -> Navigates to BookViewerFragment!
        View.OnClickListener commerceClick = v -> 
            Navigation.findNavController(rootView).navigate(R.id.action_streamSelection_to_bookViewer);
        binding.cardCommerceStream.setOnClickListener(commerceClick);
        binding.btnEnterCommerceStream.setOnClickListener(commerceClick);

        // Humanities Stream -> Navigates to BookViewerFragment!
        View.OnClickListener humanitiesClick = v -> 
            Navigation.findNavController(rootView).navigate(R.id.action_streamSelection_to_bookViewer);
        binding.cardHumanitiesStream.setOnClickListener(humanitiesClick);
        binding.btnEnterHumanitiesStream.setOnClickListener(humanitiesClick);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
