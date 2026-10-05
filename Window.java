import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Window extends JFrame{
    private AudioPlayer player;
    private JButton play;
    private JButton pause;
    private JButton restart;
    private JButton loop;
    private AudioSlider timeOfSong;
    private VolumeSlider volume;


    public Window() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        super("A Really Cool Music Player");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        player = new AudioPlayer("Linkin Park - Numb (Lyrics) 4.wav");
        player.addLineListener(new LineListener(){

            @Override
            public void update(LineEvent event) {
                if(event.getType() == LineEvent.Type.STOP && player.isAtEnd()){
                    SwingUtilities.invokeLater(()->{
                        try{
                            player.rewind();
                        } catch (Exception ex){
                            System.out.println(ex.getMessage());
                        }
                    });
                }
            }
        });

        play = new JButton("Play");
        pause = new JButton("Pause");
        restart = new JButton("Restart");
        loop = new JButton("Loop");
        timeOfSong = new AudioSlider(player);
        volume = new VolumeSlider(player);

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

        loop.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                player.toggleLoop();
            }
        });

        play.setBounds(50, 100, 100, 50);
        pause.setBounds(200, 100, 100, 50);
        restart.setBounds(350, 100, 100, 50);
        loop.setBounds(500, 100, 100, 50);
        timeOfSong.setBounds(10,450, 650,20);
        volume.setBounds(750, 10,20,450);



        add(play);
        add(pause);
        add(restart);
        add(loop);
        add(timeOfSong);
        add(volume);



        setSize(800, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
