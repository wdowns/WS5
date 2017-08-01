package ex5Solutions;

import java.util.ArrayList;
import java.awt.*;
import java.awt.image.*;
import javax.swing.JPanel;
import javax.swing.JFrame;

/**
 *  Class to display Christmas trees with baubles and a top star.
 *
 * @author William Downs
 * @version 30/11/15
 */
public class StarTrees extends BaubleTrees {

    /** The two additional field variables are the number of vertices
     * of the star at the top and the number of steps between the
     * vertices that are connected.
     */
    private int numberOfVertices;
    private int steps;
    
    /**
     *  The constructor takes three arrays of equal lengths to store
     *  the x-positions, y-positions, and scaling factor for the
     *  individual Christmas trees.
     *  @param xTrees x-positions of the trees
     *  @param yTrees y-positions of the trees
     *  @param scaleTrees scaling factors of the trees
     *  @param numberOfBaubles The number of baubles in the tree.
     *  @param numberOfVertices The number of vertices of the star at
     *  the top of the tree.
     *  @param steps The number of steps to the next vertex to be
     *  connected in the the star at the top of the tree.
     */
    public StarTrees(int[] xTrees,
                     int[] yTrees,
                     int[] scaleTrees,
                     int numberOfBaubles, int numberOfVertices, int steps) {
        super(xTrees, yTrees, scaleTrees, numberOfBaubles);
        this.numberOfVertices = numberOfVertices;
        this.steps = steps;
    }

    @Override
    /**
     *    Override the paintComponent method from the JPanel class.  
     *    @param g The graphics component used for painting the
     *    Star tree.
     */
    public void paintComponent(Graphics g){
        for (int i = 0; i < getXTrees().length; i++) {
            drawStarTree(g,
                         this.getXTrees()[i],
                         this.getYTrees()[i],
                         this.getScaleTrees()[i],
                         this.getNumberOfBaubles(),
                         this.getNumberOfVertices(),
                         this.getSteps());
        }
    }

    /**
     *  getter for the number of vertices.
     *  @return The number of vertices in the top star of the tree.
     */
    public int getNumberOfVertices() {
        return numberOfVertices;
    }

    /**
     *  getter for the number of steps.
     *  @return The number of steps to the next vertex to be connected
     *  in the top star of the tree.
     */
    public int getSteps() {
        return steps;
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
     *  @param numberOfBaubles The number of baubles in the tree.
     *  @param numberOfVertices The number of vertices of the star at
     *  the top of the tree.
     *  @param steps The number of steps to the next vertex to be
     *  connected in the the star at the top of the tree.
     */
    public void drawStarTree(Graphics g, int xPos, int yPos,
                             int scale, int numberOfBaubles,
                             int numberOfVertices, int steps) {
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
            randomY = 12 * scale * Math.random();
            randomX = 6 *scale + randomY * Math.random() - randomY/2;
            x = (int) (xPos + randomX);
            y = (int) (yPos + randomY);
            drawBauble(g, x, y, scale);
        }
        drawStar(g, xPos + 6 * scale, yPos + 0 * scale,
                 numberOfVertices, steps, scale);
    }

    /**
     *  Method to draw a the star at the top of a single Christmas tree
     *  @param g The graphics component used for painting the
     *  Christmas tree.
     *  @param xOffset The x-offset for the polygon.
     *  @param yOffset The y-offset for the polygon.
     *  @param vertices The number of vertices of the star.
     *  @param steps The number of steps to the next vertex to be
     *  connected in the the star.
     *  @param scale The scale for magnifying the star.
     */
    public void drawStar(Graphics g, int xOffset, int yOffset,
                         int vertices, int steps, int scale) {
        int[] xPoints = new int[vertices];
	int[] yPoints = new int[vertices];
	float rotation = (float) 0.0;

        g.setColor(new Color(0,0,0)); //black star
	for (int i = 0; i< vertices; i++){
            xPoints[i] = xOffset + 
                (int) Math.round(scale *
                                 Math.cos(rotation + 
                                 //We move on by steps steps.                                 
                                          2*(steps*i)*Math.PI/vertices));
            yPoints[i] = yOffset + 
                (int) Math.round(scale * 
                                 Math.sin(rotation + 
                                          2*(steps*i)*Math.PI/vertices));
        }
        g.drawPolygon(xPoints,yPoints,vertices);
    }

    /*
     *  We create a frame with a panel on which we draw a few Christmas trees.
     */
    public static void main(String[] args) {

        int[] xTrees     = {10, 150, 400, 300, 150};
        int[] yTrees     = {10, 220, 230, 100,  30};
        int[] scaleTrees = {10,  10,  15,   3,   4};

        StarTrees panel =  new StarTrees(xTrees,
                                         yTrees,
                                         scaleTrees, 20, 11, 4);
        
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
