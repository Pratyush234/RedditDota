package com.example.praty.redditdota;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {

    private List<Comment> mComments=new ArrayList<>();
    public CommentsAdapter(List<Comment> mComments) {
        this.mComments=mComments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.comments_layout,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.comment.setText(mComments.get(position).getComment());
        holder.mtitle.setText(mComments.get(position).getAuthor());
        holder.updated.setText(mComments.get(position).getUpdated());

    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView comment,mtitle,updated;
        public ViewHolder(View itemView) {
            super(itemView);
            comment=(TextView) itemView.findViewById(R.id.comment);
            mtitle=(TextView) itemView.findViewById(R.id.commentAuthor);
            updated=(TextView) itemView.findViewById(R.id.commentUpdated);
        }
    }
}
