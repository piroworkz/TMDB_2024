#noinspection ShrinkerUnresolvedReference
# ===================== GENERAL =====================
-keepattributes *Annotation*
-dontwarn java.lang.invoke.StringConcatFactory
-keep class java.lang.invoke.StringConcatFactory { *; }

# ===================== KOTLIN =====================
-keepclassmembers class **$WhenMappings { <fields>; }
-keepclassmembers class ** {
    static <fields>;
}
-keepclassmembers class kotlin.Metadata { *; }

# ===================== HILT / DAGGER =====================
-keep class dagger.hilt.** { *; }
-keep interface dagger.hilt.** { *; }
-keep class dagger.hilt.internal.** { *; }
-keep class hilt_aggregated_deps.** { *; }
-dontwarn hilt_aggregated_deps.**

-keep @dagger.hilt.android.lifecycle.HiltViewModel class * { <init>(...); }
-keep class * extends androidx.lifecycle.ViewModel
-keep class * extends dagger.hilt.android.internal.lifecycle.HiltViewModelFactory { *; }
-keep class *ViewModel_HiltModules* { *; }
-keep class *HiltModules* { *; }

# ===================== ANDROIDX =====================
-keep class androidx.lifecycle.** { *; }
-dontwarn androidx.lifecycle.**
-dontwarn androidx.room.**
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class * { *; }
-keep interface * implements androidx.room.Dao
-keep class androidx.compose.** { *; }
-dontwarn androidx.compose.**
-dontwarn androidx.navigation.**

# ===================== KOTLINX SERIALIZATION =====================
-keep class kotlinx.serialization.** { *; }
-keep class kotlinx.serialization.internal.** { *; }
-dontwarn kotlinx.serialization.**

## ===================== TU APP =====================
-keep class com.davidluna.tmdb.** { *; }
-dontwarn com.davidluna.tmdb.**
