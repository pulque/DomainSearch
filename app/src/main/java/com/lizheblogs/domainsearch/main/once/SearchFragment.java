package com.lizheblogs.domainsearch.main.once;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lizheblogs.domainsearch.R;
import com.lizheblogs.domainsearch.util.SubToast;

import java.util.ArrayList;


/**
 * Created by LiZhe on 2017-09-29.
 * 单个域名搜索
 */

public class SearchFragment extends Fragment implements SearchContract.View {

    private TextInputEditText domainACTV;
    private ListView resultsLV;
    private ArrayAdapter<String> arrayAdapter;
    private SearchContract.Presenter presenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_once, container, false);
        domainACTV = (TextInputEditText) view.findViewById(R.id.domainACTV);
        domainACTV.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == KeyEvent.ACTION_DOWN || actionId == EditorInfo.IME_ACTION_SEARCH) {
                    presenter.checkDomain(domainACTV.getText().toString());
                }
                return true;
            }
        });
        resultsLV = (ListView) view.findViewById(R.id.resultsLV);
        domainACTV.clearFocus();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
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
    public void bindData(ArrayList<String> data) {
        arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, data);
        resultsLV.setAdapter(arrayAdapter);
    }

    @Override
    public void notifyDataSetChanged() {
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(SearchContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showToast(String text) {
        SubToast.showNormalToast(getContext(), text);
    }
}
