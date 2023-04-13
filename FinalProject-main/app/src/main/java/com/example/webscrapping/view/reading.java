package com.example.webscrapping.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.renderscript.ScriptGroup;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.webscrapping.R;
import com.example.webscrapping.databinding.ActivityMainBinding;
import com.example.webscrapping.databinding.FragmentReadingBinding;
import com.squareup.picasso.Picasso;

public class reading extends Fragment {
    NavController navController;
    private FragmentReadingBinding binding;

    /*This code is the onCreateView method of a fragment in an Android app. The method inflates the layout for the fragment,
    which includes a TextView, an ImageView, and another TextView for displaying a link. The method retrieves values for the title,
    imageUrl, content, and sourceUrl from the fragment's arguments and sets the values on the corresponding views in the layout.
    The method also sets a LinkMovementMethod on the TextView that displays the link so that the user can click on the link to open it in a web browser.
        Finally, the method returns the inflated view. This method likely initializes the layout and sets the values for the fragment's views from the fragment's arguments.*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding=FragmentReadingBinding.inflate(inflater,
                container, false);
        View view = binding.getRoot();

        String value = getArguments().getString("title");
        String value1 = getArguments().getString("imageUrl");
        String value2 = getArguments().getString("content");
        String value3 = getArguments().getString("sourceUrl");

        binding.textView.setText(value);
        Picasso.get()
                .load(value1)
                .into(binding.imageView);
        binding.textViewContent.setText(value2);
        binding.textViewLink.setMovementMethod(LinkMovementMethod.getInstance());
        binding.textViewLink.setText(value3);

        return view;
    }
}