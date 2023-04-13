/*This code is a fragment for an Android app that appears to use web scraping to gather news articles and display them in a recycler view.
        The fragment defines a layout with a search bar and a recycler view.
        When the user inputs a search query in the search bar,
        the app fetches news articles related to the query and displays them in the recycler view.
        The app uses the NewsApiClient library to fetch the articles.*/

package com.example.webscrapping.view;

/*This code is a collection of import statements for a Java file. The imports include classes from the Android framework,
        classes from the com.example.webscrapping package, and classes from the com.kwabenaberko.newsapilib package.
        The imported classes include Fragment, DividerItemDecoration, LinearLayoutManager, EditText, TextView, MainAdapter,
        FragmentHomeArticleBinding, Article, NewsApiClient, EverythingRequest, and ArticleResponse.
        These classes are likely used in the Java file to create an Android fragment for displaying news articles in a recycler view.*/

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.example.webscrapping.MainAdapter;
import com.example.webscrapping.databinding.FragmentHomeArticleBinding;
import com.example.webscrapping.model.Article;
import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.request.EverythingRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import java.util.ArrayList;
import java.util.List;


public class HomeArticle extends Fragment {
    EditText search;
    List<Article> articles;
    NewsApiClient newsApiClient;
    MainAdapter mainAdapter;
    String editString;
    Thread thread;
    private FragmentHomeArticleBinding binding;


  /*  This code is the onCreateView method of a fragment for an Android app. The method inflates the layout for the fragment, which
    includes a search bar and a recycler view. The method sets an OnEditorActionListener on the search bar, which triggers a call to
    the getArticle method with the search query as input when the user submits the query. The method also sets the mainAdapter as the
        adapter for the recycler view and initializes the newsApiClient with an API key. If the search bar is empty, the method fetches articles
    related to the query "trending". Finally, the method returns the inflated view.*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeArticleBinding.inflate(inflater,
                container, false);
        View view = binding.getRoot();
        String strText = getArguments().getString("word");
        System.out.println(strText);
        mainAdapter = new MainAdapter(getActivity());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(binding.recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        //newsApiClient = new NewsApiClient("e74dd691c74a481a9e2daab92f4bd09c");
        newsApiClient = new NewsApiClient("bba5ff0bff954c028969acc7c3427a90");
        articles = new ArrayList<>();
        binding.search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                //getArticle(search_edittext.getText().toString());
                new Thread(() -> getArticle(binding.search.getText().toString())).start();
                editString = binding.search.getText().toString();
                return false;
            }
        });
        if (binding.search.getText().toString().length() <= 0) {
            new Thread(() -> getArticle("trending")).start();
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getArticle(editString);
    }

    @Override
    public void onPause() {
        super.onPause();

    }

/*    This code is a method that fetches news articles from a news API using the NewsApiClient library.
    The method takes a query string as input and uses it to build a request to the API. The request is sent asynchronously, and when the response is received,
    the method parses the response and adds the articles to a list. The method then updates the adapter of the recycler view to display the new list of articles.
    If there is an error while fetching the articles, the method logs the error message to the console.*/
    private void getArticle(String query) {

        newsApiClient.getEverything(
                new EverythingRequest.Builder()
                        .q(query)
                        .language("en")
                        .pageSize(100)
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        System.out.println(response.getArticles().size());
                        articles.clear();
                        for (int i = 0; i < response.getArticles().size(); i++) {
                            com.kwabenaberko.newsapilib.models.Article article = response.getArticles().get(i);
                            articles.add(new Article(i,
                                    article.getAuthor(),
                                    article.getTitle(),
                                    article.getDescription(),
                                    article.getUrl(),
                                    article.getUrlToImage(),
                                    article.getContent(),
                                    article.getPublishedAt()));
                        }
                        mainAdapter.setArticles(articles);
                        binding.recyclerView.setAdapter(mainAdapter);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                    }
                }
        );
    }
}