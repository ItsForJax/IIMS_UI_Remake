package com.epds.dev.iimsuiremake.postmanager;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.epds.dev.iimsuiremake.R;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private List<Post> posts;
    private Context context;

    public PostAdapter(List<Post> posts, Context context) {
        this.posts = posts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.postText.setText(post.getPost_text());
        holder.postDateTime.setText(post.getPost_dateTime());
        holder.postName.setText(post.getPost_name());
        holder.postProfilePic.setImageResource(post.getProfilePic());
        holder.postImg.setImageResource(post.getPost_img());

        holder.postImg.setOnClickListener(v -> showFullScreenImage(post.getPost_img(), post.getPost_text(), post.getPost_dateTime(), post.getPost_name(), post.getProfilePic()));
    }
    private void showFullScreenImage(int imageResource, String postText, String postDateTime, String postName, int profilePic) {
        Dialog dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_full_screen_image);
        PhotoView fullScreenImageView = dialog.findViewById(R.id.fullScreenImageView);
        ImageView postProfilePic = dialog.findViewById(R.id.post_profile_pic);
        TextView postTextDialog = dialog.findViewById(R.id.post_text);
        TextView postDateTimeDialog = dialog.findViewById(R.id.post_date_time);
        TextView postNameDialog = dialog.findViewById(R.id.post_name);

        fullScreenImageView.setImageResource(imageResource);
        postProfilePic.setImageResource(profilePic);
        postTextDialog.setText(postText);
        postDateTimeDialog.setText(postDateTime);
        postNameDialog.setText(postName);

        dialog.show();
    }


    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements PopupMenu.OnMenuItemClickListener {
        TextView postText, postDateTime, postName;
        ImageView postProfilePic, postImg, postOptions;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            postText = itemView.findViewById(R.id.post_text);
            postDateTime = itemView.findViewById(R.id.post_date_time);
            postName = itemView.findViewById(R.id.post_name);
            postProfilePic = itemView.findViewById(R.id.post_profile_pic);
            postImg = itemView.findViewById(R.id.post_img);
            postOptions = itemView.findViewById(R.id.post_options);

            postOptions.setOnClickListener(v -> {
                PopupMenu popup = new PopupMenu(itemView.getContext(), postOptions);
                popup.inflate(R.menu.post_options_menu);
                popup.setOnMenuItemClickListener(this);
                popup.show();
            });
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if (item.getItemId() == R.id.delete_post) {
                showSnackbar("Delete post by " + postName.getText());
                return true;
            } else if (item.getItemId() == R.id.message_user) {
                showSnackbar("Message user " + postName.getText());
                return true;
            }
            return false;
        }

        private void showSnackbar(String message) {
            Snackbar.make(itemView, message, Snackbar.LENGTH_SHORT).show();
        }

    }

}
