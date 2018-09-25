package com.fongwama.edupalu_v3.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import android.widget.Filter;
import com.fongwama.edupalu_v3.R;
import com.fongwama.edupalu_v3.model.PlaceModel;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{

    private Context mContext = null;
    private ArrayList<PlaceModel> data = null;


    public ListAdapter(Context mContext, ArrayList<PlaceModel> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        //Glide.with(myContext).load(R.drawable.ic_pharmacie).crossFade()into(viewHolder.img_pharma);
        holder.name_pharma.setText(data.get(i).getName());

        if(holder.name_pharma.getText().toString().toLowerCase().contains("nuit")){
            holder.img_pharma.setImageResource(R.drawable.ic_brightness_3_black_24dp);
        }else {
            holder.img_pharma.setImageResource(R.drawable.ic_wb_sunny_black_24dp);
        }


        //holder.distance_pharma.setText(""+data.get(i).getId());
        holder.adresse_pharma.setText(data.get(i).getAddress());
        holder.ville_pharma.setText(data.get(i).getCity());
        holder.phone_pharma.setText(data.get(i).getTel1() + " / "+ data.get(i).getTel2());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public final class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img_pharma;
        TextView name_pharma, distance_pharma, adresse_pharma, ville_pharma, phone_pharma;

        public ViewHolder(View itemView) {
            super(itemView);
            img_pharma = (ImageView) itemView.findViewById(R.id.imageTypePharma);
            name_pharma = (TextView)itemView.findViewById(R.id.nomPharma);
            //distance_pharma = (TextView)itemView.findViewById(R.id.distance_pharma);
            adresse_pharma = (TextView)itemView.findViewById(R.id.addr_pharma);
            ville_pharma = (TextView)itemView.findViewById(R.id.villePharma);
            phone_pharma = (TextView)itemView.findViewById(R.id.contact_1_pharma);
        }
    }



    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<PlaceModel> filteredList = new ArrayList<PlaceModel>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(data);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (PlaceModel placeItem : data) {
                    if (placeItem.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(placeItem);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            data.clear();
            data.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

}


