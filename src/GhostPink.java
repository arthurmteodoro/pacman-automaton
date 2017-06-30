/**
 * Arthur Alexsander Martins Teodoro - 0022427
 * Saulo Ricardo Dias Fernandes - 0021581
 */
public class GhostPink extends Ghosts implements Runnable
{
    GhostPink()
    {
        super();
    }

    @Override
    public void move()
    {
        Arbitro.moveAleatorio(this);
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
