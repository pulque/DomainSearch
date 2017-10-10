package com.lizheblogs.domainsearch.main.once;

import com.lizheblogs.domainsearch.common.base.BasePresenter;
import com.lizheblogs.domainsearch.common.base.BaseView;

import java.util.ArrayList;

/**
 * Created by lizhe on 2017/10/05.
 * This specifies the contract between the view and the presenter.
 */

public class SearchContract {

    interface View extends BaseView<Presenter> {

        void clearFocus();

        void showInputError();

        void bindData(ArrayList<String> data);

        void notifyDataSetChanged();

    }

    interface Presenter extends BasePresenter {

        void checkDomain(String domain);
    }
}
