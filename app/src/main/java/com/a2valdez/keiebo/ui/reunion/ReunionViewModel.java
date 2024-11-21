package com.a2valdez.keiebo.ui.reunion;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReunionViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ReunionViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}