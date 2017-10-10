

import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class Whois extends TableIniEntity{

    public static Whois getInstance()
    {
        return new Whois();
    }

    @Override
    protected void iniEntity(Schema schema) {
        Entity note = schema.addEntity(TableName);

        note.addLongProperty("id").index();
        note.addStringProperty("domain_name").primaryKey();//域名
        note.addStringProperty("registrar");//注册商
        
        note.addStringProperty("creation_date");//注册日期
        note.addStringProperty("expiration_date");//到期日期
        note.addStringProperty("updated_date");//更新日期
        
        note.addStringProperty("registrant_name");//注册人姓名
        note.addStringProperty("registrant_country");//注册人国家
        note.addStringProperty("registrant_phone");//注册人电话
        
        note.addStringProperty("admin_name");//管理联系人姓名
        note.addStringProperty("admin_phone");//管理联系人电话
        
        note.addStringProperty("tech_name");//技术联系人姓名
        note.addStringProperty("tech_phone");//技术联系人电话
        
        note.addStringProperty("original_info");//原始信息
        
       
    }
}
