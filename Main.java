import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        JFrame wind = new JFrame();
        wind.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wind.setSize(800, 600);
        wind.setLocationRelativeTo(null);

        AudioSlider slider = new AudioSlider(new AudioPlayer("Linkin Park - Numb (Lyrics) 4.wav"));
        wind.add(slider);
        wind.setVisible(true);


    }
}