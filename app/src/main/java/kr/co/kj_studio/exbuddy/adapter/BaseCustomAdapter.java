package kr.co.kj_studio.exbuddy.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ArrayAdapter;

import kr.co.kj_studio.exbuddy.utils.FontChanger;

import java.util.List;

/**
 * Created by KJ_Studio on 2015-12-08.
 */
public abstract class BaseCustomAdapter<T> extends ArrayAdapter<T> {
    static Context mContext;

    public BaseCustomAdapter(Context context, int resource, int textViewResourceId, List<T> objects) {
        super(context, resource, textViewResourceId, objects);
        mContext = context;

    }

    public void setGlobalFont(View mConvertView){
        Typeface mFont = FontChanger.setTypeFace(mContext);
        FontChanger.setGlobalFont(mFont, mContext, mConvertView);
    }
}
