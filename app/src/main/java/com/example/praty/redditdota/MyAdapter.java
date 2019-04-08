package com.example.praty.redditdota;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.EncodeStrategy;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private List<Post> mPosts;
    private Context mContext;
    private ItemClickListener mClickListener;
    private static final String TAG = "MyAdapter";
    public MyAdapter(Context mContext,List<Post> mPosts, ItemClickListener mClickListener){
        this.mPosts=mPosts;
        this.mContext=mContext;
        this.mClickListener=mClickListener;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cardview, parent, false);
        final MyViewHolder mHolder=new MyViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v, mHolder.getAdapterPosition());
            }
        });
        return mHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.mTitle.setText(mPosts.get(position).getAuthor());
        holder.mAuthor.setText((mPosts.get(position).getTitle()));
        holder.mUpdated.setText(mPosts.get(position).getDate_updated());
        Glide.with(mContext)
                .load(mPosts.get(position).getThumbnailURL())
                .into(holder.mThumbnail);

    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: "+mPosts.size());
        return mPosts.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mThumbnail;
        TextView mTitle,mAuthor,mUpdated;

        public MyViewHolder(View itemView) {
            super(itemView);
            mThumbnail=(ImageView)itemView.findViewById(R.id.cardImage);
            mAuthor=(TextView)itemView.findViewById(R.id.cardAuthor);
            mTitle=(TextView) itemView.findViewById(R.id.cardTitle);
            mUpdated=(TextView) itemView.findViewById(R.id.cardUpdated);

        }
    }
}
