package idv.jason.popupwindow.multi_checkbox;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jason on 2017/10/14.
 */

public abstract class MultiCheckBoxAdapter<T> extends RecyclerView.Adapter<MultiCheckBoxAdapter.ViewHolder>
                                            implements CompoundButton.OnCheckedChangeListener {

    //************************************** 抽象方法宣告 ****************************************//

    protected abstract String getItemName(T obj);


    //***************************************** 變數宣告 *****************************************//

    private static final int ITEM_LAYOUT = R.layout.item_multi_checkbox;

    private static final int ALL_SELECT_INDEX = 0;
    private static final int NONE_ITEM_INDEX = -1;

    private boolean onBind;

    private boolean isSupportAllSelect;
    private boolean isAllRowSingle;
    private List<T> objectList;
    private List<T> checkedList;


    //****************************************** 建構子 ******************************************//

    public MultiCheckBoxAdapter(boolean isAllRowSingle, List<T> objectList) {
        this.isSupportAllSelect = true;
        this.isAllRowSingle = isAllRowSingle;
        this.objectList = objectList;
        this.checkedList = new ArrayList<>();
    }


    //************************************* Adapter 覆寫方法 *************************************//

    @Override
    public MultiCheckBoxAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View itemView = layoutInflater.inflate(ITEM_LAYOUT, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MultiCheckBoxAdapter.ViewHolder holder, int position) {
        onBind = true;

        int index = getIndexByPosition(position);
        if (NONE_ITEM_INDEX != index)
        {
            T obj = objectList.get(index);
            holder.checkBox.setText(getItemName(obj));

            boolean checked = checkedList.contains(obj);
            holder.checkBox.setChecked(checked);

            holder.checkBox.setVisibility(View.VISIBLE);
        }
        else
            holder.checkBox.setVisibility(View.INVISIBLE);

        holder.checkBox.setTag(position);
        holder.checkBox.setOnCheckedChangeListener(this);

        onBind = false;
    }

    @Override
    public int getItemCount() {
        if (null == objectList || objectList.size() <= 0)
            return 0;

        /*
         每列單筆顯示需要兩倍資料量
         預設第一列單筆顯示需要在加上一筆資料量
          */
        return isAllRowSingle ? objectList.size() * 2 : objectList.size() + 1;
    }


    //******************************** OnMultiSelectListener 介面 ********************************//

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
        if (onBind)
            return;

        int position = (int) compoundButton.getTag();
        int index = getIndexByPosition(position);
        if (isSupportAllSelect && ALL_SELECT_INDEX == index)
        {
            checkedList.clear();

            if (checked)
                checkedList.addAll(objectList);

            notifyItemRangeChanged(0, getItemCount());
        }
        else
        {
            T obj = objectList.get(index);
            if (checked)
            {
                if (!checkedList.contains(obj))
                {
                    checkedList.add(obj);

                    notifyItemChanged(position);
                }

                if (isSupportAllSelect && checkedList.size() == objectList.size() - 1 && !checkedList.contains(objectList.get(ALL_SELECT_INDEX)))
                {
                    checkedList.add(objectList.get(ALL_SELECT_INDEX));

                    notifyItemChanged(ALL_SELECT_INDEX);
                }
            }
            else
            {
                if (checkedList.contains(obj))
                {
                    checkedList.remove(obj);

                    notifyItemChanged(position);
                }

                if (isSupportAllSelect && checkedList.contains(objectList.get(ALL_SELECT_INDEX)))
                {
                    checkedList.remove(objectList.get(ALL_SELECT_INDEX));

                    notifyItemChanged(ALL_SELECT_INDEX);
                }
            }
        }
    }


    //****************************************** 公用類 ******************************************//

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checkBox;


        public ViewHolder(View itemView) {
            super(itemView);

            checkBox = itemView.findViewById(R.id.check_box);
        }
    }


    //***************************************** 公用方法 *****************************************//

    /**
     * 取得已勾選的項目列表
     *
     * @return 回傳已勾選的項目列表
     */
    public List<T> getCheckedList() {
        return checkedList;
    }


    //***************************************** 私用方法 *****************************************//

    /**
     * 取得物件陣列索引值
     *
     * @param position 傳入 Item 位置。
     * @return 回傳對應 Object List 索引值，回傳 -1 表示該位置不在物件陣列當中。
     */
    private int getIndexByPosition(int position) {
        int index;
        if (isAllRowSingle)
        {
            // 每列只顯示顯示一筆
            if (position % 2 == 0)
                index = position / 2;
            else
                index = NONE_ITEM_INDEX;
        }
        else
        {
            // 預設樣式為第一列只顯示一筆
            if (position == 0)
                index = position;
            else if (position == 1)
                index = NONE_ITEM_INDEX;
            else
                index = position - 1;
        }

        return index;
    }

}
