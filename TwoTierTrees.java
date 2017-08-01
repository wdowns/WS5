package ex5Solutions;

import java.util.ArrayList;
import java.awt.*;
import java.awt.image.*;
import javax.swing.JPanel;
import javax.swing.JFrame;

/**
 *  Class to display two-tier Christmas trees.
 *
 *   
 * @author William Downs
 * @version 30/11/15
 */
public class TwoTierTrees extends Trees {

    /**
     *  The constructor takes three arrays of equal lengths to store
     *  the x-positions, y-positions, and scaling factor for the
     *  individual Christmas trees.
     *  @param xTrees x-positions of the trees
     *  @param yTrees y-positions of the trees
     *  @param scaleTrees scaling factors of the trees
     */
    public TwoTierTrees(int[] xTrees,
                 int[] yTrees,
                 int[] scaleTrees) {
        super(xTrees, yTrees, scaleTrees);
    }

    /**
     *  Method to draw a single two-tier Christmas tree
     *  @param g The graphics component used for painting the
     *  Christmas tree.
     *  @param xPos The x-position of the bounding box of the
     *  Christmas tree.
     *  @param yPos The y-position of the bounding box of the
     *  Christmas tree.
     *  @param scale The scale for magnifying the Christmas tree.
     */
    @Override
    public void drawSimpleTree(Graphics g, int xPos, int yPos,
                               int scale) {
        g.setColor(new Color(120,60,30)); // brown
        // Rectangle for the trunk.
        g.fillRect(xPos + 5 * scale, yPos + 12 * scale , 2 * scale, 4 * scale);
        // Triangle for the rest in form of a polygon with 3 vertices.
        int[] xPoints = {xPos +  0 * scale, xPos + 12 * scale,  // A, G
                         xPos +  9 * scale, xPos + 11 * scale,  // F, E
                         xPos +  6 * scale, xPos +  1 * scale,  // D, C
                         xPos +  3 * scale};                    // B
	int[] yPoints = {yPos + 12 * scale, yPos + 12 * scale,  // A, G
                         yPos +  6 * scale, yPos +  6 * scale,  // F, E
                         yPos +  0 * scale, yPos +  6 * scale,  // D, C
                         yPos +  6 * scale};                    // B
        Polygon poly = new Polygon(xPoints, yPoints, xPoints.length);
        g.setColor(new Color(0,200,0)); // green
        g.fillPolygon(poly);
    }

    /*
     *  We create a frame with a panel on which we draw a few Christmas trees.
     */
    public static void main(String[] args) {

        int[] xTrees     = {10, 150, 400, 300, 150};
        int[] yTrees     = {10, 220, 230, 100,  30};
        int[] scaleTrees = {10,  10,  15,   3,   4};

        TwoTierTrees panel =  new TwoTierTrees(xTrees,
                                 yTrees,
                                 scaleTrees);
        
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
