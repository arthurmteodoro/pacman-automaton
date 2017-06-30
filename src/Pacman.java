/**
 * Arthur Alexsander Martins Teodoro - 0022427
 * Saulo Ricardo Dias Fernandes - 0021581
 */
public class Pacman extends Thread
{
    private int pacman_x;
    private int pacman_y;
    private int pacman_dx;
    private int pacman_dy;
    private int req_dx;
    private int req_dy;

    private boolean[] redondeza;
    private boolean wait;

    Pacman()
    {
        this.pacman_x = 0;
        this.pacman_y = 0;
        this.pacman_dx = 0;
        this.pacman_dy = 0;
        this.req_dx = 0;
        this.req_dy = 0;

        this.wait = true;
    }

    Pacman(int pacman_x, int pacman_y, int pacman_dx, int pacman_dy, int req_dx, int req_dy,
                                    Boolean okSinal)
    {
        this.pacman_x = pacman_x;
        this.pacman_y = pacman_y;
        this.pacman_dx = pacman_dx;
        this.pacman_dy = pacman_dy;
        this.req_dx = req_dx;
        this.req_dy = req_dy;

        this.wait = true;
    }

    synchronized public int getReq_dx()
    {
        return req_dx;
    }

    synchronized public void setReq_dx(int req_dx)
    {
        this.req_dx = req_dx;
    }

    synchronized public int getReq_dy()
    {
        return req_dy;
    }

    synchronized public void setReq_dy(int req_dy)
    {
        this.req_dy = req_dy;
    }

    synchronized public int getPacman_x()
    {
        return pacman_x;
    }

    synchronized public void setPacman_x(int pacman_x)
    {
        this.pacman_x = pacman_x;
    }

    synchronized public int getPacman_y()
    {
        return pacman_y;
    }

    synchronized public void setPacman_y(int pacman_y)
    {
        this.pacman_y = pacman_y;
    }

    synchronized public int getPacman_dx()
    {
        return pacman_dx;
    }

    synchronized public void setPacman_dx(int pacman_dx)
    {
        this.pacman_dx = pacman_dx;
    }

    synchronized public int getPacman_dy()
    {
        return pacman_dy;
    }

    synchronized public void setPacman_dy(int pacman_dy)
    {
        this.pacman_dy = pacman_dy;
    }

    synchronized public void setWait(boolean wait)
    {
        this.wait = wait;
    }

    synchronized public void move()
    {
        if((pacman_x % Board.BLOCK_SIZE == 0) && (pacman_y % Board.BLOCK_SIZE == 0))
        {
            if(this.req_dx == -1 && this.req_dy == 0 && !redondeza[0])
            {
                this.setPacman_dx(this.getReq_dx());
                this.setPacman_dy(this.getReq_dy());
            }
            if(this.req_dx == 0 && this.req_dy == -1 && !redondeza[1])
            {
                this.setPacman_dx(this.getReq_dx());
                this.setPacman_dy(this.getReq_dy());
            }
            if(this.req_dx == 1 && this.req_dy == 0 && !redondeza[2])
            {
                this.setPacman_dx(this.getReq_dx());
                this.setPacman_dy(this.getReq_dy());
            }
            if(this.req_dx == 0 && this.req_dy == 1 && !redondeza[3])
            {
                this.setPacman_dx(this.getReq_dx());
                this.setPacman_dy(this.getReq_dy());
            }

            if(this.getPacman_dx() == -1 && this.getPacman_dy() == 0 && redondeza[0])
            {
                this.setPacman_dx(0);
                this.setPacman_dy(0);
            }
            if(this.getPacman_dx() == 0 && this.getPacman_dy() == -1 && redondeza[1])
            {
                this.setPacman_dx(0);
                this.setPacman_dy(0);
            }
            if(this.getPacman_dx() == 1 && this.getPacman_dy() == 0 && redondeza[2])
            {
                this.setPacman_dx(0);
                this.setPacman_dy(0);
            }
            if(this.getPacman_dx() == 0 && this.getPacman_dy() == 1 && redondeza[3])
            {
                this.setPacman_dx(0);
                this.setPacman_dy(0);
            }
        }
        this.pacman_x = (this.pacman_x + Board.PACMAN_SPEED * this.pacman_dx);
        this.pacman_y = (this.pacman_y + Board.PACMAN_SPEED * this.pacman_dy);

        wait = true;
    }

    synchronized public void setRedondeza(boolean[] redondeza)
    {
        this.redondeza = redondeza;
    }

    @Override
    public void run()
    {
        while(this.isAlive())
        {
            if(!wait)
            {
                move();
            }
            try
            {
                Thread.sleep(50);
            } catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
