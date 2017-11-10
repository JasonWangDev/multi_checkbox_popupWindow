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
import android.widget.ViewSwitcher;

/**
 * Created by jason on 2017/11/9.
 */

public class MultiCheckBoxPopupWindow extends PopupWindow {

    private static final int COL_COUNT = 2;

    private static final int LAYOUT_ID = R.layout.popupwindow_multi_checkbox;

    private static final int BACKGROUND_COLOR = android.R.color.darker_gray;

    private static final int WIDTH = ViewGroup.LayoutParams.MATCH_PARENT;
    private static final int HEIGHT = ViewGroup.LayoutParams.MATCH_PARENT;

    private ViewSwitcher viewSwitcher;
    private RecyclerView recyclerView;


    public MultiCheckBoxPopupWindow(Context context, MultiCheckBoxAdapter adapter) {
        super(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        View popupWindowView = inflater.inflate(LAYOUT_ID,null);

        viewSwitcher = popupWindowView.findViewById(R.id.view_switcher);

        recyclerView = popupWindowView.findViewById(R.id.recycler_view);

        GridLayoutManager layoutManager = new GridLayoutManager(context, COL_COUNT);
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


    public void setDateLoaded() {
        viewSwitcher.setDisplayedChild(1);
    }

}
