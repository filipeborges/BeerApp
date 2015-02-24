package br.jabarasca.beerapp;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FindBeerActivity extends ActionBarActivity {

	private ActionBarDrawerToggle actionBarDrawerToggle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_beer);
		
		this.setNavigationDrawer((DrawerLayout)findViewById(R.id.findBeerDrawerLayout), R.drawable.beer_action_bar_icon,
				R.string.openDrawerContentDesc, R.string.closeDrawerContentDesc);
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

	private void setNavigationDrawer(DrawerLayout drawer, int iconRes, int openDrawerStringRes, int closeDrawerStringRes) {		
		actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, openDrawerStringRes, openDrawerStringRes);
		drawer.setDrawerListener(actionBarDrawerToggle);
		getSupportActionBar().setIcon(iconRes);
		getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_USE_LOGO|ActionBar.DISPLAY_HOME_AS_UP|ActionBar.DISPLAY_SHOW_HOME|ActionBar.DISPLAY_SHOW_TITLE);
	}
	
	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.find_beer, menu);
		return true;
	}*/

}
