package com.lizheblogs.domainsearch.util;


import com.lizheblogs.domainsearch.bean.Property;
import com.lizheblogs.domainsearch.bean.WhoisInfo;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.mapper.MapperWrapper;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

/**
 * Created by lizhe on 2017/10/05.
 * 封装第三方库，解析XML文件
 */

public class SubXStream extends XStream {

    public SubXStream() {
        Class<?>[] classes = new Class[] { Property.class, WhoisInfo.class };
        setupDefaultSecurity(this);
        allowTypes(classes);
    }

    /**
     * 重写方法，跳过模板中未列出的元素
     *
     * @param next MapperWrapper
     * @return MapperWrapper
     */
    @Override
    protected MapperWrapper wrapMapper(MapperWrapper next) {
        return new MapperWrapper(next) {
            @Override
            public boolean shouldSerializeMember(Class definedIn, String fieldName) {
                if (definedIn == Object.class) {
                    return false;
                }
                return super.shouldSerializeMember(definedIn, fieldName);
            }
        };
    }
}
