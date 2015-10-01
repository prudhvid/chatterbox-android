package com.chatterbox;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FriendsListAdapter extends ArrayAdapter<User> {

    private TextView chatText;
    private List<User> friendsList = new ArrayList<User>();
    private LinearLayout singleMessageContainer;
    SparseBooleanArray mSparseBooleanArray;

    @Override
    public void add(User object) {
        friendsList.add(object);
        super.add(object);
    }

    public FriendsListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        mSparseBooleanArray=new SparseBooleanArray();
    }



    public int getCount() {
        return this.friendsList.size();
    }

    public User getItem(int index) {
        return this.friendsList.get(index);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.activity_single_message, parent, false);
        }
        TextView name=(TextView)row.findViewById(R.id.friend_name);
        TextView status=(TextView)row.findViewById(R.id.friend_status);
        name.setText(friendsList.get(position).name);
        status.setText(friendsList.get(position).getOnlineStatus()?"online":"offline");
        return row;
    }


    public Bitmap decodeToBitmap(byte[] decodedByte) {
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }

}