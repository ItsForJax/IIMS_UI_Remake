package com.epds.dev.iimsuiremake;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import com.epds.dev.iimsuiremake.buttonbasedchatbot.chatadapter.ChatAdapter;
import com.epds.dev.iimsuiremake.buttonbasedchatbot.dataclass.ChatItem;
import com.epds.dev.iimsuiremake.buttonbasedchatbot.viewholder.FormViewHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class ChatBotFragment extends DialogFragment implements ChatAdapter.OnItemClickListener {

    RecyclerView recyclerView;
    String lastChosen;
    int currentIndex = 0;
    ArrayList<ChatItem> chatItems;
    ChatAdapter chatAdapter;

    Button dismiss;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chat_bot_fragment, container, false);

        // Chat array for the recycler
        chatItems = new ArrayList<>();

        // Recyclerview setup
        recyclerView = view.findViewById(R.id.recyclerViewFrag);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        chatAdapter = new ChatAdapter(getContext(), recyclerView, this, this, chatItems, this);
        recyclerView.setAdapter(chatAdapter);

        // Grab time date before rendering the initial chat
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy • HH:mm");
        Date date = new Date();
        String datetime = formatter.format(date);
        chatItems.add(new ChatItem.DateTime(datetime));

        new Handler().postDelayed(() -> {
            chatItems.add(new ChatItem.Bot(getString(R.string.allowance_type)));
            chatItems.add(new ChatItem.Options(Arrays.asList("1", "2", "3", "4", "5", "6", "7")));
            chatAdapter.notifyItemInserted(chatItems.size() - 1);
        }, 500); // .5 second delay

        // Dismiss setup
        dismiss = view.findViewById(R.id.dismiss);
        dismiss.setOnClickListener(v->dismiss());

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null) {
            DisplayMetrics metrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int width = (int) (metrics.widthPixels * 0.95);
            int height = (int) (metrics.heightPixels * 0.85);
            getDialog().getWindow().setLayout(width, height);
            getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        }
    }

    @Override
    public void onItemClick(String position) {
        chatItems.remove(chatItems.size() - 1);
        if (chatAdapter != null) { // Check if adapter is initialized
            chatAdapter.notifyItemRemoved(chatItems.size());
        }
        boolean newInter = false;
        chatItems.add(new ChatItem.User("Selected: " + position));

        if (currentIndex == 0) {
            if (position.equals("1") && lastChosen == null) {
                chatItems.add(new ChatItem.Bot("Have you received your STIPEND allowance?\n1. Yes\n2. No"));
                chatItems.add(new ChatItem.Options(Arrays.asList("1", "2")));
            } else if (position.equals("2") && lastChosen == null) {
                chatItems.add(new ChatItem.Bot("Have you received your BOOK allowance?\n1. Yes\n2. No"));
                chatItems.add(new ChatItem.Options(Arrays.asList("1", "2")));
            } else if (position.equals("3") && lastChosen == null) {
                chatItems.add(new ChatItem.Bot("Have you received your LAPTOP allowance?\n1. Yes\n2. No"));
                chatItems.add(new ChatItem.Options(Arrays.asList("1", "2")));
            } else if (position.equals("4") && lastChosen == null) {
                chatItems.add(new ChatItem.Bot("Have you received your THESIS allowance?\n1. Yes\n2. No"));
                chatItems.add(new ChatItem.Options(Arrays.asList("1", "2")));
            } else if (position.equals("5") && lastChosen == null) {
                chatItems.add(new ChatItem.Bot("Have you received your OJT allowance?\n1. Yes\n2. No"));
                chatItems.add(new ChatItem.Options(Arrays.asList("1", "2")));
            } else if (position.equals("6") && lastChosen == null) {
                chatItems.add(new ChatItem.Bot("Have you received your ONE-TIME ATTENDANCE allowance?\n1. Yes\n2. No"));
                chatItems.add(new ChatItem.Options(Arrays.asList("1", "2")));
            } else if (position.equals("7") && lastChosen == null) {
                chatItems.add(new ChatItem.Bot("Have you received your ONE-TIME FINANCIAL allowance?\n1. Yes\n2. No"));
                chatItems.add(new ChatItem.Options(Arrays.asList("1", "2")));
            }
        }

        else if (currentIndex == 1) {
            if (lastChosen.equals("1")) {
                if (position.equals("1")) {
                    chatItems.add(new ChatItem.Bot("Congratulations! You have received your STIPEND allowance!"));
                    chatItems.add(new ChatItem.Form());
                } else {
                    chatItems.add(new ChatItem.Bot("Sorry to here that you have not received your STIPEND allowance!"));
                    chatItems.add(new ChatItem.Form());
                }
            }
            if (lastChosen.equals("2")) {
                if (position.equals("1")) {
                    chatItems.add(new ChatItem.Bot("Congratulations! You have received your BOOK allowance!"));
                    chatItems.add(new ChatItem.Form());
                } else {
                    chatItems.add(new ChatItem.Bot("Sorry to here that you have not received your BOOK allowance!"));
                    chatItems.add(new ChatItem.Form());
                }
            }
            if (lastChosen.equals("3")) {
                if (position.equals("1")) {
                    chatItems.add(new ChatItem.Bot("Congratulations! You have received your LAPTOP allowance!"));
                    chatItems.add(new ChatItem.Form());
                } else {
                    chatItems.add(new ChatItem.Bot("Sorry to here that you have not received your LAPTOP allowance!"));
                    chatItems.add(new ChatItem.Form());
                }
            }
            if (lastChosen.equals("4")) {
                if (position.equals("1")) {
                    chatItems.add(new ChatItem.Bot("Congratulations! You have received your THESIS allowance!"));
                    chatItems.add(new ChatItem.Form());
                } else {
                    chatItems.add(new ChatItem.Bot("Sorry to here that you have not received your THESIS allowance!"));
                    chatItems.add(new ChatItem.Form());
                }
            }
            if (lastChosen.equals("5")) {
                if (position.equals("1")) {
                    chatItems.add(new ChatItem.Bot("Congratulations! You have received your OJT allowance!"));
                    chatItems.add(new ChatItem.Form());
                } else {
                    chatItems.add(new ChatItem.Bot("Sorry to here that you have not received your OJT allowance!"));
                    chatItems.add(new ChatItem.Form());
                }
            }
            if (lastChosen.equals("6")) {
                if (position.equals("1")) {
                    chatItems.add(new ChatItem.Bot("Congratulations! You have received your ONE-TIME ATTENDANCE allowance!"));
                    chatItems.add(new ChatItem.Form());
                } else {
                    chatItems.add(new ChatItem.Bot("Sorry to here that you have not received your ONE-TIME ATTENDANCE allowance!"));
                    chatItems.add(new ChatItem.Form());
                }
            }
            if (lastChosen.equals("7")) {
                if (position.equals("1")) {
                    chatItems.add(new ChatItem.Bot("Congratulations! You have received your ONE-TIME FINANCIAL allowance!"));
                    chatItems.add(new ChatItem.Form());
                } else {
                    chatItems.add(new ChatItem.Bot("Sorry to here that you have not received your ONE-TIME FINANCIAL allowance!"));
                    chatItems.add(new ChatItem.Form());
                }
            }
        }

        else if (currentIndex == 2) {
            if (position.equals("1")) {
                newInter = true;
                lastChosen = null;
                currentIndex = 0;
                chatItems.add(new ChatItem.Bot(getString(R.string.allowance_type)));
                chatItems.add(new ChatItem.Options(Arrays.asList("1", "2", "3", "4", "5", "6", "7")));
            } else if (position.equals("2")) {
                chatItems.add(new ChatItem.Bot("Thank you for your time!"));
                chatItems.add(new ChatItem.Bot("This will automatically close, Bye!"));
                chatAdapter.notifyItemInserted(chatItems.size() - 1);
                recyclerView.smoothScrollToPosition(chatItems.size()-1);

                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    chatItems.clear();
                    chatAdapter.notifyDataSetChanged();
                    dismiss();
                }, 2000);
                return;
            }
        }

        if (!newInter){
            currentIndex++;
            lastChosen = position;
        }

        chatAdapter.notifyItemInserted(chatItems.size() - 1);
        recyclerView.smoothScrollToPosition(chatItems.size() - 1);

    }

    @Override
    public void onCancelClicked(FormViewHolder formView) {
        chatItems.remove(chatItems.size() - 1);
        chatAdapter.notifyItemRemoved(chatItems.size());
        chatItems.add(new ChatItem.Bot("You have cancelled the form, Goodbye!"));
        chatItems.add(new ChatItem.Bot("Thank you for using our service!"));
        chatItems.add(new ChatItem.Bot("Anything else you would like to ask? \n1. Yes\n2. No"));
        chatItems.add(new ChatItem.Options(Arrays.asList("1", "2")));
        chatAdapter.notifyItemInserted(chatItems.size() - 1);
    }

    @NonNull
    private static List<String> getStrings(FormViewHolder formView) {
        List<String> checkedItems = new ArrayList<>();

        // Check each checkbox and add the name to the list if it's checked
        if (formView.scholarShipForm.isChecked()) checkedItems.add("\n\t•Scholarship Form");
        if (formView.LOR.isChecked()) checkedItems.add("\n\t•Letter of Recommendation");
        if (formView.TOR.isChecked()) checkedItems.add("\n\t•Transcript of Records");
        if (formView.PSA_NSO.isChecked()) checkedItems.add("\n\t•PSA/NSO");
        if (formView.StudentForm.isChecked()) checkedItems.add("\n\t•Student Form");
        return checkedItems;
    }
    @Override
    public void onSubmitClicked(FormViewHolder formView) {
        List<String> checkedItems = getStrings(formView);

        String concatenatedString = String.join("", checkedItems);

        chatItems.remove(chatItems.size() - 1);
        chatAdapter.notifyItemRemoved(chatItems.size());
        chatItems.add(new ChatItem.Bot("You have already submitted the following forms: " + concatenatedString));
        chatItems.add(new ChatItem.Bot("Anything else you would like to ask? \n1. Yes\n2. No"));
        chatItems.add(new ChatItem.Options(Arrays.asList("1", "2")));
        chatAdapter.notifyItemInserted(chatItems.size() - 1);
        recyclerView.smoothScrollToPosition(chatItems.size() - 1);
    }
}