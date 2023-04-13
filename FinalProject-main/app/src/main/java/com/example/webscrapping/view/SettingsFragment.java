package com.example.webscrapping.view;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.ToggleButton;

import com.example.webscrapping.R;
import com.example.webscrapping.databinding.FragmentSettingsBinding;


public class SettingsFragment extends Fragment {
    Switch aSwitch_for_description;
    Switch aSwitch_for_title;
    private Settings settings;
    private FragmentSettingsBinding binding;

    /*   This code is the onCreateView method of a fragment in an Android app.
         The method inflates the layout for the fragment, which includes two ToggleButton views.
         The method setsup listeners for the ToggleButton views that change the background color of the buttons based on their state (on or off).
         The method returns the inflated view. This method likely initializes the layout and sets up the listeners for the ToggleButton views.*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater,
                container, false);
        View view = binding.getRoot();

        settings = new Settings();

        binding.toggleButton.setOnClickListener(new View.OnClickListener() {

            /*This code sets an onClickListener on the toggleButton. When the toggleButton is clicked and checked,
            the background color of the toggleButton is changed to #2E86C1. If the toggleButton is not checked,
            the background color is changed to #FF1A2727.*/
            @Override
            public void onClick(View view) {
                if (binding.toggleButton.isChecked()) {
                    view.setBackgroundResource(R.drawable.styleforviews);
                    GradientDrawable gd = (GradientDrawable) view.getBackground().getCurrent();
                    gd.setColor(Color.parseColor("#2E86C1"));
                } else {
                    view.setBackgroundResource(R.drawable.styleforviews);
                    GradientDrawable gd = (GradientDrawable) view.getBackground().getCurrent();
                    gd.setColor(Color.parseColor("#FF1A2727"));
                }

            }
        });

      /*  This code sets up a click listener for a toggle button. When the toggle button is clicked, it changes the background
        color of the view depending on whether it is checked or not. It uses a GradientDrawable to set the color.
        The color is specified in a hexadecimal RGB format.*/

        binding.toggleButtonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.toggleButtonTwo.isChecked()) {
                    view.setBackgroundResource(R.drawable.styleforviews);
                    GradientDrawable gd = (GradientDrawable) view.getBackground().getCurrent();
                    gd.setColor(Color.parseColor("#F0FF42"));
                } else {
                    view.setBackgroundResource(R.drawable.styleforviews);
                    GradientDrawable gd = (GradientDrawable) view.getBackground().getCurrent();
                    gd.setColor(Color.parseColor("#379237"));
                }

            }
        });

        return view;
    }
}