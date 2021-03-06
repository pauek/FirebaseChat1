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
        this.user = root.findViewById(R.id.user);
        this.text = root.findViewById(R.id.text);
    }

}
