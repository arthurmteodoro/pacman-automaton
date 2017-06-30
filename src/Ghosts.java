/**
 * Arthur Alexsander Martins Teodoro - 0022427
 * Saulo Ricardo Dias Fernandes - 0021581
 */
public abstract class Ghosts
{

    private int ghost_x;
    private int ghost_y;
    private int ghost_dx;
    private int ghost_dy;
    private int ghost_speed;

    private boolean wait;

    public Ghosts()
    {
        this.ghost_x = 50;
        this.ghost_y = 50;
        this.ghost_dx = 50;
        this.ghost_dy = 50;
        this.ghost_speed = 2;

        this.wait = true;
    }

    synchronized public int getGhost_x()
    {
        return ghost_x;
    }

    synchronized public void setGhost_x(int ghost_x)
    {
        this.ghost_x = ghost_x;
    }

    synchronized public int getGhost_y()
    {
        return ghost_y;
    }

    synchronized public void setGhost_y(int ghost_y)
    {
        this.ghost_y = ghost_y;
    }

    synchronized public int getGhost_dx()
    {
        return ghost_dx;
    }

    synchronized public void setGhost_dx(int ghost_dx)
    {
        this.ghost_dx = ghost_dx;
    }

    synchronized public int getGhost_dy()
    {
        return ghost_dy;
    }

    synchronized public void setGhost_dy(int ghost_dy)
    {
        this.ghost_dy = ghost_dy;
    }

    synchronized public int getGhost_speed()
    {
        return ghost_speed;
    }

    synchronized public void setGhost_speed(int ghost_speed)
    {
        this.ghost_speed = ghost_speed;
    }

    synchronized public void setWait(boolean wait)
    {
        this.wait = wait;
    }

    synchronized public boolean getWait()
    {
        return this.wait;
    }

    synchronized public int automato(boolean palavra[])
    {
        int estado = -1;

        if (palavra[0])
        {
            estado = 0; //só nao vai para a esquerda.
        }

        if (palavra[1])
        {
            if(estado == -1)
            {
                estado = 1; //só nao vai para a cima.
            }

            else estado = 2; //nao vai para a esquerda e para cima
        }

        if (palavra[2])
        {
            if(estado == -1)
            {
                estado = 3; //só nao vai para a direita.
            }

            else if(estado == 0)
            {
                estado = 4; //nao vai para a esquerda e para a direita
            }

            else if(estado == 1)
            {
                estado = 5; //nao vai para cima e para a direita
            }
        }

        if (palavra[3])
        {
            if(estado == -1)
            {
                estado = 6; //só nao vai para baixo
            }

            else if(estado == 0)
            {
                estado = 7; //nao vai para a esquerda e para baixo
            }

            else if(estado == 1)
            {
                estado = 8; //nao vai para a cima e para baixo
            }

            else if(estado == 3)
            {
                estado = 9; //nao vai para a direita e para baixo
            }
        }

        return estado;
    }

    public abstract void move();
}
