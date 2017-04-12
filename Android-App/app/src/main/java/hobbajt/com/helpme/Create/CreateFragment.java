package hobbajt.com.helpme.Create;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.apache.commons.lang3.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hobbajt.com.helpme.Base.BaseFragment;
import hobbajt.com.helpme.CustomHelpType;
import hobbajt.com.helpme.R;
import hobbajt.com.helpme.SharedPrefs.SharedPrefsWriter;

public class CreateFragment extends BaseFragment implements CreateMVP.View, TextWatcher
{
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.etName) EditText etName;
    @BindView(R.id.etPhoneNumber) EditText etPhoneNumber;
    @BindView(R.id.bAdd) Button bAdd;

    @OnClick(R.id.bAdd)
    void onAddClicked()
    {
        CustomHelpType customHelpType = new CustomHelpType(etName.getText().toString(), etPhoneNumber.getText().toString());
        SharedPrefsWriter.saveCustomHelpType(customHelpType);
        activity.getFragmentsManager().backToFragment("EventsFragment");
    }

    CreateMVP.Presenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_create, container, false);
        ButterKnife.bind(this, view);

        setHasOptionsMenu(true);
        toolbar.setTitleTextColor(android.graphics.Color.WHITE);
        toolbar.setTitle("Dodaj własny numer telefonu");

        etName.addTextChangedListener(this);
        etPhoneNumber.addTextChangedListener(this);

        presenter = new CreatePresenter(getContext(), this, activity.getFragmentsManager());
        //displayData();

        return view;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
    {
        if(StringUtils.isNotBlank(etName.getText().toString()) && StringUtils.isNotBlank(etPhoneNumber.getText().toString()) && etPhoneNumber.getText().toString().length() == 9)
            bAdd.setEnabled(true);
        else
            bAdd.setEnabled(false);
    }

    @Override
    public void afterTextChanged(Editable editable) {}
}
