import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

/**
 * Arthur Alexsander Martins Teodoro - 0022427
 * Saulo Ricardo Dias Fernandes - 0021581
 */
public class Board extends JPanel implements ActionListener
{
    private Dimension d;
    private final Font smallFont = new Font("Helvetica", Font.BOLD, 14);

    private Image ii;
    private final Color dotColor = new Color(192, 192, 0);
    private Color mazeColor;

    public static boolean inGame = false;
    public static boolean dying = false;

    public static final int BLOCK_SIZE = 24;
    public static int N_BLOCKS = 24;
    private final int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE;
    public static final int PACMAN_SPEED = 6;

    private int pacsLeft;
    private static int pacmanAnimPos;
    private static int score;

    private static final int PAC_ANIM_DELAY = 2;
    private static final int PACMAN_ANIM_COUNT = 4;
    private static int pacAnimCount = PAC_ANIM_DELAY;
    private static int pacAnimDir = 1;


    private static Image imageBlue, imageRed, imagePink, imageYellow;
    private static Image pacman1, pacman2up, pacman2left, pacman2right, pacman2down;
    private static Image pacman3up, pacman3down, pacman3left, pacman3right;
    private static Image pacman4up, pacman4down, pacman4left, pacman4right;

    private Pacman pacman;
    private GhostBlue ghostBlue;
    private GhostRed ghostRed;
    private GhostYellow ghostYellow;
    private GhostPink ghostPink;

    private Arbitro arbitro;

    private int levelAtual = 1;
    private Timer timer;

    private int[] levelData =
    {
        19, 26, 26, 26, 26, 18, 26, 26, 26, 26, 22,  0, 19, 26, 26, 26, 26, 26, 18, 26, 26, 26, 26, 22,
        21,  0,  0,  0,  0, 21,  0,  0,  0,  0, 21,  0, 21,  0,  0,  0,  0,  0, 21,  0,  0,  0,  0, 21,
        21,  0,  0,  0,  0, 21,  0,  0,  0,  0, 21,  0, 21,  0,  0,  0,  0,  0, 21,  0,  0,  0,  0, 21,
        17, 26, 26, 26, 26, 16, 26, 18, 26, 26, 24, 26, 24, 26, 26, 26, 18, 26, 16, 26, 26, 26, 26, 20,
        21,  0,  0,  0,  0, 21,  0, 21,  0,  0,  0,  0,  0,  0,  0,  0, 21,  0, 21,  0,  0,  0,  0, 21,
        21,  0,  0,  0,  0, 21,  0, 21,  0,  0,  0,  0,  0,  0,  0,  0, 21,  0, 21,  0,  0,  0,  0, 21,
        25, 26, 26, 26, 26, 20,  0, 25, 26, 26, 22,  0, 19, 26, 26, 26, 28,  0, 17, 26, 26, 26, 26, 28,
        1,  0,  0,  0,  0, 21,  0,  0,  0,  0, 21,  0, 21,  0,  0,  0,  0,  0, 21,  0,  0,  0,  0,  4,
        1,  0,  0,  0,  0, 21,  0,  0,  0,  0, 21,  0, 21,  0,  0,  0,  0,  0, 21,  0,  0,  0,  0,  4,
        1,  0,  0,  0,  0, 21,  0, 19, 26, 26, 24, 26, 24, 26, 26, 26, 22,  0, 21,  0,  0,  0,  0,  4,
        1,  0,  0,  0,  0, 21,  0, 21,  3,  2,  2,  9,  12,  2,  2,  6, 21,  0, 21,  0,  0,  0,  0,  4,
        1,  0,  0,  0,  0, 17, 26, 20,  1,  0,  0,  9, 12,  0,  0,  4, 17, 26, 20,  0,  0,  0,  0,  4,
        1,  0,  0,  0,  0, 21,  0, 21,  1,  0,  0,  0,  0,  0,  0,  4, 21,  0, 21,  0,  0,  0,  0,  4,
        1,  0,  0,  0,  0, 21,  0, 21,  9,  8,  8,  8,  8,  8,  8, 12, 21,  0, 21,  0,  0,  0,  0,  4,
        1,  0,  0,  0,  0, 21,  0, 17, 26, 26, 26, 26, 26, 26, 26, 26, 20,  0, 21,  0,  0,  0,  0,  4,
        1,  0,  0,  0,  0, 21,  0, 21,  0,  0,  0,  0,  0,  0,  0,  0, 21,  0, 21,  0,  0,  0,  0,  4,
        19, 26, 26, 26, 26, 16, 26, 24, 26, 26, 22,  0, 19, 26, 26, 26, 16, 26, 24, 26, 26, 26, 26, 22,
        21,  0,  0,  0,  0, 21,  0,  0,  0,  0, 21,  0, 21,  0,  0,  0, 21,  0,  0,  0,  0,  0,  0, 21,
        21,  0,  0,  0,  0, 21,  0,  0,  0,  0, 21,  0, 21,  0,  0,  0, 21,  0,  0,  0,  0,  0,  0, 21,
        17, 26, 26, 22,  0, 21,  0,  0,  0,  0, 21,  0, 21,  0,  0,  0, 21,  0,  0, 19, 26, 26, 26, 20,
        21,  0,  0, 21,  0, 17, 26, 26, 26, 26, 16, 26, 16, 26, 26, 26, 20,  0,  0, 21,  0,  0,  0, 21,
        21,  0,  0, 21,  0, 21,  0,  0,  0,  0, 21,  0, 21,  0,  0,  0, 21,  0,  0, 21,  0,  0,  0, 21,
        21,  0,  0, 21,  0, 21,  0,  0,  0,  0, 21,  0, 21,  0,  0,  0, 21,  0,  0, 21,  0,  0,  0, 21,
        25, 26, 26, 24, 26, 24, 26, 26, 26, 26, 24, 26, 24, 26, 26, 26, 24, 26, 26, 24, 26, 26, 26, 28

    };
    private final int backupMaze[] = Arrays.copyOf(levelData, levelData.length);

