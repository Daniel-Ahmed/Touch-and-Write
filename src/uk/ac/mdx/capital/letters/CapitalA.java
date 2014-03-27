package uk.ac.mdx.capital.letters;

import uk.ac.mdx.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CapitalA extends Activity {

	
	ImageView circle;
	TextView coordinates;
	
	private final int yCoOrdinate[] = {310, 360, 410, 460, 510, 560, 610, 660, 710, 780};
	private final int xCoOrdinateFar[] = {905, 890, 875, 860, 845, 830, 815, 800, 785, 770};
	
	private final int yInsideTriangle[] = {425, 475, 525, 575};
	private final int xLeftInsideTriangle[] = {890, 880, 870, 860};
	
	private final int xCoOrdinateRight[] = {990, 1005, 1020, 1035, 1050, 1065, 1080, 1095, 1110, 1125};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_capital_a);
		
		coordinates = (TextView)findViewById(R.id.textView1);
		
		circle = (ImageView)findViewById(R.id.imageCircle);
		
		Log.d("Setting Image", "Image Set at position x/y");
		
		circle.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				int eventID = event.getAction();
				
				switch (eventID) {
				case MotionEvent.ACTION_MOVE:
					
					RelativeLayout.LayoutParams circleParams = (RelativeLayout.LayoutParams) circle.getLayoutParams();

					// Get finger position
					int x = (int)event.getRawX();
					int y = (int)event.getRawY();
					
					coordinates.setText("X = " + x + " - Y = " + y);
					
					
					if ( y >= 360 && x == 895) {
						x = 893;
					}
					
					// Left Side of A
					for(int i = 0; i < yCoOrdinate.length; i++) {
						
						// Top of A
						if(y <= yCoOrdinate[0]) {
							y = yCoOrdinate[0];
						}
						
						// Bottom of A
						if(y >= yCoOrdinate[9]) {
							y = yCoOrdinate[9];
						}
						
						// Left Boundaries
						if(y == yCoOrdinate[0] && x <= xCoOrdinateFar[0]) {
							x = xCoOrdinateFar[0];
						} else if (y <= yCoOrdinate[i] && x <= xCoOrdinateFar[i]) {
							y = yCoOrdinate[i];
							x = xCoOrdinateFar[i];
						}
						
						// Move to Right side
						if(x == xCoOrdinateFar[9] && y == yCoOrdinate[9]) {
							x = 985;
							y = yCoOrdinate[0];
						}
						
						// Right Boundaries
						if(y == yCoOrdinate[0] && x >= xCoOrdinateRight[0]) {
							x = xCoOrdinateRight[0];
						} else if (y <= yCoOrdinate[i] && x >= xCoOrdinateRight[i]) {
							y = yCoOrdinate[i];
							x = xCoOrdinateRight[i];
						}
						
					}
					
					// Set image to finger position - its starting position.
					circleParams.leftMargin = x - 730;
					circleParams.topMargin = y - 290;
                    
					circle.setLayoutParams(circleParams);
					
					break;
					
				default:
					break;
					
				}
				
				return true;
			}
		});
	}
}