package com.samset.simplenavigationdrawer.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.samset.simplenavigationdrawer.R;
import com.samset.simplenavigationdrawer.listeners.OnItemListener;
import com.samset.simplenavigationdrawer.model.NavDrawerItem;

import java.util.List;


/**
 * Created by samset on 30-09-2015.
 */
public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.MyHolderView> {

    List<NavDrawerItem> data;
    static Activity context;
    int icons[];
    private static OnItemListener listner;

    public OnItemListener getListner() {
        return listner;
    }

    public void setListner(OnItemListener listner) {
        this.listner = listner;
    }

    public NavigationDrawerAdapter(Activity ctx, List<NavDrawerItem> datalist, int[] icon) {
        NavigationDrawerAdapter.context = ctx;

        this.data = datalist;
        this.icons = icon;
    }



    @Override
    public int getItemCount() {
        // TODO Auto-generated method stub
        return data.size();
    }


    @Override
    public NavigationDrawerAdapter.MyHolderView onCreateViewHolder(
            ViewGroup root_parent, int position) {
        View v = LayoutInflater.from(root_parent.getContext()).inflate(
                R.layout.nav_drawer_row, root_parent, false);
        MyHolderView holdview = new MyHolderView(v);
        return holdview;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    public void onBindViewHolder(MyHolderView holder, int position) {

       // Log.e("Ada"," Icon size "+icons.length);
        if (icons != null && data != null) {
            try {
                holder.list_item.setText("" + data.get(position).getTitle().toString());
                holder.icons.setImageResource(icons[position]);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }


        }


    }
    public class MyHolderView extends RecyclerView.ViewHolder

    {

        TextView list_item;
        ImageView icons;

        public MyHolderView(View view) {
            super(view);
            view.setClickable(true);

            list_item = (TextView) view
                    .findViewById(R.id.tvDrawer_title);
            icons = (ImageView) view
                    .findViewById(R.id.ivdrawer_icon);

            list_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listner != null) {
                        listner.OnItemClick(v, getPosition());
                    } else {
                        Log.e("Adapter", " Listener Null");
                    }
                }
            });


        }


    }


}
