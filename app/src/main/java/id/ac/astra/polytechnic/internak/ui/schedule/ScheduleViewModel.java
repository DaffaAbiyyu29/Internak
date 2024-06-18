package id.ac.astra.polytechnic.internak.ui.schedule;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ScheduleViewModel extends ViewModel {
    private final MutableLiveData<String> sText;

    public ScheduleViewModel() {
        sText = new MutableLiveData<>();
        sText.setValue("This is schedule fragment");
    }

    public LiveData<String> getText() {
        return sText;
    }

}
