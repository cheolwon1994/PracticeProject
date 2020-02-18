package com.example.myapplication.adapter.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.item.MemoData;
import java.util.ArrayList;

public class MemoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int TYPE_ITEM = 1;
    private ArrayList<MemoData> listMemo = new ArrayList<>();
    private Context context;
    private String memo_Id;

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title;
        private TextView content;
        private TextView date;
        private ImageView imagepath;

        ItemViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.memoTitle);
            date = itemView.findViewById(R.id.memoDate);
            content = itemView.findViewById(R.id.memoContent);
            imagepath = itemView.findViewById(R.id.memoImage);
        }

        void onBind(MemoData memoData, int position) {
            String temp = memoData.getImagePath();
//            temp = temp.replace("/", "%2F");
//            String sum = ImageURL + temp;
//            try {
//                URL url = new URL(sum);
//                Picasso.get().load(url.toString()).into(ingredient_image);
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            }
            memo_Id = memoData.getMemo_Id();
            title.setText(memoData.getTitle());
            content.setText(String.valueOf(memoData.getContent()));
            date.setText(memoData.getDate());
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) { int pos = getAdapterPosition() ;}
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        RecyclerView.ViewHolder holder;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_memo, parent, false);
        holder = new MemoAdapter.ItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MemoAdapter.ItemViewHolder itemViewHolder = (MemoAdapter.ItemViewHolder) holder;
        itemViewHolder.onBind(listMemo.get(position), position);
    }

    @Override
    public int getItemViewType(int position){ return TYPE_ITEM; }

    @Override
    public int getItemCount() {
        return listMemo.size();
    }

    public void addItem(MemoData memoData) {
        listMemo.add(memoData);
    }
    public void removeItem(int position){
        listMemo.remove(position);
        notifyItemRemoved(position);
    }
}
