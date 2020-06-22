package com.example.pixaloop.util;

import android.util.Log;

import com.google.common.collect.EvictingQueue;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Locale;

public class LogU {
    public static final String[] a = {"", "", "V/", "D/", "I/", "W/", "E/", "A/"};
    public static final DateFormat b = new SimpleDateFormat("dd-MM HH:mm:ss", Locale.US);
    public static final EvictingQueue<String> c = EvictingQueue.create(2048);
    public static boolean d = true;

    public static String a(int i, String str) {
        return str;
    }



    public static void c(String str, String str2) {
        a(4, 4, str, str2);
    }

    public static void d(String str, String str2) {
        if (d) {

        }
        Log.d(str, str2);
    }

    public static void e(String str, String str2) {
        Log.e(str,str2);
    }

    public static void b(String str, String str2) {
        Exception exc = new Exception(str2);
        StackTraceElement[] stackTraceElementArr = new StackTraceElement[(exc.getStackTrace().length - 1)];
        System.arraycopy(exc.getStackTrace(), 1, stackTraceElementArr, 0, stackTraceElementArr.length);
        exc.setStackTrace(stackTraceElementArr);
        a(6, 4, str, str2);
        a(6, 4, str, (Throwable) exc);
    }



    public static void a(String str, String str2, Throwable th) {
        Log.e(str, str2);
    }

    public static void a(String str, String str2) {
        a(3, 4, str, str2);
    }

    public static String a() {
        String sb;
        StringBuilder sb2 = new StringBuilder();
        synchronized (c) {
            Iterator<String> it = c.iterator();
            while (it.hasNext()) {
                sb2.append(it.next());
            }
            sb = sb2.toString();
        }
        return sb;
    }

    public static void a(int i, int i2, String str, String str2) {

        if (d) {
            Log.e(str, str2);
        } else {
            android.util.Log.println(i, str, str2);
        }
    }

    public static void a(int i, int i2, String str, Throwable th) {
        a(i2 + 1, str);
        String stackTraceString = android.util.Log.getStackTraceString(th);
        a(i, str, stackTraceString);
        if (d) {
            Log.d("xxl", str);
        } else {
            android.util.Log.println(i, str, stackTraceString);
        }
    }

    public static void a(int i, String str, String str2) {
        synchronized (c) {
            //c.add(String.format("%s %s%s: %s\n", new Object[]{b.format(new Date()), a[i], str, str2}));
        }
    }
}
