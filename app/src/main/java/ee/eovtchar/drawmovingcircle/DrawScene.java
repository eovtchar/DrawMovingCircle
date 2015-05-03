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
    private int scene_width;
    private int scene_height;

    private int x = 50;
    private int y = 50;
    private int circleRadius = 50;
    //na skolko warik budet peremewatsja
    private int dx = 15;
    private int dy = 15;
    //final - ozna4aet 4to pomenjat ee v programme ja ne mogy
    private final int FRAME_RATE = 10;

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

//        if (x>scene_width-circleRadius || x<circleRadius)
            if (x>scene_width || x<circleRadius)

            {
            dx = dx * -1;
        }
        //if (y>scene_height-circleRadius || y<circleRadius)
        if (y>scene_height || y<circleRadius)
        {
            dy = dy * -1;
        }

        h.postDelayed(r, FRAME_RATE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        scene_width = this.getWidth();
        scene_height = this.getHeight();
    }
}
