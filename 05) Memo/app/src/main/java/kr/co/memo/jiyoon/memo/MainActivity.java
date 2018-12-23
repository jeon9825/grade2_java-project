package kr.co.memo.jiyoon.memo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import kr.co.memo.jiyoon.memo.adapter.MemoListAdapter;
import kr.co.memo.jiyoon.memo.database.DbOpenHelper;
import kr.co.memo.jiyoon.memo.item.MemoItem;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Context context;
    RecyclerView memoList;
    MemoListAdapter memoListAdapter;
    LinearLayoutManager layoutManager;
    DbOpenHelper dbOpenHelper;

    Spinner categorySpinner;
    EditText memoEdit;
    int position =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        dbOpenHelper = new DbOpenHelper(this);
//        dbOpenHelper.open();
        context= this;
        setView();
    }

    private void setView() {
        categorySpinner=findViewById(R.id.category);
        memoEdit=findViewById(R.id.memo);

        Button registerButton = findViewById(R.id.register);
        registerButton.setOnClickListener(this);

        memoList=findViewById(R.id.recyclerview);

        setRecyclerView();
        setMemoItemListItem();
    }

    private void setRecyclerView() {
        layoutManager =new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        memoList.setLayoutManager(layoutManager);

        memoListAdapter=new MemoListAdapter(context, R.layout.row_memo_item, new ArrayList<MemoItem>());
        memoList.setAdapter(memoListAdapter);
    }

    private void setMemoItemListItem() {
        ArrayList<MemoItem> memoList = new ArrayList<>();

        MemoItem item1 = new MemoItem("일상", "오늘 덕질했다 히히");
        MemoItem item2 = new MemoItem("공부", "자바공부중");

        memoList.add(item1);
        memoList.add(item2);
    }

    @Override
    public void onClick(View v) {
        registerMemo();
    }

    private void registerMemo() {
        String category = (String) categorySpinner.getSelectedItem();
        String memo = memoEdit.getText().toString();

        if(TextUtils.isEmpty(memo)){
            Toast.makeText(context,R.string.msg_memo_input,Toast.LENGTH_SHORT).show();
            return;
        }

        addMemoItem(category,memo);
//        dbOpenHelper.insert(position++,category,memo,"2018-11-30");

        categorySpinner.setSelection(0);
        memoEdit.setText("");

        hideKeyboard();
    }

    private void addMemoItem(String category, String memo) {
        MemoItem item = new MemoItem(category,memo);
        memoListAdapter.addItem(item);
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if(view!=null){
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }
}
