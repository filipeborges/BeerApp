package br.jabarasca.beerapp;

import android.os.Bundle;
import android.app.Activity;
//import android.view.Menu;
import android.widget.LinearLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ImageView;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {

	final String OPTIONS_1 = "Buscar Cerveja";
	final int OPTIONS_1_POSITION = 0;
	
	LayoutInflater inflater;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
		
		ListView listView = (ListView)findViewById(R.id.mainListView);

		this.setTopBarLayout((LinearLayout)this.findViewById(R.id.mainLinearLayout));
		this.setTopBarMoreOptsIconListener((ImageView)this.findViewById(R.id.topBarImgViewMoreOpts));
		this.setTopBarFavListIconListener((ImageView)this.findViewById(R.id.topBarImgViewFavList));
		this.setTopBarSetFavIconListener((ImageView)this.findViewById(R.id.topBarImgViewSetFav));
		this.setListViewAdapter(listView);
		this.setListViewItemsListener((ListView)this.findViewById(R.id.mainListView));
	}

	private void setTopBarLayout(LinearLayout linearLayout) {
		RelativeLayout topBarLayout = (RelativeLayout)inflater.inflate(R.layout.top_bar_layout, null);
		linearLayout.addView(topBarLayout, 0);
	}
	
	private void setTopBarSetFavIconListener(ImageView topBarSetFavImgView) {
		OnClickListener action = new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "Favoritar não implementado!", Toast.LENGTH_SHORT).show();
			}
		};
		
		topBarSetFavImgView.setOnClickListener(action);
	}
	
	private void setTopBarFavListIconListener(ImageView topBarFavListImgView) {
		OnClickListener action = new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "Lista de Fav. não implementado!", Toast.LENGTH_SHORT).show();
			}
		};
		
		topBarFavListImgView.setOnClickListener(action);
	}
	
	private void setTopBarMoreOptsIconListener(ImageView topBarMoreOptsImgView) {
		OnClickListener action = new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "Mais Opções não implementado!", Toast.LENGTH_SHORT).show();
			}
		};
		
		topBarMoreOptsImgView.setOnClickListener(action);
	}
	
	private void setListViewAdapter(ListView listView) {
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_view_item, R.id.textViewListItem);
		arrayAdapter.add(OPTIONS_1);
		listView.setAdapter(arrayAdapter);
	}
	
	private void setListViewItemsListener(ListView listView) {
		OnItemClickListener listViewItemAction = new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> listView, View listItem, int itemPosition,
					long itemRowId) {
				switch(itemPosition) {
					case OPTIONS_1_POSITION:
						Toast.makeText(getApplicationContext(), OPTIONS_1+" não implementada!", Toast.LENGTH_SHORT).show();
						break;
				}
			}
		};
		
		listView.setOnItemClickListener(listViewItemAction);
	}//End setListViewItemsListener.
		
	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/

}
