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

public class CapitalB extends Activity {

	private ImageView pointer;

	private int currentXPos;
	private int currentYPos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_capital_b);

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
						
						// Middle Triangle
						if(isInTriangleBoundary(currentXPos, currentYPos, 1070, 530, 1170, 460, 1170, 600)){
							resetPosition();
						} else if(isInTriangleBoundary(currentXPos, currentYPos, 1070, 250, 1170, 360, 1170, 250)) { // Top Right Triangle
							resetPosition();
						} else if(isInTriangleBoundary(currentXPos, currentYPos, 1070, 810, 1170, 710, 1170, 810)) { // Bottom Right Triangle
							resetPosition();
						}
						
						// Inside Rectangle
						if(isInRectangleBoundary(currentXPos, currentYPos, 870, 360, 1020, 360, 1020, 500, 870, 500)) { // Top Rectangle
							resetPosition();
						} else if (isInRectangleBoundary(currentXPos, currentYPos, 870, 600, 1040, 600, 1040, 750, 870, 750)) { // Bottom Rectangle
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

	/**
	 * Used to calculate the area of the rectangle.
	 * <p>
	 * The rectangles coordinates must be clockwise for this to work correctly.
	 * </p>
	 * 
	 * <p>
	 * A --- B
	 * </p>
	 * <p>
	 * D --- C
	 * </p>
	 * 
	 * @param aX
	 *            - Coordinate start x
	 * @param aY
	 *            - Coordinate start y
	 * @param bX
	 *            - Second coordinate x
	 * @param bY
	 *            - Second coordinate y
	 * @param cX
	 *            - Third coordinate x
	 * @param cY
	 *            - Third coordinate y
	 * @param dX
	 *            - Last coordinate x
	 * @param dY
	 *            - Last coordinate y
	 * @return area of rectangle
	 */
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

		if (y <= 250 || y >= 790 || x <= 735 || x >= 1150) {
			return false;
		}

		return true;
	}

	private void resetPosition() {
		pointer.setX(770);
		pointer.setY(315);
	}
}
