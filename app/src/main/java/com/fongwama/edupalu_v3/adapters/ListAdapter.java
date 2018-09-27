package com.fongwama.edupalu_v3.adapters;

import android.content.Context;
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

    private Context mContext;
    private List<PlaceModel> data;
    private List<PlaceModel> dataFilterd;
    private PlaceAdapterListener listener;


    public ListAdapter(Context mContext, ArrayList<PlaceModel> data,PlaceAdapterListener listener) {
        this.mContext = mContext;
        this.data = data;
        this.listener = listener;
        this.dataFilterd = data;
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
        return dataFilterd.size();
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onContactSelected(dataFilterd.get(getAdapterPosition()));
                }
            });
        }
    }

    public void initData(ArrayList<PlaceModel> p){
        dataFilterd.clear();
        data.addAll(p);
        notifyDataSetChanged();
    }


    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String charString = constraint.toString();
            if(charString.isEmpty()){
                dataFilterd = data;
            }else {
                ArrayList<PlaceModel> filteredList = new ArrayList<>();
                for (PlaceModel placeItem : data) {
                    if (placeItem.getName().toLowerCase().contains(charString.toLowerCase()) || placeItem.getAddress().contains(charString)) {
                        filteredList.add(placeItem);
                    }
                }

            }


            FilterResults results = new FilterResults();
            results.values = results;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dataFilterd = (ArrayList<PlaceModel>) results.values;

//            dataFilterd.clear();
//            dataFilterd.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };


    public interface PlaceAdapterListener {
        void onContactSelected(PlaceModel placeModel);
    }


}


