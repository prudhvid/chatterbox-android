package com.chatterbox;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class Friends extends ActionBarActivity {
    FriendsListAdapter friendsListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        friendsListAdapter=new FriendsListAdapter(Friends.this,R.layout.activity_friends);

        Intent intent=getIntent();
        String flist=intent.getStringExtra("flist");

        String[] split = flist.split(";");
        for(String personstatus:split){
            String[] fp = personstatus.split(":");
            User u=new User(fp[0],fp[0],fp[1].equals("true")?true:false);
            friendsListAdapter.add(u);
        }
        friendsListAdapter.notifyDataSetChanged();



        ListView listView=(ListView)findViewById(R.id.friends_list);

        listView.setAdapter(friendsListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 //Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_friends, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
