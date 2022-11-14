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
import ste.com.tp_restapi.models.AuthorModel;

public class AuthorAdapter extends BaseAdapter {

    private List<AuthorModel> authorModelList;

    public AuthorAdapter(List<AuthorModel> authorModelList) {
        this.authorModelList = authorModelList;
    }

    @Override
    public int getCount() {
        return authorModelList.size();
    }

    @Override
    public AuthorModel getItem(int i) {

        return this.authorModelList.get(i);
    }

    @Override
    public long getItemId(int i) {

        return getItem(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View layoutview;
        ImageView imageView;
        TextView textView;
        if(view==null){
            layoutview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.author_provider,viewGroup, false);
        }else {
            layoutview = view;
        }

        AuthorModel authorModel = getItem(i);
        imageView = layoutview.findViewById(R.id.img_author_provider);
        textView = layoutview.findViewById(R.id.txt_author_provider);
        String url = authorModel.getUrlImg();
        Picasso.get().load(url).into(imageView);
        String text = authorModel.getFirstName() + " " + authorModel.getLastName();
        textView.setText(text);
        return layoutview;
    }
}
