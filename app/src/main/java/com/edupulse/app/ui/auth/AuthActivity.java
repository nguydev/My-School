package com.edupulse.app.ui.auth;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.edupulse.app.R;
import com.edupulse.app.databinding.ActivityAuthBinding;

public class AuthActivity extends AppCompatActivity {

    private ActivityAuthBinding binding;
    private NavController navController;
    private AuthViewModel authViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        }

        setupQuickScreenSwitcher();
        observeEvents();
    }

    private void observeEvents() {
        authViewModel.getToastMessage().observe(this, event -> {
            String msg = event.getContentIfNotHandled();
            if (msg != null) {
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Floating quick switcher allowing QA, developers, and reviewers
     * to immediately jump between all UI mockups.
     */
    private void setupQuickScreenSwitcher() {
        binding.fabScreenSwitcher.setOnClickListener(v -> {
            String[] screens = {
                    "✨ 0. App First Page (Onboarding Mentor)",
                    "📖 19. Book Store Catalog (অর্ডার সিস্টেম)",
                    "📝 20. Book Details & Order Form (অর্ডার ফর্ম)",
                    "🎉 21. Order Submitted Successfully (সাকসেস পেজ)",
                    "🏠 7. Student Home Page (নতুন হোম পেজ)",
                    "🔔 16. Alerts & Updates Feed (বিজ্ঞপ্তি পেজ)",
                    "🔖 17. Saved & Downloads Manager (সেভড পেজ)",
                    "👤 18. Student Profile & Analytics (প্রোফাইল পেজ)",
                    "📚 8. Course Detail Page (কোর্স ডিটেলস)",
                    "🔬 9. Stream Selection (স্ট্রিম সিলেকশন)",
                    "📖 10. Book Viewer (বুক ভিউয়ার)",
                    "🔍 11. Book Detail (বুক ডিটেলস)",
                    "🗺️ 12. Chapter Roadmap (চ্যাপ্টার রোডম্যাপ)",
                    "📺 13. Video Playlist (ভিডিও প্লেলিস্ট)",
                    "▶️ 14. Video Player & Interactive (ভিডিও প্লেয়ার)",
                    "📄 15. PDF Viewer (পিডিএফ রিডার)",
                    "1. Nexus Portal Login (সাইন ইন পেজ)",
                    "2. EduPulse Scholar Sign-In (অল্টারনেটিভ সাইন ইন)",
                    "3. Complete Registration Step 2 (রেজিস্ট্রেশন পেজ)",
                    "4. Verify Your Email (ইমেইল ভেরিফিকেশন)",
                    "5. 6-Digit OTP Identity Verification (ওটিপি পেজ)",
                    "6. Reset Password (পাসওয়ার্ড রিসেট)"
            };

            new AlertDialog.Builder(this)
                    .setTitle("Navigate App Screens")
                    .setItems(screens, (dialog, which) -> {
                        if (navController == null) return;
                        switch (which) {
                            case 0:
                                navController.navigate(R.id.onboardingFragment);
                                break;
                            case 1:
                                navController.navigate(R.id.bookStoreFragment);
                                break;
                            case 2:
                                navController.navigate(R.id.bookOrderFragment);
                                break;
                            case 3:
                                navController.navigate(R.id.orderSuccessFragment);
                                break;
                            case 4:
                                navController.navigate(R.id.homeFragment);
                                break;
                            case 5:
                                navController.navigate(R.id.alertsFragment);
                                break;
                            case 6:
                                navController.navigate(R.id.savedFragment);
                                break;
                            case 7:
                                navController.navigate(R.id.profileFragment);
                                break;
                            case 8:
                                navController.navigate(R.id.courseDetailFragment);
                                break;
                            case 9:
                                navController.navigate(R.id.streamSelectionFragment);
                                break;
                            case 10:
                                navController.navigate(R.id.bookViewerFragment);
                                break;
                            case 11:
                                navController.navigate(R.id.bookDetailFragment);
                                break;
                            case 12:
                                navController.navigate(R.id.chapterRoadmapFragment);
                                break;
                            case 13:
                                navController.navigate(R.id.videoPlaylistFragment);
                                break;
                            case 14:
                                navController.navigate(R.id.videoPlayerFragment);
                                break;
                            case 15:
                                navController.navigate(R.id.pdfViewerFragment);
                                break;
                            case 16:
                                navController.navigate(R.id.nexusPortalLoginFragment);
                                break;
                            case 17:
                                navController.navigate(R.id.edupulseSignInFragment);
                                break;
                            case 18:
                                navController.navigate(R.id.registerStep2Fragment);
                                break;
                            case 19:
                                navController.navigate(R.id.verifyEmailFragment);
                                break;
                            case 20:
                                navController.navigate(R.id.verifyOtpFragment);
                                break;
                            case 21:
                                navController.navigate(R.id.resetPasswordFragment);
                                break;
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });
    }
}
