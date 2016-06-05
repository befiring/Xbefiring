package com.befiring.xbefiring.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.befiring.xbefiring.R;

/**
 * Created by ASUS on 2016/6/2.
 */
public class CustomEditText extends EditText{

    private Context mContext;
    private Drawable mDrawableRight;
    private Drawable mDrawableLeft;
    private TextWatcherCallback mCallback;

    public CustomEditText(Context context) {
        super(context);
        this.mContext=context;
        init();
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext=context;
        TypedArray array=mContext.obtainStyledAttributes(attrs, R.styleable.CustomEditText);
        mDrawableLeft=array.getDrawable(R.styleable.CustomEditText_leftIcon);
        init();
    }

    public void init(){
        mDrawableRight=mContext.getResources().getDrawable(R.mipmap.ic_launcher);
        mCallback=null;
//        Drawable d2=array.getDrawable(R.styleable.CustomEditText_rightIcon);
//        this.setCompoundDrawablesWithIntrinsicBounds(d1,null,null,null);
        TextWatcher textWatcher=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                updateCleanable(length(),true);
//                if(mCallback!=null){
//                    mCallback.handleMoreTextChanged();
//                }

            }
        };
        this.addTextChangedListener(textWatcher);
        this.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                updateCleanable(length(),hasFocus);
            }
        });

    }

    public void updateCleanable(int length,boolean hasFocus){
        if(length>0&&hasFocus){
            setCompoundDrawablesWithIntrinsicBounds(mDrawableLeft,null,mDrawableRight,null);
            setCompoundDrawablePadding(5);
        }else {
            setCompoundDrawablesWithIntrinsicBounds(mDrawableLeft,null,null,null);
            setCompoundDrawablePadding(15);
//            setPadding(15,15,15,15);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int DRAWABLE_RIGHT=2;
        Drawable rightIcon=getCompoundDrawables()[DRAWABLE_RIGHT];
        if(rightIcon!=null&&event.getAction()==MotionEvent.ACTION_UP){
            int leftEdgeOfDrawable=getRight()-getPaddingRight()-rightIcon.getBounds().width();
            if(event.getRawX()>=leftEdgeOfDrawable){
                setText("");
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void finalize() throws Throwable {
        mDrawableLeft=null;
        mDrawableRight=null;
        super.finalize();
    }

    public interface TextWatcherCallback{
         void handleMoreTextChanged();
    }
}
