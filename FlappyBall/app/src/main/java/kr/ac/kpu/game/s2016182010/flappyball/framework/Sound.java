package kr.ac.kpu.game.s2016182010.flappyball.framework;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;

import java.util.HashMap;

import kr.ac.kpu.game.s2016182010.flappyball.R;

public class Sound {
    private static final String TAG = Sound.class.getSimpleName();
    private static SoundPool soundPool;
    private static int[] SOUND_IDS = {R.raw.effect, R.raw.hit};
    private static HashMap<Integer, Integer> soundIdMap = new HashMap<>();

    public static void init(Context context) {
        AudioAttributes audioAttributes;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MOVIE)
                    .build();
            Sound.soundPool = new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes)
                    .setMaxStreams(3)
                    .build();
        } else {
            Sound.soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        }

        for (int resId : SOUND_IDS) {
            int soundId = soundPool.load(context, resId, 1);
            soundIdMap.put(resId,soundId);
        }
    }

    public static int play(int resId){
        Log.d(TAG,"play : " + resId);
        int soundId = soundIdMap.get(resId);
        int streamId = soundPool.play(soundId, 1f, 1f, 1, 0, 1f);
        return streamId;
    }

}
