package com.edupulse.app.ui.book;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.edupulse.app.R;
import com.edupulse.app.databinding.FragmentBookOrderBinding;
import com.edupulse.app.ui.common.BottomNavHelper;
import com.edupulse.app.ui.common.ViewAnimationUtils;

public class BookOrderFragment extends Fragment {

    private FragmentBookOrderBinding binding;
    private int quantity = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentBookOrderBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BottomNavHelper.setupBottomNav(view, BottomNavHelper.ActiveTab.COURSES);

        ViewAnimationUtils.applyBounceEffect(
                binding.btnBackOrder,
                binding.btnQtyMinus,
                binding.btnQtyPlus,
                binding.btnConfirmOrder
        );

        binding.btnBackOrder.setOnClickListener(v ->
                Navigation.findNavController(view).navigateUp()
        );

        // Quantity Stepper (1 to 2)
        binding.btnQtyMinus.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                binding.tvOrderQty.setText(String.valueOf(quantity));
            } else {
                Toast.makeText(requireContext(), "Minimum quantity is 1", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnQtyPlus.setOnClickListener(v -> {
            if (quantity < 2) {
                quantity++;
                binding.tvOrderQty.setText(String.valueOf(quantity));
            } else {
                Toast.makeText(requireContext(), "Maximum quota is 2 sponsored copies", Toast.LENGTH_SHORT).show();
            }
        });

        ViewAnimationUtils.hideKeyboardOnTouchOutside(view, requireActivity());

        // Confirm Order Validation
        binding.btnConfirmOrder.setOnClickListener(v -> {
            ViewAnimationUtils.hideKeyboard(requireActivity());
            String name = binding.etRecipientName.getText().toString().trim();
            String phone = binding.etContactPhone.getText().toString().trim();
            String address = binding.etDeliveryAddress.getText().toString().trim();

            if (TextUtils.isEmpty(name)) {
                binding.etRecipientName.setError("Full Name is required");
                binding.etRecipientName.requestFocus();
                return;
            }

            // BD Mobile Number Regex Validation (+8801... or 01...)
            String cleanPhone = phone.replaceAll("[\\s\\-\\(\\)]", "");
            if (!cleanPhone.matches("^(?:\\+?880|0)?1[3-9]\\d{8}$")) {
                binding.etContactPhone.setError("Please enter a valid 11-digit BD mobile number");
                binding.etContactPhone.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(address) || address.length() < 10) {
                binding.etDeliveryAddress.setError("Please enter complete delivery address (Min 10 characters)");
                binding.etDeliveryAddress.requestFocus();
                return;
            }

            // Navigate to Order Success
            Navigation.findNavController(view).navigate(R.id.action_bookOrder_to_orderSuccess);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
