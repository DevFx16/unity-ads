# Unity Ads Flutter

## Getting Started

### 1. Initialization

Call `UnityAds.initialize('your_game_android', 'your_game_ios', this, testMode: !kReleaseMode)` during app initialization.

### 2. show Interstitial Ad and Rewarded Video Ad

``` dart
  UnityAds.show('#placement');
```

### 3. Events

``` dart
  void onUnityAdsError(UnityAdsError error, String message);

  void onUnityAdsFinish(String placementId, FinishState result);

  void onUnityAdsReady(String placementId);

  void onUnityAdsStart(String placementId);
```

## Future Work
Implement Banner and plugin for iOS platform.