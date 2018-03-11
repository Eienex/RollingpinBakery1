package com.rollingpinbakery.rollingpinbakery;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rollingpinbakery.rollingpinbakery.Data.AppDatabase;
import com.rollingpinbakery.rollingpinbakery.Data.Product;

/**
 * Created by equno_000 on 2/28/2018.
 */

public class DBInitialize extends AppCompatActivity {
    protected void Initialize(Bundle savedInstanceState) {

    AppDatabase.getAppDatabase(this).productDao().insert(
                new Product("Loaf of Bread",
                    10.99,
                    null,
                    "A sample loaf of Bread",
                    "Bread",
                    false,
                    null));
    AppDatabase.getAppDatabase(this).productDao().insert(
                new Product("Rye Bread",
                    14.99,
                    null,
                    "Or try the kizer",
                    "Bread",
                    true,
                    null));
    }
}
