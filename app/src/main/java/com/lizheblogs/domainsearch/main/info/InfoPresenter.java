package com.lizheblogs.domainsearch.main.info;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.lizheblogs.domainsearch.bean.WhoisInfo;
import com.lizheblogs.domainsearch.common.BaseCallBack;
import com.lizheblogs.domainsearch.data.DomainInfoRepository;
import com.lizheblogs.domainsearch.data.remote.DomainInfo;


/**
 * Created by lizhe on 2017/10/05.
 * Domain information presenter
 */
public class InfoPresenter implements InfoContract.Presenter, BaseCallBack<WhoisInfo> {

    private final DomainInfoRepository mDomainCheckRepository;
    private final InfoContract.View mInfoView;

    public InfoPresenter(@NonNull DomainInfoRepository infoRepository,
                         @NonNull InfoContract.View infoView) {
        mDomainCheckRepository = infoRepository;
        mInfoView = infoView;

        mInfoView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void searchDomain(String domain) {
        if (!TextUtils.isEmpty(domain)) {
            if (domain.charAt(0) != 56 && domain.charAt(domain.length() - 1) != 56 && domain.contains(".")) {
                mInfoView.hideKeyboard();
                mInfoView.showLoading();
                mDomainCheckRepository.searchDomainInfo(domain, this);
                mInfoView.clearFocus();
                return;
            }
        }
        mInfoView.showInputError();
    }

    @Override
    public void onSuccess(WhoisInfo property) {
        mInfoView.showDomainName(property.getDomainName());
        mInfoView.showRegistrar(property.getRegistrar());
        mInfoView.showCreationDate(property.getCreationDate());
        mInfoView.showExpirationDate(property.getExpirationDate());
        mInfoView.showUpdatedDate(property.getUpdatedDate());
        mInfoView.showRegistrantName(property.getRegistrantName());
        mInfoView.showRegistrantCountry(property.getRegistrantCountry());
        mInfoView.showRegistrantPhone(property.getRegistrantPhone());
        mInfoView.showAdminName(property.getAdminName());
        mInfoView.showAdminPhone(property.getAdminPhone());
        mInfoView.showTechName(property.getTechName());
        mInfoView.showTechPhone(property.getTechPhone());
        mInfoView.showOriginalInfo(property.getOriginalInfo());

        mInfoView.hideLoading();
    }

    @Override
    public void onFailure(String httpRsp) {
        if (!TextUtils.isEmpty(httpRsp)) {
            mInfoView.showToast(httpRsp);
        }
        mInfoView.hideLoading();
    }

}
