package br.jabarasca.beerapp;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import br.jabarasca.beerapp.utils.GuiUtils;
import br.jabarasca.beerapp.utils.HtmlDownloaderTask;
import br.jabarasca.beerapp.utils.DownloaderPostAction;

public class FindBeerActivity extends ActionBarActivity implements DownloaderPostAction {
	private final String BEER_NAME_HTML_TAG = "Name:", HTML_ATTR_DELIMITER = "|";
	private final String WEB_URL = "http://cervafinder.net16.net/select.php";
	
	private ActionBarDrawerToggle actionBarDrawerToggle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_beer);
		
		actionBarDrawerToggle = GuiUtils.setNavigationDrawer(this, (DrawerLayout)findViewById(R.id.findBeerDrawerLayout), 
				R.drawable.beer_action_bar_icon, R.string.openDrawerContentDesc, R.string.closeDrawerContentDesc);
		getSupportActionBar().setTitle(getResources().getString(R.string.mainMenuListViewOpt1));
		
		ListView navListView = (ListView)findViewById(R.id.findBeerNavDrawerListView);
		String[] nav_list_items_opts = new String[]{getResources().getString(R.string.navListViewOpt1)};
		GuiUtils.setListViewArrayAdapter(this, navListView, R.layout.nav_list_view_item, R.id.navListViewItemTxtViewOpt, nav_list_items_opts);
		
		getListOfBeersFromWeb(WEB_URL);
	}
	
	@Override
	public void imageDownloaderPostAction(Bitmap bitmap) {}

	/*This implementation sets the Beers getted from a Web on a ListView.*/
	@Override
	public void htmlDownloaderPostAction(String htmlContent) {
		if(htmlContent != null) {
			List<String> beerNames = new ArrayList<String>();
			
			for(int i = 0; i < htmlContent.length(); i++) {
				i = htmlContent.indexOf(BEER_NAME_HTML_TAG, i);
				if(i == -1) {
					break;
				}
				else {
					i += BEER_NAME_HTML_TAG.length();
					int a = htmlContent.indexOf(HTML_ATTR_DELIMITER, i);
					beerNames.add(htmlContent.substring(i, a));
				}
			}
			
			Collections.sort(beerNames);
			ListView listView = (ListView)findViewById(R.id.findBeerSearchListView);
			GuiUtils.setListViewArrayAdapter(this, listView, R.layout.list_view_item, R.id.listItemTxtViewOpt, 
					beerNames);
			
			setFindBeerListViewItemsListener(listView);
		}
		else {
			Toast.makeText(this, getResources().getString(R.string.networkConnectionError), Toast.LENGTH_SHORT).show();
		}
		removeProgressAnimation(R.id.progressBarRelLay, R.id.findBeerRelLayContent);
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

	private void getListOfBeersFromWeb(String url) {
		HtmlDownloaderTask downloadTask = new HtmlDownloaderTask(this);
		if(downloadTask.networkIsAvailable) {
			loadProgressAnimation(R.layout.progress_bar, R.id.findBeerRelLayContent, this);
			downloadTask.execute(url);
		}
		else {
			Toast.makeText(getApplicationContext(), getResources().getString(R.string.noNetworkConnection), Toast.LENGTH_SHORT).show();
		}
	}
	
	private void loadProgressAnimation(int progressBarLayout, int parentLayoutId, Context context) {
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		ViewGroup parentLayout = (ViewGroup)findViewById(parentLayoutId);
		inflater.inflate(progressBarLayout, parentLayout);
	}
	
	private void removeProgressAnimation(int progressBarRelLayId, int parentLayoutId) {
		ViewGroup progressBarRelLay = (ViewGroup)findViewById(progressBarRelLayId);
		ViewGroup parentLayout = (ViewGroup)findViewById(parentLayoutId);
		parentLayout.removeView(progressBarRelLay);
	}
	
	private void setFindBeerListViewItemsListener(ListView listView) {
		final ActionBarActivity actionBarActivity = this;
		AdapterView.OnItemClickListener listViewItemAction = new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> listView, View listItemViewGroupLayout, int itemPosition, long itemRowId) {
				Intent intent = new Intent(actionBarActivity.getApplicationContext(), BeerDetailActivity.class); //HardCoded Activity.
				TextView beerName = (TextView)((ViewGroup)listItemViewGroupLayout).getChildAt(1);
				intent.putExtra("BeerName", beerName.getText());
				actionBarActivity.startActivity(intent);
			}
		};
		listView.setOnItemClickListener(listViewItemAction);
	}//End setListViewItemsListener.
	
	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.find_beer, menu);
		return true;
	}*/

}
