package com.example.webscrapping;

import android.app.Activity;
import android.os.Binder;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
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
import androidx.viewbinding.ViewBinding;

import com.example.webscrapping.database.NewsDataBase;
import com.example.webscrapping.databinding.DisplayPrototypeBinding;
import com.example.webscrapping.databinding.FragmentHomeArticleBinding;
import com.example.webscrapping.model.Article;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {

    List<Article> articles;
    Activity activity;

    public MainAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //RecipeCardBinding itemBinding = RecipeCardBinding .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        //return new ViewHolder(itemBinding);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_prototype, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(articles.get(position).getTitle());
        holder.description.setText(articles.get(position).getDescription());
        Picasso.get().load(articles.get(position).getImageUrl()).into(holder.img);
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
                //action =  actionGameBoardToGameOver(moveCount + "");
                Navigation.findNavController(holder.itemView).navigate(R.id.action_homeArticle_to_reading_Fragment, bundle);

            } else {
                Snackbar snackbar = Snackbar.make(holder.constraintLayout, R.string.error, Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });

     /*   This code is setting an onClickListener on a "likeButton" and checking if the article at the current position has a title, image URL, and content.
                If all three are not null, the article is inserted into the database using the NewsDataBase object.
        If the article is successfully added to the database, a Snackbar is displayed to confirm. If the article does not have a title,
        image URL, or content, an error message is displayed in a Snackbar.*/
        holder.likeButton.setOnClickListener(click -> {
            if (articles.get(position).getTitle() != null && articles.get(position).getImageUrl() != null && articles.get(position).getContent() != null) {
                NewsDataBase newsDataBase = NewsDataBase.getInstance(holder.likeButton.getContext());
                newsDataBase.newDao().insert(articles.get(position));
                Snackbar.make(holder.constraintLayout, R.string.faveConfirm, Snackbar.LENGTH_LONG).show();
            } else {
                Snackbar snackbar = Snackbar.make(holder.constraintLayout, R.string.error2, Snackbar.LENGTH_LONG);
                snackbar.show();
            }
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
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, description;
        ImageView img;
        ConstraintLayout constraintLayout;
        Button likeButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            img = itemView.findViewById(R.id.img);
            likeButton = itemView.findViewById(R.id.likeButton);
            constraintLayout = itemView.findViewById(R.id.main_layout_item);
            itemView.setOnClickListener(x -> {/*do something here if one item in listview was clicked*/ });
        }
    }
}