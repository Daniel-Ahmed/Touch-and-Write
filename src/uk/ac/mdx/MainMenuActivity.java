 package uk.ac.mdx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainMenuActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		
		// Start Lower Case Letters Activity
		ImageButton lowerCaseButton = (ImageButton)findViewById(R.id.LowerCaseLettersButton);
		lowerCaseButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainMenuActivity.this, LowerLetterSelectionActivity.class);
				startActivity(intent);
				
			}
		});
		
		// Start Upper Case Letters Activity
		ImageButton upperCaseButton = (ImageButton)findViewById(R.id.UpperCaseLettersButton);
		upperCaseButton.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainMenuActivity.this, CapitalLetterSelection.class);
				startActivity(intent);
				
			}
		});
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
