package uk.ac.mdx.capital.letters;

import uk.ac.mdx.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;

public class CapitalA extends Activity {

	private ImageView pointer;

	private int currentXPos;
	private int currentYPos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_capital_a);
		
		pointer = (ImageView) findViewById(R.id.imageCircle);
		pointer.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				int id = event.getAction();
				
				switch(id) {
					case MotionEvent.ACTION_MOVE:
						
						// Get position on screen
						currentXPos = (int) event.getRawX();
						currentYPos = (int) event.getRawY();
						
						// Set Image to that position
						pointer.setX(currentXPos - (pointer.getWidth() / 2));
						pointer.setY(currentYPos - (pointer.getHeight() / 2));
						
						// In Image Boundaries
						if(!inImgBoundaries((int)pointer.getX(), (int)pointer.getY())) {
							resetPosition();
						}
						
						// Left Side
						if(isInTriangleBoundary(currentXPos, currentYPos, 710, 830, 710, 260, 890, 260)) {
							resetPosition();
						} else if(isInTriangleBoundary(currentXPos, currentYPos, 960, 420, 910, 620, 1010, 620))  { // Middle Triangle
							resetPosition();
						} else if(isInTriangleBoundary(currentXPos, currentYPos, 1030, 260, 1220, 260, 1220, 800)) { // Right Right
							resetPosition();
						} else if(isInTriangleBoundary(currentXPos, currentYPos, 870, 730, 870, 800, 835, 800)) { // Left of Rectangle
							resetPosition();
						} else if(isInTriangleBoundary(currentXPos, currentYPos, 1050, 730, 1050, 800, 1080, 800)) { // Right of Rectangle
							resetPosition();
						}
						
						// Rectangle
						if(isInRectangleBoundary(currentXPos, currentYPos, 880, 730, 1050, 730, 1050, 840, 880, 840)) {
							resetPosition();
						}
						
						break;
						
					default:
						break;
				}
				
				return true;
			}
		});
	}

	/**
	 * Calculates the area of the triangle with the given positions. //
	 * http://www
	 * .geeksforgeeks.org/check-whether-a-given-point-lies-inside-a-triangle
	 * -or-not/
	 * 
	 */
	private int triangleAreaCalc(int posX1, int posY1, int posX2, int posY2,
			int posX3, int posY3) {
		return Math
				.abs((posX1 * (posY2 - posY3) + posX2 * (posY3 - posY1) + posX3
						* (posY1 - posY2)) / 2);
	}

	private boolean isInTriangleBoundary(int currentXPos, int currentYPos,
			int aX, int aY, int bX, int bY, int cX, int cY) {

		// Calculate Area of Triangle (A, B, C)
		int area = triangleAreaCalc(aX, aY, bX, bY, cX, cY);

		// Calculate Area of Triangle (Current Position, B, C)
		int pbcArea = triangleAreaCalc(currentXPos, currentYPos, bX, bY, cX, cY);

		// Calculate Area of Triangle (A, Current Position, C)
		int pacArea = triangleAreaCalc(aX, aY, currentXPos, currentYPos, cX, cY);

		// Calculate Area of Triangle (A, B, Current Position)
		int pabArea = triangleAreaCalc(aX, aY, bX, bY, currentXPos, currentYPos);

		return (area == pbcArea + pacArea + pabArea);
	}

	private int rectangleAreaCalc(int aX, int aY, int bX, int bY, int cX,
			int cY, int dX, int dY) {

		int width = (aX - bX);
		int height = (aY - dY);

		int area = width * height;

		return area;
	}

	private boolean isInRectangleBoundary(int currentXPos, int currentYPos,
			int aX, int aY, int bX, int bY, int cX, int cY, int dX, int dY) {

		int area = rectangleAreaCalc(aX, aY, bX, bY, cX, cY, dX, dY);

		int apdArea = triangleAreaCalc(aX, aY, currentXPos, currentYPos, dX, dY);
		int dpcArea = triangleAreaCalc(dX, dY, currentXPos, currentYPos, cX, cY);
		int cpbArea = triangleAreaCalc(cX, cY, currentXPos, currentYPos, bX, bY);
		int pbaArea = triangleAreaCalc(currentXPos, currentYPos, bX, bY, aX, aY);

		if (area == apdArea + dpcArea + cpbArea + pbaArea) {
			return true;
		}

		return false;
	}

	private boolean inImgBoundaries(int x, int y) {

		if (y <= 260 || y >= 800 || x <= 710 || x >= 1180) {
			return false;
		}

		return true;
	}

	private void resetPosition() {
		pointer.setX(890);
		pointer.setY(300);
	}
}