package fernandez.pau.simplechat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView recyclerview;
    private EditText editmsg;

    private RecyclerView.Adapter<MessageViewHolder> adapter;
    private ArrayList<Message> messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messages = new ArrayList<>();

        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        editmsg = (EditText) findViewById(R.id.editmsg);

        // 1. Posar un layout manager al recyclerview (que controla com es mostren els elements)
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(manager);

        // 2. Posar un adaptador
        adapter = new RecyclerView.Adapter<MessageViewHolder>() {
            @Override
            public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_message, parent, false);
                MessageViewHolder holder = new MessageViewHolder(root);
                holder.user = root.findViewById(R.id.user);
                holder.text = root.findViewById(R.id.text);
                return holder;
            }
            @Override
            public void onBindViewHolder(MessageViewHolder holder, int position) {
                Message msg = messages.get(position);
                holder.user.setText(msg.user);
                holder.text.setText(msg.text);
            }
            @Override
            public int getItemCount() {
                return messages.size();
            }
        };
        recyclerview.setAdapter(adapter);
    }

    public void sendMessage(View view) {
        String text = editmsg.getText().toString();
        editmsg.setText("");
        messages.add(new Message("Groucho", text));
        adapter.notifyItemInserted(messages.size()-1);
    }
}
