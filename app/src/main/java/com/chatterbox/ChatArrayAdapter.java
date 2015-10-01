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

public class ChatArrayAdapter extends ArrayAdapter<ChatMessage> {

	private TextView chatText;
	private List<ChatMessage> chatMessageList = new ArrayList<ChatMessage>();
	private LinearLayout singleMessageContainer;
	SparseBooleanArray mSparseBooleanArray;

	@Override
	public void add(ChatMessage object) {
		chatMessageList.add(object);
		super.add(object);
	}

	public ChatArrayAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
		mSparseBooleanArray=new SparseBooleanArray();
	}

	public ArrayList<ChatMessage> getCheckedItems()
	{
		ArrayList<ChatMessage> mTempArry = new ArrayList<ChatMessage>();

		for(int i=0;i<chatMessageList.size();i++) {
			if (mSparseBooleanArray.get(i)) {
				mTempArry.add(chatMessageList.get(i));
			}
		}
		return mTempArry;
	}

	public int getCount() {
		return this.chatMessageList.size();
	}

	public ChatMessage getItem(int index) {
		return this.chatMessageList.get(index);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.activity_single_message, parent, false);
		}

		singleMessageContainer = (LinearLayout) row.findViewById(R.id.singleMessageContainer);
		ChatMessage chatMessageObj = getItem(position);
		chatText = (TextView) row.findViewById(R.id.singleMessage);
		chatText.setText(chatMessageObj.message);
		chatText.setBackgroundResource(chatMessageObj.left ? R.drawable.bubble_a : R.drawable.bubble_b);
		singleMessageContainer.setGravity(chatMessageObj.left ? Gravity.LEFT : Gravity.RIGHT);

		CheckBox checkBox=(CheckBox)row.findViewById(R.id.incheckbox);
		checkBox.setChecked(mSparseBooleanArray.get(position));
		checkBox.setTag(position);
		checkBox.setOnCheckedChangeListener(mCheckedChangeListener);
		return row;
	}

	CompoundButton.OnCheckedChangeListener mCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			mSparseBooleanArray.put((Integer) buttonView.getTag(), isChecked);

			int c=0;
			int start=-1,end;

			int lsize=chatMessageList.size();

			for(int i=0;i<lsize;i++){
				if(mSparseBooleanArray.get(i)) {
					c++;
					if (start == -1)
						start = i;
					else {
						for (int j = lsize - 1; j >= i; j--) {
							if (mSparseBooleanArray.get(j)) {
								end = j;
								for(int k=start;k<=end;k++)
									mSparseBooleanArray.put(k,true);

							}
						}
					}
				}
				else{
					int y=10;
				}
			}
		}
	};

	public void clearCheckedItems()
	{
		for (int i=0;i<chatMessageList.size();i++)
		{
			if(mSparseBooleanArray.get(i))
				mSparseBooleanArray.put(i,false);
		}
	}
	public Bitmap decodeToBitmap(byte[] decodedByte) {
		return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
	}

}