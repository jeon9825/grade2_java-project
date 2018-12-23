package kr.co.memo.jiyoon.memo.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import  java.util.ArrayList;
import java.util.Dictionary;

import kr.co.memo.jiyoon.memo.MainActivity;
import kr.co.memo.jiyoon.memo.R;
import kr.co.memo.jiyoon.memo.item.MemoItem;

public class MemoListAdapter extends RecyclerView.Adapter<MemoListAdapter.ViewHolder> {
    private Context context;
    private int resource;
    private ArrayList<MemoItem> itemList;

    public MemoListAdapter(Context context, int resource, ArrayList<MemoItem> itemList) {
        this.context=context;
        this.itemList=itemList;
        this.resource=resource;
    }

    public void addItem(MemoItem item){
        this.itemList.add(0,item);
        notifyDataSetChanged();
    }

    public void addItemList(ArrayList<MemoItem> itemList){
        this.itemList.addAll(itemList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount(){
        return this.itemList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        View v=LayoutInflater.from(parent.getContext()).inflate(resource,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        final MemoItem item = itemList.get(position);

        holder.categoryText.setText(item.category);
        holder.dateText.setText(item.date);
        holder.memoText.setText(item.memo);

//        holder.itemView.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                Toast.makeText(context,item.memo,Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{

        TextView categoryText;
        TextView memoText;
        TextView dateText;

        public ViewHolder(View itemView) {
            super(itemView);
            categoryText=itemView.findViewById(R.id.category);
            memoText=itemView.findViewById(R.id.memo);
            dateText=itemView.findViewById(R.id.regdate);
            itemView.setOnCreateContextMenuListener(this);
        }
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {  // 3. 컨텍스트 메뉴를 생성하고 메뉴 항목 선택시 호출되는 리스너를 등록해줍니다. ID 1001, 1002로 어떤 메뉴를 선택했는지 리스너에서 구분하게 됩니다.

            MenuItem Edit = menu.add(Menu.NONE, 1001, 1, "편집");
            MenuItem Delete = menu.add(Menu.NONE, 1002, 2, "삭제");
            Edit.setOnMenuItemClickListener(onEditMenu);
            Delete.setOnMenuItemClickListener(onEditMenu);

        }

        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case 1001:
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        // 다이얼로그를 보여주기 위해 edit_box.xml 파일을 사용합니다.

                        View view = LayoutInflater.from(context)
                                .inflate(R.layout.edit_box, null, false);
                        builder.setView(view);
                        final Button ButtonSubmit = (Button) view.findViewById(R.id.modify);
                        final EditText editTextID = (EditText) view.findViewById(R.id.memo);
                        final Spinner spinnerCategory = (Spinner) view.findViewById(R.id.category);

                        editTextID.setText(itemList.get(getAdapterPosition()).getMemo());
                        String category = itemList.get(getAdapterPosition()).getCategory();
                        int index = -1;
                        if (category.equals("일상"))
                            index = 0;
                        else if(category.equals("공부"))
                            index = 1;
                        else if(category.equals("기타"))
                            index = 2;

                        spinnerCategory.setSelection(index);

                        final AlertDialog dialog = builder.create();
                        ButtonSubmit.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                String strID = editTextID.getText().toString();
                                String strCategory = (String) spinnerCategory.getSelectedItem();
                                MemoItem memo = new MemoItem(strCategory,strID);

                                itemList.set(getAdapterPosition(), memo);

                                notifyItemChanged(getAdapterPosition());
                                dialog.dismiss();
                            }
                        });
                        dialog.show();
                        break;

                    case 1002:
                        itemList.remove(getAdapterPosition());
                        notifyItemRemoved(getAdapterPosition());
                        notifyItemRangeChanged(getAdapterPosition(), itemList.size());
                        break;

                }
                return true;
            }
        };

    }
}
