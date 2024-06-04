package id.ac.astra.polytechnic.internak.ui.cage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CageViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public CageViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is cage fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
