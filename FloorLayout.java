package ex5Solutions;

import java.util.ArrayList;
import java.awt.*;
import java.awt.image.*;
import javax.swing.JPanel;
import javax.swing.JFrame;

/**
 *  Class to display a floor layout consisting of furniture in form of
 *  polygons.
 *
 *  @version 2015-11-17
 *  @author Manfred Kerber
 */
public class FloorLayout extends JPanel {
    /** 
     *  A variable to move the floor from the boarder of the panel.
     */
    public static final int shift = 10;

    /**
     *  Field variable to add to all the elements to be drawn.
     */
    private ArrayList<Polygon> toDraw = new ArrayList<Polygon>();

    /**
     *  The original floor layout starts by drawing a rectangle of
     *  corresponding size, shifted slightly out of the corner by
     *  shift.
     *  @param x The width of the rectangle.
     *  @param y The height of the rectangle.
     */
    public FloorLayout(int x, int y) {
        addRectangle(shift, shift, x + shift, y + shift);
    }

    /** 
     *  A polygon is added to the list of all polygons to be drawn.
     *  @param poly The polygon added to the list of all polygons to
     *  be drawn.
     */
    public void addToDraw(Polygon poly) {
        toDraw.add(poly);
    }

    /**
     *  In order to add a rectangle we convert it to a polygon and add
     *  it to the list of all polygons that need to be drawn.
     *  @param xPos The x value of the left upper position of the rectangle.
     *  @param yPos The y value of the left upper position of the rectangle.
     *  @param dX The width of the rectangle.
     *  @param dY The height of the rectangle.
     */
    public void addRectangle(int xPos, int yPos, int dX, int dY) {
	int[] xpoints = {xPos,xPos+dX,xPos+dX,xPos};
	int[] ypoints = {yPos,yPos,  yPos+dY, yPos+dY};
        Polygon poly = new Polygon(xpoints, ypoints, 4);
        toDraw.add(poly);
    }

    @Override
    /**
     *    Override the paintComponent method from the JPanel class.  
     *    @param g The graphics component used for painting the
     *    different polygons.
     */
    public void paintComponent(Graphics g){
       	super.paintComponent(g);
        for (Polygon poly : toDraw) {
            g.drawPolygon(poly);
        }
    }

    /*
     *  We create a frame with a panel on which we draw the boarder of
     *  the floor and any polygons created.
     */
    public static void main(String[] args) {
       FloorLayout panel =  new FloorLayout(600, 400);

	int[] xpoints1 = {100, 200, 200, 100};
	int[] ypoints1 = {320, 325, 350, 320};
        Polygon poly1 = new Polygon(xpoints1, ypoints1, 4);

        int[] xpoints2 = {470, 440, 440, 470};
	int[] ypoints2 = { 50,  50,  20,  20};
        Polygon poly2 = new Polygon(xpoints2, ypoints2, 4);

        int[] xpoints3 = {310, 340, 340, 310};
	int[] ypoints3 = {250, 250, 220, 220};
        Polygon poly3 = new Polygon(xpoints3, ypoints3, 4);


        panel.addToDraw(poly1);
        panel.addToDraw(poly2);
        panel.addToDraw(poly3);
        panel.addRectangle(40,40,200,200);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final int FRAME_WIDTH = 800;
        final int FRAME_HEIGHT = 600;
        frame.setSize(FRAME_WIDTH,FRAME_HEIGHT); 
        //creates a window of size 800 x 600 pixels
        frame.add(panel); 
        frame.setVisible(true); // makes the application visible.
    }
}
