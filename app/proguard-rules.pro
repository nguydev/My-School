# EduPulse Proguard Rules
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes InnerClasses
-keepattributes EnclosingMethod

# Gson Models
-keep class com.edupulse.app.data.model.** { *; }
-keep class com.edupulse.app.data.remote.** { *; }

# Retrofit
-keepclassmembers,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
