package idv.jason.popupwindow.multi_checkbox.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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


    private List<User> userList;
    private MultiCheckBoxAdapter<User> adapter;
    private MultiCheckBoxPopupWindow popupWindow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        btn = findViewById(R.id.btn);
        tv = findViewById(R.id.tv);

        btn.setOnClickListener(this);

        userList = new ArrayList<>();
        adapter = new UserMultiCheckBoxAdapter(true, userList);
    }


    @Override
    public void onClick(View view) {
        if (null == popupWindow)
        {
            popupWindow = new MultiCheckBoxPopupWindow(this, adapter);
            popupWindow.setOnDismissListener(this);
        }

        if (!popupWindow.isShowing())
        {
            popupWindow.showAsDropDown(view);

            if (userList.size() <= 0)
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try
                        {
                            Thread.sleep(3000);
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }

                        DemoActivity.this.getWindow().getDecorView().post(new Runnable() {
                            @Override
                            public void run() {
                                List<User> data = getData();
                                userList.addAll(data);

                                adapter.notifyItemRangeInserted(0, data.size());

                                if (null != popupWindow)
                                    popupWindow.setDateLoaded();
                            }
                        });
                    }
                }).start();
            }
        }
    }


    @Override
    public void onDismiss() {
        String str = "Checked:" + '\n';
        List<User> checkedList = adapter.getCheckedList();
        for (User u : checkedList)
            str += u.getName() + '\n';

        tv.setText(str);
    }


    private List<User> getData() {
        List<User> userList = new ArrayList<>();
        for (int i = 0 ; i < 10 ; i++)
            userList.add(new User("Name " + i));
        userList.add(0, new User("全選"));

        return userList;
    }

}
