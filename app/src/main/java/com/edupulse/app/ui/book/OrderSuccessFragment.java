package com.edupulse.app.ui.book;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
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
import com.edupulse.app.databinding.FragmentOrderSuccessBinding;
import com.edupulse.app.ui.common.ViewAnimationUtils;

public class OrderSuccessFragment extends Fragment {

    private FragmentOrderSuccessBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentOrderSuccessBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewAnimationUtils.applyBounceEffect(
                binding.cardCopyOrderId,
                binding.btnBackToBookStore,
                binding.btnSuccessGoHome
        );

        // Copy Order ID
        binding.cardCopyOrderId.setOnClickListener(v -> {
            String orderId = binding.tvSuccessOrderId.getText().toString();
            ClipboardManager clipboard = (ClipboardManager) requireContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Order ID", orderId);
            if (clipboard != null) {
                clipboard.setPrimaryClip(clip);
                Toast.makeText(requireContext(), "Order ID Copied: " + orderId, Toast.LENGTH_SHORT).show();
            }
        });

        // Back to Book Store
        binding.btnBackToBookStore.setOnClickListener(v ->
                Navigation.findNavController(view).navigate(R.id.action_orderSuccess_to_bookStore)
        );

        // Go to Home
        binding.btnSuccessGoHome.setOnClickListener(v ->
                Navigation.findNavController(view).navigate(R.id.action_orderSuccess_to_home)
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
