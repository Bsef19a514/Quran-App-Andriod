package com.example.quran;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myRecyclerViewAdapter extends RecyclerView.Adapter<myRecyclerViewAdapter.MyVH>{
    ArrayList<String> surahList;
    private View.OnClickListener onItemClickListener;
    public myRecyclerViewAdapter(ArrayList<String> surahList) {
        this.surahList = surahList;
    }

    @NonNull
    @Override
    public myRecyclerViewAdapter.MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.surahview, parent, false);
        return new MyVH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull myRecyclerViewAdapter.MyVH holder, int position) {
        holder.data = surahList.get(position);
        holder.ayatView.setText(holder.data);
    }

    @Override
    public int getItemCount() {
        return surahList.size();
    }

    //TODO: Step 2 of 4: Assign itemClickListener to your local View.OnClickListener variable
    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        onItemClickListener = itemClickListener;
    }

    public class MyVH extends RecyclerView.ViewHolder {
        TextView ayatView;
        String data;

        public MyVH(@NonNull View itemView) {
            super(itemView);
            ayatView=itemView.findViewById(R.id.ayatTextView);
            Typeface typeface = ResourcesCompat.getFont(ayatView.getContext(), R.font.noorehuda);
            ayatView.setTypeface(typeface);

            //TODO: Step 3 of 4: setTag() as current view holder along with
            // setOnClickListener() as your local View.OnClickListener variable.
            // You can set the same mOnItemClickListener on multiple views if required
            // and later differentiate those clicks using view's id.
            itemView.setTag(this);
            itemView.setOnClickListener(onItemClickListener);

        }
    }
}
