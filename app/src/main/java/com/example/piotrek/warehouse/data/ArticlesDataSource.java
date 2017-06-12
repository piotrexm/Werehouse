package com.example.piotrek.warehouse.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piotrek on 2017-06-10.
 */

public class ArticlesDataSource implements IDataProvider {

    private DatabaseHelper helper;
    private SQLiteDatabase database;

    public final static String CATEGORIES_TABLE = "categories";
    public final static String ID_COL = "_id";
    public final static String NAME_COL = "name";
    public final static String ID_PARENT_COL = "id_parent";

    public final static String PROVIDERS_TABLE = "providers";
    public final static String TEL_COL = "tel";
    public final static String ADDRESS_COL = "address";

    public final static String ARTICLES_TABLE = "articles";
    public final static String PRICE_COL = "price";
    public final static String ID_CATEGORY_COL = "id_category";
    public final static String ID_PROVIDER_COL = "id_provider";

    public ArticlesDataSource(Context context) {
        this.helper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        this.database = helper.getWritableDatabase();
    }

    public void close() {
        this.helper.close();
    }

    @Override
    public List<Category> getCategories() {
        Cursor cursor = database.query(CATEGORIES_TABLE, new String[]{ID_COL, NAME_COL, ID_PARENT_COL}, null, null, null, null, null);

        ArrayList<Category> categories = new ArrayList<Category>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Category category;
            if (cursor.isNull(cursor.getColumnIndex(ID_PARENT_COL))) {
                category = new Category(cursor.getInt(cursor.getColumnIndex(ID_COL)), cursor.getString(cursor.getColumnIndex(NAME_COL)), null);
            } else {
                category = new Category(cursor.getInt(cursor.getColumnIndex(ID_COL)), cursor.getString(cursor.getColumnIndex(NAME_COL)), getCategoryById(cursor.getColumnIndex(ID_PARENT_COL)));
            }
            categories.add(category);
            cursor.moveToNext();
        }
        cursor.close();
        return categories;
    }

    @Override
    public List<Category> getCategories(String column, String order) {
        Cursor cursor = database.query(CATEGORIES_TABLE, new String[]{ID_COL, NAME_COL, ID_PARENT_COL}, null, null, null, column + " " + order, null);

        ArrayList<Category> categories = new ArrayList<Category>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Category category;
            if (cursor.isNull(cursor.getColumnIndex(ID_PARENT_COL))) {
                category = new Category(cursor.getInt(cursor.getColumnIndex(ID_COL)), cursor.getString(cursor.getColumnIndex(NAME_COL)), null);
            } else {
                category = new Category(cursor.getInt(cursor.getColumnIndex(ID_COL)), cursor.getString(cursor.getColumnIndex(NAME_COL)), getCategoryById(cursor.getColumnIndex(ID_PARENT_COL)));
            }
            categories.add(category);
            cursor.moveToNext();
        }
        cursor.close();
        return categories;
    }

    @Override
    public Category getCategoryById(int id) {
        this.database.rawQuery("SELECT * FROM " + CATEGORIES_TABLE + " WHERE " + ID_COL + " = ?", new String[]{Long.toString(id)});

        Cursor cursor = this.database.query(CATEGORIES_TABLE, new String[]{ID_COL, NAME_COL, ID_PARENT_COL}, ID_COL + " = " + id, null, null, null, null);
        cursor.moveToFirst();
        Category category = new Category(cursor.getInt(cursor.getColumnIndex(ID_COL)), cursor.getString(cursor.getColumnIndex(NAME_COL)), getCategoryById(cursor.getColumnIndex(ID_PARENT_COL)));
        cursor.close();

        return category;
    }

    @Override
    public void addCategory(Category category) {
        ContentValues values = new ContentValues();
        values.put(NAME_COL, category.getName());
        if (category.getParent() != null) {
            values.put(ID_PARENT_COL, category.getParent().getId());
        }
        long recordId = this.database.insert(CATEGORIES_TABLE, null, values);
    }

    @Override
    public void removeCategory(int id) {
        this.database.delete(CATEGORIES_TABLE, ID_COL + " = ?", new String[]{Long.toString(id)});
    }

    @Override
    public List<Provider> getProviders() {
        Cursor cursor = database.query(PROVIDERS_TABLE, new String[]{ID_COL, NAME_COL, TEL_COL, ADDRESS_COL}, null, null, null, null, null);

        ArrayList<Provider> providers = new ArrayList<Provider>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Provider provider = new Provider(cursor.getInt(cursor.getColumnIndex(ID_COL)), cursor.getString(cursor.getColumnIndex(NAME_COL)), cursor.getString(cursor.getColumnIndex(TEL_COL)), cursor.getString(cursor.getColumnIndex(ADDRESS_COL)));

            providers.add(provider);
            cursor.moveToNext();
        }
        cursor.close();
        return providers;
    }

    @Override
    public List<Provider> getProviders(String column, String order) {
        Cursor cursor = database.query(PROVIDERS_TABLE, new String[]{ID_COL, NAME_COL, TEL_COL, ADDRESS_COL}, null, null, null, column + " " + order, null);

        ArrayList<Provider> providers = new ArrayList<Provider>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Provider provider = new Provider(cursor.getInt(cursor.getColumnIndex(ID_COL)), cursor.getString(cursor.getColumnIndex(NAME_COL)), cursor.getString(cursor.getColumnIndex(TEL_COL)), cursor.getString(cursor.getColumnIndex(ADDRESS_COL)));

            providers.add(provider);
            cursor.moveToNext();
        }
        cursor.close();
        return providers;
    }

    @Override
    public Provider getProviderById(int id) {
        this.database.rawQuery("SELECT * FROM " + PROVIDERS_TABLE + " WHERE " + ID_COL + " = ?", new String[]{Long.toString(id)});

        Cursor cursor = this.database.query(PROVIDERS_TABLE, new String[]{ID_COL, NAME_COL, TEL_COL, ADDRESS_COL}, ID_COL + " = " + id, null, null, null, null);
        cursor.moveToFirst();
        Provider provider = new Provider(cursor.getInt(cursor.getColumnIndex(ID_COL)), cursor.getString(cursor.getColumnIndex(NAME_COL)), cursor.getString(cursor.getColumnIndex(TEL_COL)), cursor.getString(cursor.getColumnIndex(ADDRESS_COL)));
        cursor.close();

        return provider;
    }

    @Override
    public void addProvider(Provider provider) {
        ContentValues values = new ContentValues();
        values.put(NAME_COL, provider.getName());
        values.put(TEL_COL, provider.getTel());
        values.put(ADDRESS_COL, provider.getAddress());
        long recordId = this.database.insert(PROVIDERS_TABLE, null, values);
    }

    @Override
    public void removeProvider(int id) {
        this.database.delete(PROVIDERS_TABLE, ID_COL + " = ?", new String[]{Long.toString(id)});
    }

    @Override
    public List<Article> getArticles() {
        Cursor cursor = database.query(ARTICLES_TABLE, new String[]{ID_COL, NAME_COL, PRICE_COL, ID_CATEGORY_COL, ID_PROVIDER_COL}, null, null, null, null, null);

        ArrayList<Article> articles = new ArrayList<Article>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Article article = new Article(cursor.getInt(cursor.getColumnIndex(ID_COL)), cursor.getString(cursor.getColumnIndex(NAME_COL)), cursor.getFloat(cursor.getColumnIndex(PRICE_COL)), getCategoryById(cursor.getInt(cursor.getColumnIndex(ID_CATEGORY_COL))), getProviderById(cursor.getInt(cursor.getColumnIndex(ID_PROVIDER_COL))));

            articles.add(article);
            cursor.moveToNext();
        }
        cursor.close();
        return articles;
    }

    @Override
    public List<Article> getArticles(String column, String order) {
        Cursor cursor = database.query(ARTICLES_TABLE, new String[]{ID_COL, NAME_COL, PRICE_COL, ID_CATEGORY_COL, ID_PROVIDER_COL}, null, null, null, column + " " + order, null);

        ArrayList<Article> articles = new ArrayList<Article>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Article article = new Article(cursor.getInt(cursor.getColumnIndex(ID_COL)), cursor.getString(cursor.getColumnIndex(NAME_COL)), cursor.getFloat(cursor.getColumnIndex(PRICE_COL)), getCategoryById(cursor.getInt(cursor.getColumnIndex(ID_CATEGORY_COL))), getProviderById(cursor.getInt(cursor.getColumnIndex(ID_PROVIDER_COL))));

            articles.add(article);
            cursor.moveToNext();
        }
        cursor.close();
        return articles;
    }

    @Override
    public Article getArticleById(int id) {
        this.database.rawQuery("SELECT * FROM " + ARTICLES_TABLE + " WHERE " + ID_COL + " = ?", new String[]{Long.toString(id)});

        Cursor cursor = this.database.query(ARTICLES_TABLE, new String[]{ID_COL, NAME_COL, PRICE_COL, ID_CATEGORY_COL, ID_PROVIDER_COL}, ID_COL + " = " + id, null, null, null, null);
        cursor.moveToFirst();
        Article article = new Article(cursor.getInt(cursor.getColumnIndex(ID_COL)), cursor.getString(cursor.getColumnIndex(NAME_COL)), cursor.getFloat(cursor.getColumnIndex(PRICE_COL)), getCategoryById(cursor.getInt(cursor.getColumnIndex(ID_CATEGORY_COL))), getProviderById(cursor.getInt(cursor.getColumnIndex(ID_PROVIDER_COL))));
        cursor.close();

        return article;
    }

    @Override
    public void addArticle(Article article) {
        ContentValues values = new ContentValues();
        values.put(NAME_COL, article.getName());
        values.put(PRICE_COL, article.getPrice());
        values.put(ID_CATEGORY_COL, article.getCategory().getId());
        values.put(ID_PROVIDER_COL, article.getProvider().getId());
        long recordId = this.database.insert(PROVIDERS_TABLE, null, values);
    }

    @Override
    public void removeArticle(int id) {
        this.database.delete(ARTICLES_TABLE, ID_COL + " = ?", new String[]{Long.toString(id)});
    }
}
