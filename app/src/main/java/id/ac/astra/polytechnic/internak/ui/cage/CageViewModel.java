package id.ac.astra.polytechnic.internak.ui.cage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CageViewModel extends ViewModel {
    private final MutableLiveData<String> mText;
    private final MutableLiveData<String> buttonAction;

    public CageViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Kandang");

        buttonAction = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<String> getButtonAction() {
        return buttonAction;
    }

    public void onLeftButtonClick() {
        buttonAction.setValue("Aktif");
    }

    public void onRightButtonClick() {
        buttonAction.setValue("Rehat");
    }
}