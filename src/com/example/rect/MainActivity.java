package com.example.rect;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends Activity {
	
	Cub cub;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(new MyView(this));
    }

    class MyView extends View {
            Paint p;
            // координаты для рисования квадрата
            float x = 100;
            float y = 100;
            int side = 100;

            // переменные для перетаскивания
            boolean drag = false;
            float dragX = 0;
            float dragY = 0;

            public MyView(Context context) {
                    super(context);
                    p = new Paint();
                    p.setColor(Color.GREEN);
                    cub = new Cub();
                    
            }



            protected void onDraw(Canvas canvas) {
                    // рисуем квадрат
//                    canvas.drawRect(x - side, y - side, x + side, y + side, p);
                    cub.setX0((float)x);
                    cub.setY0((float)y);
                    cub.recalculate();
                    cub.draw(canvas,p);
            }

            @Override
            public boolean onTouchEvent(MotionEvent event) {
                    // координаты Touch-события
                    float evX = event.getX();
                    float evY = event.getY();

                    switch (event.getAction()) {
                    // касание началось
                    case MotionEvent.ACTION_DOWN:
                            // если касание было начато в пределах квадрата
                            if (evX >= x - side && evX <= x + side && evY >= y - side && evY <= y + side) {
                                    // включаем режим перетаскивания
                                    drag = true;
                                    // разница между левым верхним углом квадрата и точкой касания
                                    dragX = evX - x;
                                    dragY = evY - y;
                                    invalidate();
                                    
                            }
                            break;
                    // тащим
                    case MotionEvent.ACTION_MOVE:
                            // если режим перетаскивания включен
                            if (drag) {
                                    // определеяем новые координаты для рисования
                                    x = evX - dragX;
                                    y = evY - dragY;
                                    // перерисовываем экран
                                    invalidate();
                                    cub.recalculate();
                            }
                            break;
                    // касание завершено
                    case MotionEvent.ACTION_UP:
                            // выключаем режим перетаскивания
                            drag = false;
                            break;
                    }
                    return true;
            }
    }
}