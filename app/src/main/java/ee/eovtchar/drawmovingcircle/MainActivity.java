package ee.eovtchar.drawmovingcircle;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends Activity
{
    private int start_x;
    private int start_y;

    public static final String PREFS_NAME = "SharedPrefsFile";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences storedData = getSharedPreferences(PREFS_NAME, 0);
        start_x = storedData.getInt("start_x",50);
        start_y = storedData.getInt("start_y",50);

        DrawScene scene = new DrawScene(this, start_x, start_y);
        setContentView(scene);
    }

    public void saveStats()
    {
        SharedPreferences storedData = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor storedDataEditor = storedData.edit();
        storedDataEditor.putInt("start_x", start_x);
        storedDataEditor.putInt("start_y", start_y);
        storedDataEditor.commit();
    }

    @Override
    public void onStop ()
    {
        super.onStop();
        this.saveStats();
    }
}
