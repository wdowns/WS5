package ex5Solutions;

import java.util.ArrayList;
import java.awt.*;
import java.awt.image.*;
import javax.swing.JPanel;
import javax.swing.JFrame;

/**
 *  Class to display simple Christmas trees.
 *
 *  
 * @author William Downs
 * @version 30/11/15
 */
public class Trees extends JPanel {

    /**
     *  The Christmas trees are put at certain x-positions and
     *  y-positions with a certain scaling, each stored in a separate
     *  array, all three of equal length.
     */
    private int[] xTrees;
    private int[] yTrees;
    private int[] scaleTrees;

    /** 
     *  getter for the x-values of the Christmas trees.
     *  @return The x-values of the positions of the Christmas trees.
     */
    public int[] getXTrees() {
        return xTrees;
    }

    /** 
     *  getter for the y-values of the Christmas trees.
     *  @return The y-values of the positions of the Christmas trees.
     */
    public int[] getYTrees() {
        return yTrees;
    }

    /** 
     *  getter for the scales of the Christmas trees.
     *  @return The scales of the positions of the Christmas trees.
     */
    public int[] getScaleTrees() {
        return scaleTrees;
    }

    
    /**
     *  The constructor takes three arrays of equal lengths to store
     *  the x-positions, y-positions, and scaling factor for the
     *  individual Christmas trees.
     *  @param xTrees x-positions of the trees
     *  @param yTrees y-positions of the trees
     *  @param scaleTrees scaling factors of the trees
     */
    public Trees(int[] xTrees,
                 int[] yTrees,
                 int[] scaleTrees) {
        this.xTrees = xTrees;
        this.yTrees = yTrees;
        this.scaleTrees = scaleTrees;
    }

    @Override
    /**
     *    Override the paintComponent method from the JPanel class.  
     *    @param g The graphics component used for painting the
     *    Christmas tree.
     */
    public void paintComponent(Graphics g){
        for (int i = 0; i < xTrees.length; i++) {
            drawSimpleTree(g,
                           this.xTrees[i],
                           this.yTrees[i],
                           this.scaleTrees[i]);
        }
    }


    /**
     *  Method to draw a single Christmas tree
     *  @param g The graphics component used for painting the
     *  Christmas tree.
     *  @param xPos The x-position of the bounding box of the
     *  Christmas tree.
     *  @param yPos The y-position of the bounding box of the
     *  Christmas tree.
     *  @param scale The scale for magnifying the Christmas tree.
     */
    public void drawSimpleTree(Graphics g, int xPos, int yPos,
                               int scale) {
        g.setColor(new Color(120,60,30)); // brown
        // Rectangle for the trunk.
        g.fillRect(xPos + 5 * scale, yPos + 12 * scale , 2 * scale, 4 * scale);
        // Triangle for the rest in form of a polygon with 3 vertices.
        int[] xpoints = {xPos +  0 * scale, xPos + 12 * scale, xPos + 6 * scale};
	int[] ypoints = {yPos + 12 * scale, yPos + 12 * scale, yPos + 0 * scale};
        Polygon poly = new Polygon(xpoints, ypoints, 3);
        g.setColor(new Color(0,200,0)); // green
        g.fillPolygon(poly);
    }

    /*
     *  We create a frame with a panel on which we draw a few Christmas trees.
     */
    public static void main(String[] args) {

        int[] xTrees     = {10, 150, 400, 300, 140};
        int[] yTrees     = {10, 220, 230, 100, 30};
        int[] scaleTrees = {10,  10,  15,   3,  4};

        Trees panel =  new Trees(xTrees,
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
