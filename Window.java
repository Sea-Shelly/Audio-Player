import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Window extends JFrame{
    private AudioPlayer player;
    private JButton play;
    private JButton pause;
    private JButton restart;
    private JSlider timeOfSong;

    public Window() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        super("A Really Cool Music Player");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        player = new AudioPlayer("Linkin Park - Numb (Lyrics) 4.wav");

        play = new JButton("Play");
        pause = new JButton("Pause");
        restart = new JButton("Restart");
        timeOfSong = new JSlider(0,100,0);

        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.play();
            }
        });
        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.pause();
            }
        });
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    player.restart();
                } catch (Exception ex){
                    System.out.println(ex.getMessage());
                }

            }
        });

        play.setBounds(50, 400, 100, 50);
        pause.setBounds(200, 400, 100, 50);
        restart.setBounds(350, 400, 100, 50);


        add(play);
        add(pause);
        add(restart);



        setSize(800, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
