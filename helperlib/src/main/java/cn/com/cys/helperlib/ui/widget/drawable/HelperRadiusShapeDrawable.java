package cn.com.cys.helperlib.ui.widget.drawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

/**
 * author: Damon
 * data: On 4/15/21
 */
public class HelperRadiusShapeDrawable extends Drawable {

    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final RectF rectF = new RectF();
    private int outColor = Color.TRANSPARENT;
    private int outWidth = 0;
    private int innerColor = Color.TRANSPARENT;
    private float radius = 0;
    private boolean halfCircle = false;
    private Context context;

    public HelperRadiusShapeDrawable(Context context) {
        this.context = context;
    }

    public HelperRadiusShapeDrawable setOutWidth(int outWidth) {
        this.outWidth = outWidth;
        return this;
    }

    public HelperRadiusShapeDrawable setOutColorRes(@ColorRes int outColor) {
        int color = ContextCompat.getColor(context, outColor);
        this.outColor = color;
        return this;
    }

    public HelperRadiusShapeDrawable setInnerColor(int innerColor) {
        this.innerColor = innerColor;
        return this;
    }


    public HelperRadiusShapeDrawable setInnerColorRes(@ColorRes int innerColor) {
        int color = ContextCompat.getColor(context, innerColor);
        this.innerColor = color;
        return this;
    }

    public HelperRadiusShapeDrawable setRadius(int radius) {
        this.radius = radius;
        return this;
    }

    public HelperRadiusShapeDrawable setHalfCircle(boolean halfCircle) {
        this.halfCircle = halfCircle;
        return this;
    }

    @Override
    public void draw(Canvas canvas) {
        rectF.left = getBounds().left + outWidth / 2f;
        rectF.top = getBounds().top + outWidth / 2f;
        rectF.right = getBounds().right - outWidth / 2f;
        rectF.bottom = getBounds().bottom - outWidth / 2f;

        if (halfCircle) {
            radius = rectF.height() / 2f;
        }
        paint.setColor(innerColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(rectF, radius, radius, paint);

        if (outWidth != 0) {
            paint.setColor(outColor);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(outWidth);
            canvas.drawRoundRect(rectF, radius, radius, paint);
        }
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
        invalidateSelf();
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSPARENT;
    }
}
