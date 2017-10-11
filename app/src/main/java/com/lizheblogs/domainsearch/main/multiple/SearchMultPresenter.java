package com.lizheblogs.domainsearch.main.multiple;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.lizheblogs.domainsearch.bean.LogBean;
import com.lizheblogs.domainsearch.bean.Property;
import com.lizheblogs.domainsearch.common.BaseCallBack;
import com.lizheblogs.domainsearch.common.SubRunnable;
import com.lizheblogs.domainsearch.data.DomainCheckRepository;

import java.util.ArrayList;


/**
 * Created by lizhe on 2017/10/05.
 * Domain information presenter
 */
public class SearchMultPresenter implements SearchMultContract.Presenter, BaseCallBack<Property>, Handler.Callback {

    private static final int MSG_HIDE_LOADING = 1;
    private static final int MSG_NOTIFY_DATA = MSG_HIDE_LOADING + 1;
    private static final int MSG_NOTIFY_LOG_DATA = MSG_NOTIFY_DATA + 1;
    private static final int MSG_ADD_DATA = MSG_NOTIFY_LOG_DATA + 1;
    private static final int MSG_ADD_LOG_DATA = MSG_ADD_DATA + 1;
    private final DomainCheckRepository mDomainCheckRepository;
    private final SearchMultContract.View mInfoView;
    private ArrayList<String> adapterData;
    private ArrayList<LogBean> adapterLogData;
    private boolean isRunning = false;
    private boolean isFail = false;
    private Handler mHandler;
    private SubRunnable runnable;

    public SearchMultPresenter(@NonNull DomainCheckRepository infoRepository,
                               @NonNull SearchMultContract.View infoView) {
        mDomainCheckRepository = infoRepository;
        mInfoView = infoView;

        mInfoView.setPresenter(this);
    }

    @Override
    public void start() {
        adapterData = new ArrayList<String>();
        mInfoView.bindData(adapterData);
        adapterLogData = new ArrayList<LogBean>();
        mInfoView.bindLogData(adapterLogData);
        mHandler = new Handler(this);
    }

    @Override
    public void onSuccess(Property property) {
        if (property.getOriginal().contains("210")) {
            sendMessage(MSG_ADD_DATA, property.getKey());
        }
        sendMessage(MSG_ADD_LOG_DATA, property);
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
            runnable = new SubRunnable() {

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
                    mHandler.sendEmptyMessage(MSG_HIDE_LOADING);
                }

                private void foreach(String text, char[] letter, int index, int sum) {
                    for (int j = 97; j < 123; j++) {
                        if (isStopRun) {
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
                            do {
                                isRunning = true;
                                sendMessage(MSG_ADD_LOG_DATA, new Property(temp, ""));
                                checkDomain(temp);
                                do {
                                    try {
                                        Thread.sleep(100);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    if (isStopRun) {
                                        return;
                                    }
                                } while (isRunning);
                            } while (isFail);

                        }
                    }
                }
            };
            Thread thread = new Thread(runnable);
            thread.setName("Domain Search");
            thread.start();
        } else {
            checkDomain(domains);
            stopCheck();
        }
    }

    private void checkDomain(String domain) {
        mDomainCheckRepository.searchDomain(domain, this);
    }

    private void startCheck() {
        mInfoView.showLoading();
        if (runnable != null) {
            runnable.stopThread(false);
        }
        adapterData.clear();
        adapterLogData.clear();
        mHandler.sendEmptyMessage(MSG_NOTIFY_DATA);
        mHandler.sendEmptyMessage(MSG_NOTIFY_LOG_DATA);
    }

    private void stopCheck() {
        mInfoView.hideLoading();
        if (runnable != null) {
            runnable.stopThread(true);
        }
    }

    @Override
    public void cancelRequest() {
        mDomainCheckRepository.cancelRequest();
        stopCheck();
    }

    private void addLogResult(LogBean result) {
        adapterLogData.add(0, result);
        int size = adapterLogData.size();
        if (size > 200) {
            adapterLogData.remove(size - 1);
        }
    }

    private void sendMessage(int what, Object obj) {
        Message meg = mHandler.obtainMessage();
        meg.what = what;
        meg.obj = obj;
        mHandler.sendMessage(meg);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_HIDE_LOADING:
                mInfoView.hideLoading();
                break;
            case MSG_ADD_DATA:
                adapterData.add(0, (String) msg.obj);
            case MSG_NOTIFY_DATA:
                mInfoView.notifyDataSetChanged();
                break;
            case MSG_ADD_LOG_DATA:
                Property property = (Property) msg.obj;
                LogBean logBeanTemp = adapterLogData != null && adapterLogData.size() > 0 ? adapterLogData.get(0) : new LogBean();
                if (property.getKey() != null && property.getKey().equalsIgnoreCase(logBeanTemp.key)) {
                    LogBean temp = adapterLogData.get(0);
                    temp.value = property.getOriginal();
                } else {
                    LogBean logBean = new LogBean();
                    logBean.key = property.getKey();
                    logBean.value = property.getOriginal();
                    addLogResult(logBean);
                }
            case MSG_NOTIFY_LOG_DATA:
                mInfoView.notifyLogDataSetChanged();
                break;
            default:
                break;
        }
        return false;
    }
}
