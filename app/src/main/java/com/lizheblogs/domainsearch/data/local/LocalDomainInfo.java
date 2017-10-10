package com.lizheblogs.domainsearch.data.local;

import com.lizheblogs.domainsearch.bean.WhoisInfo;
import com.lizheblogs.domainsearch.bean.db.Whois;
import com.lizheblogs.domainsearch.util.Utils;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by LiZhe on 2017-10-10.
 * com.lizheblogs.domainsearch.data.local
 */

public class LocalDomainInfo {

    public static WhoisInfo getDomainWhois(String domain) {
        DaoSession ds = DBHelper.getInstance().getDaoSession();
        QueryBuilder<Whois> qb = ds.getWhoisDao().queryBuilder().where(WhoisDao.Properties.Domain_name.eq(domain));
        Whois mWhois = qb.unique();
        if (mWhois != null) {
            if (Utils.getMillisFromTimestamp(mWhois.getExpiration_date()) > Utils.getTime()) {
                return WhoisInfo.copy(mWhois);
            }
        }
        return null;
    }

    public static void cacheLocal(WhoisInfo mInfo) {
        DaoSession ds = DBHelper.getInstance().getDaoSession();
        WhoisDao whoisDao = ds.getWhoisDao();
        whoisDao.deleteByKey(mInfo.getDomainName());

        Whois whois = new Whois();

        whois.setDomain_name(mInfo.getDomainName());
        whois.setRegistrar(mInfo.getRegistrar());
        whois.setCreation_date(mInfo.getCreationDate());
        whois.setExpiration_date(mInfo.getExpirationDate());
        whois.setUpdated_date(mInfo.getUpdatedDate());
        whois.setRegistrant_name(mInfo.getRegistrantName());
        whois.setRegistrant_country(mInfo.getRegistrantCountry());
        whois.setRegistrant_phone(mInfo.getRegistrantPhone());

        whois.setAdmin_name(mInfo.getAdminName());
        whois.setAdmin_phone(mInfo.getAdminPhone());
        whois.setTech_name(mInfo.getTechName());
        whois.setTech_phone(mInfo.getTechPhone());
        whois.setOriginal_info(mInfo.getOriginalInfo());

        whoisDao.insert(whois);

    }
}
