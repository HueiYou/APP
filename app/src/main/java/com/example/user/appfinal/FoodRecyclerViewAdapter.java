package com.example.user.appfinal;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.appfinal.data.FoodContract;

/**
 * Created by user on 2017/6/17.
 */

public class FoodRecyclerViewAdapter extends RecyclerView.Adapter<FoodRecyclerViewAdapter.FoodViewHolder>{

    private Cursor mCursor;
    private Context mContext;


    public FoodRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.grid_recyclerview, parent, false);

        return new FoodViewHolder(view);
    }


    @Override
    public void onBindViewHolder(FoodViewHolder holder, int position) {

        int idIndex = mCursor.getColumnIndex(FoodContract.FoodEntry._ID);
        int foodNameIndex = mCursor.getColumnIndex(FoodContract.FoodEntry.COLUMN_FOOD_NAME);
        int buyTimeIndex = mCursor.getColumnIndex(FoodContract.FoodEntry.COLUMN_BUY_TIME);
        int alertTimeIndex = mCursor.getColumnIndex(FoodContract.FoodEntry.COLUMN_ALERT_TIME);
        int remarkIndex = mCursor.getColumnIndex(FoodContract.FoodEntry.COLUMN_REMARK);
        mCursor.moveToPosition(position);


        final int id = mCursor.getInt(idIndex);
        String fName = mCursor.getString(foodNameIndex);
        String bTime = mCursor.getString(buyTimeIndex);
        String aTime = mCursor.getString(alertTimeIndex);
        String rem = mCursor.getString(remarkIndex);

        holder.itemView.setTag(id);
        holder.foodNameShow.setText(fName );
        holder.buyTimeShow.setText(bTime);
        holder.alertTimeShow.setText(aTime);
        holder.remarkShow.setText(rem);

    }

    @Override
    public int getItemCount() {
        if (mCursor == null) {
            return 0;
        }
        return mCursor.getCount();
    }

    public Cursor swapCursor(Cursor c) {
        // check if this cursor is the same as the previous cursor (mCursor)
        if (mCursor == c) {
            return null; // bc nothing has changed
        }
        Cursor temp = mCursor;
        this.mCursor = c; // new cursor value assigned

        //check if this is a valid cursor, then update the cursor
        if (c != null) {
            this.notifyDataSetChanged();
        }
        return temp;
    }



    class FoodViewHolder extends RecyclerView.ViewHolder {

        private TextView foodNameShow;
        private TextView buyTimeShow;
        private TextView alertTimeShow;
        private TextView remarkShow;

        public FoodViewHolder(View itemView) {
            super(itemView);

            foodNameShow = (TextView) itemView.findViewById(R.id.txt_showfoodname);
            buyTimeShow = (TextView) itemView.findViewById(R.id.txt_buytime);
            alertTimeShow = (TextView)itemView.findViewById(R.id.txt_alerttime);
            remarkShow = (TextView)itemView.findViewById(R.id.txt_remark);
        }

    }
}
