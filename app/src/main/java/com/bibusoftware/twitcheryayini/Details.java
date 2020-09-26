package com.bibusoftware.twitcheryayini;


import com.bibusoftware.twitcheryayini.module.ourWebView;
import com.bibusoftware.twitcheryayini.R;
import com.bibusoftware.twitcheryayini.config.admob;

import android.support.v7.app.AppCompatActivity;
import android.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Details extends AppCompatActivity {

	WebView browser;
	ActionBar actionBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		
		LinearLayout linearlayout = (LinearLayout)findViewById(R.id.Unit_Ads2);
        admob.admobBannerCall(this, linearlayout);
        
		//this.setTitleColor(getResources().getColor(R.color.menuTextcolor));
		actionBar= this.getActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.menuTextcolor), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        this.setTitle(getIntent().getStringExtra("title"));
        
		browser = (WebView) findViewById(R.id.webView1);
		
		//Toast.makeText(Details.this, "id:"+getIntent().getStringExtra("id"), Toast.LENGTH_SHORT).show();
		
		browser.getSettings().setJavaScriptEnabled(true);
		browser.getSettings().setLoadWithOverviewMode(true);
		browser.getSettings().setUseWideViewPort(true);
		browser.setWebViewClient(new ourWebView());
		
		final StringBuilder s = new StringBuilder();    

		s.append("<html>");  
		s.append("<head><meta name='viewport' content='width=device-width, user-scalable=no' >" +
				"<style>body {line-height: 170%;}</style>" +
				"</head>");
		s.append("<body style='padding-bottom:60px'>");
		s.append(getIntent().getStringExtra("detail"));
		s.append("</body>");                            
		s.append("</html>");
		browser.loadDataWithBaseURL(null, s.toString(), "text/html", "UTF-8", null);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.details, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		/*int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);*/
		switch (item.getItemId()) {
        case android.R.id.home:
            //Toast.makeText(this, "This is my Toast message!",
                    //Toast.LENGTH_LONG).show();
            /*Intent intent = new Intent(Details.this, ListViews.class);
            startActivity(intent);*/
            this.finish();
        	//finishActivity(0);
            return true;
        default:
            Toast.makeText(this, "Ayarlar geliştirme aşamasında...",
                    Toast.LENGTH_LONG).show();
            return super.onOptionsItemSelected(item);
    }
	}
}
