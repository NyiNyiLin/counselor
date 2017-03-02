package com.nyi.annonymous.counselling.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nyi.annonymous.counselling.R;
import com.nyi.annonymous.counselling.data.VOS.Comment;
import com.nyi.annonymous.counselling.data.VOS.MsgList;
import com.nyi.annonymous.counselling.data.models.UserModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IN-3442 on 10-Feb-17.
 */

public class CommentVH extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_item_view_comment)
    TextView tvComment;

    @BindView(R.id.tv_item_view_comment_name)
    TextView tvCommentName;

    @BindView(R.id.iv_user_image)
    ImageView ivUserImage;

    private Comment comment;

    public CommentVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }

    public void bindData(Comment comment){
        this.comment = comment;

        tvCommentName.setText(comment.getName());
        tvComment.setText(comment.getComment());
    }

}
