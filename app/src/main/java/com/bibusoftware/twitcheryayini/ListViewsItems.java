package com.bibusoftware.twitcheryayini;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.bibusoftware.twitcheryayini.adapter.ListItemAdapter;
import com.bibusoftware.twitcheryayini.config.admob;
import com.bibusoftware.twitcheryayini.database.DataBaseHelper;
import com.bibusoftware.twitcheryayini.module.Item;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;


public class ListViewsItems extends Activity {
    
	InterstitialAd  mInterstitialAd;
	private ListView lvItem;
	private List<Item> mItemList;

	Button rateus, shareapp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);
		//View adContainer = findViewById(R.id.unitads);

		LinearLayout linearlayout = findViewById(R.id.unitads);
        admob.admobBannerCall(this, linearlayout);
        
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(SettingsApp.Interstitial);
        mInterstitialAd.setAdListener(new AdListener() {
        	@Override
        	public void onAdClosed() {
        		requestNewInterstitial();
        	}
		});

        requestNewInterstitial();
        
        rateus = findViewById(R.id.rateus2);
        shareapp= findViewById(R.id.play2);
        
        rateus.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AppRater.rateLink(ListViewsItems.this);
			     
			}
		});
        
        shareapp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ShareApp();
			}
		});

		DataBaseHelper mDBHelper = new DataBaseHelper(this);
        lvItem = findViewById(R.id.listViewtest);
        //Check exists database
        File database = getApplicationContext().getDatabasePath(DataBaseHelper.DBNAME);
        if(!database.exists()) {
            mDBHelper.getReadableDatabase();
            //Copy db
            if(copyDatabase(this)) {
                //Toast.makeText(this, "Copy database succes", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Copy data error"+DataBaseHelper.DBLOCATION, Toast.LENGTH_LONG).show();
                return;
            }
        }
        //Get product list in db when db exists
        mItemList = mDBHelper.getListItem();
        //Init adapter
		ListItemAdapter adapter = new ListItemAdapter(this, mItemList);
        //Set adapter for listview
        lvItem.setAdapter(adapter);
        itemSelected();
	}
	
	private void ShareApp(){
		Intent sharingIntent = new Intent(Intent.ACTION_SEND);
		sharingIntent.setType("text/plain");
		String shareBody = "Hey! bu uygulamayı denemeye ne dersin? :)\n https://play.google.com/store/apps/details?id="+ getPackageName() +" \n";
		sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
		sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
		startActivity(Intent.createChooser(sharingIntent, "Şununla Paylaş"));
	}
	
	private boolean copyDatabase(Context context) {
        try {

            InputStream inputStream = context.getAssets().open(DataBaseHelper.DBNAME);
            String outFileName = DataBaseHelper.DBLOCATION + DataBaseHelper.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[]buff = new byte[1024];
            int length;
			while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("MainActivity","DB copied");
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
	
	
	@Override
    public void onBackPressed() {
    	AppRater.app_launched(this);
    }
   
    private void requestNewInterstitial() {
		 AdRequest adRequest = new AdRequest.Builder().build();
		 mInterstitialAd.loadAd(adRequest);
	} 
  
    private void itemSelected() {
    	
    	lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    		 Item itemClicked = mItemList.get(position)	;
    		    
    			try {
    				//Class classe= Class.forName(getPackageName()+".Details");
    				Intent intent=new Intent(ListViewsItems.this, Details.class);
    				intent.putExtra("detail",""+itemClicked.getText());
    				intent.putExtra("title",""+itemClicked.getTitle());
    				startActivityForResult(intent, 1);
    				//startActivity(intent);
    				//finish();
    				if(admob.mCount == admob.nbShowInterstitial) {
    					if (mInterstitialAd.isLoaded()) 
    						mInterstitialAd.show();
    						admob.mCount=0;
    				}
    				++admob.mCount;
    			} catch (Exception e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		 
    		}
		});
    }
    
}
