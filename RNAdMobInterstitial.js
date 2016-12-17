'use strict';

import {
  NativeModules,
  DeviceEventEmitter,
} from 'react-native';

const RNAdMobInterstitial = NativeModules.RNAdMobInterstitial;

const eventHandlers = {
  interstitialDidLoad: new Map(),
  interstitialDidFailToLoad: new Map(),
  interstitialDidOpen: new Map(),
  interstitialDidClose: new Map(),
  interstitialWillLeaveApplication: new Map(),
  interstitialWillLeaveApplication2: new Map(),
};

const addEventListener = (type, handler) => {
  switch (type) {
    case 'interstitialDidLoad':
      eventHandlers[type].set(handler, DeviceEventEmitter.addListener(type, handler));
      break;
    case 'interstitialDidFailToLoad':
      eventHandlers[type].set(handler, DeviceEventEmitter.addListener(type, (error) => { handler(error); }));
      break;
    case 'interstitialDidOpen':
      eventHandlers[type].set(handler, DeviceEventEmitter.addListener(type, handler));
      break;
    case 'interstitialDidClose':
      eventHandlers[type].set(handler, DeviceEventEmitter.addListener(type, handler));
      break;
    case 'interstitialWillLeaveApplication':
      eventHandlers[type].set(handler, DeviceEventEmitter.addListener(type, handler));
      break;
    case 'interstitialWillLeaveApplication2':
      eventHandlers[type].set(handler, DeviceEventEmitter.addListener(type, handler));
      break;
    default:
      console.log(`Event with type ${type} does not exist.`);
  }
}

const removeEventListener = (type, handler) => {
  if (!eventHandlers[type].has(handler)) {
    return;
  }
  eventHandlers[type].get(handler).remove();
  eventHandlers[type].delete(handler);
}

const removeAllListeners = () => {
  DeviceEventEmitter.removeAllListeners('interstitialDidLoad');
  DeviceEventEmitter.removeAllListeners('interstitialDidFailToLoad');
  DeviceEventEmitter.removeAllListeners('interstitialDidOpen');
  DeviceEventEmitter.removeAllListeners('interstitialDidClose');
  DeviceEventEmitter.removeAllListeners('interstitialWIllLeaveApplication');
  DeviceEventEmitter.removeAllListeners('interstitialWIllLeaveApplication2');
};

// replaces deprecated API
const tryShowNewInterstitial = (testID) => {
  console.warn(`tryShowNewInterstitial method is deprecated and will be removed in the next major release, please use requestAd() and showAd() directly.\n\nExample: AdMobInterstitial.requestAd(AdMobInterstitial.showAd)`);
  if (testID) {
    RNAdMobInterstitial.setTestDeviceID(testID);
  }

  RNAdMobInterstitial.isReady((isReady) => {
    if (isReady) {
      RNAdMobInterstitial.showAd(() => {});
    } else {
      RNAdMobInterstitial.requestAd(() => RNAdMobInterstitial.showAd(() => {}));
    }
  });
};

module.exports = {
  ...RNAdMobInterstitial,
  requestAd: (cb = () => {}) => RNAdMobInterstitial.requestAd(cb), // requestAd callback is optional
  showAd: (cb = () => {}) => RNAdMobInterstitial.showAd(cb),       // showAd callback is optional
  tryShowNewInterstitial,
  addEventListener,
  removeEventListener,
  removeAllListeners,
  setAdUnitId: (id) => {
    RNAdMobInterstitial.setAdUnitID(id);
    console.warn(`setAdUnitId will be deprecated soon. Please use setAdUnitID instead.`);
  },
  requestAd2: (cb = () => {}) => RNAdMobInterstitial.requestAd2(cb),
  showAd2: (cb = () => {}) => RNAdMobInterstitial.showAd2(cb),
  setAdUnitId2: (id) => {
    RNAdMobInterstitial.setAdUnitID2(id);
  },
  getAdUnitId: (cb = () => {}) => RNAdMobInterstitial.getAdUnitId(cb),
  getAdUnitId2: (cb = () => {}) => RNAdMobInterstitial.getAdUnitId2(cb),
  setAdUnitID3: (id) => {
    RNAdMobInterstitial.setAdUnitID3(id);
  },
  requestAd3: (cb = () => {}) => RNAdMobInterstitial.requestAd3(cb),
  showAd3: (cb = () => {}) => RNAdMobInterstitial.showAd3(cb),
  getAdUnitId3: (cb = () => {}) => RNAdMobInterstitial.getAdUnitId3(cb)
};
