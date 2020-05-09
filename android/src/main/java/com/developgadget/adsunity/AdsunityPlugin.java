package com.developgadget.adsunity;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.unity3d.ads.UnityAds;

import java.util.Map;

import io.flutter.Log;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** AdsunityPlugin */
public class AdsunityPlugin implements FlutterPlugin, MethodCallHandler, ActivityAware {

  private static MethodChannel Channel;
  private static AdsunityPlugin Instance;
  private static Activity InstanceActivity;
  final Listeners ListenersAds = new Listeners();

  public static AdsunityPlugin getInstance() {
    return Instance;
  }

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    this.UnityAttach(flutterPluginBinding.getBinaryMessenger());
  }

  public static void registerWith(Registrar registrar) {
    if (InstanceActivity == null) InstanceActivity = registrar.activity();
    if (Instance == null) Instance = new AdsunityPlugin();
    Instance.UnityAttach(registrar.messenger());
  }

  private void UnityAttach(BinaryMessenger messenger) {
    if (AdsunityPlugin.Instance == null)
      AdsunityPlugin.Instance = new AdsunityPlugin();
    if (AdsunityPlugin.Channel != null)
      return;
    AdsunityPlugin.Channel = new MethodChannel(messenger, "UnityAds");
    AdsunityPlugin.Channel.setMethodCallHandler(this);
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    try {
      switch (call.method) {
        case "init":
          UnityAds.initialize(this.InstanceActivity,
                  call.<String>argument("gameId"), call.<Boolean>argument("testMode"));
          UnityAds.addListener(this.ListenersAds);
          break;
        case "getDebugMode":
          result.success(UnityAds.getDebugMode());
          break;
        case "getDefaultPlacementState":
          result.success(UnityAds.getPlacementState().ordinal());
          break;
        case "getPlacementState":
          result.success(UnityAds.getPlacementState(call.<String>argument("placementId")).ordinal());
          break;
        case "getVersion":
          result.success(UnityAds.getVersion());
          break;
        case "isInitialized":
          result.success(UnityAds.isInitialized());
          break;
        case "isReady":
          result.success(UnityAds.isReady(call.<String>argument("placementId")));
          break;
        case "isSupported":
          result.success(UnityAds.isSupported());
          break;
        case "setDebugMode":
          UnityAds.setDebugMode(call.<Boolean>argument("debugMode"));
          break;
        case "show":
          if (call.hasArgument("placementId")) {
            UnityAds.show(InstanceActivity, call.<String>argument("placementId"));
          } else {
            UnityAds.show(InstanceActivity);
          }
          break;
      }
      result.success(Boolean.TRUE);
    } catch (Exception e) {
      Log.e("UnityAds", "Error " + e.toString());
    }
  }
  void OnMethodCallHandler(final String method, final Map<String, Object> arguments) {
    try {
      AdsunityPlugin.InstanceActivity.runOnUiThread(new Runnable() {
        @Override
        public void run() {
          Channel.invokeMethod(method, arguments);
        }
      });
    } catch (Exception e) {
      Log.e("UnityAds", "Error " + e.toString());
    }
  }

  @Override
  public void onAttachedToActivity(ActivityPluginBinding binding) {
    AdsunityPlugin.InstanceActivity = binding.getActivity();
  }

  @Override
  public void onDetachedFromActivityForConfigChanges() {

  }

  @Override
  public void onReattachedToActivityForConfigChanges(ActivityPluginBinding binding) {
    AdsunityPlugin.InstanceActivity = binding.getActivity();
  }

  @Override
  public void onDetachedFromActivity() {

  }
  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
  }
}
