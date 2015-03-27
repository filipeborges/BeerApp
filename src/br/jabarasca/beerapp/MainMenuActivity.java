package br.jabarasca.beerapp;

import br.jabarasca.beerapp.utils.GuiUtils;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainMenuActivity extends ActionBarActivity {
	private final int LIST_VIEW_OPTIONS_1_POSITION = 0;
	private final int TIME_LIMIT_TO_QUIT_MILLIS = 2000;
	
	private ActionBarDrawerToggle actionBarDrawerToggle;
	private long quitAppTime = 0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		
		ListView mainMenuListView = (ListView)findViewById(R.id.mainMenuListView);
		String[] mainMenuListViewOpts = new String[]{getResources().getString(R.string.mainMenuListViewOpt1)};
		GuiUtils.setListViewArrayAdapter(this, mainMenuListView, R.layout.list_view_item, R.id.listItemTxtViewOpt, mainMenuListViewOpts);
		
		setMainMenuListViewItemsListener(mainMenuListView);
		
		ListView navListView = (ListView)findViewById(R.id.mainMenuNavDrawerListView);
		String[] nav_list_items_opts = new String[]{getResources().getString(R.string.navListViewOpt1)};
		GuiUtils.setListViewArrayAdapter(this, navListView, R.layout.nav_list_view_item, R.id.navListViewItemTxtViewOpt, nav_list_items_opts);
		
		actionBarDrawerToggle = GuiUtils.setNavigationDrawer(this, (DrawerLayout)findViewById(R.id.mainMenuDrawerLayout), 
												R.drawable.beer_action_bar_icon, R.string.openDrawerContentDesc, R.string.closeDrawerContentDesc);
	}
	
	@Override
	public void onBackPressed() {
		if(System.currentTimeMillis() - quitAppTime <= TIME_LIMIT_TO_QUIT_MILLIS) {
			finish();
		}
		else {
			Toast.makeText(this, R.string.toastBackToFinishApp, Toast.LENGTH_SHORT).show();
			quitAppTime = System.currentTimeMillis();
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
