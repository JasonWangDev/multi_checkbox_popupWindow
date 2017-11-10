package idv.jason.popupwindow.multi_checkbox.demo;

import java.util.List;

import idv.jason.popupwindow.multi_checkbox.MultiCheckBoxAdapter;

/**
 * Created by jason on 2017/11/10.
 */

public class UserMultiCheckBoxAdapter extends MultiCheckBoxAdapter<User> {

    public UserMultiCheckBoxAdapter(boolean isAllRowSingle, List<User> objectList) {
        super(isAllRowSingle, objectList);
    }


    @Override
    protected String getItemName(User obj) {
        return obj.getName();
    }

    @Override
    protected int getItemViewBackgroundResource(int position) {
        return R.color.colorPrimary;
    }

}
