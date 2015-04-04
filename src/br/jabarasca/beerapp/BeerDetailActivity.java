package br.jabarasca.beerapp;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.os.Bundle;
import br.jabarasca.beerapp.utils.GuiUtils;

public class BeerDetailActivity extends ActionBarActivity {

	private ActionBarDrawerToggle actionBarDrawerToggle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beer_detail);
		
		actionBarDrawerToggle = GuiUtils.setNavigationDrawer(this, (DrawerLayout)findViewById(R.id.beerDetailDrawerLayout), 
				R.drawable.beer_action_bar_icon, R.string.openDrawerContentDesc, R.string.closeDrawerContentDesc);
		
		getSupportActionBar().setTitle(getIntent().getCharSequenceExtra("BeerName"));
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
	
	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.beer_detail, menu);
		return true;
	}*/
}
