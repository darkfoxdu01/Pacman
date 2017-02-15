import javax.swing.JFrame;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.Random;

public class init extends JFrame implements KeyListener {
    public static void main(String[] str) {
        new init();
    }

    private static final int NORTH = 0;
    private static final int EAST = 1;
    private static final int SOUTH = 2;
    private static final int WEST = 3;

    private int xSize;
    private int ySize;
    private int[][] plateform = {
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
        {1,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,1},
        {1,0,1,1,1,0,1,1,1,0,1,1,0,1,1,1,0,1,1,1,0,1},
        {1,0,1,1,1,0,1,1,1,0,1,1,0,1,1,1,0,1,1,1,0,1},
        {1,0,1,1,1,0,1,1,1,0,1,1,0,1,1,1,0,1,1,1,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,1,1,1,0,1,0,1,1,1,1,1,1,0,1,0,1,1,1,0,1},
        {1,0,1,1,1,0,1,0,1,1,1,1,1,1,0,1,0,1,1,1,0,1},
        {1,0,0,0,0,0,1,0,1,1,1,1,1,1,0,1,0,0,0,0,0,1},
        {1,1,1,1,1,0,1,0,0,0,1,1,0,0,0,1,0,1,1,1,1,1},
        {1,1,1,1,1,0,1,1,1,0,1,1,0,1,1,1,0,1,1,1,1,1},
        {1,1,1,1,1,0,1,0,0,0,0,0,0,0,0,1,0,1,1,1,1,1},
        {1,1,1,1,1,0,1,0,1,1,1,1,1,1,0,1,0,1,1,1,1,1},
        {1,1,1,1,1,0,1,0,1,1,1,1,1,1,0,1,0,1,1,1,1,1},
        {0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0},
        {1,1,1,1,1,0,1,0,1,1,1,1,1,1,0,1,0,1,1,1,1,1},
        {1,1,1,1,1,0,1,0,1,1,1,1,1,1,0,1,0,1,1,1,1,1},
        {1,1,1,1,1,0,1,0,1,1,1,1,1,1,0,1,0,1,1,1,1,1},
        {1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1},
        {1,0,1,1,1,0,1,0,1,1,1,1,1,1,0,1,0,1,1,1,0,1},
        {1,0,1,1,1,0,1,0,1,1,1,1,1,1,0,1,0,1,1,1,0,1},
        {1,0,1,1,1,0,0,0,0,0,1,1,0,0,0,0,0,1,1,1,0,1},
        {1,0,0,0,0,0,1,1,1,0,1,1,0,1,1,1,0,0,0,0,0,1},
        {1,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,1},
        {1,0,0,1,1,0,1,0,1,1,1,1,1,1,0,1,0,1,1,0,0,1},
        {1,1,0,1,1,0,1,0,1,1,1,1,1,1,0,1,0,1,1,0,1,1},
        {1,0,0,0,0,0,1,0,0,0,1,1,0,0,0,1,0,0,0,0,0,1},
        {1,0,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,0,1},
        {1,0,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
    private int[][] gum;
    private Display display;
    private int xPacman;
    private int yPacman;
    private int dirPacman;
    private int[] xGhosts;
    private int[] yGhosts;
    private int[] prevCaseGhosts;
    private int[] dirGhosts;
    private Boolean pause;
    private Boolean ended = false;
    private Random random = new Random();


    public init() {
        // Default frame settings
        setTitle("Pacman");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Defining the plateform and its size
        xSize = 22;
        ySize = 31;
        // Defining the gum array, just the opposite of plateform at the initialisation
        gum = new int[ySize][xSize];
        for(int y = 0; y < ySize; y++) {
            for(int x = 0; x < xSize; x++) {
                if(plateform[y][x] == 1 || x == 0 || x == xSize-1) {
                    gum[y][x] = 0;
                }
                else {
                    gum[y][x] = 1;
                }
            }
        }

        // Defining the pacman coordonates and it's direction :
        xPacman = 1;
        yPacman = 1;
        dirPacman = NORTH;

        // Allocating the memory for the ghosts data
        xGhosts = new int[4];
        yGhosts = new int[4];
        prevCaseGhosts = new int[4];
        dirGhosts = new int[4];

        // setting up their values
        for(int i = 0; i < 4; i++) {
            xGhosts[i] = xSize-2-i; // Making a line of them in a first time on the right side
            yGhosts[i] = ySize-2; // On the bottom
            prevCaseGhosts[i] = 0;
            dirGhosts[i] = SOUTH;
        }

        // Creating a new panel display
        display = new Display(xSize, ySize, plateform);
        display.setGum(gum);
        display.setPacmanCoord(xPacman, yPacman);
        display.setGhostsCoord(xGhosts, yGhosts);
        setContentPane(display);
        pause = true;
        // Adding setting up the keyboard management.
        addKeyListener(this);

        setVisible(true);
        run();
    }

    public void run() {
        while(!ended) {
            while(!pause && !ended) {
                tryToEatGum();
                movePacman();
                pacmanAlive();
                moveGhosts();
                pacmanAlive();

                updateDisplay();
                clockManagement();
            }
            try {
                Thread.sleep(100);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.exit(0);
    }

    public void pacmanAlive() {
        for(int i = 0; i < 4; i++) {
            if(xPacman == xGhosts[i] && yPacman == yGhosts[i]) {
                System.out.println("DEAD !");
                xPacman = 1;
                yPacman = 1;
            }
        }
    }

    public void moveGhosts() {
        int possibilities = 0;
        int currentX;
        int currentY;
        for(int i = 0; i < 4 ; i++) {
            possibilities = 0;
            currentX = xGhosts[i];
            currentY = yGhosts[i];
            if(plateform[currentY-1][currentX] == 0 && dirGhosts[i] != SOUTH) {
                possibilities++;
            }
            if(plateform[currentY+1][currentX] == 0 && dirGhosts[i] != NORTH) {
                possibilities++;
            }
            if(plateform[currentY][currentX-1] == 0 && dirGhosts[i] != EAST) {
                possibilities++;
            }
            if(plateform[currentY][currentX+1] == 0 && dirGhosts[i] != WEST) {
                possibilities++;
            }
            possibilities = random.nextInt(possibilities);
            if(plateform[currentY-1][currentX] == 0 && dirGhosts[i] != SOUTH) {
                if(possibilities == 0) {
                    dirGhosts[i] = NORTH;
                    yGhosts[i] --;
                }
                possibilities--;
            }
            if(plateform[currentY+1][currentX] == 0 && dirGhosts[i] != NORTH && possibilities >= 0) {
                if(possibilities == 0) {
                    dirGhosts[i] = SOUTH;
                    yGhosts[i]++;
                }
                possibilities--;
            }
            if(plateform[currentY][currentX-1] == 0 && dirGhosts[i] != EAST && possibilities >= 0) {
                if(possibilities == 0) {
                    dirGhosts[i] = WEST;
                    xGhosts[i]--;
                    if(xGhosts[i] == 0) {
                        xGhosts[i] = 20;
                    }
                }
                possibilities--;
            }
            if(plateform[currentY][currentX+1] == 0 && dirGhosts[i] != WEST && possibilities >= 0) {
                if(possibilities == 0) {
                    dirGhosts[i] = EAST;
                    xGhosts[i]++;
                    if(xGhosts[i] == 21) {
                        xGhosts[i] = 1;
                    }
                }
                possibilities--;
            }
        }
    }

    public void clockManagement() {
        if(countGum() == 0) {
            ended = true;
        }
        try {
            Thread.sleep(300);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateDisplay() {
        display.setGum(gum);
        display.setPacmanCoord(xPacman, yPacman);
        display.setGhostsCoord(xGhosts, yGhosts);
        display.repaint();
    }

    public int countGum() {
        for(int y = 0; y < ySize; y++) {
            for(int x = 0; x < xSize; x++) {
                if(gum[y][x] == 1) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public void tryToEatGum() {
        if(gum[yPacman][xPacman] == 1) {
            gum[yPacman][xPacman] = 0;
        }
    }

    public void movePacman() {
        if(dirPacman == NORTH && plateform[yPacman-1][xPacman] == 0) {
            yPacman -= 1;
        }
        else if(dirPacman == SOUTH && plateform[yPacman+1][xPacman] == 0) {
            yPacman += 1;
        }
        else if(dirPacman == WEST && plateform[yPacman][xPacman-1] == 0) {
            xPacman -= 1;
            if(xPacman == 0) {
                xPacman = 20;
            }
        }
        else if(dirPacman == EAST && plateform[yPacman][xPacman+1] == 0) {
            xPacman += 1;
            if(xPacman == 21) {
                xPacman = 1;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if((e.getKeyCode() == KeyEvent.VK_Z || e.getKeyCode() == KeyEvent.VK_UP) && plateform[yPacman-1][xPacman] == 0)  {
            //System.out.println("UP !");
            dirPacman = NORTH;
        }
        else if((e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) && plateform[yPacman][xPacman+1] == 0) {
            //System.out.println("RIGHT !");
            dirPacman = EAST;
        }
        else if((e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) && plateform[yPacman+1][xPacman] == 0) {
            //System.out.println("DOWN !");
            dirPacman = SOUTH;
        }
        else if((e.getKeyCode() == KeyEvent.VK_Q || e.getKeyCode() == KeyEvent.VK_LEFT) && plateform[yPacman][xPacman-1] == 0) {
            //System.out.println("LEFT !");
            dirPacman = WEST;
        }
        else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            if(pause) {
                System.out.println("Continue");
                pause = false;
            }
            else {
                System.out.println("Pause");
                pause = true;
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
