package fernandez.pau.simplechat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView recyclerview;
    private EditText editmsg;

    private FirebaseRecyclerAdapter<Message, MessageViewHolder> adapter;

    private DatabaseReference messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messages = FirebaseDatabase.getInstance().getReference("messages");

        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        editmsg = (EditText) findViewById(R.id.editmsg);

        // 1. Posar un layout manager al recyclerview (que controla com es mostren els elements)
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(manager);

        // 2. Crear un Query
        Query query = messages.limitToLast(200);

        // 3. Posar un adaptador
        adapter = new FirebaseRecyclerAdapter<Message, MessageViewHolder>(
                Message.class,
                R.layout.chat_message,
                MessageViewHolder.class,
                messages
        ) {
            @Override
            protected void populateViewHolder(MessageViewHolder holder, Message model, int position) {
                holder.text.setText(model.text);
                holder.user.setText(model.user);
            }
        };
        recyclerview.setAdapter(adapter);
    }

    public void sendMessage(View view) {
        String text = editmsg.getText().toString();
        editmsg.setText("");
        messages.push().setValue(new Message("Groucho", text));
    }
}
