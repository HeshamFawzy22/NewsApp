package com.abofa.newsapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abofa.newsapp.ApiResponses.ArticlesItem;
import com.abofa.newsapp.R;
import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    OnItemClickListener onItemClickListener;

    List<ArticlesItem> articles;
    Context context;

    public NewsAdapter(OnItemClickListener onItemClickListener, List<ArticlesItem> articles) {
        this.onItemClickListener = onItemClickListener;
        this.articles = articles;
    }

    public NewsAdapter(List<ArticlesItem> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                         int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArticlesItem item = articles.get(position);
        holder.title.setText(item.getTitle());
        holder.content.setText(item.getDescription());
        holder.date.setText(item.getPublishedAt());//Todo:search how to format time
        holder.sourceName.setText(item.getSource().getName());
        Glide.with(context)
                .load(item.getUrlToImage())
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return articles==null?0:articles.size();
    }
    public void changeData(List<ArticlesItem> items){
        this.articles=items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView sourceName;
        ImageView image;
        TextView content;
        TextView  title, date;


        public ViewHolder(@NonNull View rootView) {
            super(rootView);
            sourceName =  rootView.findViewById(R.id.source_name);
            title =  rootView.findViewById(R.id.title);
            date =  rootView.findViewById(R.id.date);
            image =  rootView.findViewById(R.id.image);
            content =  rootView.findViewById(R.id.content);
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            });
        }

    }

    public interface OnItemClickListener{
        void onItemClick(int pos);
    }
}
