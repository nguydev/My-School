# 🎓 EduPulse - Academic Learning Portal (Android Frontend)

A high-fidelity, production-grade Android authentication system engineered with **Java 17**, **Android Jetpack**, and **Clean Architecture (MVVM)**, designed to match modern cyber/glassmorphism design standards.

---

## 📱 Implemented Auth Screens & Architecture Mapping

| Screen Mockup | Fragment Class | XML Layout | Key Features |
|---|---|---|---|
| **1. Nexus Portal Sign-In** *(Img 1 Left)* | `NexusPortalLoginFragment.java` | `fragment_nexus_portal_login.xml` | Institutional credentials login, password toggle, Remember Me, SSO/MFA badge, Auth state simulation (Error/Success) |
| **2. EduPulse Scholar Sign-In** *(Img 1 Right / Img 2 Left)* | `EduPulseSignInFragment.java` | `fragment_edupulse_sign_in.xml` | Term status chip, student learning portal card, 30-day device remember toggle, Google Classroom SSO |
| **3. Complete Registration** *(Img 2 Right)* | `RegisterStep2Fragment.java` | `fragment_register_step2.xml` | 2-step progress bar, passport photo upload trigger, major stream checkmark, class dropdown selector, SMS OTP verified badge, terms checkbox |
| **4. Verify Your Email** *(Img 3 Left)* | `VerifyEmailFragment.java` | `fragment_verify_email.xml` | Glowing envelope container, academic email validation, 256-bit SSL verification badge |
| **5. 6-Digit OTP Identity Verification** *(Img 3 Middle)* | `VerifyOtpFragment.java` | `fragment_verify_otp.xml` | 6 separate auto-advancing OTP digit boxes, auto-fill active simulator, resend countdown pill, rate-limiting security gateway notice |
| **6. Reset Password** *(Img 3 Right)* | `ResetPasswordFragment.java` | `fragment_reset_password.xml` | 3-step credential recovery, verified student record locator ("Alex Parker - Grade 11B"), real-time password matching validator, IT helpdesk launcher |

---

## 🏛 Clean Architecture & Project Directory Structure

```
Yesin-app/
├── build.gradle                          # Root Gradle build script
├── settings.gradle                       # Gradle project settings
├── gradle.properties                     # JVM & AndroidX optimization flags
└── app/
    ├── build.gradle                      # App-level dependencies (ViewBinding, Jetpack Navigation, Material3)
    ├── proguard-rules.pro                # Production obfuscation and model-preserving rules
    └── src/
        └── main/
            ├── AndroidManifest.xml       # App declaration, permissions, and launcher activity
            ├── java/com/edupulse/app/
            │   ├── EduPulseApplication.java          # App bootstrap & DI initializer
            │   │
            │   ├── data/                             # DATA LAYER
            │   │   ├── local/
            │   │   │   └── PreferenceManager.java    # SharedPreferences & session storage
            │   │   ├── model/
            │   │   │   ├── User.java                 # Student entity
            │   │   │   ├── AuthState.java            # Immutable UI state holder
            │   │   │   ├── StudentRegistrationRequest.java
            │   │   │   └── ResetPasswordRequest.java
            │   │   ├── remote/
            │   │   │   └── Resource.java             # Network status encapsulation (LOADING, SUCCESS, ERROR)
            │   │   └── repository/
            │   │       ├── AuthRepository.java       # Clean interface definition
            │   │       └── AuthRepositoryImpl.java   # Local/Remote implementation with mock latency
            │   │
            │   ├── domain/                           # DOMAIN LAYER (Pure Business Logic)
            │   │   └── usecase/
            │   │       ├── LoginUseCase.java
            │   │       ├── RegisterStudentUseCase.java
            │   │       ├── VerifyOtpUseCase.java
            │   │       ├── SendEmailVerificationUseCase.java
            │   │       └── ResetPasswordUseCase.java
            │   │
            │   ├── di/                               # DEPENDENCY INJECTION
            │   │   └── ServiceLocator.java           # Zero-boilerplate service locator container
            │   │
            │   ├── ui/                               # PRESENTATION LAYER
            │   │   ├── base/
            │   │   │   └── UiEvent.java              # SingleLiveEvent pattern for toasts & navigation
            │   │   └── auth/
            │   │       ├── AuthActivity.java         # Host Activity with Floating Screen Switcher
            │   │       ├── AuthViewModel.java        # Shared ViewModel observing Use Cases & exposing LiveData
            │   │       ├── portal/
            │   │       │   └── NexusPortalLoginFragment.java
            │   │       ├── signin/
            │   │       │   └── EduPulseSignInFragment.java
            │   │       ├── register/
            │   │       │   └── RegisterStep2Fragment.java
            │   │       ├── verify/
            │   │       │   ├── VerifyEmailFragment.java
            │   │       │   └── VerifyOtpFragment.java
            │   │       └── password/
            │   │           └── ResetPasswordFragment.java
            │   │
            │   └── utils/
            │       └── ValidationUtils.java          # Regex for email, password strength, and OTP
            │
            └── res/                                  # DESIGN SYSTEM & RESOURCES
                ├── anim/                             # Transitions (slide_in_right, slide_out_left, fade_in/out)
                ├── drawable/                         # Glassmorphism, neon glows, input borders & custom vectors
                ├── layout/                           # High-fidelity XML layouts for all 6 screens
                ├── navigation/
                │   └── nav_auth_graph.xml            # Jetpack Navigation Graph connecting all screens
                ├── values/
                │   ├── colors.xml                    # Cyber dark palette (#090E17, #00E5FF, #0077FE)
                │   ├── strings.xml                   # Localized strings for entire auth journey
                │   ├── dimens.xml                    # 8dp grid spacing & corner radii
                │   └── themes.xml                    # Material3 Dark Theme
                └── xml/
                    ├── backup_rules.xml
                    └── data_extraction_rules.xml
```

---

## 🎨 Design System Palette

- **Canvas Background**: `#090E17` / `#0F172A`
- **Glassmorphic Surface**: `#111B2E` with 1dp border `#203254`
- **Electric Cyan (Neon Accent)**: `#00E5FF`
- **Electric Blue (Primary Button)**: `#0077FE` -> `#0284C7` (linear gradient)
- **Verified / Success Green**: `#10B981` (badge background `#1A10B981`)
- **Text Contrast**: High-emphasis `#FFFFFF`, Medium `#94A3B8`, Muted `#64748B`

---

## 🚀 How to Run in Android Studio

1. Open **Android Studio** (Hedgehog 2023.1.1 or Iguana/Jellyfish/Koala/Ladybug).
2. Select **File -> Open...** and select this directory: `Yesin-app`.
3. Allow Gradle to sync dependencies.
4. Select an Android Emulator or physical device (Android 7.0+ / API 24 to 34).
5. Click **Run 'app'** (`Shift + F10`).
6. **Testing all screens**: Tap the floating action button (school cap icon) at the bottom right corner of the app at any time to instantly jump between all 6 screens!
