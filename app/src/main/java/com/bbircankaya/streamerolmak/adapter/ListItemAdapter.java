package com.bbircankaya.streamerolmak.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

import com.bbircankaya.streamerolmak.R;
import com.bbircankaya.streamerolmak.module.Item;

public class ListItemAdapter extends BaseAdapter{
	private final Context mContext;
    private final List<Item> mItemList;


    public ListItemAdapter(Context mContext, List<Item> mItemList) {
        this.mContext = mContext;
        this.mItemList = mItemList;

    }

    @Override
    public int getCount() {
        return mItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return mItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mItemList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View v  = convertView;

	if(v==null){
		LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		v = inflater.inflate(R.layout.item_view, parent, false);
	}
        //View v = View.inflate(mContext, R.layout.item_view, null);
        
    // find the item to work with
	Item currentItem = mItemList.get(position);
	// fill the view
	ImageView img = (ImageView) v.findViewById(R.id.imageView1);
	String uri = "@drawable/"+mItemList.get(position).getIcon();  
	int imageResource = mContext.getResources().getIdentifier(uri, null, mContext.getPackageName());
	//Drawable res = mContext.getResources().getDrawable(imageResource);
	Drawable image = mContext.getResources().getDrawable(R.drawable.twstart);
	img.setImageDrawable(image);
	// fill  title

        TextView txv = (TextView) v.findViewById(R.id.textView1) ;
        //txv.setTypeface(type2);
        txv.setText(mItemList.get(position).getTitle().trim());
	v.setPadding(0, 5, 0, 5);

	    //Typeface type1 ,type2 ;

        //type1 = Typeface.createFromAsset(mContext.getAssets(),"fonts/Roboto-Bold.ttf");
        //type2 = Typeface.createFromAsset(mContext.getAssets(),"fonts/Roboto-Bold.ttf");

        // fill distance


       // TextView txv2 = (TextView) v.findViewById(R.id.textView2);
       // txv2.setTypeface(type1);



	return v;
    }
    
}
