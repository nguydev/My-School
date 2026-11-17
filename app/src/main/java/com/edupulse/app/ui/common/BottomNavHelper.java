package com.edupulse.app.ui.common;

import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.edupulse.app.R;

public class BottomNavHelper {

    public enum ActiveTab {
        HOME,
        COURSES,
        SAVED,
        ALERTS,
        PROFILE
    }

    public static void setupBottomNav(View rootView, ActiveTab currentTab) {
        View navHome = rootView.findViewById(R.id.navItemHome);
        View navCourses = rootView.findViewById(R.id.navItemCourses);
        View navSaved = rootView.findViewById(R.id.navItemSaved);
        View navAlerts = rootView.findViewById(R.id.navItemAlerts);
        View navProfile = rootView.findViewById(R.id.navItemProfile);

        if (navHome == null) return;

        // Apply bounce animations to navigation icons
        ViewAnimationUtils.applyBounceEffect(navHome, navCourses, navSaved, navAlerts, navProfile);

        navHome.setOnClickListener(v -> {
            if (currentTab != ActiveTab.HOME) {
                try {
                    NavController navController = Navigation.findNavController(rootView);
                    navController.popBackStack(R.id.homeFragment, false);
                } catch (Exception ignored) {}
            }
        });

        navCourses.setOnClickListener(v -> {
            if (currentTab != ActiveTab.COURSES) {
                try {
                    NavController navController = Navigation.findNavController(rootView);
                    navController.navigate(R.id.streamSelectionFragment);
                } catch (Exception ignored) {}
            }
        });

        navSaved.setOnClickListener(v -> {
            if (currentTab != ActiveTab.SAVED) {
                try {
                    NavController navController = Navigation.findNavController(rootView);
                    navController.navigate(R.id.savedFragment);
                } catch (Exception ignored) {}
            }
        });

        navAlerts.setOnClickListener(v -> {
            if (currentTab != ActiveTab.ALERTS) {
                try {
                    NavController navController = Navigation.findNavController(rootView);
                    navController.navigate(R.id.alertsFragment);
                } catch (Exception ignored) {}
            }
        });

        navProfile.setOnClickListener(v -> {
            if (currentTab != ActiveTab.PROFILE) {
                try {
                    NavController navController = Navigation.findNavController(rootView);
                    navController.navigate(R.id.profileFragment);
                } catch (Exception ignored) {}
            }
        });
    }
}
