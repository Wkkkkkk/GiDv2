package com.weikang.getindutch;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AllPageAdapter extends RecyclerView.Adapter<AllPageAdapter.ViewHolder> {


    private ArrayList<Group> mGroups = new ArrayList<>();
    //private ArrayList<String> mImages = new ArrayList<>();
    private Context mContext;

    //Firebase uid details
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser mUser = mAuth.getCurrentUser();
    String mUserId = mUser.getUid();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //each dataItem is only just a string, we should create all the views needed here
        //declare all the views needed
        TextView mGroupName;
        TextView mUserBalance;
        CircleImageView mGroupImage;
        RelativeLayout mParentLayout;

        public ViewHolder(View itemView){
            super(itemView);
            mGroupName = itemView.findViewById(R.id.group_name);
            mUserBalance = itemView.findViewById(R.id.user_balance);
            mGroupImage = itemView.findViewById(R.id.group_image);
            mParentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }

    //provide a suitable constructor based on type of dataset
    //constructor will get the data we need
    public AllPageAdapter(ArrayList<Group> Groups, Context context){
        //mImageNames = imageNames;
        //mImages = images;
        mGroups = Groups;
        mContext = context;
    }

    //create new views (invoked by layout manager)
    @Override
    public AllPageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        //create new view //potential bug
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_all_page_adapter,
                parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    //replace the contents of a view(invoked by layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){
        //get images
        Glide.with(mContext)
                .asBitmap()
                .load(mGroups.get(position).getPhotoUrl())
                .into(holder.mGroupImage);

        holder.mGroupName.setText(mGroups.get(position).getName());
        holder.mUserBalance.setText(mGroups.get(position).getMembers().get(mUserId).toString());

        //sets what happens when u click the object
        holder.mParentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                Toast.makeText(mContext, mGroups.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //return size of your dataset (invoked by layout manager)
    @Override
    public int getItemCount(){
        return mGroups.size();
    }
}

