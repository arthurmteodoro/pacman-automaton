/**
 * Arthur Alexsander Martins Teodoro - 0022427
 * Saulo Ricardo Dias Fernandes - 0021581
 */
public class Arbitro
{

    private static Pacman pacman;
    private static Board board;
    private static Ghosts ghostBlue;

    Arbitro(Board board, Pacman pacman, Ghosts ghostBlue)
    {
        this.board = board;
        this.pacman = pacman;
        this.ghostBlue = ghostBlue;
    }

    public static int[] getPosicaoPacman()
    {
        int[] vector = new int[2];
        vector[0] = pacman.getPacman_x();
        vector[1] = pacman.getPacman_y();

        return vector;
    }

    public static void verificaMorte(Pacman pacman, GhostBlue blue, GhostRed red, GhostPink pink, GhostYellow yellow)
    {
        if (pacman.getPacman_x() > (blue.getGhost_x() - 15)
                && pacman.getPacman_x() < (blue.getGhost_x() + 15)
                && pacman.getPacman_y() > (blue.getGhost_y() - 15)
                && pacman.getPacman_y() < (blue.getGhost_y() + 15)
                && Board.inGame)
        {
            Board.dying = true;
        }
        else if (pacman.getPacman_x() > (red.getGhost_x() - 15)
                && pacman.getPacman_x() < (red.getGhost_x() + 15)
                && pacman.getPacman_y() > (red.getGhost_y() - 15)
                && pacman.getPacman_y() < (red.getGhost_y() + 15)
                && Board.inGame)
        {
            Board.dying = true;
        }

        else if (pacman.getPacman_x() > (pink.getGhost_x() - 15)
                && pacman.getPacman_x() < (pink.getGhost_x() + 15)
                && pacman.getPacman_y() > (pink.getGhost_y() - 15)
                && pacman.getPacman_y() < (pink.getGhost_y() + 15)
                && Board.inGame)
        {
            Board.dying = true;
        }

        else if (pacman.getPacman_x() > (yellow.getGhost_x() - 15)
                && pacman.getPacman_x() < (yellow.getGhost_x() + 15)
                && pacman.getPacman_y() > (yellow.getGhost_y() - 15)
                && pacman.getPacman_y() < (yellow.getGhost_y() + 15)
                && Board.inGame)
        {
            Board.dying = true;
        }
    }

    public static void moveAleatorio(Ghosts ghost)
    {
        int[] dx, dy;
        dx = new int[4];
        dy = new int[4];
        int count = 0;
        int pos;
        pos = ghost.getGhost_x() / Board.BLOCK_SIZE + Board.N_BLOCKS * (int) (ghost.getGhost_y() / Board.BLOCK_SIZE);

        if(ghost.getGhost_x() % Board.BLOCK_SIZE == 0 && ghost.getGhost_y() % Board.BLOCK_SIZE == 0)
        {
            if ((board.getLevelData()[pos] & 1) == 0 && ghost.getGhost_dx() != 1)

            {
                dx[count] = -1;
                dy[count] = 0;
                count++;
            }

            if ((board.getLevelData()[pos] & 2) == 0 && ghost.getGhost_dy() != 1)
            {
                dx[count] = 0;
                dy[count] = -1;
                count++;
            }

            if ((board.getLevelData()[pos] & 4) == 0 && ghost.getGhost_dx() != -1)
            {
                dx[count] = 1;
                dy[count] = 0;
                count++;
            }

            if ((board.getLevelData()[pos] & 8) == 0 && ghost.getGhost_dy() != -1)
            {
                dx[count] = 0;
                dy[count] = 1;
                count++;
            }

            if (count == 0)
            {
                if ((board.getLevelData()[pos] & 15) == 15)
                {
                    ghost.setGhost_dx(0);
                    ghost.setGhost_dy(0);
                }
                else
                {
                    ghost.setGhost_dx(-ghost.getGhost_dy());
                    ghost.setGhost_dy(-ghost.getGhost_y());
                }
            }

            else
            {
                count = (int) (Math.random() * count);

                if(count > 3)
                {
                    count = 3;
                }

                ghost.setGhost_dx(dx[count]);
                ghost.setGhost_dy(dy[count]);
            }
        }
    }

    public static boolean[] verificaProximidade(Ghosts ghost)
    {
        int pos = ghost.getGhost_x() / Board.BLOCK_SIZE + Board.N_BLOCKS * ghost.getGhost_y() / Board.BLOCK_SIZE;

        boolean[] palavra = new boolean[4];

        if((board.getLevelData()[pos] & 1) == 1)
        {
            palavra[0] = true;
        }

        if((board.getLevelData()[pos] & 2) == 2)
        {
            palavra[1] = true;
        }

        if((board.getLevelData()[pos] & 4) == 4)
        {
            palavra[2] = true;
        }

        if((board.getLevelData()[pos] & 8) == 8)
        {
            palavra[3] = true;
        }

        return palavra;
    }
}
