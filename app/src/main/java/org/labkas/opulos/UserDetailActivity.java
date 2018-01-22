package org.labkas.opulos;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.labkas.opulos.models.User;
import org.labkas.opulos.models.Badge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDetailActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "UserDetailActivity";

    public static final String EXTRA_USER_ID = "uid";

    private DatabaseReference mUserReference;
    private DatabaseReference mBadgesReference;
    private ValueEventListener mUserListener;
    private String mUserID;

    private TextView mAuthorView;
    //add other views here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userdetail_main);

        // Get job key from intent
        mUserID = getIntent().getStringExtra(EXTRA_USER_ID);
        if (mUserID == null) {
            throw new IllegalArgumentException("Must pass EXTRA_USER_ID");
        }

        // Initialize Database
        mUserReference = FirebaseDatabase.getInstance().getReference()
                .child("users").child(mUserID);
        mBadgesReference = FirebaseDatabase.getInstance().getReference()
                .child("user-badges").child(mUserID);

        // Initialize Views
        mAuthorView = findViewById(R.id.post_author);
    }

    @Override
    public void onStart() {
        super.onStart();

        // Add value event listener to the post
        // [START user_value_event_listener]
        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                User user = dataSnapshot.getValue(User.class);
                // [START_EXCLUDE]
                mAuthorView.setText(user.aboutMe);
                // [END_EXCLUDE]
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting User failed, log a message
                Log.w(TAG, "loadUser:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                Toast.makeText(UserDetailActivity.this, "Failed to load post.",
                        Toast.LENGTH_SHORT).show();
                // [END_EXCLUDE]
            }
        };
        mUserReference.addValueEventListener(userListener);
        // [END user_value_event_listener]

        // Keep copy of post listener so we can remove it when app stops
        mUserListener = userListener;

    }

    @Override
    public void onStop() {
        super.onStop();

        // Remove post value event listener
        if (mUserListener != null) {
            mUserReference.removeEventListener(mUserListener);
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
//        if (i == R.id.button_post_comment) {
//            postComment();
//        }
    }

}
