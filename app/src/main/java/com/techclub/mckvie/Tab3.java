package com.techclub.mckvie;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Tab3 extends Fragment {

    private FirebaseRecyclerAdapter<object, Tab3.NewsViewHolder> mPeopleRVAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab1, container, false);
        final Context context = getActivity().getApplicationContext();

        TextView title = view.findViewById(R.id.textViewTitle);
        title.setText("EVENTS");

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Notices/Events");
        mDatabase.keepSynced(true);
        RecyclerView mPeopleRV = view.findViewById(R.id.myRecycleView1);

        DatabaseReference personsRef = FirebaseDatabase.getInstance().getReference().child("Notices/Events");
        Query personsQuery = personsRef.orderByKey();

        mPeopleRV.hasFixedSize();
        mPeopleRV.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        FirebaseRecyclerOptions personsOptions = new FirebaseRecyclerOptions.Builder<object>().setQuery(personsQuery, object.class).build();

        mPeopleRVAdapter = new FirebaseRecyclerAdapter<object, Tab3.NewsViewHolder>(personsOptions) {
            @Override
            protected void onBindViewHolder(Tab3.NewsViewHolder holder, final int position, final object model) {
                holder.setTitle(model.getTitle());
                holder.setTime(model.getTime());

                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String url = model.getUrl();
                        if(!url.equals("none")) {
                            Intent intent = new Intent(context, webview.class);
                            intent.putExtra("id", url);
                            startActivity(intent);
                        }
                    }
                });

                holder.setIsRecyclable(false);
            }

            @Override
            public Tab3.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.home_row, parent, false);

                return new Tab3.NewsViewHolder(view);
            }
        };

        mPeopleRV.setAdapter(mPeopleRVAdapter);

        return view;
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder{
        View mView;
        private String date1;

        public NewsViewHolder(View itemView){
            super(itemView);
            mView = itemView;
        }
        public void setTitle(String title){
            TextView post_title = (TextView)mView.findViewById(R.id.post_title);
            post_title.setText(title.substring(0,1).toUpperCase() + title.substring(1));
        }

        public void setTime(String time){
            ImageView newLogo = (ImageView) mView.findViewById(R.id.new_logo);
            date1 = time;

            try {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                Date post_date = format.parse(date1);
                Calendar c = Calendar.getInstance();
                c.setTime(post_date);
                c.add(Calendar.DATE, 7);
                Date futureDate = c.getTime();
                Date currentDate = Calendar.getInstance().getTime();

                if (!currentDate.after(futureDate)) {
                    newLogo.setVisibility(View.VISIBLE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mPeopleRVAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPeopleRVAdapter.stopListening();
    }

    public interface OnFragmentInteractionListener {

    }
}
