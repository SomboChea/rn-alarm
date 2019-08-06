package com.backgroundexample;

import android.app.Application;
import android.util.Log;

import com.facebook.react.PackageList;
import com.facebook.hermes.reactexecutor.HermesExecutorFactory;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.ReactApplication;
import com.emekalites.react.alarm.notification.ANPackage;
import com.jamesisaac.rnbackgroundtask.BackgroundTaskPackage;
import io.realm.react.RealmReactPackage;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.soloader.SoLoader;
import com.facebook.react.shell.MainReactPackage;

import android.app.NotificationManager;
import android.app.NotificationChannel;
import android.graphics.Color;

import com.backgroundexample.service.LocationService;
import android.content.Context;
import android.os.Bundle;
import android.content.Intent;
import com.facebook.react.HeadlessJsTaskService;

import java.util.List;

public class MainApplication extends Application implements ReactApplication {

  private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
    @Override
    public boolean getUseDeveloperSupport() {
      return BuildConfig.DEBUG;
    }

    @Override
    protected List<ReactPackage> getPackages() {
      @SuppressWarnings("UnnecessaryLocalVariable")
      List<ReactPackage> packages = new PackageList(this).getPackages();
      // Packages that cannot be autolinked yet can be added manually here, for
      // example:
      // packages.add(new MyReactNativePackage());
      return packages;
    }

    @Override
    protected String getJSMainModuleName() {
      return "index";
    }
  };

  @Override
  public ReactNativeHost getReactNativeHost() {
    return mReactNativeHost;
  }

  @Override
  public void onCreate() {
    super.onCreate();

    SoLoader.init(this, /* native exopackage */ false);
    BackgroundTaskPackage.useContext(this); // ADD ME HERE!

    String id = "wakeup"; // The id of the channel.
    CharSequence name = "wakeup"; // The user-visible name of the channel.
    String description = "wakeup from sleep"; // The user-visible description of the channel.

    NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_DEFAULT);

    // Configure the notification channel.
    mChannel.setDescription(description);

    mChannel.enableLights(true);
    // Sets the notification light color for notifications posted to this
    // channel, if the device supports this feature.
    mChannel.setLightColor(Color.RED);

    mChannel.enableVibration(true);
    mChannel.setVibrationPattern(new long[] { 100, 200, 300, 400, 500, 400, 300, 200, 400 });

    NotificationManager mNotificationManager = (NotificationManager) this
        .getSystemService(Context.NOTIFICATION_SERVICE);
    mNotificationManager.createNotificationChannel(mChannel);

  }
}
