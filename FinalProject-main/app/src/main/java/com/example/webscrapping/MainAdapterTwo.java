package com.example.webscrapping;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.webscrapping.database.NewsDataBase;
import com.example.webscrapping.databinding.DisplayPrototypeBinding;
import com.example.webscrapping.model.Article;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainAdapterTwo extends RecyclerView.Adapter<MainAdapterTwo.MyViewHolder2> {

    List<Article> articles;
    Activity activity;
    NewsDataBase newsDataBase;
    TextView textView_for_noArticle;


    public MainAdapterTwo(Activity activity, TextView textView) {
        this.activity = activity;
        textView_for_noArticle = textView;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_prototypetwo, parent, false);
        return new MyViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder2 holder, int position) {
        holder.title.setText(articles.get(position).getTitle());
        holder.description.setText(articles.get(position).getDescription());
        Picasso.get()
                .load(articles.get(position).getImageUrl())
                .into(holder.img);

       /* This is a lambda type of function that is checking which article was click and direct the user to the reading fragment.
        also it checks if any of the data being receive is null, if yes the user wont be able to read the article.
        if there is one data that is null, a snakBar will be generated to let the user know*/
        holder.constraintLayout.setOnClickListener(x -> {
            if (articles.get(position).getTitle() != null && articles.get(position).getImageUrl() != null && articles.get(position).getContent() != null) {
                Bundle bundle = new Bundle();
                bundle.putString("title", articles.get(position).getTitle());
                bundle.putString("imageUrl", articles.get(position).getImageUrl().toString());
                bundle.putString("content", articles.get(position).getContent());
                bundle.putString("sourceUrl", articles.get(position).getUrlSource());
                Navigation.findNavController(holder.itemView).navigate(R.id.action_favorite_news_to_reading_Fragment, bundle);
            } else {
                Snackbar snackbar = Snackbar.make(holder.constraintLayout, R.string.error, Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
 /*   This code is setting an onClickListener on a "likeButton" and checking if the article at the current position has a title, image URL, and content.
                If all three are not null, the article is inserted into the database using the NewsDataBase object.
        If the article is successfully added to the database, a Snackbar is displayed to confirm. If the article does not have a title,
        image URL, or content, an error message is displayed in a Snackbar.*/
        holder.deleteButton.setOnClickListener(click -> {
            newsDataBase = NewsDataBase.getInstance(holder.deleteButton.getContext());
            newsDataBase.newDao().delete(articles.get(position));
            Log.i("size", articles.size() + "");
            notifyItemRemoved(position);
            articles.remove(position);
            if (articles.size() <= 0) {
                textView_for_noArticle.setVisibility(View.VISIBLE);
            }
            Snackbar snackbar = Snackbar.make(holder.constraintLayout, R.string.deleteConfirmation, Snackbar.LENGTH_LONG);
            snackbar.show();
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    /*    This class is a ViewHolder for an RecyclerView and is used to hold and reference the views in a list item layout.
           It has several member variables for the TextView, ImageView, ConstraintLayout, and Button that are in the layout.
           The ViewHolder is constructed by inflating the layout and then calling findViewById on each of the views to get a reference to them.
           It also sets an OnClickListener on the entire item view to perform an action when the item is clicked.*/
    class MyViewHolder2 extends RecyclerView.ViewHolder {
        TextView title, description;
        ImageView img;
        ConstraintLayout constraintLayout;
        Button deleteButton;

        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTwo);
            description = itemView.findViewById(R.id.descriptionTwo);
            img = itemView.findViewById(R.id.imgTwo);
            deleteButton = itemView.findViewById(R.id.delete);
            constraintLayout = itemView.findViewById(R.id.main_layout_two);
        }
    }
}