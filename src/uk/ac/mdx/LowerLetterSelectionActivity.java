package uk.ac.mdx;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class LowerLetterSelectionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lower_letter_selection);
		
		// Letter A
		ImageButton LetterAButton = (ImageButton)findViewById(R.id.button_capital_a);
		LetterAButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// Create Letter A Activity
				
			}
		});
	}

}
