package com.lizheblogs.domainsearch.main.multiple;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lizheblogs.domainsearch.R;
import com.lizheblogs.domainsearch.bean.LogBean;
import com.lizheblogs.domainsearch.util.SubToast;

import java.util.ArrayList;


/**
 * Created by LiZhe on 2017-09-29.
 * 单个域名搜索
 */

public class SearchMultFragment extends Fragment implements View.OnClickListener, SearchMultContract.View {


    private android.support.design.widget.TextInputEditText domainACTV;
    private ListView resultsLV;
    private ArrayAdapter<String> arrayAdapter;
    private ProgressBar ingPB;
    private ListView logLV;
    private LogAdapter arrayLogAdapter;
    private SearchMultContract.Presenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_mult, container, false);
        domainACTV = (android.support.design.widget.TextInputEditText) view.findViewById(R.id.domainACTV);
        domainACTV.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == KeyEvent.ACTION_DOWN || actionId == EditorInfo.IME_ACTION_SEARCH) {
                    presenter.checkDomains(domainACTV.getText().toString());
                }
                return true;
            }
        });
        resultsLV = (ListView) view.findViewById(R.id.resultsLV);
        logLV = (ListView) view.findViewById(R.id.logLV);

        ingPB = (ProgressBar) view.findViewById(R.id.ingPB);
        ingPB.setOnClickListener(this);
        ingPB.setVisibility(View.GONE);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ingPB:
                presenter.cancelRequest();
                break;
        }
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
    public void bindData(ArrayList<String> data) {
        arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, data);
        resultsLV.setAdapter(arrayAdapter);
    }

    @Override
    public void bindLogData(ArrayList<LogBean> logData) {
        arrayLogAdapter = new LogAdapter(getContext(), logData);
        logLV.setAdapter(arrayLogAdapter);
    }

    @Override
    public void notifyDataSetChanged() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (arrayAdapter != null) {
                    arrayAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void notifyLogDataSetChanged() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (arrayLogAdapter != null) {
                    arrayLogAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void hideKeyboard() {
        InputMethodManager imm1 = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm1.hideSoftInputFromWindow(domainACTV.getWindowToken(), 0);
    }

    @Override
    public void runOnUiThread(Runnable action) {
        getActivity().runOnUiThread(action);
    }

    @Override
    public void setPresenter(SearchMultContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showToast(String text) {
        SubToast.showNormalToast(getContext(), text);
    }
}
