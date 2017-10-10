package com.lizheblogs.domainsearch.main.info;

import com.lizheblogs.domainsearch.common.base.BasePresenter;
import com.lizheblogs.domainsearch.common.base.BaseView;

/**
 * Created by lizhe on 2017/10/05.
 * This specifies the contract between the view and the presenter.
 */

public class InfoContract {

    interface View extends BaseView<Presenter> {

        void showLoading();

        void hideLoading();

        void clearFocus();

        void showInputError();

        void hideKeyboard();


        void showDomainName(String DomainName);

        void showRegistrar(String Registrar);

        void showCreationDate(String CreationDate);

        void showExpirationDate(String ExpirationDate);

        void showUpdatedDate(String UpdatedDate);

        void showRegistrantName(String RegistrantName);

        void showRegistrantCountry(String RegistrantCountry);

        void showRegistrantPhone(String RegistrantPhone);

        void showAdminName(String AdminName);

        void showAdminPhone(String AdminPhone);

        void showTechName(String TechName);

        void showTechPhone(String TechPhone);

        void showOriginalInfo(String OriginalInfo);

    }

    interface Presenter extends BasePresenter {

        void searchDomain(String domain);
    }
}
