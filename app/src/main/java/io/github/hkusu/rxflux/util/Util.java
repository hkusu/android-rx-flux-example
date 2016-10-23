package io.github.hkusu.rxflux.util;

import android.support.annotation.NonNull;
import android.util.Log;

import io.github.hkusu.rxflux.BuildConfig;

public class Util {
    public static void logThread(@NonNull String methodName) {
        if (BuildConfig.DEBUG) {
            Log.d("current-thread:" + methodName, Thread.currentThread().getName());
        }
    }
}
