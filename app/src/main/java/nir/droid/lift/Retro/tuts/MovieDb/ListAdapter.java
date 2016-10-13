package nir.droid.lift.Retro.tuts.MovieDb;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import nir.droid.lift.R;
import nir.droid.lift.Retro.tuts.ApiClient;

/**
 * Created by droidcafe on 10/9/2016.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    List<MovieResponse> responses;
    Context context;

    ListAdapter(Context context,List<MovieResponse> response){
        responses = response;
        this.context = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


            if(responses.get(position) != null){
                holder.title.setText(responses.get(position).getTitle());
                holder.overview.setText(responses.get(position).getOverview());

                String uri = ApiClient.BASE_URL_IMAGE.concat(responses.get(position).getPoster());
                Log.d("la", "url " + uri +" "+responses.get(position).getTitle());


                Glide.with(context)
                        .load(uri)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.poster);
            }else{

                Glide.clear(holder.poster);
                holder.poster.setImageDrawable(null);
            }

    }


    @Override
    public int getItemCount() {
        return responses.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, overview;
        ImageView poster;
        public ViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.title);
            overview = (TextView) view.findViewById(R.id.overview);
            poster = (ImageView) view.findViewById(R.id.poster);
        }
    }
}
