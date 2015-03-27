package br.jabarasca.beerapp.utils;

import java.util.Iterator;
import java.util.List;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class GuiUtils {
	
	public static ActionBarDrawerToggle setNavigationDrawer(ActionBarActivity activity, DrawerLayout drawer, int actionBarIconRes, 
									 	   int openDrawerStringRes, int closeDrawerStringRes) {		
		final int ACTION_BAR_DISPLAY_OPTS = ActionBar.DISPLAY_USE_LOGO|ActionBar.DISPLAY_HOME_AS_UP|
											ActionBar.DISPLAY_SHOW_HOME|ActionBar.DISPLAY_SHOW_TITLE;
		
		ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(activity, drawer, openDrawerStringRes, openDrawerStringRes);
		drawer.setDrawerListener(actionBarDrawerToggle);
		activity.getSupportActionBar().setIcon(actionBarIconRes);
		activity.getSupportActionBar().setDisplayOptions(ACTION_BAR_DISPLAY_OPTS);
		
		return actionBarDrawerToggle;
	}
	
	public static void setListViewArrayAdapter(ActionBarActivity activity, ListView listView, int listItemLayoutRes, 
											int txtViewLayoutChildRes, String[] list_items_opts) {
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(activity, listItemLayoutRes, txtViewLayoutChildRes);
		for(int i = 0; i < list_items_opts.length; i++) {
			arrayAdapter.add(list_items_opts[i]);
		}
		listView.setAdapter(arrayAdapter);
	}
	
	public static void setListViewArrayAdapter(ActionBarActivity activity, ListView listView, int listItemLayoutRes, int txtViewLayoutChildRes, 
											List<String> list_items_opts) {
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(activity, listItemLayoutRes, txtViewLayoutChildRes);
		Iterator<String> list_ite = list_items_opts.iterator();
		while(list_ite.hasNext()) {
			arrayAdapter.add(list_ite.next());
		}
		listView.setAdapter(arrayAdapter);
	}
}
