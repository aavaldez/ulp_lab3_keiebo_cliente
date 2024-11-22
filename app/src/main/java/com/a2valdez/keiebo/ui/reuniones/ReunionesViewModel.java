package com.a2valdez.keiebo.ui.reuniones;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ReunionesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<List<Participante>> participantes = new MutableLiveData<>(new ArrayList<>());

    public ReunionesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Participante>> getParticipantes() {
        return participantes;
    }

    public void addInvitee(Participante invitee) {
        List<Participante> currentList = participantes.getValue();
        currentList.add(invitee);
        participantes.setValue(currentList);
    }
}
