
import javax.swing.*;
import java.awt.*;

/**
 * This is a class
 * Created 2022-04-11
 *
 * @author Magnus Silverdal
 */
public class rita extends Canvas {
    Graphics dbg;
    Image image;

    private final int height = 600;
    private final int width = 800;
    int treeX = 200;
    int treeY = 200;
    int sunX = 700;
    int sunY = 100;
    int cloudX = 100;
    int cloudY = 50;
    int husX = 400;
    int husY = 300;
    int sky = 0x22FFDD;

    public rita() {
        JFrame frame = new JFrame("Del 1");
        this.setSize(width, height);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void paint(Graphics g) {
        if (image == null) {
            // skapa en andra skärm i minnet som vi kan rita till
            image = createImage(width, height);
            if (image == null) {
                System.out.println("image is still null!");
                return;
            } else {
                dbg = image.getGraphics();
            }
        }
        update();
        draw(dbg);
        g.drawImage(image, 0, 0, null);
        try {
            Thread.sleep(35);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        repaint();
    }

    private void update() {
        cloudX += 5;
        treeX += 5;
        sunY += 5;
        if (cloudX > 700)
            cloudX = 0;
        if (treeX > 700)
            treeX = 0;
        if (sunY > 300)
            sky = 0x0000000;
        if (sunY > 700) {
            sky = 0x22FFDD;
            sunY = 0;
        }

    }

    private void draw(Graphics g) {
        drawHeaven(g);
        drawSun(g, sunX, sunY);
        drawMountains(g, 200);
        drawGrass(g);
        drawCloud(g, cloudX,cloudY);
        drawTree(g, treeX,treeY);
        drawTree(g, treeX+30,treeY);
        drawTree(g, treeX+60,treeY);
        drawHus(g,husX,husY);
    }

    private void drawCloud(Graphics g, int cloudX, int cloudY) {
        g.setColor(Color.white);
        g.fillOval(cloudX,cloudY,30,30);
        g.fillOval(cloudX+20,cloudY-10,30,30+10);
        g.fillOval(cloudX+40,cloudY,30,30);
    }

    private void drawSun(Graphics g, int sunX, int sunY) {
        g.setColor(Color.yellow);
        g.fillOval(sunX,sunY,40,40);
    }

    private void drawGrass(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(0,250,width,height);
    }

    private void drawMountains(Graphics g, int y) {
        g.setColor(Color.darkGray);
        int[] xs = {0,200,240,470,550,650,800};
        int[] ys = {250,150,200,150,200,150,250};
        Polygon shape = new Polygon(xs,ys,7);
        g.fillPolygon(shape);
    }

    private void drawHeaven(Graphics g) {
        g.setColor(new Color(sky));
        g.fillRect(0,0,width,height);
    }

    private void drawHus(Graphics g, int x, int y) {
        g.setColor(new Color(0x742601));
        g.fillRect(x,y,100,60);

        g.setColor(new Color(0x91009F));
        g.fillRect(x+40,y+30,20,30);

        g.setColor(new Color(0x5E1E01));
        g.fillRect(x-15,y-10,130,10);

        g.setColor(new Color(0x5E1E01));
        g.fillRect(x,y-20,100,10);

        g.setColor(new Color(0x5E1E01));
        g.fillRect(x+14,y-30,70,10);

        g.setColor(new Color(0x8BFFF3));
        g.fillRect(x+10,y+20,14,14);

        g.setColor(new Color(0x8BFFF3));
        g.fillRect(x+75,y+20,14,14);
    }

    private void drawTree(Graphics g, int x, int y) {
        g.setColor(new Color(0x00DD33));
        g.fillRect(x,y,20,40);
        g.setColor(Color.white);
        g.fillRect(x+8,y+40,4,20);
    }


    public static void main(String[] args) {
        rita exempel = new rita();
    }
}