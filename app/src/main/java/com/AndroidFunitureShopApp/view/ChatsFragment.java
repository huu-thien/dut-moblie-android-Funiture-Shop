package com.AndroidFunitureShopApp.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.AndroidFunitureShopApp.databinding.FragmentChatsBinding;
import com.AndroidFunitureShopApp.model.Chats.ChatAdapter;
import com.AndroidFunitureShopApp.model.Chats.ChatMessages;
import com.AndroidFunitureShopApp.viewmodel.Utils;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.logging.SimpleFormatter;

public class ChatsFragment extends Fragment {

    private FragmentChatsBinding binding;
    FirebaseFirestore db;
    ChatAdapter adapter;
    List<ChatMessages> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentChatsBinding.inflate(getLayoutInflater());
        db = FirebaseFirestore.getInstance();
        list = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.rcvMessgage.setLayoutManager(layoutManager);
        binding.rcvMessgage.setHasFixedSize(true);
        adapter = new ChatAdapter(getContext(),list,String.valueOf(Utils.account.getId()));
        binding.rcvMessgage.setAdapter(adapter);
        activities();
        listenMess();
    }

    private String format_date(Date date) {
        return new SimpleDateFormat("MMMM dd, yyyy- hh:mm a", Locale.getDefault()).format(date);
    }

    private void activities() {
        binding.imgSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessageToFire();
            }
        });
    }

    private void sendMessageToFire() {
        String strmess = binding.edtInputtxt.getText().toString().trim();
        if (TextUtils.isEmpty(strmess)) {

        } else {
            HashMap<String, Object> message = new HashMap<>();
            message.put(Utils.SENT_ID, String.valueOf(Utils.account.getId()));
            message.put(Utils.RECEIVED_ID, Utils.ID_RECEIVED);
            message.put(Utils.MESS, strmess);
            message.put(Utils.DATE_TIME, new Date());
            db.collection(Utils.PATH_CHAT).add(message);
            binding.edtInputtxt.setText("");
        }
    }
    private void listenMess(){
        db.collection(Utils.PATH_CHAT)
                .whereEqualTo(Utils.SENT_ID, String.valueOf(Utils.account.getId()))
                .whereEqualTo(Utils.RECEIVED_ID,Utils.ID_RECEIVED)
                .addSnapshotListener(eventListener);
        db.collection(Utils.PATH_CHAT)
                .whereEqualTo(Utils.SENT_ID, Utils.ID_RECEIVED)
                .whereEqualTo(Utils.RECEIVED_ID,Utils.ID_RECEIVED)
                .addSnapshotListener(eventListener);
    }
    private final EventListener<QuerySnapshot> eventListener = (value, error) -> {
        if (error != null) {
            return;
        }
        if (value != null) {
            int count = list.size();
            for (DocumentChange documentchange : value.getDocumentChanges()) {
                if (documentchange.getType() == DocumentChange.Type.ADDED) {
                    ChatMessages chatMessages = new ChatMessages();
                    chatMessages.ID_SEND = documentchange.getDocument().getString(Utils.SENT_ID);
                    chatMessages.ID_RECEIVE = documentchange.getDocument().getString(Utils.RECEIVED_ID);
                    chatMessages.MESSAGE = documentchange.getDocument().getString(Utils.MESS);
                    chatMessages.dateOBJ = documentchange.getDocument().getDate(Utils.DATE_TIME);
                    chatMessages.DATETIME = format_date(documentchange.getDocument().getDate(Utils.DATE_TIME));
                    list.add(chatMessages);
                }
            }
            Collections.sort(list, (obj1, obj2) -> obj1.dateOBJ.compareTo(obj2.dateOBJ) );
            if (count == 0) {
                adapter.notifyDataSetChanged();
            } else {
                adapter.notifyItemRangeInserted(list.size(), list.size());
                binding.rcvMessgage.smoothScrollToPosition(list.size() - 1);
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot = binding.getRoot();
        return viewRoot;
    }
}