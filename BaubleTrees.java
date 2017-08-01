import java.util.ArrayList;
import java.awt.*;
import java.awt.image.*;
import javax.swing.JPanel;
import javax.swing.JFrame;

/**
 * Write a class FloorLayout with the only field variable private ArrayList<polygon>
 * toDraw; that allows to produce a rectangular layout of a floor.
 * 
 * @author William Downs
 * @version 29/11/15
 */
public class BaubleTrees extends Trees {

    /** The additional field variable is the number of baubles
     *  in the tree.
     */

    private int numberOfBaubles;
    
    /**
     *  The constructor takes three arrays of equal lengths to store
     *  the x-positions, y-positions, and scaling factor for the
     *  individual Christmas trees.
     *  @param xTrees x-positions of the trees
     *  @param yTrees y-positions of the trees
     *  @param scaleTrees scaling factors of the trees
     *  @param numberOfBaubles The number of baubles in the tree.
     */
    public BaubleTrees(int[] xTrees,
                       int[] yTrees,
                       int[] scaleTrees,
                       int numberOfBaubles) {
        super(xTrees, yTrees, scaleTrees);
        this.numberOfBaubles = numberOfBaubles;
    }

    /**
     *  getter for the number of baubles.
     *  @return The number of baubles in the tree.
     */
    public int getNumberOfBaubles() {
        return numberOfBaubles;
    }
    
    @Override
    /**
     *    Override the paintComponent method from the JPanel class.  
     *    @param g The graphics component used for painting the
     *    Christmas tree.
     */
    public void paintComponent(Graphics g){
        for (int i = 0; i < getXTrees().length; i++) {
            drawBaubleTree(g,
                           this.getXTrees()[i],
                           this.getYTrees()[i],
                           this.getScaleTrees()[i],
                           this.getNumberOfBaubles());
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
     *  @param numberOfBaubles The number of baubles in the Christmas tree.
     */
    public void drawBaubleTree(Graphics g, int xPos, int yPos,
                               int scale, int numberOfBaubles) {
        g.setColor(new Color(120,60,30)); // brown
        // Rectangle for the trunk.
        g.fillRect(xPos + 5 * scale, yPos + 12 * scale , 2 * scale, 4 * scale);
        // Triangle for the rest in form of a polygon with 3 vertices.
        int[] xpoints = {xPos +  0 * scale, xPos + 12 * scale, xPos + 6 * scale};
	int[] ypoints = {yPos + 12 * scale, yPos + 12 * scale, yPos + 0 * scale};
        Polygon poly = new Polygon(xpoints, ypoints, 3);
        g.setColor(new Color(0,200,0)); // green
        g.fillPolygon(poly);

        // the y-value is between yPos + 0 and yPos + 12 *scale
        double randomX, randomY;
        int x,y;
        for (int i = 0; i < numberOfBaubles; i++){
            // We randomly generate a y-value between 0 and 12 * scale
            randomY = 12 * scale * Math.random();
            // We randomly generate an x-value around 6 * scale within
            // the triangle given by y so that the value is within
            // plus/minus randomY/2
            randomX = 6 *scale + randomY * Math.random() - randomY/2;
            // The actual x- and y-values are integer versions of the
            // accordingly offsetted values of randomX and randomY,
            // respectively.
            x = (int) (xPos + randomX);
            y = (int) (yPos + randomY);
            // A single bauble is drawn.
            drawBauble(g, x, y, scale);
        }
    }

    /**
     *  A single bauble is drawn at the corresponding position. Its
     *  colour is randomly selected out of red, blue, and yellow.
     *  @param g The graphics component used for painting the bauble.
     *  @param x The x-position of the bounding box of the bauble
     *  @param y The y-position of the bounding box of the bauble
     *  @param scale The scale for magnifying the bauble.
     */
    public void drawBauble(Graphics g, int x, int y, int scale) {
        int i = (int) (3 * Math.random());
        switch(i) {
        case 0 : g.setColor(new Color(200,0,0));   break; // red
        case 1 : g.setColor(new Color(0,0,200));   break; // blue
        case 2 : g.setColor(new Color(200,200,0)); break; // yellow
        }
        g.fillOval(x, y, (int) (1.1 * scale), (int) (1.1 * scale));
    }

    /*
     *  We create a frame with a panel on which we draw a few Christmas trees.
     */
    public static void main(String[] args) {

        int[] xTrees     = {10, 150, 400, 300, 150};
        int[] yTrees     = {10, 220, 230, 100,  30};
        int[] scaleTrees = {10,  10,  15,   3,   4};

        BaubleTrees panel =  new BaubleTrees(xTrees,
                                             yTrees,
                                             scaleTrees, 20);
        
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
