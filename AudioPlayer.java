import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {
    private long currentFrame;
    private Clip clip;
    private String status;
    private AudioInputStream audioInputStream;
    private String filePath;

    public AudioPlayer(String file) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        filePath=file;
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        status = "play";
    }

    public void play(){
        clip.start();
        status = "play";
    }

    public void pause(){
        if(status.equals("pause")){
            System.out.println("Already Paused");
            return;
        }
        this.currentFrame = this.clip.getMicrosecondPosition();
        clip.stop();
        status = "pause";
    }

    public void resume() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if(status.equals("play")){
            System.out.println("Already Played");
        }

        clip.close();
        resetAudioStream();
        clip.setMicrosecondPosition(currentFrame);
        this.play();
    }

    public void restart() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        clip.stop();
        clip.close();
        resetAudioStream();
        currentFrame = 0L;
        clip.setMicrosecondPosition(0);
        this.play();
    }

    public void stop(){
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }

    public void jump(long point) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if(point > 0 && point < clip.getMicrosecondLength()){
            clip.stop();
            clip.close();
            resetAudioStream();
            currentFrame = point;
            clip.setMicrosecondPosition(currentFrame);
            this.play();
        }
    }

    public void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public long getCurrentFrame() {
        return  clip.getMicrosecondPosition();
    }

    public long getDuration(){
        return clip.getMicrosecondLength();
    }
    public void changeFilePath(String filePath){
        this.filePath = filePath;
    }
}
