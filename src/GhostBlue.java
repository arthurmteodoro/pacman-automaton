/**
 * Arthur Alexsander Martins Teodoro - 0022427
 * Saulo Ricardo Dias Fernandes - 0021581
 */
public class GhostBlue extends Ghosts implements Runnable
{
    GhostBlue()
    {
        super();
    }

    @Override
    public void move()
    {
        boolean[] palavra;
        palavra = Arbitro.verificaProximidade(this);

        if (this.getGhost_x() % Board.BLOCK_SIZE == 0 && this.getGhost_y() % Board.BLOCK_SIZE == 0)
        {
            int estado;
            estado = this.automato(palavra);

            int pacmanX = Arbitro.getPosicaoPacman()[0];
            int pacmanY = Arbitro.getPosicaoPacman()[1];

            if(pacmanX <= this.getGhost_x() && pacmanY <= this.getGhost_y())
            {
                switch(estado)
                {
                    case -1:
                        if((this.getGhost_x()-pacmanX) > (this.getGhost_y()-pacmanY))
                        {
                            this.setGhost_dx(-1);
                            this.setGhost_dy(0);
                        }
                        else
                        {
                            this.setGhost_dx(0);
                            this.setGhost_dy(-1);
                        }
                        break;

                    case 0:
                        this.setGhost_dx(0);
                        this.setGhost_dy(-1);
                        break;

                    case 1:
                        this.setGhost_dx(-1);
                        this.setGhost_dy(0);
                        break;

                    case 2:
                        this.setGhost_dx(1);
                        this.setGhost_dy(0);
                        break;

                    case 3:
                        if((this.getGhost_x()-pacmanX) > (this.getGhost_y()-pacmanY))
                        {
                            this.setGhost_dx(-1);
                            this.setGhost_dy(0);
                        }
                        else
                        {
                            this.setGhost_dx(0);
                            this.setGhost_dy(-1);
                        }
                        break;

                    case 4:
                        this.setGhost_dx(0);
                        this.setGhost_dy(-1);
                        break;

                    case 5:
                        this.setGhost_dx(-1);
                        this.setGhost_dy(0);
                        break;

                    case 6:
                        if((this.getGhost_x()-pacmanX) > (this.getGhost_y()-pacmanY))
                        {
                            this.setGhost_dx(-1);
                            this.setGhost_dy(0);
                        }
                        else
                        {
                            this.setGhost_dx(0);
                            this.setGhost_dy(-1);
                        }
                        break;

                    case 7:
                        this.setGhost_dx(0);
                        this.setGhost_dy(-1);
                        break;

                    case 8:
                        this.setGhost_dx(-1);
                        this.setGhost_dy(0);
                        break;

                    case 9:
                        if((this.getGhost_x()-pacmanX) > (this.getGhost_y()-pacmanY))
                        {
                            this.setGhost_dx(-1);
                            this.setGhost_dy(0);
                        }
                        else
                        {
                            this.setGhost_dx(0);
                            this.setGhost_dy(-1);
                        }
                        break;
                }
            }

            if(pacmanX <= this.getGhost_x() && pacmanY > this.getGhost_y())
            {
                switch(estado)
                {
                    case -1:
                        if((this.getGhost_x()-pacmanX) > (pacmanY-this.getGhost_y()))
                        {
                            this.setGhost_dx(-1);
                            this.setGhost_dy(0);
                        }
                        else
                        {
                            this.setGhost_dx(0);
                            this.setGhost_dy(1);
                        }
                        break;

                    case 0:
                        this.setGhost_dx(0);
                        this.setGhost_dy(1);
                        break;

                    case 1:
                        if((this.getGhost_x()-pacmanX) > (pacmanY-this.getGhost_y()))
                        {
                            this.setGhost_dx(-1);
                            this.setGhost_dy(0);
                        }
                        else
                        {
                            this.setGhost_dx(0);
                            this.setGhost_dy(1);
                        }
                        break;

                    case 2:
                        this.setGhost_dx(0);
                        this.setGhost_dy(1);
                        break;

                    case 3:
                        if((this.getGhost_x()-pacmanX) > (pacmanY-this.getGhost_y()))
                        {
                            this.setGhost_dx(-1);
                            this.setGhost_dy(0);
                        }
                        else
                        {
                            this.setGhost_dx(0);
                            this.setGhost_dy(1);
                        }
                        break;

                    case 4:
                        this.setGhost_dx(0);
                        this.setGhost_dy(1);
                        break;

                    case 5:
                        if((this.getGhost_x()-pacmanX) > (pacmanY-this.getGhost_y()))
                        {
                            this.setGhost_dx(-1);
                            this.setGhost_dy(0);
                        }
                        else
                        {
                            this.setGhost_dx(0);
                            this.setGhost_dy(1);
                        }
                        break;

                    case 6:
                        this.setGhost_dx(-1);
                        this.setGhost_dy(0);
                        break;

                    case 7:
                        this.setGhost_dx(0);
                        this.setGhost_dy(-1);
                        break;

                    case 8:
                        this.setGhost_dx(-1);
                        this.setGhost_dy(0);
                        break;

                    case 9:
                        this.setGhost_dx(-1);
                        this.setGhost_dy(0);
                        break;
                }
            }

            if(pacmanX > this.getGhost_x() && pacmanY <= this.getGhost_y())
            {
                switch(estado)
                {
                    case -1:
                        if((pacmanX - this.getGhost_x()) > (this.getGhost_y()-pacmanY))
                        {
                            this.setGhost_dx(1);
                            this.setGhost_dy(0);
                        }
                        else
                        {
                            this.setGhost_dx(0);
                            this.setGhost_dy(-1);
                        }
                        break;

                    case 0:
                        if((pacmanX - this.getGhost_x()) > (this.getGhost_y()-pacmanY))
                        {
                            this.setGhost_dx(1);
                            this.setGhost_dy(0);
                        }
                        else
                        {
                            this.setGhost_dx(0);
                            this.setGhost_dy(-1);
                        }
                        break;

                    case 1:
                        this.setGhost_dx(1);
                        this.setGhost_dy(0);
                        break;

                    case 2:
                        this.setGhost_dx(1);
                        this.setGhost_dy(0);
                        break;

                    case 3:
                        this.setGhost_dx(0);
                        this.setGhost_dy(-1);
                        break;

                    case 4:
                        this.setGhost_dx(0);
                        this.setGhost_dy(-1);
                        break;

                    case 5:
                        this.setGhost_dx(0);
                        this.setGhost_dy(1);
                        break;

                    case 6:
                        if((pacmanX - this.getGhost_x()) > (this.getGhost_y()-pacmanY))
                        {
                            this.setGhost_dx(1);
                            this.setGhost_dy(0);
                        }
                        else
                        {
                            this.setGhost_dx(0);
                            this.setGhost_dy(-1);
                        }
                        break;

                    case 7:
                        if((pacmanX - this.getGhost_x()) > (this.getGhost_y()-pacmanY))
                        {
                            this.setGhost_dx(1);
                            this.setGhost_dy(0);
                        }
                        else
                        {
                            this.setGhost_dx(0);
                            this.setGhost_dy(-1);
                        }
                        break;

                    case 8:
                        this.setGhost_dx(1);
                        this.setGhost_dy(0);
                        break;

                    case 9:
                        this.setGhost_dx(0);
                        this.setGhost_dy(-1);
                        break;
                }
            }

            if(pacmanX > this.getGhost_x() && pacmanY > this.getGhost_y())
            {
                switch(estado)
                {
                    case -1:
                        if((pacmanX - this.getGhost_x()) > (pacmanY-this.getGhost_y()))
                        {
                            this.setGhost_dx(1);
                            this.setGhost_dy(0);
                        }
                        else
                        {
                            this.setGhost_dx(0);
                            this.setGhost_dy(1);
                        }
                        break;

                    case 0:
                        if((pacmanX - this.getGhost_x()) > (pacmanY-this.getGhost_y()))
                        {
                            this.setGhost_dx(1);
                            this.setGhost_dy(0);
                        }
                        else
                        {
                            this.setGhost_dx(0);
                            this.setGhost_dy(1);
                        }
                        break;

                    case 1:
                        if((pacmanX - this.getGhost_x()) > (pacmanY-this.getGhost_y()))
                        {
                            this.setGhost_dx(1);
                            this.setGhost_dy(0);
                        }
                        else
                        {
                            this.setGhost_dx(0);
                            this.setGhost_dy(1);
                        }
                        break;

                    case 2:
                        if((pacmanX - this.getGhost_x()) > (pacmanY-this.getGhost_y()))
                        {
                            this.setGhost_dx(1);
                            this.setGhost_dy(0);
                        }
                        else
                        {
                            this.setGhost_dx(0);
                            this.setGhost_dy(1);
                        }
                        break;

                    case 3:
                        this.setGhost_dx(0);
                        this.setGhost_dy(1);
                        break;

                    case 4:
                        this.setGhost_dx(0);
                        this.setGhost_dy(1);
                        break;

                    case 5:
                        this.setGhost_dx(0);
                        this.setGhost_dy(1);
                        break;

                    case 6:
                        this.setGhost_dx(1);
                        this.setGhost_dy(0);
                        break;

                    case 7:
                        this.setGhost_dx(1);
                        this.setGhost_dy(0);
                        break;

                    case 8:
                        this.setGhost_dx(1);
                        this.setGhost_dy(0);
                        break;

                    case 9:
                        this.setGhost_dx(0);
                        this.setGhost_dy(-1);
                        break;
                }
            }
        }

        this.setGhost_x(this.getGhost_x() + this.getGhost_dx() * this.getGhost_speed());
        this.setGhost_y(this.getGhost_y() + this.getGhost_dy() * this.getGhost_speed());

        this.setWait(true);
    }

    @Override
    public void run()
    {
        while(true)
        {
            if(!this.getWait())
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
