package com.nyi.annonymous.counselling.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyi.annonymous.counselling.Counselling;
import com.nyi.annonymous.counselling.R;
import com.nyi.annonymous.counselling.data.VOS.Comment;
import com.nyi.annonymous.counselling.data.VOS.Msg;
import com.nyi.annonymous.counselling.views.holders.CommentVH;
import com.nyi.annonymous.counselling.views.holders.MessageVH;
import com.nyi.annonymous.counselling.views.holders.MsgListVH;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IN-3442 on 21-Oct-16.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentVH> {
    private LayoutInflater inflater;
    private List<Comment> commentList = new ArrayList<>();

    public CommentAdapter(List<Comment> commentList) {
        this.commentList = commentList;
        inflater = LayoutInflater.from(Counselling.getContext());
    }


    @Override
    public CommentVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_view_comment, parent, false);
        CommentVH commentVH = new CommentVH(view);

        return commentVH;
    }

    @Override
    public void onBindViewHolder(CommentVH holder, int position) {
        holder.bindData(commentList.get(position));
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public void addNewComment(Comment comment){
        commentList.add(comment);
        notifyDataSetChanged();
    }

}
