import javax.sound.sampled.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {
    private long currentFrame;
    private Clip clip;
    private String status;
    private AudioInputStream audioInputStream;
    private String filePath;
    private int loopState;

    public AudioPlayer(String file) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        filePath=file;
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        loopState = 0;
        status = "play";
    }

    public void play(){
        if(loopState == 0){
            clip.start();
        } else{
            clip.loop(loopState);
        }

        status = "play";
    }

    public void pause(){
        if(status.equals("pause")){
            System.out.println("Already Paused");
            return;
        }
        this.currentFrame = getCurrentFrame();
        clip.stop();
        status = "pause";
    }

    public void resume() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if(status.equals("play")){
            System.out.println("Already Played");
            return;
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
        if(point >= 0 && point < clip.getMicrosecondLength()){
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
    }

    public long getCurrentFrame() {
        return  clip.getMicrosecondLength() > 0 ? clip.getMicrosecondPosition() % clip.getMicrosecondLength() : 0;
    }

    public long getDuration(){
        return clip.getMicrosecondLength();
    }
    public void changeFilePath(String filePath){
        this.filePath = filePath;
    }

    public float getVolume() {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        return (float) Math.pow(10f, gainControl.getValue() / 20f);
    }

    public void setVolume(float volume) {
        if (volume < 0f || volume > 1f)
            throw new IllegalArgumentException("Volume not valid: " + volume);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
    }

    public void addLineListener(LineListener listener){
        clip.addLineListener(listener);
    }

    public int getLoopState(){
        return loopState;
    }

    public void toggleLoop(){
        switch(loopState){
            case 0:
                loopState = 1;
                break;
            case 1:
                loopState = -1;
                break;
            case -1:
                loopState = 0;
                break;
        }

        if(status.equals("play")){
            clip.loop(loopState);
        }
    }
    public void rewind(){
        clip.setMicrosecondPosition(0);
        currentFrame = 0L;
        status = "pause";
    }

    public boolean isAtEnd(){
        return clip.getMicrosecondPosition() >= clip.getMicrosecondLength();
    }
}
