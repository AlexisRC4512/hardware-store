package CountBank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newappbank.R;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private List<Articulo> mdata;
    private LayoutInflater mInflate;
    private Context context;
    final ArticleAdapter.OnItemClickListener listener;
    public interface OnItemClickListener{
        void onItemClick(Articulo item);
    }
    public ArticleAdapter(List<Articulo> itemList,Context context,ArticleAdapter.OnItemClickListener listener){
        this.mInflate=LayoutInflater.from(context);
        this.context=context;
        this.mdata=itemList;
        this.listener=listener;
    }


    @Override
    public int getItemCount() {
        return mdata.size();
    }
    @Override
    public ArticleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=mInflate.from(parent.getContext()).inflate(R.layout.articuloelement,parent,false);
        return new ArticleAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final   ArticleAdapter.ViewHolder holder,final int position) {
        holder.cv.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_animatio));
        holder.binData(mdata.get(position));
    }



    public void setItem(List<Articulo> items){
        mdata=items;
  }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ArticlImg;
        TextView   Nombre,Descripcion,precio;
        CardView cv;
         ViewHolder(@NonNull View itemView) {
            super(itemView);
            ArticlImg=itemView.findViewById(R.id.articleImg);
            Nombre=itemView.findViewById(R.id.nameTextArt);
            Descripcion=itemView.findViewById(R.id.DescipcionText);
            precio=itemView.findViewById(R.id.Price);
            cv=itemView.findViewById(R.id.cv);
        }
        void binData(final Articulo item){
             Nombre.setText(item.getNombre());
             Descripcion.setText(item.getDescripcion());
            precio.setText(String.valueOf(item.getPrecio()));
            ArticlImg.setImageResource(item.getImg());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
