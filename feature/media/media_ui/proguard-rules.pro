#noinspection ShrinkerUnresolvedReference
# ===================== HILT / DAGGER FOR UI MODULE =====================

# Keep all annotations (required by Hilt/Dagger)
-keepattributes *Annotation*

# Keep classes annotated with @HiltViewModel (used for injecting ViewModels)
-keep @dagger.hilt.android.lifecycle.HiltViewModel class * { <init>(...); }

# Keep all ViewModel subclasses (used by Jetpack Compose / Lifecycle)
-keep class * extends androidx.lifecycle.ViewModel

# Keep generated Hilt modules for ViewModels
-keep class *ViewModel_HiltModules* { *; }
-keep class *HiltModules* { *; }

# Keep Hilt internal classes
-keep class dagger.hilt.internal.** { *; }
-dontwarn dagger.hilt.internal.**

# Keep Hilt aggregated dependency classes
-keep class hilt_aggregated_deps.** { *; }
-dontwarn hilt_aggregated_deps.**

# ===================== ARROW EITHER =====================

# Keep Arrow Either classes to avoid type erasure issues in release builds
-keep class arrow.core.Either { *; }
-keep class arrow.core.Left { *; }
-keep class arrow.core.Right { *; }

# ===================== LOCAL CLASSES IN auth_ui MODULE =====================

-keep class com.davidluna.tmdb.media_ui.** { *; }
-dontwarn com.davidluna.tmdb.media_ui.**