

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
        note.addStringProperty("domain_name").primaryKey();//����
        note.addStringProperty("registrar");//ע����
        
        note.addStringProperty("creation_date");//ע������
        note.addStringProperty("expiration_date");//��������
        note.addStringProperty("updated_date");//��������
        
        note.addStringProperty("registrant_name");//ע��������
        note.addStringProperty("registrant_country");//ע���˹���
        note.addStringProperty("registrant_phone");//ע���˵绰
        
        note.addStringProperty("admin_name");//������ϵ������
        note.addStringProperty("admin_phone");//������ϵ�˵绰
        
        note.addStringProperty("tech_name");//������ϵ������
        note.addStringProperty("tech_phone");//������ϵ�˵绰
        
        note.addStringProperty("original_info");//ԭʼ��Ϣ
        
       
    }
}
