package idv.jason.popupwindow.multi_checkbox;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

/**
 * Created by jason on 2017/11/9.
 */

public class MultiCheckBoxPopupWindow extends PopupWindow {

    private static final int LAYOUT_ID = R.layout.popupwindow_multi_checkbox;

    private static final int BACKGROUND_COLOR = android.R.color.darker_gray;

    private static final int WIDTH = ViewGroup.LayoutParams.MATCH_PARENT;
    private static final int HEIGHT = ViewGroup.LayoutParams.MATCH_PARENT;

    private RecyclerView recyclerView;


    public MultiCheckBoxPopupWindow(Context context, MultiCheckBoxAdapter adapter) {
        super(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        View popupWindowView = inflater.inflate(LAYOUT_ID,null);

        recyclerView = popupWindowView.findViewById(R.id.recycler_view);

        GridLayoutManager layoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        setContentView(popupWindowView);

        setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(context, BACKGROUND_COLOR)));

        setWidth(WIDTH);
        setHeight(HEIGHT);

        setTouchable(true);

        setFocusable(true);
        setOutsideTouchable(true);
    }

}
