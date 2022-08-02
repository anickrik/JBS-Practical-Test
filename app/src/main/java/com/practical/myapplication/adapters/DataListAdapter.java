package com.practical.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.practical.myapplication.MainActivity;
import com.practical.myapplication.R;
import com.practical.myapplication.models.DataModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DataListAdapter extends RecyclerView.Adapter<DataListAdapter.myviewholder>
{
    Context context;
    List<DataModel> listdata;
    RequestOptions option;
    private Object String;


    public DataListAdapter(Context context, List<DataModel> listdata) {
        this.context = context;
        this.listdata = listdata;

        option = new RequestOptions().centerCrop()
                .placeholder(R.drawable.loading_shape)
                .error(R.drawable.loading_shape2);
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_list,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {

        holder.albumId.setText(listdata.get(position).getAlbumId());
        holder.id.setText(listdata.get(position).getId());
        holder.title.setText(listdata.get(position).getTitle());

       // Glide.with(context).load(listdata.get(position)
         //       .getThumbnailUrl()).apply(option)
           //     .into(holder.imageView);

        Picasso.with(context).load(listdata.get(position).getUrl()).fit().centerCrop()
                .placeholder(R.drawable.loading_shape)
                .error(R.drawable.loading_shape2)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder{

        TextView albumId;
        TextView id;
        TextView title;
        ImageView imageView;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            albumId=itemView.findViewById(R.id.tvablumid);
            id=itemView.findViewById(R.id.tvid);
            title=itemView.findViewById(R.id.tvtitle);
            imageView=itemView.findViewById(R.id.imageview);


        }
    }
}
