import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.TimerTask;


public class AudioSlider extends JSlider implements MouseListener{

    private AudioPlayer audioPlayer;
    private Timer timer;
    private boolean isUserDragging;

    public AudioSlider(AudioPlayer audioPlayer) {
        super(JSlider.HORIZONTAL);
        this.audioPlayer = audioPlayer;
        setMaximum((int) audioPlayer.getDuration());
        setMinimum(0);
        addMouseListener(this);

        timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               if(!isUserDragging){
                   setValue((int) audioPlayer.getCurrentFrame());
               }

            }
        });
        timer.start();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        isUserDragging = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isUserDragging = false;
        try {
            audioPlayer.jump(getValue());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
