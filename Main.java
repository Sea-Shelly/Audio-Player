import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        AudioPlayer player = new AudioPlayer("Linkin Park - Numb (Lyrics) 4.wav");
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("Choice: ");
            int choice = input.nextInt();
            switch (choice){
                case 1:
                    player.play();
                    break;
                case 2:
                    player.stop();
                    break;
                case 3:
                    player.resume();
                    break;
                case 4:
                    player.jump(110000000L);
                    break;
                case 5:
                    player.pause();
                    break;
                case 6:
                    player.restart();
                    break;


            }
        }
    }
}