package com.example.pixaloop.util;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class Preferences {

    public static class RateDialog {
        public static int a(@NonNull Context context) {
            return context.getSharedPreferences("rate_dialog_preferences_file", 0).getInt("rate_dialog_exports_count", 0);
        }

        public static long b(@NonNull Context context) {
            return context.getSharedPreferences("rate_dialog_preferences_file", 0).getLong("rate_dialog_shown", -1);
        }

        public static void c(@NonNull Context context) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("rate_dialog_preferences_file", 0);
            int i = sharedPreferences.getInt("rate_dialog_exports_count", 0);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putInt("rate_dialog_exports_count", i + 1);
            edit.apply();
        }

        public static void d(@NonNull Context context) {
            Preferences.b(context, System.currentTimeMillis(), "rate_dialog_preferences_file", "rate_dialog_shown");
        }
    }

    public static void b(@NonNull Context context, boolean z, @NonNull String str, @NonNull String str2) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.putBoolean(str2, z);
        edit.apply();
    }

    public static class Onboarding {
        public static boolean a(@NonNull Context context) {
            return context.getSharedPreferences("onboarding_preferences_file", 0).getBoolean("onboarding_shown", false);
        }

        public static void a(@NonNull Context context, boolean z) {
            Preferences.b(context, z, "onboarding_preferences_file", "onboarding_shown");
        }
    }

    public static class Projects {
        @Nullable
        public static String a(@NonNull Context context) {
            return context.getSharedPreferences("projects_preferences_file", 0).getString("active_project_id", (String) null);
        }

        @Nullable
        public static String b(@NonNull Context context) {
            return context.getSharedPreferences("projects_preferences_file", 0).getString("default_project_id", (String) null);
        }

        public static void a(@NonNull Context context, @NonNull String str) {
            Preferences.b(context, str, "projects_preferences_file", "active_project_id");
        }

        public static void b(@NonNull Context context, @NonNull String str) {
            Preferences.b(context, str, "projects_preferences_file", "default_project_id");
        }
    }

    public static void b(@NonNull Context context, String str, @NonNull String str2, @NonNull String str3) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str2, 0).edit();
        edit.putString(str3, str);
        edit.apply();
    }

    public static void b(@NonNull Context context, long j, @NonNull String str, @NonNull String str2) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.putLong(str2, j);
        edit.apply();
    }
}
