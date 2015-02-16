package br.jabarasca.beerapp;

import android.os.Bundle;
import android.app.Activity;
//import android.view.Menu;
import android.widget.LinearLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity {

	LayoutInflater inflater;
	OnClickListener action = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Toast.makeText(getApplicationContext(), "Opção não implementada!", Toast.LENGTH_SHORT).show();
		}
	};//End OnClickListener.
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
		
		this.setTopBarLayout((LinearLayout)this.findViewById(R.id.mainLinearLayout));
		
		this.setScrollViewItem((LinearLayout)this.findViewById(R.id.mainScrollViewLinearLayout), R.id.scrollViewItemRelativeLayout1, R.drawable.item_list, R.string.txtViewItem1);
		this.setScrollViewItem((LinearLayout)this.findViewById(R.id.mainScrollViewLinearLayout), R.id.scrollViewItemRelativeLayout2, R.drawable.item_list, R.string.txtViewItem2);
		this.setScrollViewItem((LinearLayout)this.findViewById(R.id.mainScrollViewLinearLayout), R.id.scrollViewItemRelativeLayout3, R.drawable.item_list, R.string.txtViewItem3);
		this.setScrollViewItem((LinearLayout)this.findViewById(R.id.mainScrollViewLinearLayout), R.id.scrollViewItemRelativeLayout4, R.drawable.item_list, R.string.txtViewItem4);
		this.setScrollViewItem((LinearLayout)this.findViewById(R.id.mainScrollViewLinearLayout), R.id.scrollViewItemRelativeLayout5, R.drawable.item_list, R.string.txtViewItem5);
		this.setScrollViewItem((LinearLayout)this.findViewById(R.id.mainScrollViewLinearLayout), R.id.scrollViewItemRelativeLayout6, R.drawable.item_list, R.string.txtViewItem6);
		this.setScrollViewItem((LinearLayout)this.findViewById(R.id.mainScrollViewLinearLayout), R.id.scrollViewItemRelativeLayout7, R.drawable.item_list, R.string.txtViewItem7);
		this.setScrollViewItem((LinearLayout)this.findViewById(R.id.mainScrollViewLinearLayout), R.id.scrollViewItemRelativeLayout8, R.drawable.item_list, R.string.txtViewItem8);
		this.setScrollViewItem((LinearLayout)this.findViewById(R.id.mainScrollViewLinearLayout), R.id.scrollViewItemRelativeLayout9, R.drawable.item_list, R.string.txtViewItem9);
		
		
		this.addListenerOnScrollViewItem((RelativeLayout)this.findViewById(R.id.scrollViewItemRelativeLayout1), action);
		this.addListenerOnScrollViewItem((RelativeLayout)this.findViewById(R.id.scrollViewItemRelativeLayout2), action);
		this.addListenerOnScrollViewItem((RelativeLayout)this.findViewById(R.id.scrollViewItemRelativeLayout3), action);
		this.addListenerOnScrollViewItem((RelativeLayout)this.findViewById(R.id.scrollViewItemRelativeLayout4), action);
		this.addListenerOnScrollViewItem((RelativeLayout)this.findViewById(R.id.scrollViewItemRelativeLayout5), action);
		this.addListenerOnScrollViewItem((RelativeLayout)this.findViewById(R.id.scrollViewItemRelativeLayout6), action);
		this.addListenerOnScrollViewItem((RelativeLayout)this.findViewById(R.id.scrollViewItemRelativeLayout7), action);
		this.addListenerOnScrollViewItem((RelativeLayout)this.findViewById(R.id.scrollViewItemRelativeLayout8), action);
		this.addListenerOnScrollViewItem((RelativeLayout)this.findViewById(R.id.scrollViewItemRelativeLayout9), action);
	
		this.addListenerOnTopBar((RelativeLayout)this.findViewById(R.id.topBarRelativeLayout), action);
	}

	private void setTopBarLayout(LinearLayout linearLayout) {
		RelativeLayout topBarLayout = (RelativeLayout)inflater.inflate(R.layout.top_bar_layout, null);
		linearLayout.addView(topBarLayout, 0);
	}
	
	//Rever esse método.
	private void addListenerOnTopBar(RelativeLayout topBarRelativeLayout, OnClickListener action) {
		ImageView imgViewMoreOptsIcon = (ImageView)topBarRelativeLayout.getChildAt(1);
		ImageView imgViewFavListIcon = (ImageView)topBarRelativeLayout.getChildAt(2);
		ImageView imgViewSetFavIcon = (ImageView)topBarRelativeLayout.getChildAt(3);
		
		imgViewMoreOptsIcon.setOnClickListener(action);
		imgViewFavListIcon.setOnClickListener(action);
		imgViewSetFavIcon.setOnClickListener(action);
	}
	
	private void addListenerOnScrollViewItem(RelativeLayout scrollViewItemRelativeLayout, OnClickListener action) {
		ImageView imgViewItem = (ImageView)scrollViewItemRelativeLayout.getChildAt(0);
		imgViewItem.setOnClickListener(action);
		TextView txtViewItem = (TextView)scrollViewItemRelativeLayout.getChildAt(1);
		txtViewItem.setOnClickListener(action);
	}
	
	private void setScrollViewItem(LinearLayout scrollViewLinearLayout, int idScrollViewItemRelativeLayout, int idImgViewItemBackground,  int idTxtViewItemString) {
		RelativeLayout itemRelativeLayout = (RelativeLayout)inflater.inflate(R.layout.scrow_view_item, null);
		itemRelativeLayout.setId(idScrollViewItemRelativeLayout);
		ImageView imgViewItemBackground = (ImageView)itemRelativeLayout.getChildAt(0);
		imgViewItemBackground.setImageResource(idImgViewItemBackground);
		TextView itemTxtView = (TextView)itemRelativeLayout.getChildAt(1);
		itemTxtView.setText(idTxtViewItemString);
		scrollViewLinearLayout.addView(itemRelativeLayout);
	}
		
	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/

}
