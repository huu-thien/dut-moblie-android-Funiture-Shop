package com.AndroidFunitureShopApp.model.Chats;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.AndroidFunitureShopApp.R;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;
    List<ChatMessages> chatMessagesList;
    private String sendid;
    private static final int TYPE_SEND = 1;
    private static final int TYPE_RECEIVE= 2;

    public ChatAdapter(Context context, List<ChatMessages> chatMessagesList, String sendid) {
        this.context = context;
        this.chatMessagesList = chatMessagesList;
        this.sendid = sendid;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType == TYPE_SEND) {
            view = LayoutInflater.from(context).inflate(R.layout.send_item, parent, false);
            return new SendMessViewHolder(view);
        }else {
            view = LayoutInflater.from(context).inflate(R.layout.item_receive, parent, false);
            return new ReceiveMessViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == TYPE_SEND){
            ((SendMessViewHolder) holder).txtSendMess.setText(chatMessagesList.get(position).MESSAGE);
            ((SendMessViewHolder) holder).txtSendTime.setText(chatMessagesList.get(position).DATETIME);
        }else{
            ((ReceiveMessViewHolder) holder).txtReceiveMess.setText(chatMessagesList.get(position).MESSAGE);
            ((ReceiveMessViewHolder) holder).txtReceiveTime.setText(chatMessagesList.get(position).DATETIME);
        }
    }

    @Override
    public int getItemCount() {
        return chatMessagesList.size();
    }
    @Override
    public int getItemViewType(int position){
        if(chatMessagesList.get(position).ID_SEND.equals(sendid)){
            return TYPE_SEND;
        }else {
            return TYPE_RECEIVE;
        }
    }
    class SendMessViewHolder extends RecyclerView.ViewHolder{
        TextView txtSendMess,txtSendTime;
        public SendMessViewHolder(@NonNull View itemView){
            super(itemView);
            txtSendMess = itemView.findViewById(R.id.txt_sendmess);
            txtSendTime = itemView.findViewById(R.id.txt_sendtime);
        }
    }
    class ReceiveMessViewHolder extends RecyclerView.ViewHolder{
        TextView txtReceiveMess,txtReceiveTime;
        public ReceiveMessViewHolder(@NonNull View itemView){
            super(itemView);
            txtReceiveMess = itemView.findViewById(R.id.txt_receivemess);
            txtReceiveTime = itemView.findViewById(R.id.txt_receivetime);
        }
    }
}
