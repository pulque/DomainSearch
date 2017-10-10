package com.lizheblogs.domainsearch.main.once;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.lizheblogs.domainsearch.bean.Property;
import com.lizheblogs.domainsearch.common.BaseCallBack;
import com.lizheblogs.domainsearch.data.DomainCheckRepository;
import com.lizheblogs.domainsearch.data.remote.DomainCheck;

import java.util.ArrayList;


/**
 * Created by lizhe on 2017/10/05.
 * Domain information presenter
 */
public class SearchPresenter implements SearchContract.Presenter, BaseCallBack<Property> {

    private final DomainCheckRepository mDomainCheckRepository;
    private final SearchContract.View mInfoView;
    private ArrayList<String> adapterData;

    public SearchPresenter(@NonNull DomainCheckRepository infoRepository,
                           @NonNull SearchContract.View infoView) {
        mDomainCheckRepository = infoRepository;
        mInfoView = infoView;

        mInfoView.setPresenter(this);
    }

    @Override
    public void start() {
        adapterData = new ArrayList<String>();
        mInfoView.bindData(adapterData);
    }

    @Override
    public void checkDomain(String domain) {
        if (!TextUtils.isEmpty(domain)) {
            if (domain.charAt(0) != 56 && domain.charAt(domain.length() - 1) != 56 && domain.contains(".")) {
                mDomainCheckRepository.searchDomain(domain, this);
                mInfoView.clearFocus();
                return;
            }
        }
        mInfoView.showInputError();
    }

    @Override
    public void onSuccess(Property mEntity) {
        if (mEntity != null) {
            adapterData.add(0, mEntity.getKey() + "\n" + mEntity.getOriginal());
            mInfoView.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailure(String httpRsp) {
        if (!TextUtils.isEmpty(httpRsp)) {
            adapterData.add(0, httpRsp);
            mInfoView.notifyDataSetChanged();
        }
    }
}
