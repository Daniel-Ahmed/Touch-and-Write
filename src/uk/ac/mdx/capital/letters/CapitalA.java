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
	RelativeLayout.LayoutParams circleParams;
	
	private final int yCoordinates[] = {310, 360, 410, 460, 510, 560, 610, 660, 710, 775};
	private final int xCoordinateLeft[] = {902, 887, 872, 857, 841, 826, 811, 796, 781, 767};
	private final int xCoordinateRight[] = {925, 905, 895, 880, 865, 850, 840, 820, 800, 780};
	
	//private final int xCoOrdinateRight[] = {990, 1005, 1020, 1035, 1050, 1065, 1080, 1095, 1110, 1125};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_capital_a);
		
		coordinates = (TextView)findViewById(R.id.textView1);
		circle = (ImageView)findViewById(R.id.imageCircle);
		circleParams = (RelativeLayout.LayoutParams) circle.getLayoutParams();
		
		circle.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				int eventID = event.getAction();
				
				switch (eventID) {
				case MotionEvent.ACTION_MOVE:

					// Get finger position
					int x = (int)event.getRawX();
					int y = (int)event.getRawY();
					
					coordinates.setText("X = " + x + " - Y = " + y);
					
					
/*					if ( y >= 360 && x == 895) {
						x = 893;
					}
					
					// Left Side of A
					for(int i = 0; i < yCoordinates.length; i++) {
						
						// Top of A
						if(y <= yCoordinates[0]) {
							y = yCoordinates[0];
						}
						
						
						// Left Boundaries
						if(y == yCoordinates[0] && x <= xCoordinateLeft[0]) {
							x = xCoordinateLeft[0];
						} else if (y <= yCoordinates[i] && x <= xCoordinateLeft[i]) {
							y = yCoordinates[i];
							x = xCoordinateLeft[i];
						}
						
						
						
						// Right Boundaries
						if(y == yCoordinates[0] && x >= xCoOrdinateRight[0]) {
							x = xCoOrdinateRight[0];
						} else if (y <= yCoordinates[i] && x >= xCoOrdinateRight[i]) {
							y = yCoordinates[i];
							x = xCoOrdinateRight[i];
						}
						
					}*/
					
					moveImage(x, y);
					
					
					break;
					
				default:
					break;
					
				}
				
				return true;
			}
		});
	}
	
	private void moveImage(int x, int y) {
		
		for(int i = 0; i < yCoordinates.length; i++) {
			
			// Collide with top outline
			if(y <= yCoordinates[0]) {
				y = yCoordinates[0];
			}
			
			// Collide with left outline & Right outline
			if(y == yCoordinates[0] && x <= xCoordinateLeft[0]) {			// Top left
				x = xCoordinateLeft[0];
			} else if (y <= yCoordinates[i] && x <= xCoordinateLeft[i]) { // Left outline
				y = yCoordinates[i];
				x = xCoordinateLeft[i];
			} else if (y >= yCoordinates[i] && x >= xCoordinateRight[i]) {	// Right outline
				x = xCoordinateRight[i];
			}
			
			
			// Bottom of A
			if(y >= yCoordinates[9]) {
				y = yCoordinates[9];
			}
			
			// Collide with bottom and move To Right Section
			// and give new xCoordinates
			if(x == xCoordinateLeft[9] || x == xCoordinateRight[9] && y == yCoordinates[9]) {
				x = 985;
				y = yCoordinates[0];
			}
		}
		
		// Place image at spot
		// Set image to finger position - its starting position.
		circleParams.leftMargin = x - 730;
		circleParams.topMargin = y - 290;
        
		circle.setLayoutParams(circleParams);
	}
}