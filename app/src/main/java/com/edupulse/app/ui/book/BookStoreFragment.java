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
import com.edupulse.app.databinding.FragmentBookStoreBinding;
import com.edupulse.app.ui.common.ViewAnimationUtils;
import com.edupulse.app.ui.common.BottomNavHelper;

public class BookStoreFragment extends Fragment {

    private FragmentBookStoreBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentBookStoreBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewAnimationUtils.applyBounceEffect(
                binding.btnBackBookStore,
                binding.btnOrderBook1,
                binding.btnOrderBook2,
                binding.btnOrderBook3,
                binding.cardBookOrder1,
                binding.cardBookOrder2,
                binding.cardBookOrder3,
                binding.tabAllBooks,
                binding.tabPhysics,
                binding.tabChemistry,
                binding.tabHigherMath
        );

        binding.btnBackBookStore.setOnClickListener(v ->
                Navigation.findNavController(view).navigateUp()
        );
        BottomNavHelper.setupBottomNav(view, BottomNavHelper.ActiveTab.COURSES);

        binding.ivProfileTopStore.setOnClickListener(v ->
                Navigation.findNavController(view).navigate(R.id.profileFragment)
        );

        // Subject Tabs
        binding.tabAllBooks.setOnClickListener(v -> {
            binding.cardBookOrder1.setVisibility(View.VISIBLE);
            binding.cardBookOrder2.setVisibility(View.VISIBLE);
            binding.cardBookOrder3.setVisibility(View.VISIBLE);
            Toast.makeText(requireContext(), "Showing All Handbooks", Toast.LENGTH_SHORT).show();
        });

        binding.tabPhysics.setOnClickListener(v -> {
            binding.cardBookOrder1.setVisibility(View.VISIBLE);
            binding.cardBookOrder2.setVisibility(View.GONE);
            binding.cardBookOrder3.setVisibility(View.GONE);
            Toast.makeText(requireContext(), "Filtered: Physics Handbooks", Toast.LENGTH_SHORT).show();
        });

        binding.tabChemistry.setOnClickListener(v -> {
            binding.cardBookOrder1.setVisibility(View.GONE);
            binding.cardBookOrder2.setVisibility(View.VISIBLE);
            binding.cardBookOrder3.setVisibility(View.GONE);
            Toast.makeText(requireContext(), "Filtered: Chemistry Handbooks", Toast.LENGTH_SHORT).show();
        });

        binding.tabHigherMath.setOnClickListener(v -> {
            binding.cardBookOrder1.setVisibility(View.GONE);
            binding.cardBookOrder2.setVisibility(View.GONE);
            binding.cardBookOrder3.setVisibility(View.VISIBLE);
            Toast.makeText(requireContext(), "Filtered: Higher Math Handbooks", Toast.LENGTH_SHORT).show();
        });

        // Order Buttons -> Go to Book Details & Order Form
        binding.btnOrderBook1.setOnClickListener(v ->
                Navigation.findNavController(view).navigate(R.id.action_bookStore_to_bookOrder)
        );
        binding.cardBookOrder1.setOnClickListener(v ->
                Navigation.findNavController(view).navigate(R.id.action_bookStore_to_bookOrder)
        );

        binding.btnOrderBook2.setOnClickListener(v ->
                Navigation.findNavController(view).navigate(R.id.action_bookStore_to_bookOrder)
        );

        binding.btnOrderBook3.setOnClickListener(v ->
                Navigation.findNavController(view).navigate(R.id.action_bookStore_to_bookOrder)
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
