package com.example.webscrapping.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.webscrapping.model.Article;

import java.util.List;
@Dao
public interface NewDao {
    @Query("SELECT * FROM Article")
    List<Article> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Article article);

    @Update
    void update(Article article);

    @Delete
    void delete(Article article);
}
