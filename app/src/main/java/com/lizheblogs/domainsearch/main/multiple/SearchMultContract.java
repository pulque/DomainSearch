package com.lizheblogs.domainsearch.main.multiple;

import com.lizheblogs.domainsearch.bean.LogBean;
import com.lizheblogs.domainsearch.common.base.BasePresenter;
import com.lizheblogs.domainsearch.common.base.BaseView;

import java.util.ArrayList;

/**
 * Created by lizhe on 2017/10/05.
 * This specifies the contract between the view and the presenter.
 */

public class SearchMultContract {

    interface View extends BaseView<Presenter> {

        void showLoading();

        void hideLoading();

        void clearFocus();

        void showInputError();

        void bindData(ArrayList<String> data);

        void bindLogData(ArrayList<LogBean> logData);

        void notifyDataSetChanged();

        void notifyLogDataSetChanged();

        void hideKeyboard();

    }

    interface Presenter extends BasePresenter {

        void checkDomains(String domain);

        void cancelRequest();
    }
}
