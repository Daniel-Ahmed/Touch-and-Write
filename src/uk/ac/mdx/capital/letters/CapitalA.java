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
	TextView mistakesText;
	
	RelativeLayout.LayoutParams circleParams;
	
	private final int yCoordinates[] = {310, 360, 410, 460, 510, 560, 610, 660, 710, 775};
	private final int xCoordinateLeft[] = {902, 887, 872, 857, 841, 826, 811, 796, 781, 767};
	
	
	private final int xCoordinateRight[] = {945, 945, 925, 910, 900, 890, 880, 870, 840, 840};
	
	private int mistakes = 0;
	
	//private final int xCoOrdinateRight[] = {990, 1005, 1020, 1035, 1050, 1065, 1080, 1095, 1110, 1125};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_capital_a);
		
		coordinates = (TextView)findViewById(R.id.textView1);
		mistakesText = (TextView)findViewById(R.id.mistakesView);
		
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
					
					coordinates.setText("X: " + x + "Y: " + y);
					
										
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
		
		// Top of Letter A
		if(y <= 280) {
			y = 280;
		}
		
		// Bottom of Letter A
		if(y >= 830) {
			y = 830;
		}
		
		if(x >= 850 && y >= 825) {
			x = 850;
			y = 825;
		}
		
		if(x >= 855 && y >= 790) {
			x = 855;
			y = 790;
		}
		
		if(x >= 860 && y >= 755) {
			x = 845;
			y = 790;
		}
		
		// Set image to finger position - its starting position.
		circleParams.leftMargin = x - 730;
		circleParams.topMargin = y - 290;
        
		circle.setLayoutParams(circleParams);
	}

	public int getMistakes() {
		return mistakes;
	}
}