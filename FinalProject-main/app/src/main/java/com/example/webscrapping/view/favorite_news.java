package com.example.webscrapping.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.webscrapping.MainAdapterTwo;
import com.example.webscrapping.R;
import com.example.webscrapping.database.NewsDataBase;
import com.example.webscrapping.databinding.FragmentFavoriteNewsBinding;
import com.example.webscrapping.model.Article;

import java.util.List;

public class favorite_news extends Fragment {
    RecyclerView recyclerView;
    List<Article> articles;
    MainAdapterTwo mainAdapter;
    NewsDataBase newsDataBase;
    TextView textView_no_article;

    private FragmentFavoriteNewsBinding binding;

    /* This method below is the onCreateView method of a fragment in an Android app. The method inflates the layout for the fragment,
     which includes a recycler view and a TextView for displaying a message when there are no articles in the recycler view.
     The method sets the mainAdapter as the adapter for the recycler view and sets a DividerItemDecoration on the recycler view
     to add dividers between the items. The method also retrieves a list of articles from a database using a NewsDataBase instance
     and a DAO object, and sets the list of articles on the mainAdapter. If the list of articles is empty, the method sets the visibility
     of the TextView to View.VISIBLE to display the message. Finally, the method returns the inflated view. This method likely initializes
     the layout and adapter for the fragment and populates the recycler view with articles from a database.*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFavoriteNewsBinding.inflate(inflater,
                container, false);
        View view = binding.getRoot();

        textView_no_article = binding.noArticle;
        mainAdapter = new MainAdapterTwo(getActivity(), textView_no_article);
        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        newsDataBase = NewsDataBase.getInstance(this.getContext());
        articles = newsDataBase.newDao().getAll();
        Log.i("size", articles.size() + "");
        if (articles.size() <= 0) {
            textView_no_article.setVisibility(View.VISIBLE);
        }
        mainAdapter.setArticles(articles);
        recyclerView.setAdapter(mainAdapter);
        return view;
    }
}