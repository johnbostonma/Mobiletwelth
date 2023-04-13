/*import androidx.fragment.app.Fragment;This ipublic class AboutThisApp extends Fragment {s the About This app page,
    It contains informations about the The developer ofthis app.
            so this app is responsible for that.*/

package com.example.webscrapping.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.webscrapping.R;
import com.example.webscrapping.databinding.FragmentAboutThisAppBinding;

public class AboutThisApp extends Fragment {
    private FragmentAboutThisAppBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAboutThisAppBinding.inflate(inflater,
                container, false);
        return binding.getRoot();
    }
}