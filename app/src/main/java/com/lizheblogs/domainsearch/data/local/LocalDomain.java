package com.lizheblogs.domainsearch.data.local;

import com.lizheblogs.domainsearch.bean.Property;
import com.lizheblogs.domainsearch.bean.db.Domain;
import com.lizheblogs.domainsearch.util.Utils;

import java.util.Date;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by LiZhe on 2017-10-10.
 * com.lizheblogs.domainsearch.data.local
 */

public class LocalDomain {

    public static Property getDomainProperty(String domain) {
        DaoSession ds = DBHelper.getInstance().getDaoSession();
        QueryBuilder<Domain> qb = ds.getDomainDao().queryBuilder().where(DomainDao.Properties.Domain_name.eq(domain));
        Domain mDomain = qb.unique();
        if (mDomain != null) {
            if (mDomain.getUpdate_time() > Utils.getDayBefore()) {
                return new Property(mDomain.getDomain_name(), mDomain.getOriginal());
            }
        }
        return null;
    }

    public static void cacheLocal(Property property) {
        DaoSession ds = DBHelper.getInstance().getDaoSession();
        DomainDao domainDao = ds.getDomainDao();
        domainDao.deleteByKey(property.getKey());
        Domain domain = new Domain();
        domain.setDomain_name(property.getKey());
        String original = property.getOriginal();
        domain.setOriginal(original);
        domain.setCode(original.substring(0, 2));
        domain.setUpdate_time(new Date().getTime());
        domainDao.insert(domain);
    }
}
