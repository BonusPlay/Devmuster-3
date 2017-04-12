
package hobbajt.com.helpme.Menu;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class TileLayout extends FrameLayout
{
    public TileLayout(Context context)
    {
        super(context);
    }

    public TileLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public TileLayout(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
