package uk.ac.mdx;

import uk.ac.mdx.capital.letters.CapitalA;
import uk.ac.mdx.capital.letters.CapitalB;
import uk.ac.mdx.capital.letters.CapitalC;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class CapitalLetterSelection extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_capital_letter_selection);
		
		// A Button
		ImageButton imgBtnA = (ImageButton)findViewById(R.id.button_A);
		imgBtnA.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(CapitalLetterSelection.this, CapitalA.class);
				startActivity(intent);
				
			}
		});
		
		// B Button
		ImageButton imgBtnB = (ImageButton)findViewById(R.id.button_B);
		imgBtnB.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View v) {
						
				Intent intent = new Intent(CapitalLetterSelection.this, CapitalB.class);
				startActivity(intent);
						
			}
		});
		
		// B Button
		ImageButton imgBtnC = (ImageButton)findViewById(R.id.button_C);
		imgBtnC.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View v) {
						
				Intent intent = new Intent(CapitalLetterSelection.this, CapitalC.class);
				startActivity(intent);
						
			}
		});
	}

}
