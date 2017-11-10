package idv.jason.popupwindow.multi_checkbox.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import idv.jason.popupwindow.multi_checkbox.MultiCheckBoxAdapter;
import idv.jason.popupwindow.multi_checkbox.MultiCheckBoxPopupWindow;

public class DemoActivity extends AppCompatActivity implements View.OnClickListener, PopupWindow.OnDismissListener {

    private Button btn;
    private TextView tv;

    private MultiCheckBoxAdapter<User> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        btn = findViewById(R.id.btn);
        tv = findViewById(R.id.tv);

        btn.setOnClickListener(this);

        List<User> userList = new ArrayList<>();
        for (int i = 0 ; i < 10 ; i++)
            userList.add(new User("Name " + i));

        adapter = new UserMultiCheckBoxAdapter(true, userList, new ArrayList<User>());
    }


    @Override
    public void onClick(View view) {
        MultiCheckBoxPopupWindow popupWindow = new MultiCheckBoxPopupWindow(this, adapter);
        popupWindow.showAsDropDown(view);

        popupWindow.setOnDismissListener(this);
    }


    @Override
    public void onDismiss() {
        String str = "Checked:" + '\n';
        List<User> checkedList = adapter.getCheckedList();
        for (User u : checkedList)
            str += u.getName() + '\n';

        tv.setText(str);
    }

}
