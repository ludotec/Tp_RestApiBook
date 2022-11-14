package ste.com.tp_restapi.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ste.com.tp_restapi.R;
import ste.com.tp_restapi.models.Volume;

public class BookAdapter extends BaseAdapter {

    private List<Volume> results;

    public BookAdapter(List<Volume> results) {
        this.results = results;

    }
    @Override
    public int getCount() {

        return results.size();
    }

    @Override
    public Volume getItem(int i) {
            return results.get(i);
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }



    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View view;
        ImageView imageViewList;
        TextView txtAuthor;
        Volume info = new Volume();
        // optimizacion de la vista
        if(convertView==null){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_provider, null);
        }else
        {
            view = convertView;
        }

        info = getItem(i);

        txtAuthor = view.findViewById(R.id.txtAuthor);
        imageViewList = view.findViewById(R.id.imgSmallThumbnailAuthor);

        if (info.getVolumeInfo().getImageLinks() != null){
            String url = info.getVolumeInfo().getImageLinks().getThumbnail()
                                .replace("http://", "https://");
            Picasso.get().load(url).placeholder(R.drawable.ic_launcher_foreground).into(imageViewList);
        }
        txtAuthor.setText(info.getVolumeInfo().getTitle());
        return view;
    }
}