    Board()//ok
    {
        loadImages();
        mazeColor = new Color(49, 2, 100);
        d = new Dimension(2500, 2500);
        timer = new Timer(40, this);
        timer.start();

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.black);
        setDoubleBuffered(true);

        pacman = new Pacman();
        pacman.start();

        arbitro = new Arbitro(this, pacman, ghostBlue);

        ghostBlue = new GhostBlue();
        Thread threadBlue = new Thread(ghostBlue);
        threadBlue.start();

        ghostRed = new GhostRed();
        Thread threadRed = new Thread(ghostRed);
        threadRed.start();

        ghostYellow = new GhostYellow();
        Thread threadYellow = new Thread(ghostYellow);
        threadYellow.start();

        ghostPink = new GhostPink();
        Thread threadPink = new Thread(ghostPink);
        threadPink.start();
    }

    public int[] getLevelData()
    {
        return levelData;
    }

    private void loadImages()
    {
        imageBlue = new ImageIcon("imagens/ghost.png").getImage();
        imageRed = new ImageIcon("imagens/ghostRed.png").getImage();
        imagePink = new ImageIcon("imagens/ghostPink.png").getImage();
        imageYellow = new ImageIcon("imagens/ghostYellow.png").getImage();
        pacman1 = new ImageIcon("imagens/PacMan.png").getImage();
        pacman2up = new ImageIcon("imagens/PacManUp.png").getImage();
        pacman3up = new ImageIcon("imagens/PacManUpMeio.png").getImage();
        pacman4up = new ImageIcon("imagens/PacManFechado.png").getImage();
        pacman2down = new ImageIcon("imagens/PacManDown.png").getImage();
        pacman3down = new ImageIcon("imagens/PacManDownMeio.png").getImage();
        pacman4down = new ImageIcon("imagens/PacManFechado.png").getImage();
        pacman2left = new ImageIcon("imagens/PacManLeft.png").getImage();
        pacman3left = new ImageIcon("imagens/PacManLeftMeio.png").getImage();
        pacman4left = new ImageIcon("imagens/PacManFechado.png").getImage();
        pacman2right = new ImageIcon("imagens/PacMan.png").getImage();
        pacman3right = new ImageIcon("imagens/PacManMeio.png").getImage();
        pacman4right = new ImageIcon("imagens/PacManFechado.png").getImage();
    }

    private void initGame()//ok
    {
        pacsLeft = 3;
        score = 0;
        continueLevel();
    }

    private void continueLevel()//ok
    {

        pacman.setPacman_x(11 * BLOCK_SIZE);
        pacman.setPacman_y(14 * BLOCK_SIZE);
        pacman.setPacman_dx(0);
        pacman.setPacman_dy(0);
        pacman.setReq_dx(0);
        pacman.setReq_dy(0);

        ghostBlue.setGhost_x(11 * BLOCK_SIZE);
        ghostBlue.setGhost_y(10 * BLOCK_SIZE);
        ghostBlue.setGhost_dx(0);
        ghostBlue.setGhost_dy(1);

        ghostRed.setGhost_x(12 * BLOCK_SIZE);
        ghostRed.setGhost_y(10 * BLOCK_SIZE);
        ghostRed.setGhost_dx(0);
        ghostRed.setGhost_dy(1);

        ghostPink.setGhost_x(12 * BLOCK_SIZE);
        ghostPink.setGhost_y(10 * BLOCK_SIZE);
        ghostPink.setGhost_dx(0);
        ghostPink.setGhost_dy(1);

        ghostYellow.setGhost_x(12 * BLOCK_SIZE);
        ghostYellow.setGhost_y(11 * BLOCK_SIZE);
        ghostYellow.setGhost_dx(0);
        ghostYellow.setGhost_dy(1);

        if(levelAtual < 4)
        {
            ghostBlue.setGhost_speed(levelAtual*2);
            ghostRed.setGhost_speed(levelAtual*2);
            ghostPink.setGhost_speed(levelAtual*2);
            ghostYellow.setGhost_speed(levelAtual*2);
        }

        else
        {
            ghostBlue.setGhost_speed(6);
            ghostRed.setGhost_speed(6);
            ghostPink.setGhost_speed(6);
            ghostYellow.setGhost_speed(6);
        }

        dying = false;
    }

    private void animationPacman()//ok
    {
        pacAnimCount--;

        if (pacAnimCount <= 0) {
            pacAnimCount = PAC_ANIM_DELAY;
            pacmanAnimPos = pacmanAnimPos + pacAnimDir;

            if (pacmanAnimPos == (PACMAN_ANIM_COUNT - 1) || pacmanAnimPos == 0) {
                pacAnimDir = -pacAnimDir;
            }
        }
    }

    private void showIntroScreen(Graphics2D g2d)//ok
    {
        g2d.setColor(new Color(0, 32, 48));
        g2d.fillRect(50, SCREEN_SIZE / 2 - 30, SCREEN_SIZE - 100, 50);
        g2d.setColor(Color.white);
        g2d.drawRect(50, SCREEN_SIZE / 2 - 30, SCREEN_SIZE - 100, 50);

        String s = "Press s to start.";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g2d.setColor(Color.white);
        g2d.setFont(small);
        g2d.drawString(s, (SCREEN_SIZE - metr.stringWidth(s)) / 2, SCREEN_SIZE / 2);
    }

    private void drawScore(Graphics2D g)//ok
    {
        int i;
        String s;

        g.setFont(smallFont);
        g.setColor(new Color(26, 28, 255));
        s = "Score: " + score;
        g.drawString(s, SCREEN_SIZE / 2 + 96, SCREEN_SIZE + 16);

        for (i = 0; i < pacsLeft; i++)
        {
            g.drawImage(pacman3left, i * 28 + 8, SCREEN_SIZE + 1, this);
        }
    }

    private void checkMaze()//ok
    {
        short i = 0;
        boolean finished = true;

        while (i < N_BLOCKS * N_BLOCKS && finished)
        {
            if ((levelData[i] & 16) != 0)
            {
                finished = false;
            }
            i++;
        }

        if (finished)
        {
            if(levelAtual < 7)
                levelAtual++;

            levelData = Arrays.copyOf(backupMaze, backupMaze.length);
            continueLevel();
        }
    }

    private void drawMaze(Graphics2D g2d)//ok
    {
        short i = 0;
        int x, y;

        for (y = 0; y < SCREEN_SIZE; y += BLOCK_SIZE)
        {
            for (x = 0; x < SCREEN_SIZE; x += BLOCK_SIZE)
            {
                g2d.setColor(mazeColor);
                g2d.setStroke(new BasicStroke(2));

                if ((levelData[i] & 1) != 0)
                {
                    g2d.drawLine(x, y, x, y + BLOCK_SIZE - 1);
                }

                if ((levelData[i] & 2) != 0)
                {
                    g2d.drawLine(x, y, x + BLOCK_SIZE - 1, y);
                }

                if ((levelData[i] & 4) != 0)
                {
                    g2d.drawLine(x + BLOCK_SIZE - 1, y, x + BLOCK_SIZE - 1, y + BLOCK_SIZE - 1);
                }

                if ((levelData[i] & 8) != 0)
                {
                    g2d.drawLine(x, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1,y + BLOCK_SIZE - 1);
                }

                if ((levelData[i] & 16) != 0)
                {
                    g2d.setColor(dotColor);
                    g2d.fillRect(x + 11, y + 11, 2, 2);
                }

                i++;
            }
        }
    }

    private void death()
    {
        pacsLeft--;

        if (pacsLeft == 0)
        {
            levelData = Arrays.copyOf(backupMaze, backupMaze.length);
            inGame = false;
        }

        dying = false;
        continueLevel();
    }

    private void playGame(Graphics2D g2d)//ok
    {
        arbitro.verificaMorte(pacman, ghostBlue, ghostRed, ghostPink, ghostYellow);
        if (dying)
        {
            death();
        }
        else
        {
            this.geraSinalPacman(this);
            pacman.setWait(false);
            this.drawPacman(g2d, this);

            ghostBlue.setWait(false);
            drawGhost(g2d, imageBlue, ghostBlue.getGhost_x() + 1, ghostBlue.getGhost_y() + 1, this);

            ghostRed.setWait(false);
            drawGhost(g2d, imageRed, ghostRed.getGhost_x() + 1, ghostRed.getGhost_y() + 1, this);

            ghostPink.setWait(false);
            drawGhost(g2d, imagePink, ghostPink.getGhost_x() + 1, ghostPink.getGhost_y() + 1, this);

            ghostYellow.setWait(false);
            drawGhost(g2d, imageYellow, ghostYellow.getGhost_x() + 1, ghostYellow.getGhost_y() + 1, this);

            checkMaze();
        }
    }

    public void drawGhost(Graphics2D g2d, Image ghost, int x, int y, Board board)
    {
        g2d.drawImage(ghost, x, y, board);
    }

    private void doDrawing(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, d.width, d.height);

        drawMaze(g2d);
        drawScore(g2d);
        animationPacman();
        if (inGame)
        {
            playGame(g2d);
        }
        else
        {
            showIntroScreen(g2d);
        }

        g2d.drawImage(ii, 5, 5, this);
        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }

    @Override
    public void addNotify()//ok
    {
        super.addNotify();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        doDrawing(g);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        repaint();
    }

    class TAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)//envia o sinal de pra onde quer andar para o pacman
        {
            int key = e.getKeyCode();

            if(inGame)
            {
                if(key == KeyEvent.VK_LEFT)
                {
                    pacman.setReq_dx(-1);
                    pacman.setReq_dy(0);
                } else if(key == KeyEvent.VK_RIGHT)
                {
                    pacman.setReq_dx(1);
                    pacman.setReq_dy(0);
                } else if(key == KeyEvent.VK_UP)
                {
                    pacman.setReq_dx(0);
                    pacman.setReq_dy(-1);
                } else if(key == KeyEvent.VK_DOWN)
                {
                    pacman.setReq_dx(0);
                    pacman.setReq_dy(1);
                } else if(key == KeyEvent.VK_ESCAPE && timer.isRunning())
                {
                    inGame = false;
                    levelAtual = 1;
                    levelData = Arrays.copyOf(backupMaze, backupMaze.length);
                }else if(key == KeyEvent.VK_L)
                {
                    inGame = false;
                    levelAtual++;
                    levelData = Arrays.copyOf(backupMaze, backupMaze.length);
                } else if(key == KeyEvent.VK_P)
                {
                    if(timer.isRunning())
                    {
                        timer.stop();
                    } else
                    {
                        timer.start();
                    }
                }
            } else
            {
                if(key == 's' || key == 'S')
                {
                    inGame = true;
                    initGame();
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e)
        {
            int key = e.getKeyCode();

            if(key == Event.LEFT || key == Event.RIGHT || key == Event.UP || key == Event.DOWN)
            {
                pacman.setReq_dx(0);
                pacman.setReq_dy(0);
            }
        }

    }

    private void geraSinalPacman(Board mapa)
    {
        if(pacman.getReq_dx() == -pacman.getPacman_dx() && pacman.getReq_dy() == -pacman.getPacman_dy())
        {
            pacman.setPacman_dx(pacman.getReq_dx());
            pacman.setPacman_dy(pacman.getReq_dy());
        }

        boolean[] sinal = new boolean[4];

        int pos = pacman.getPacman_x() / Board.BLOCK_SIZE + Board.N_BLOCKS * (int) (pacman.getPacman_y() / Board.BLOCK_SIZE);
        int ch = mapa.levelData[pos];

        if(pacman.getReq_dx() != 0 || pacman.getReq_dy() != 0)
        {
            if((ch & 1) != 0)
                sinal[0] = true;
            if((ch& 2) != 0)
                sinal[1] = true;
            if((ch & 4) != 0)
                sinal[2] = true;
            if((ch & 8) != 0)
                sinal[3] = true;
        }

        pacman.setRedondeza(sinal);
    }

    public void drawPacman(Graphics2D g2d, Board board)
    {
        //adiciona score quando o pacman come
        int pos = pacman.getPacman_x() / board.BLOCK_SIZE + board.N_BLOCKS * (int) (pacman.getPacman_y() / board.BLOCK_SIZE);
        int ch = board.levelData[pos];

        if((ch & 16) != 0)
        {
            board.levelData[pos] = (ch & 15);
            board.score++;
        }

        if (pacman.getPacman_dx() == -1)
        {
            drawPacmanLeft(g2d, board);
        }
        else if(pacman.getPacman_dy() == 1)
        {
            drawPacmanDown(g2d, board);
        }
        else if(pacman.getPacman_dy() == -1)
        {
            drawPacmanUp(g2d, board);
        }
        else
        {
            drawPacmanRight(g2d, board);
        }
    }

    public void drawPacmanUp(Graphics2D g2d, Board board)
    {
        switch (pacmanAnimPos)
        {
            case 1:
                g2d.drawImage(pacman2up, pacman.getPacman_x() + 1, pacman.getPacman_y() + 1, board);
                break;

            case 2:
                g2d.drawImage(pacman3up, pacman.getPacman_x() + 1, pacman.getPacman_y() + 1, board);
                break;

            case 3:
                g2d.drawImage(pacman4up, pacman.getPacman_x() + 1, pacman.getPacman_y() + 1, board);
                break;

            default:
                g2d.drawImage(pacman2up, pacman.getPacman_x() + 1, pacman.getPacman_y() + 1, board);
                break;
        }
    }

    private void drawPacmanDown(Graphics2D g2d, Board board)
    {
        switch (pacmanAnimPos)
        {
            case 1:
                g2d.drawImage(pacman2down, pacman.getPacman_x() + 1, pacman.getPacman_y() + 1, board);
                break;

            case 2:
                g2d.drawImage(pacman3down, pacman.getPacman_x() + 1, pacman.getPacman_y() + 1, board);
                break;

            case 3:
                g2d.drawImage(pacman4down, pacman.getPacman_x() + 1, pacman.getPacman_y() + 1, board);
                break;

            default:
                g2d.drawImage(pacman2down, pacman.getPacman_x() + 1, pacman.getPacman_y() + 1, board);
                break;
        }
    }

    private void drawPacmanLeft(Graphics2D g2d, Board board)
    {
        switch (pacmanAnimPos)
        {
            case 1:
                g2d.drawImage(pacman2left, pacman.getPacman_x() + 1, pacman.getPacman_y() + 1, board);
                break;

            case 2:
                g2d.drawImage(pacman3left, pacman.getPacman_x() + 1, pacman.getPacman_y() + 1, board);
                break;

            case 3:
                g2d.drawImage(pacman4left, pacman.getPacman_x() + 1, pacman.getPacman_y() + 1, board);
                break;

            default:
                g2d.drawImage(pacman2left, pacman.getPacman_x() + 1, pacman.getPacman_y() + 1, board);
                break;
        }
    }

    private void drawPacmanRight(Graphics2D g2d, Board board)
    {
        switch (pacmanAnimPos)
        {
            case 1:
                g2d.drawImage(pacman2right, pacman.getPacman_x() + 1, pacman.getPacman_y() + 1, board);
                break;

            case 2:
                g2d.drawImage(pacman3right, pacman.getPacman_x() + 1, pacman.getPacman_y() + 1, board);
                break;

            case 3:
                g2d.drawImage(pacman4right, pacman.getPacman_x() + 1, pacman.getPacman_y() + 1, board);
                break;

            default:
                g2d.drawImage(pacman2right, pacman.getPacman_x() + 1, pacman.getPacman_y() + 1, board);
                break;
        }
    }
}
