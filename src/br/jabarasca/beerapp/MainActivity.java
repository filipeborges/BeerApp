package br.jabarasca.beerapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.LinearLayout;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.ImageView;

public class MainActivity extends Activity {

	LayoutInflater inflater;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
		
		this.setTopBarLayout((LinearLayout)this.findViewById(R.id.mainLinearLayout));
		
		//For para fins de teste. Deletar depois.
		for(int i = 0; i < 15; i++) {
			this.setScrollViewItem((LinearLayout)this.findViewById(R.id.mainScrollViewLinearLayout));
		}
	}

	private void setTopBarLayout(LinearLayout linearLayout) {
		RelativeLayout topBarLayout = (RelativeLayout)inflater.inflate(R.layout.top_bar_layout, null);
		linearLayout.addView(topBarLayout, 0);
	}
	
	private void setScrollViewItem(LinearLayout scrollViewLinearLayout) {
		RelativeLayout itemRelativeLayout = (RelativeLayout)inflater.inflate(R.layout.scrow_view_item, null);
		ImageView imgViewItemBackground = (ImageView)itemRelativeLayout.getChildAt(0);
		imgViewItemBackground.setImageResource(R.drawable.item_list);
		scrollViewLinearLayout.addView(itemRelativeLayout);
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
