package cm.wzh.live.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;  
  
public class MyVideoView extends VideoView {  
  
    public MyVideoView(Context context, AttributeSet attrs, int defStyleAttr) {  
        super(context, attrs, defStyleAttr);
    }  
      
     
    public MyVideoView(Context context, AttributeSet attrs) {  
        super(context, attrs);
    }  
      
    public MyVideoView(Context context) {  
        super(context);
    }  
    @Override  
    protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec){  
        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(height, width);  
    }  
  
}  