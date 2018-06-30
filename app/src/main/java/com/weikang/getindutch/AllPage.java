package com.weikang.getindutch;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AllPage extends Fragment {
    private static final String TAG = "AllPageFragment";

    private Spinner mSortDropdown;
    private FloatingActionButton mAddButton;
    private RecyclerView mRecyclerView;

    //variables for recyclerview Adapter
    private ArrayList<Group> mGroups = new ArrayList<>();

    //Firebase variables
    //Database
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mGroupsDatabaseReference;
    private ChildEventListener mChildEventListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_page,container,false);

        mAddButton = (FloatingActionButton) view.findViewById(R.id.addBtn);
        mSortDropdown = (Spinner) view.findViewById(R.id.dropDown);


        //Initialise Adapter and recyclerview etc
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        //use getActivity() instead of (this) for context cos this is a fragment
        final AllPageAdapter mAdapter = new AllPageAdapter(mGroups, getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //initialise Firebase variables
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mGroupsDatabaseReference = mFirebaseDatabase.getReference().child("groups");

        //database child event listener
        mChildEventListener = new ChildEventListener() {
            //datasnapshot contains data at that location when listener is triggered
            //first adding group object into the arraylist, then use adpater.notifyiteminserted

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Group group = dataSnapshot.getValue(Group.class);
                mGroups.add(group);
                mAdapter.notifyItemInserted(mGroups.size() - 1);
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
        };
        //add a child event listener. SO the reference (mGroupDatabaseRef) defines which part of
        //database to listen to, mchildEventlistener defines what to do
        mGroupsDatabaseReference.addChildEventListener(mChildEventListener);


        return view;
    }
}