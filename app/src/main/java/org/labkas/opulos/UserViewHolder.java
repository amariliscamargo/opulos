package org.labkas.opulos;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.labkas.opulos.models.User;

public class UserViewHolder extends RecyclerView.ViewHolder {

    public TextView titleView;

    public UserViewHolder(View itemView) {
        super(itemView);

        titleView = itemView.findViewById(R.id.post_title);
    }

    public void bindToUser(User user, View.OnClickListener starClickListener) {
        titleView.setText(user.aboutMe);
    }
}
