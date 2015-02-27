package br.jabarasca.beerapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
//import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this.setAnimation((ImageView)this.findViewById(R.id.mainImgViewBgd), R.anim.animation_main_activity);
	}
	
	//Set animation on the specified ImageView.
	private void setAnimation(ImageView imgView, int animationRes) {
		final Context appContext = this.getApplicationContext();
		final Activity activity = this;
		Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), animationRes);
		Animation.AnimationListener animListener = new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {}
			
			@Override
			public void onAnimationRepeat(Animation animation) {}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				Intent intent = new Intent(appContext, MainMenuActivity.class); //HardCoded Activity.
				activity.startActivity(intent);
				finish();
			}
		};
		anim.setAnimationListener(animListener);
		imgView.startAnimation(anim);
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.initial, menu);
		return true;
	}*/

}
