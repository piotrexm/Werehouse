package com.example.piotrek.warehouse.data;

import java.util.List;

/**
 * Created by Piotrek on 2017-06-10.
 */

public interface IDataProvider {
    List<Category> getCategories();
    List<Category> getCategories(String column, String order);
    Category getCategoryById(int id);
    void addCategory(Category category);
    void removeCategory(int id);

    List<Provider> getProviders();
    List<Provider> getProviders(String column, String order);
    Provider getProviderById(int id);
    void addProvider(Provider provider);
    void removeProvider(int id);

    List<Article> getArticles();
    List<Article> getArticles(String column, String order);
    Article getArticleById(int id);
    void addArticle(Article article);
    void removeArticle(int id);

}
