/***********************************************
 Authors: Victoria Munroe, John Youte
 Date:12/26/22
 Purpose: <Insert the purpose of the program here>
 What Learned: <what have you learned most from doing this
 assignment>
 Sources of Help:
 W3Schools
 developer.android.com
 https://stackoverflow.com/questions/59582918/how-to-write-sql-query-to-check-room-availability
 class lectures/notes/previous assignments

 Time Spent (Hours):

 Mobile App Development I -- COMP.4630 Honor Statement
 The practice of good ethical behavior is essential for
 maintaining good order in the classroom, providing an
 enriching learning experience for students, and training as
 a practicing computing professional upon graduation. This
 practice is manifested in the University's Academic
 Integrity policy. Students are expected to strictly avoid
 academic dishonesty and adhere to the Academic Integrity
 policy as outlined in the course catalog. Violations will
 be dealt with as outlined therein. All programming
 assignments in this class are to be done by the student
 alone unless otherwise specified. No outside help is
 permitted except the instructor and approved tutors.
 I certify that the work submitted with this assignment is
 mine and was generated in a manner consistent with this
 document, the course academic policy on the course website
 on Blackboard, and the UMass Lowell academic code.
 Date:12/26/22
 Names:Victoria Munroe & John Youte
 ***********************************************/

package com.example.webscrapping;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.webscrapping.databinding.ActivityMainBinding;
import com.example.webscrapping.view.HomeArticle;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kwabenaberko.newsapilib.NewsApiClient;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private ActivityMainBinding binding;

    /*This code is the onCreate method of an Android Activity. The method is called when the Activity is first created.
    The method inflates the layout for the Activity, sets the view, and sets the bottomNavigationView and the toolbar for the Activity.
    The method then sets up a NavController and uses it to set up the bottomNavigationView and the toolbar with the NavController.
    The NavController manages the app's navigation and allows the Activity to move between different destinations in the app.
    The method also sets up an AppBarConfiguration for the toolbar and sets it up with the NavController. This method likely
    initializes the Activity and sets up the navigation and toolbar for the Activity.*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        bottomNavigationView=binding.bottomNav;
        setSupportActionBar(binding.toolbar);

        NavHostFragment navHostFragment = binding.navHostFragment.getFragment();
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        AppBarConfiguration.Builder builder = new AppBarConfiguration.Builder(navController.getGraph());
        AppBarConfiguration appBarConfiguration = builder.build();
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration);

    }

/*    This code is an onCreateOptionsMenu method that is likely overridden in an Android Activity.
    The method inflates the menu specified by the R.menu.menu_toolbar resource and adds the items to the options menu.
    The method then returns the result of calling super.onCreateOptionsMenu with the newly inflated menu.
    This method likely initializes the options menu for the Activity by inflating a menu resource and adding the items to the menu.*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

  /*  This code is an onOptionsItemSelected method that is likely overridden in an Android Activity.
    The method is called when the user selects an item from the app's options menu.
    The method uses the navigation controller to pop the back stack to the R.id.action_homeArticle_to_reading_Fragment
    destination and clears the back stack up to the R.id.action_favorite_news_to_homeArticle destination. Finally,
    the method returns the result of calling NavigationUI.onNavDestinationSelected with the selected menu item and the
    navigation controller, or true if this returns false. This method likely handles navigation in the app when the user selects an item from the options menu.*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navController.popBackStack(R.id.action_homeArticle_to_reading_Fragment, true);
        navController.clearBackStack(R.id.action_favorite_news_to_homeArticle);
        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
    }
}