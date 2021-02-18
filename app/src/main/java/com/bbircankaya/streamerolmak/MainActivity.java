package com.bbircankaya.streamerolmak;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

import com.bbircankaya.streamerolmak.database.DataBaseHelper;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends Activity {

	InterstitialAd  mInterstitialAd;
	Button start;
	ProgressBar progressBar ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		
		setContentView(R.layout.main_activity);
		MobileAds.initialize(this, new OnInitializationCompleteListener() {
					@Override
					public void onInitializationComplete(InitializationStatus initializationStatus) {

					}
				});

				start = (Button) findViewById(R.id.start);
		DataBaseHelper.setmDatabase(this);
        AppRater.setAPP_PNAME(this);



        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(SettingsApp.Interstitial);
        progressBar=(ProgressBar) findViewById(R.id.progressBar1);
        mInterstitialAd.setAdListener(new AdListener() {
        	@Override
        	public void onAdClosed() {
        		requestNewInterstitial();
        	}
		});

        requestNewInterstitial();

		int SPLASH_DISPLAY_LENGTH = 7000;
		new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
               progressBar.setVisibility(View.GONE);
               start.setVisibility(View.VISIBLE);
            }
        }, SPLASH_DISPLAY_LENGTH);
        
		start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity.this, ListViewsItems.class);
				//intent.putExtra("id",""+itemClicked.getDirection());
				//startActivityForResult(intent, 1);
				startActivity(intent);
				if (mInterstitialAd.isLoaded())  mInterstitialAd.show();
				finish();
				//else Toast.makeText(Start.this, "nonLoaded", Toast.LENGTH_SHORT);
			}
		});
	}
	
	private void requestNewInterstitial() {
		 AdRequest adRequest = new AdRequest.Builder().build();
		 mInterstitialAd.loadAd(adRequest);
	}

	
}
