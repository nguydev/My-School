package com.edupulse.app;

import android.app.Application;
import com.edupulse.app.di.ServiceLocator;

/**
 * Main Application class for EduPulse.
 * Initializes the Dependency Injection container and global services.
 */
public class EduPulseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize Service Locator for Clean Architecture DI
        ServiceLocator.getInstance().init(this);
    }
}
