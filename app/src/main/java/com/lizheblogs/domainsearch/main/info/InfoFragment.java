package com.lizheblogs.domainsearch.main.info;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lizheblogs.domainsearch.R;
import com.lizheblogs.domainsearch.util.SubToast;


/**
 * Created by LiZhe on 2017-09-29.
 * 域名信息获取20
 * https://help.aliyun.com/document_detail/42883.html?spm=5176.doc42882.6.588.81tqBN
 */

public class InfoFragment extends Fragment implements InfoContract.View {

    private TextView DomainName;
    private TextView Registrar;
    private TextView CreationDate;
    private TextView ExpirationDate;
    private TextView UpdatedDate;
    private TextView RegistrantName;
    private TextView RegistrantCountry;
    private TextView RegistrantPhone;
    private TextView AdminName;
    private TextView AdminPhone;
    private TextView TechName;
    private TextView TechPhone;
    private TextView OriginalInfo;

    private android.support.design.widget.TextInputEditText domainACTV;
    private LinearLayout ingPB;
    private InfoContract.Presenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_info, container, false);
        domainACTV = (android.support.design.widget.TextInputEditText) view.findViewById(R.id.domainACTV);
        domainACTV.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == KeyEvent.ACTION_DOWN || actionId == EditorInfo.IME_ACTION_SEARCH) {
                    presenter.searchDomain(domainACTV.getText().toString());
                }
                return true;
            }
        });
        domainACTV.clearFocus();
        ingPB = (LinearLayout) view.findViewById(R.id.ingPB);
        ingPB.setOnClickListener(null);
        ingPB.setVisibility(View.GONE);

        DomainName = (TextView) view.findViewById(R.id.DomainName);
        Registrar = (TextView) view.findViewById(R.id.Registrar);
        CreationDate = (TextView) view.findViewById(R.id.CreationDate);
        ExpirationDate = (TextView) view.findViewById(R.id.ExpirationDate);
        UpdatedDate = (TextView) view.findViewById(R.id.UpdatedDate);
        RegistrantName = (TextView) view.findViewById(R.id.RegistrantName);
        RegistrantCountry = (TextView) view.findViewById(R.id.RegistrantCountry);
        RegistrantPhone = (TextView) view.findViewById(R.id.RegistrantPhone);
        AdminName = (TextView) view.findViewById(R.id.AdminName);
        AdminPhone = (TextView) view.findViewById(R.id.AdminPhone);
        TechName = (TextView) view.findViewById(R.id.TechName);
        TechPhone = (TextView) view.findViewById(R.id.TechPhone);
        OriginalInfo = (TextView) view.findViewById(R.id.OriginalInfo);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void showLoading() {
        ingPB.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        ingPB.setVisibility(View.GONE);
    }

    @Override
    public void clearFocus() {
        domainACTV.clearFocus();
    }

    @Override
    public void showInputError() {
        domainACTV.setError(getString(R.string.domain_edit_error));
        domainACTV.requestFocus();
    }

    @Override
    public void hideKeyboard() {
        InputMethodManager imm1 = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm1.hideSoftInputFromWindow(domainACTV.getWindowToken(), 0);
    }

    @Override
    public void showDomainName(String DomainName) {
        this.DomainName.setText(DomainName);
    }

    @Override
    public void showRegistrar(String Registrar) {
        this.Registrar.setText(Registrar);
    }

    @Override
    public void showCreationDate(String CreationDate) {
        this.CreationDate.setText(CreationDate);
    }

    @Override
    public void showExpirationDate(String ExpirationDate) {
        this.ExpirationDate.setText(ExpirationDate);
    }

    @Override
    public void showUpdatedDate(String UpdatedDate) {
        this.UpdatedDate.setText(UpdatedDate);
    }

    @Override
    public void showRegistrantName(String RegistrantName) {
        this.RegistrantName.setText(RegistrantName);
    }

    @Override
    public void showRegistrantCountry(String RegistrantCountry) {
        this.RegistrantCountry.setText(RegistrantCountry);
    }

    @Override
    public void showRegistrantPhone(String RegistrantPhone) {
        this.RegistrantPhone.setText(RegistrantPhone);
    }

    @Override
    public void showAdminName(String AdminName) {
        this.AdminName.setText(AdminName);
    }

    @Override
    public void showAdminPhone(String AdminPhone) {
        this.AdminPhone.setText(AdminPhone);
    }

    @Override
    public void showTechName(String TechName) {
        this.TechName.setText(TechName);
    }

    @Override
    public void showTechPhone(String TechPhone) {
        this.TechPhone.setText(TechPhone);
    }

    @Override
    public void showOriginalInfo(String OriginalInfo) {
        this.OriginalInfo.setText(OriginalInfo);
    }

    @Override
    public void showToast(String text) {
        SubToast.showNormalToast(getContext(), text);
    }

    @Override
    public void setPresenter(InfoContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
