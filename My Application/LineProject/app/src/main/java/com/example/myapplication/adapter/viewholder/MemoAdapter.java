package com.example.myapplication.adapter.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MemoListItemView;
import com.example.myapplication.R;
import com.example.myapplication.item.MemoData;
import java.util.ArrayList;
import java.util.List;

public class MemoAdapter extends BaseAdapter {

    private Context mContext;

    private List<MemoData> mItems = new ArrayList<MemoData>();

    public MemoAdapter(Context context) {
        mContext = context;
    }
    public void clear() {
        mItems.clear();
    }
    public void addItem(MemoData it) {
        mItems.add(it);
    }
    public void setListItems(List<MemoData> lit) {
        mItems = lit;
    }
    public int getCount() {
        return mItems.size();
    }
    public Object getItem(int position) {
        return mItems.get(position);
    }
    public boolean areAllItemsSelectable() {
        return false;
    }
    public boolean isSelectable(int position) {
        try {
            return mItems.get(position).isSelectable();
        } catch (IndexOutOfBoundsException ex) {
            return false;
        }
    }
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        MemoListItemView itemView;
        if (convertView == null) {
            itemView = new MemoListItemView(mContext);
        } else {
            itemView = (MemoListItemView) convertView;
        }
        // 현재아이템 불러오기
        itemView.setContents(0, ((String) mItems.get(position).getData(0)));
        itemView.setContents(1, ((String) mItems.get(position).getData(1)));
        itemView.setContents(1, ((String) mItems.get(position).getData(2)));
        itemView.setContents(3, ((String) mItems.get(position).getData(4)));
        return itemView;
    }
}
