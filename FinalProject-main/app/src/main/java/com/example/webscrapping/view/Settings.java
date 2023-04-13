package com.example.webscrapping.view;
// This class is not being used. but Will be used in the advancement of the app.
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import java.io.Closeable;

public class Settings extends ViewModel {


    private boolean Description;
    private boolean Title;
    private boolean onOFf;

    public Settings(boolean description, boolean title, boolean onOFf, @NonNull Closeable... closeables) {
        super(closeables);
        Description = description;
        Title = title;
        this.onOFf = onOFf;
    }

    public Settings(boolean description, boolean title, boolean onOFf) {
        Description = description;
        Title = title;
        this.onOFf = onOFf;
    }

    public Settings() {
    }

    public boolean getDescription() {
        return Description;
    }

    public void setDescription(boolean description) {
        Description = description;
    }

    public boolean getTitle() {
        return Title;
    }

    public void setTitle(boolean title) {
        Title = title;
    }

    public boolean getOnOFf() {
        return onOFf;
    }

    public void setOnOFf(boolean onOFf) {
        this.onOFf = onOFf;
    }
}
