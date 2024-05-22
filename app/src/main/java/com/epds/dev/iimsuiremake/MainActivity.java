package com.epds.dev.iimsuiremake;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.epds.dev.iimsuiremake.postmanager.Post;
import com.epds.dev.iimsuiremake.postmanager.PostAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    PostAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Refresh
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this::refreshItems);

        // Array of post for the recycler
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("Di naman sya nakakaiyak...", "2024-05-19 • 12:12pm", "Tom Hanks", R.drawable.tom_hanks, R.drawable.man_called_otto));
        posts.add(new Post("Bahala na daw si Batman!", "2024-05-12 • 11:13am", "Batman", R.drawable.batman, R.drawable.joker));
        posts.add(new Post("Wolverine is recovering from his injuries when he crosses paths with the loudmouth, Deadpool. They team up to defeat a common enemy.", "2024-05-11 • 14:21pm", "Deadpool", R.drawable.deadpool, R.drawable.deadpool_movie_poster));
        posts.add(new Post("Nakita mo tropa mo na may paputok na pimple.", "2024-05-08 • 19:51am", "Thanos The Destroyer", R.drawable.thanos, R.drawable.mind_stone));

        // Recyclerview init and adapter setting
        recyclerView = findViewById(R.id.post_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        postAdapter = new PostAdapter(posts,this);
        recyclerView.setAdapter(postAdapter);

        // Chat bot button
        ImageButton openDialogButton = findViewById(R.id.chat_bot_btn);
        openDialogButton.setOnClickListener(v -> openChatBot());
    }

    private void openChatBot() {
        // Open chatbot
        ChatBotFragment dialogFragment = new ChatBotFragment();
        dialogFragment.show(getSupportFragmentManager(), "ChatBotFragment");
    }

    private void refreshItems() {
        // Refresh function TODO change to actual data retrieval
        new Handler().postDelayed(() -> {
            swipeRefreshLayout.setRefreshing(false);
            showSnackbar("Student Testimony Refreshed!");
        }, 2000); // 2 seconds delay (TEMPORARY)
    }

    private void showSnackbar(String message) {
        // Snack bar builder
        Snackbar.make(recyclerView, message, Snackbar.LENGTH_SHORT).show();
    }
}
