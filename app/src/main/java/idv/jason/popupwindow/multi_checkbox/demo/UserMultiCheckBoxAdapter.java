package idv.jason.popupwindow.multi_checkbox.demo;

import java.util.List;

import idv.jason.popupwindow.multi_checkbox.MultiCheckBoxAdapter;

/**
 * Created by jason on 2017/11/10.
 */

public class UserMultiCheckBoxAdapter extends MultiCheckBoxAdapter<User> {

    public UserMultiCheckBoxAdapter(boolean isAllRowSingle, List<User> objectList, List<User> checkedList) {
        super(isAllRowSingle, objectList, checkedList);
    }


    @Override
    protected String getItemName(User obj) {
        return obj.getName();
    }

}
