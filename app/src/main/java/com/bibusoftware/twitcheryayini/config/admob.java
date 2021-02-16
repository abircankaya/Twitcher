package com.bibusoftware.twitcheryayini.config;

import android.app.Activity;
import android.widget.LinearLayout;

import com.bibusoftware.twitcheryayini.SettingsApp;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class admob {
	

	public static int nbShowInterstitial = 1;
	public static int mCount = 1;
	
	public static void admobBannerCall(Activity acitivty , LinearLayout linerlayout){
		
        AdView adView = new AdView(acitivty);
        adView.setAdUnitId(SettingsApp.admBanner);
        adView.setAdSize(AdSize.BANNER);
        AdRequest.Builder builder = new AdRequest.Builder();
        adView.loadAd(builder.build());
        linerlayout.addView(adView);

	}

    public static String getBannerId(){
		return SettingsApp.admBanner;
	}
	
}