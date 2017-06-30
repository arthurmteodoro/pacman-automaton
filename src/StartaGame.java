import javax.swing.*;

/**
 * Arthur Alexsander Martins Teodoro - 0022427
 * Saulo Ricardo Dias Fernandes - 0021581
 */
public class StartaGame extends JFrame
{
    StartaGame()
    {
        add(new Board());
        setTitle("Pacman");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(575, 625);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
