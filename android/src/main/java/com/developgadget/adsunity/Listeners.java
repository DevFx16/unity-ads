package com.developgadget.adsunity;

import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.mediation.IUnityAdsExtendedListener;

import java.util.HashMap;
import java.util.Map;

public class Listeners implements IUnityAdsListener, IUnityAdsExtendedListener {

    private Map<String, Object> arguments = new HashMap<String, Object>();

    @Override
    public void onUnityAdsReady(String s) {
        arguments.clear();
        arguments.put("placementId", s);
        AdsunityPlugin.getInstance().OnMethodCallHandler("onUnityAdsReady",  arguments);
    }

    @Override
    public void onUnityAdsStart(String s) {
        arguments.clear();
        arguments.put("placementId", s);
        AdsunityPlugin.getInstance().OnMethodCallHandler("onUnityAdsStart",  arguments);
    }

    @Override
    public void onUnityAdsFinish(String s, UnityAds.FinishState finishState) {
        arguments.clear();
        arguments.put("placementId", s);
        arguments.put("result", finishState.ordinal());
        AdsunityPlugin.getInstance().OnMethodCallHandler("onUnityAdsFinish",  arguments);
    }

    @Override
    public void onUnityAdsError(UnityAds.UnityAdsError unityAdsError, String s) {
        arguments.clear();
        arguments.put("error", unityAdsError.ordinal());
        arguments.put("message", s);
        AdsunityPlugin.getInstance().OnMethodCallHandler("onUnityAdsStart",  arguments);
    }

    @Override
    public void onUnityAdsClick(String s) {
        arguments.clear();
        arguments.put("message", s);
        AdsunityPlugin.getInstance().OnMethodCallHandler("onUnityAdsClick",  arguments);
    }

    @Override
    public void onUnityAdsPlacementStateChanged(String s, UnityAds.PlacementState placementState, UnityAds.PlacementState placementState1) {
        arguments.clear();
        arguments.put("message", s);
        AdsunityPlugin.getInstance().OnMethodCallHandler("onUnityAdsPlacementStateChanged",  arguments);
    }

}