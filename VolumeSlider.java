import javax.swing.*;
import java.awt.event.*;

public class VolumeSlider extends JSlider implements MouseListener {
    private AudioPlayer audioPlayer;
    private Timer timer;
    private boolean isUserDragging;

    public VolumeSlider(AudioPlayer audioPlayer) {
        super(JSlider.VERTICAL,0,100,100);
        this.audioPlayer = audioPlayer;

        addMouseListener(this);

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
        audioPlayer.setVolume(getValue() / 100f);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
