package com.example.renu.fireuserget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String name;
  FirebaseDatabase db=FirebaseDatabase.getInstance();
  DatabaseReference myrootref=db.getReference();
  DatabaseReference userref=myrootref.child("Users");
  ListView listView;
    ArrayList<String> usernames=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listview);
        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,usernames);
        listView.setAdapter(arrayAdapter);
        userref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //return the snapshot of existing data and also add new child
                //snapshot gives te jason object  of all the value it contain
                String value=dataSnapshot.getValue().toString();
                try {
                    JSONObject object=new JSONObject(value);
                    Log.d("renu2222",object.toString());
                    name=object.getString("email");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                usernames.add(name);
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
