package com.sbugert.rnadmob;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class RNAdMobInterstitialAdModule extends ReactContextBaseJavaModule {
  InterstitialAd mInterstitialAd;
  InterstitialAd mInterstitialAd2;
  InterstitialAd mInterstitialAd3;
  String adUnitID;
  String testDeviceID;
  Callback requestAdCallback;
  Callback showAdCallback;
  Callback requestAdCallback2;
  Callback showAdCallback2;
  Callback requestAdCallback3;
  Callback showAdCallback3;

  @Override
  public String getName() {
    return "RNAdMobInterstitial";
  }

  public RNAdMobInterstitialAdModule(ReactApplicationContext reactContext) {
    super(reactContext);
    mInterstitialAd = new InterstitialAd(reactContext);
    mInterstitialAd2 = new InterstitialAd(reactContext);
    mInterstitialAd3 = new InterstitialAd(reactContext);

    new Handler(Looper.getMainLooper()).post(new Runnable() {
      @Override
      public void run() {
        mInterstitialAd.setAdListener(new AdListener() {
          @Override
          public void onAdClosed() {
            sendEvent("interstitialDidClose", null);
            showAdCallback.invoke();
          }
          @Override
          public void onAdFailedToLoad(int errorCode) {
            WritableMap event = Arguments.createMap();
            String errorString = null;
            switch (errorCode) {
              case AdRequest.ERROR_CODE_INTERNAL_ERROR:
                errorString = "ERROR_CODE_INTERNAL_ERROR";
                break;
              case AdRequest.ERROR_CODE_INVALID_REQUEST:
                errorString = "ERROR_CODE_INVALID_REQUEST";
                break;
              case AdRequest.ERROR_CODE_NETWORK_ERROR:
                errorString = "ERROR_CODE_NETWORK_ERROR";
                break;
              case AdRequest.ERROR_CODE_NO_FILL:
                errorString = "ERROR_CODE_NO_FILL";
                break;
            }
            event.putString("error", errorString);
            sendEvent("interstitialDidFailToLoad", event);
            requestAdCallback.invoke(errorString);
          }
          @Override
          public void onAdLeftApplication() {
            sendEvent("interstitialWillLeaveApplication", null);
          }
          @Override
          public void onAdLoaded() {
            sendEvent("interstitialDidLoad", null);
            requestAdCallback.invoke();
          }
          @Override
          public void onAdOpened() {
            sendEvent("interstitialDidOpen", null);
          }
        });

        mInterstitialAd2.setAdListener(new AdListener() {
          @Override
          public void onAdClosed() {
            sendEvent("interstitialDidClose", null);
            showAdCallback2.invoke();
          }
          @Override
          public void onAdFailedToLoad(int errorCode) {
            WritableMap event = Arguments.createMap();
            String errorString = null;
            switch (errorCode) {
              case AdRequest.ERROR_CODE_INTERNAL_ERROR:
                errorString = "ERROR_CODE_INTERNAL_ERROR";
                break;
              case AdRequest.ERROR_CODE_INVALID_REQUEST:
                errorString = "ERROR_CODE_INVALID_REQUEST";
                break;
              case AdRequest.ERROR_CODE_NETWORK_ERROR:
                errorString = "ERROR_CODE_NETWORK_ERROR";
                break;
              case AdRequest.ERROR_CODE_NO_FILL:
                errorString = "ERROR_CODE_NO_FILL";
                break;
            }
            event.putString("error", errorString);
            sendEvent("interstitialDidFailToLoad", event);
            requestAdCallback2.invoke(errorString);
          }
          @Override
          public void onAdLeftApplication() {
            sendEvent("interstitialWillLeaveApplication2", null);
          }
          @Override
          public void onAdLoaded() {
            sendEvent("interstitialDidLoad", null);
            requestAdCallback2.invoke();
          }
          @Override
          public void onAdOpened() {
            sendEvent("interstitialDidOpen", null);
          }
        });

        mInterstitialAd3.setAdListener(new AdListener() {
          @Override
          public void onAdClosed() {
            sendEvent("interstitialDidClose3", null);
            showAdCallback3.invoke();
          }
          @Override
          public void onAdFailedToLoad(int errorCode) {
            WritableMap event = Arguments.createMap();
            String errorString = null;
            switch (errorCode) {
              case AdRequest.ERROR_CODE_INTERNAL_ERROR:
                errorString = "ERROR_CODE_INTERNAL_ERROR";
                break;
              case AdRequest.ERROR_CODE_INVALID_REQUEST:
                errorString = "ERROR_CODE_INVALID_REQUEST";
                break;
              case AdRequest.ERROR_CODE_NETWORK_ERROR:
                errorString = "ERROR_CODE_NETWORK_ERROR";
                break;
              case AdRequest.ERROR_CODE_NO_FILL:
                errorString = "ERROR_CODE_NO_FILL";
                break;
            }
            event.putString("error", errorString);
            sendEvent("interstitialDidFailToLoad", event);
            requestAdCallback3.invoke(errorString);
          }
          @Override
          public void onAdLeftApplication() {
            sendEvent("interstitialWillLeaveApplication3", null);
          }
          @Override
          public void onAdLoaded() {
            sendEvent("interstitialDidLoad", null);
            requestAdCallback3.invoke();
          }
          @Override
          public void onAdOpened() {
            sendEvent("interstitialDidOpen", null);
          }
        });
      }
    });
  }
  private void sendEvent(String eventName, @Nullable WritableMap params) {
    getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName, params);
  }

  @ReactMethod
  public void setAdUnitID(String adUnitID) {
    mInterstitialAd.setAdUnitId(adUnitID);
  }

  @ReactMethod
  public void setAdUnitID2(String adUnitID) {
    mInterstitialAd2.setAdUnitId(adUnitID);
  }

  @ReactMethod
  public void setAdUnitID3(String adUnitID) {
    mInterstitialAd3.setAdUnitId(adUnitID);
  }

  @ReactMethod
  public void getAdUnitId(final Callback callback){
    callback.invoke(mInterstitialAd.getAdUnitId());
  }

  @ReactMethod
  public void getAdUnitId2(final Callback callback){
    callback.invoke(mInterstitialAd2.getAdUnitId());
  }

  @ReactMethod
  public void getAdUnitId3(final Callback callback){
    callback.invoke(mInterstitialAd3.getAdUnitId());
  }

  @ReactMethod
  public void setTestDeviceID(String testDeviceID) {
    this.testDeviceID = testDeviceID;
  }

  @ReactMethod
  public void requestAd(final Callback callback) {
    new Handler(Looper.getMainLooper()).post(new Runnable() {
      @Override
      public void run () {
        if (mInterstitialAd.isLoaded() || mInterstitialAd.isLoading()) {
          callback.invoke("Ad is already loaded."); // TODO: make proper error
        } else {
          requestAdCallback = callback;
          AdRequest.Builder adRequestBuilder = new AdRequest.Builder();
          if (testDeviceID != null){
            if (testDeviceID.equals("EMULATOR")) {
              adRequestBuilder = adRequestBuilder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR);
            } else {
              adRequestBuilder = adRequestBuilder.addTestDevice(testDeviceID);
            }
          }
          AdRequest adRequest = adRequestBuilder.build();
          mInterstitialAd.loadAd(adRequest);
        }
      }
    });
  }

  @ReactMethod
  public void requestAd2(final Callback callback) {
    new Handler(Looper.getMainLooper()).post(new Runnable() {
      @Override
      public void run () {
        if (mInterstitialAd2.isLoaded() || mInterstitialAd2.isLoading()) {
          callback.invoke("Ad is already loaded."); // TODO: make proper error
        } else {
          requestAdCallback2 = callback;
          AdRequest.Builder adRequestBuilder = new AdRequest.Builder();
          if (testDeviceID != null){
            if (testDeviceID.equals("EMULATOR")) {
              adRequestBuilder = adRequestBuilder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR);
            } else {
              adRequestBuilder = adRequestBuilder.addTestDevice(testDeviceID);
            }
          }
          AdRequest adRequest = adRequestBuilder.build();
          mInterstitialAd2.loadAd(adRequest);
        }
      }
    });
  }

  @ReactMethod
  public void requestAd3(final Callback callback) {
    new Handler(Looper.getMainLooper()).post(new Runnable() {
      @Override
      public void run () {
        if (mInterstitialAd3.isLoaded() || mInterstitialAd3.isLoading()) {
          callback.invoke("Ad is already loaded."); // TODO: make proper error
        } else {
          requestAdCallback3 = callback;
          AdRequest.Builder adRequestBuilder = new AdRequest.Builder();
          if (testDeviceID != null){
            if (testDeviceID.equals("EMULATOR")) {
              adRequestBuilder = adRequestBuilder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR);
            } else {
              adRequestBuilder = adRequestBuilder.addTestDevice(testDeviceID);
            }
          }
          AdRequest adRequest = adRequestBuilder.build();
          mInterstitialAd3.loadAd(adRequest);
        }
      }
    });
  }

  @ReactMethod
  public void showAd(final Callback callback) {
    new Handler(Looper.getMainLooper()).post(new Runnable() {
      @Override
      public void run () {
        if (mInterstitialAd.isLoaded()) {
          showAdCallback = callback;
          mInterstitialAd.show();
        } else {
          callback.invoke("Ad is not ready."); // TODO: make proper error
        }
      }
    });
  }

  @ReactMethod
  public void showAd2(final Callback callback) {
    new Handler(Looper.getMainLooper()).post(new Runnable() {
      @Override
      public void run () {
        if (mInterstitialAd2.isLoaded()) {
          showAdCallback2 = callback;
          mInterstitialAd2.show();
        } else {
          callback.invoke("Ad is not ready."); // TODO: make proper error
        }
      }
    });
  }

  @ReactMethod
  public void showAd3(final Callback callback) {
    new Handler(Looper.getMainLooper()).post(new Runnable() {
      @Override
      public void run () {
        if (mInterstitialAd3.isLoaded()) {
          showAdCallback3 = callback;
          mInterstitialAd3.show();
        } else {
          callback.invoke("Ad is not ready."); // TODO: make proper error
        }
      }
    });
  }


  @ReactMethod
  public void isReady(final Callback callback) {
    new Handler(Looper.getMainLooper()).post(new Runnable() {
      @Override
      public void run () {
        callback.invoke(mInterstitialAd.isLoaded());
      }
    });
  }
}
