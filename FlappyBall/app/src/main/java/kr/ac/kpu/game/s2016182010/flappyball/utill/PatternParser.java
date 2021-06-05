package kr.ac.kpu.game.s2016182010.flappyball.utill;

import android.util.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import kr.ac.kpu.game.s2016182010.flappyball.R;
import kr.ac.kpu.game.s2016182010.flappyball.framework.GameView;
import kr.ac.kpu.game.s2016182010.flappyball.game.Block;
import kr.ac.kpu.game.s2016182010.flappyball.game.Pattern;
import kr.ac.kpu.game.s2016182010.flappyball.game.PatternBlock;

public class PatternParser {
    private static ArrayList<Pattern> cache;
    public ArrayList<Pattern> read() throws JSONException {
        if(cache != null) return cache;
        String text = getJsonText();
        cache = parse(text);
        return cache;
    }

    private String getJsonText() {
        StringBuilder jsonBuilder = new StringBuilder();
        InputStream inputStream = GameView.instance.getContext().getResources().openRawResource(R.raw.pattern);
        InputStreamReader isr = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(isr);
        while(true) {
            String line = null;
            try {
                line = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(line == null) break;
            jsonBuilder.append(line);
        }
        String rawJson = jsonBuilder.toString();
        return rawJson;
    }


    private ArrayList<Pattern> parse(String json) throws JSONException {
        JSONObject jsonObj = new JSONObject(json);
        JSONArray arr = jsonObj.getJSONArray("patterns");
        return parse(arr);
    }
    private ArrayList<Pattern> parse(JSONArray arr) throws JSONException {
        ArrayList<Pattern> result = new ArrayList<>();
        int count = arr.length();
        for (int i = 0; i < count; i++) {
            Pattern res = parsePattern(arr.getJSONArray(i));
            result.add(res);
        }
        return result;
    }

    private Pattern parsePattern(JSONArray arr) throws JSONException {
        Pattern pattern = new Pattern();
        int count = arr.length();
        for (int i = 0; i < count; i++) {
            PatternBlock result = parseBlock(arr.getJSONObject(i));
            pattern.patternBlockArrayList.add(result);
        }
        return pattern;
    }

    private PatternBlock parseBlock(JSONObject obj) throws JSONException {
        PatternBlock block = new PatternBlock();
        block.type = Block.BLOCK_TYPE.values()[obj.getInt("type")];
        block.center = obj.getInt("center");
        block.margin = obj.getInt("margin");
        block.next = obj.getInt("next");
        return block;
    }

}
