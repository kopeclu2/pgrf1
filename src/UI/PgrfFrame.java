package UI;

import drawables.Drawable;
import utils.Renderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class PgrfFrame extends JFrame implements MouseMotionListener {

    private BufferedImage img;
    static int width = 800;
    static int height = 600;
    private JPanel panel;
    static int FPS = 1000 / 60;
    private Renderer renderer;
    private int coorX;
    private int coorY;
    private int clickX=300;
    private int clickY=300;
    private int count = 5;

    private List<Drawable> drawables;

    public static void main(String... args) {


        PgrfFrame pgrfFrame = new PgrfFrame();
        pgrfFrame.img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        pgrfFrame.init(width, height);

    }

    private void init(int width, int height) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(width, height);
        setTitle("Pocitacova grafika");

        setLocationRelativeTo(null);


        panel = new JPanel();
        add(panel);

        panel.addMouseMotionListener(this);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clickX =e.getX();
                clickY= e.getY();
                super.mouseClicked(e);
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP)
                {
                    //šipka hore;
                    count++;

                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN){
                    //šipka dolů;
                    //todo nesmí klesout od 3!
                    count--;
                }
                super.keyReleased(e);
            }
        });

        renderer = new Renderer(img);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                draw();
            }
        }, 100, FPS);

        //draw();
    }

    private void draw() {
        img.getGraphics().fillRect(0, 0, img.getWidth(), img.getHeight());




        panel.getGraphics().drawImage(img, 0, 0, img.getWidth(), img.getHeight(), null);
        panel.paintComponents(getGraphics());

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
            coorX = e.getX();
            coorY = e.getY();
        System.out.println(coorX+" "+coorY);;

    }
}


