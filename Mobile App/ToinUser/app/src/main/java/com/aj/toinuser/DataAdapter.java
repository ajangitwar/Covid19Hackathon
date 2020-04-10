package com.aj.toinuser;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aj.toinuser.Modal.Shops;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private Context mcontext;
    private List<Shops> plotList;
    String TAG = "hello";

    public DataAdapter(Context mcontext, List<Shops> plotList) {
        this.plotList = plotList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_listing_card,viewGroup,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        Log.d(TAG,"called");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.tv1.setText(plotList.get(i).getName());
        viewHolder.tv2.setText(plotList.get(i).getAddress());

        viewHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(mcontext, Details.class);
                j.putExtra("owner_name",plotList.get(i).getOwner_name());
                j.putExtra("mcount",plotList.get(i).getMask_count());
                j.putExtra("scount",plotList.get(i).getSanitize_count());
                j.putExtra("email",plotList.get(i).getEmail());
                j.putExtra("mobile",plotList.get(i).getMobile());
                j.putExtra("name",plotList.get(i).getName());
                mcontext.startActivity(j);
            }
        });
    }

    @Override
    public int getItemCount() {
        return plotList.size();
    }




    public static class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv1,tv2;
            CardView cv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.shop_name);
            tv2= itemView.findViewById(R.id.shop_address);
            cv = itemView.findViewById(R.id.cardview);
        }
    }
}
