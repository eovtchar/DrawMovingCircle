package ee.eovtchar.drawmovingcircle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.View;

/**
 * Created by user_43 on 23.04.2015.
 */
public class DrawScene extends View
{
    private Paint canvasPaint;
    private Paint circlePaint;
    private Handler h;

    private int x = 50;
    private int y = 50;
    private int circleRadius = 50;
    //na skolko warik budet peremewatsja
    private int dx = 5;
    private int dy = 5;
    //final - ozna4aet 4to pomenjat ee v programme ja ne mogy
    private final int FRAME_RATE = 30;

    public DrawScene(Context context, int start_x, int start_y)
    {
        super(context);
        h = new Handler();

        x = start_x;
        y = start_y;

        canvasPaint = new Paint();
        circlePaint = new Paint();

        canvasPaint.setStyle(Paint.Style.FILL);
        canvasPaint.setColor(Color.BLUE);

        circlePaint.setColor(Color.RED);
    }

    private Runnable r = new Runnable()
    {
        @Override
    public void run()
        {
        invalidate();
        }
    };

    @Override
    protected void onDraw(Canvas sceneCanvas)
    {
        super.onDraw(sceneCanvas);
        sceneCanvas.drawPaint(canvasPaint);

        sceneCanvas.drawCircle(x,y,circleRadius,circlePaint);
        x = x + dx;
        y += dy; // y = y + dy

        if (x > this.getWidth() || x < circleRadius)
        {
            dx = dx * -1;
        }
        if (y > this.getHeight() || y < circleRadius)
        {
            dy = dy * -1;
        }

        h.postDelayed(r, FRAME_RATE);
    }
}
