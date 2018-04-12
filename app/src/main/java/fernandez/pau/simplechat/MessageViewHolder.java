package fernandez.pau.simplechat;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by pablofd on 12/04/2018.
 */

public class MessageViewHolder extends RecyclerView.ViewHolder {
    public TextView user, text;

    public MessageViewHolder(View root) {
        super(root);
    }

    public MessageViewHolder(View root, TextView user, TextView text) {
        super(root);
        this.user = user;
        this.text = text;
    }
}
