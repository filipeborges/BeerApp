package br.jabarasca.beerapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import br.jabarasca.beerapp.utils.HtmlDownloaderTask;
import br.jabarasca.beerapp.utils.DownloaderPostAction;

public class FindBeerActivity extends ActionBarActivity implements DownloaderPostAction {

	final String NAV_LIST_VIEW_OPTIONS_1 = "Minhas Cervejas";
	final String NETWORK_CONNECTION_ERROR = "Conexão à internet não disponível";
	final String WEB_URL = "http://cervafinder.net16.net/select.php";
	
	private ActionBarDrawerToggle actionBarDrawerToggle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_beer);
		
		this.setNavigationDrawer((DrawerLayout)findViewById(R.id.findBeerDrawerLayout), R.drawable.beer_action_bar_icon,
				R.string.openDrawerContentDesc, R.string.closeDrawerContentDesc);
		
		ListView navListView = (ListView)findViewById(R.id.findBeerNavDrawerListView);
		String[] nav_list_items_opts = new String[]{NAV_LIST_VIEW_OPTIONS_1};
		this.setListViewArrayAdapter(navListView, R.layout.nav_list_view_item, R.id.navListViewItemTxtViewOpt, nav_list_items_opts);
		
		if(this.networkConnectionAvailable()) {
			this.networkConnectionTest();
		}
	}
	
	@Override
	public void htmlDownloaderPostAction(String htmlContent) {
		TextView txtView = (TextView)findViewById(R.id.htmlTextView);
		txtView.setText(htmlContent);
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

	private void networkConnectionTest() {
		HtmlDownloaderTask downloadTask = new HtmlDownloaderTask(this);
		downloadTask.execute(WEB_URL);
	}
	
	private boolean networkConnectionAvailable() {
		ConnectivityManager connManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = connManager.getActiveNetworkInfo();
		if(!(netInfo != null && netInfo.isConnected())) {
			Toast.makeText(getApplicationContext(), NETWORK_CONNECTION_ERROR, Toast.LENGTH_SHORT).show();
			return false;
		}
		else {
			return true;
		}
	}
	
	private void setNavigationDrawer(DrawerLayout drawer, int iconRes, int openDrawerStringRes, int closeDrawerStringRes) {		
		actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, openDrawerStringRes, openDrawerStringRes);
		drawer.setDrawerListener(actionBarDrawerToggle);
		getSupportActionBar().setIcon(iconRes);
		getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_USE_LOGO|ActionBar.DISPLAY_HOME_AS_UP|ActionBar.DISPLAY_SHOW_HOME|ActionBar.DISPLAY_SHOW_TITLE);
	}
	
	private void setListViewArrayAdapter(ListView listView, int listItemLayoutRes, int txtViewLayoutChildRes, String[] list_items_opts) {
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, listItemLayoutRes, txtViewLayoutChildRes);
		for(int i = 0; i < list_items_opts.length; i++) {
			arrayAdapter.add(list_items_opts[i]);
		}
		listView.setAdapter(arrayAdapter);
	}
	
	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.find_beer, menu);
		return true;
	}*/

}
