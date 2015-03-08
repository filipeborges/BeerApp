package br.jabarasca.beerapp;

import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class MainMenuActivity extends ActionBarActivity {
	private final String LIST_VIEW_OPTIONS_1 = "Encontrar Cerveja";
	private final int LIST_VIEW_OPTIONS_1_POSITION = 0;
	
	private final String NAV_LIST_VIEW_OPTIONS_1 = "Minhas Cervejas";
	
	private ActionBarDrawerToggle actionBarDrawerToggle;
	private boolean quitApp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);

		ListView mainMenuListView = (ListView)findViewById(R.id.mainMenuListView);
		String[] mainMenuListViewOpts = new String[]{LIST_VIEW_OPTIONS_1};
		this.setListViewArrayAdapter(mainMenuListView, R.layout.list_view_item, R.id.listItemTxtViewOpt, mainMenuListViewOpts);
		this.setMainMenuListViewItemsListener(mainMenuListView);
		
		ListView navListView = (ListView)findViewById(R.id.mainMenuNavDrawerListView);
		String[] nav_list_items_opts = new String[]{NAV_LIST_VIEW_OPTIONS_1};
		this.setListViewArrayAdapter(navListView, R.layout.nav_list_view_item, R.id.navListViewItemTxtViewOpt, nav_list_items_opts);
		
		this.setNavigationDrawer((DrawerLayout)findViewById(R.id.mainMenuDrawerLayout), R.drawable.beer_action_bar_icon, 
				R.string.openDrawerContentDesc, R.string.closeDrawerContentDesc);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		quitApp = false;
	}
	
	@Override
	public void onBackPressed() {
		if(quitApp) {
			finish();
		}
		else {
			Toast.makeText(this, R.string.toastBackToFinishApp, Toast.LENGTH_SHORT).show();
			quitApp = true;
		}
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
          return true;
        }
        return super.onOptionsItemSelected(item);
    }
	
	@Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        actionBarDrawerToggle.syncState();
    }

	private void setNavigationDrawer(DrawerLayout drawer, int actionBarIconRes, int openDrawerStringRes, int closeDrawerStringRes) {		
		actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, openDrawerStringRes, openDrawerStringRes);
		drawer.setDrawerListener(actionBarDrawerToggle);
		getSupportActionBar().setIcon(actionBarIconRes);
		getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_USE_LOGO|ActionBar.DISPLAY_HOME_AS_UP|ActionBar.DISPLAY_SHOW_HOME|ActionBar.DISPLAY_SHOW_TITLE);
	}
	
	private void setListViewArrayAdapter(ListView listView, int listItemLayoutRes, int txtViewLayoutChildRes, String[] list_items_opts) {
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, listItemLayoutRes, txtViewLayoutChildRes);
		for(int i = 0; i < list_items_opts.length; i++) {
			arrayAdapter.add(list_items_opts[i]);
		}
		listView.setAdapter(arrayAdapter);
	}
	
	private void setMainMenuListViewItemsListener(ListView listView) {
		final ActionBarActivity actionBarActivity = this;
		final Context appContext = this.getApplicationContext();
		AdapterView.OnItemClickListener listViewItemAction = new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> listView, View listItemViewGroupLayout, int itemPosition, long itemRowId) {
				switch(itemPosition) {
					case LIST_VIEW_OPTIONS_1_POSITION:
						Intent intent = new Intent(appContext, FindBeerActivity.class); //HardCoded Activity.
						actionBarActivity.startActivity(intent);
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
