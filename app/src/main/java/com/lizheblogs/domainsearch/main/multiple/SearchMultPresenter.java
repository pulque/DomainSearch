package com.lizheblogs.domainsearch.main.multiple;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.lizheblogs.domainsearch.bean.LogBean;
import com.lizheblogs.domainsearch.bean.Property;
import com.lizheblogs.domainsearch.common.BaseCallBack;
import com.lizheblogs.domainsearch.data.DomainCheckRepository;
import com.lizheblogs.domainsearch.data.remote.DomainCheck;

import java.util.ArrayList;


/**
 * Created by lizhe on 2017/10/05.
 * Domain information presenter
 */
public class SearchMultPresenter implements SearchMultContract.Presenter, BaseCallBack<Property> {

    private final DomainCheckRepository mDomainCheckRepository;
    private final SearchMultContract.View mInfoView;
    private ArrayList<String> adapterData;
    private ArrayList<LogBean> adapterLogData;
    private boolean isStop = false;
    public boolean isRunning = false;
    public boolean isFail = false;

    public SearchMultPresenter(@NonNull DomainCheckRepository infoRepository,
                               @NonNull SearchMultContract.View infoView) {
        mDomainCheckRepository = infoRepository;
        mInfoView = infoView;

        mInfoView.setPresenter(this);
    }

    @Override
    public void start() {
        adapterData = new ArrayList<String>();
        adapterLogData = new ArrayList<LogBean>();
        mInfoView.bindData(adapterData);
        mInfoView.bindLogData(adapterLogData);
    }

    @Override
    public void onSuccess(Property property) {
        if (property.getOriginal().contains("210")) {
            adapterData.add(0, property.getKey());
            mInfoView.notifyDataSetChanged();
        }
        LogBean logBeanTemp = adapterLogData != null && adapterLogData.size() > 0 ? adapterLogData.get(0) : new LogBean();
        if (property.getKey() != null && property.getKey().equalsIgnoreCase(logBeanTemp.key)) {
            LogBean temp = adapterLogData.get(0);
            temp.value = property.getOriginal();
            mInfoView.notifyLogDataSetChanged();
        } else {
            LogBean logBean = new LogBean();
            logBean.key = property.getKey();
            logBean.value = property.getOriginal();
            addLogResult(logBean);
        }
        isFail = false;
        isRunning = false;
    }

    @Override
    public void onFailure(String httpRsp) {
        if (!TextUtils.isEmpty(httpRsp)) {
            mInfoView.showToast(httpRsp);
        }
        isFail = true;
        isRunning = false;
    }

    @Override
    public void checkDomains(String domain) {
        if (!TextUtils.isEmpty(domain)) {
            if (domain.charAt(0) != 56 && domain.charAt(domain.length() - 1) != 56 && domain.contains(".")) {
                mInfoView.hideKeyboard();
                startCheck();
                analyzeDomains(domain);
                mInfoView.clearFocus();
                return;
            }
        }
        mInfoView.showInputError();
    }

    private void analyzeDomains(final String domains) {
        if (domains.contains("?")) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String temp = domains;
                    int sum = 0;
                    while (temp.contains("?")) {
                        sum++;
                        temp = temp.replaceFirst("\\?", "");
                    }
                    char[] letter = new char[sum];
                    foreach(domains, letter, 0, sum);
                    mInfoView.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mInfoView.hideLoading();
                        }
                    });
                }
            }).start();
        } else {
            checkDomain(domains);
            stopCheck();
        }
    }

    public void foreach(String text, char[] letter, int index, int sum) {
        for (int j = 97; j < 123; j++) {
            if (isStop) {
                return;
            }
            letter[index] = (char) j;
            if (index + 1 < sum) {
                foreach(text, letter, index + 1, sum);
            } else {
                String temp = text;
                for (int i = 0; i < sum; i++) {
                    temp = temp.replaceFirst("\\?", String.valueOf(letter[i]));
                }
                final String tempShow = temp;
                do {
                    isRunning = true;
                    mInfoView.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            LogBean logBean = new LogBean();
                            logBean.key = tempShow;
                            addLogResult(logBean);
                        }
                    });
                    checkDomain(temp);
                    do {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } while (isRunning);
                } while (isFail);

            }
        }
    }

    private void checkDomain(String domain) {
        mDomainCheckRepository.searchDomain(domain, this);
    }

    private void startCheck() {
        mInfoView.showLoading();
        isStop = false;
    }

    private void stopCheck() {
        mInfoView.hideLoading();
        isStop = true;
    }

    @Override
    public void cancelRequest() {
        mDomainCheckRepository.cancelRequest();
        stopCheck();
    }

    public void addLogResult(LogBean result) {
        adapterLogData.add(0, result);
        int size = adapterLogData.size();
        if (size > 200) {
            adapterLogData.remove(size - 1);
        }
        mInfoView.notifyLogDataSetChanged();
    }
}
