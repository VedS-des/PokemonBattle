package audio;

import javax.sound.sampled.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SoundManager {

    private static Clip musicClip;
    private static final List<Clip> soundClips = new ArrayList<>();

    public static void playSound(String fileName) {
        try {

            File file = new File("assets/sounds/" + fileName);

            AudioInputStream audioStream =
                    AudioSystem.getAudioInputStream(file);

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            soundClips.add(clip);

            clip.start();

        } catch (Exception e) {
            System.out.println("Could not play sound: " + fileName);
            e.printStackTrace();
        }
    }

    public static void stopSound() {
        for (Clip clip : soundClips) {
            clip.stop();
            clip.close();
        }

        soundClips.clear();
    }

    public static void playMusic(String fileName) {
        try {
            stopMusic();

            File file = new File("assets/sounds/" + fileName);

            AudioInputStream audioStream =
                    AudioSystem.getAudioInputStream(file);

            musicClip = AudioSystem.getClip();
            musicClip.open(audioStream);

            musicClip.loop(Clip.LOOP_CONTINUOUSLY);
            musicClip.start();

        } catch (Exception e) {
            System.out.println("Could not play music: " + fileName);
            e.printStackTrace();
        }
    }

    public static void stopMusic() {
        if (musicClip != null) {
            musicClip.stop();
            musicClip.close();
            musicClip = null;
        }
    }
}