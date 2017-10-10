

import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class Domain extends TableIniEntity{

    public static Domain getInstance()
    {
        return new Domain();
    }

    @Override
    protected void iniEntity(Schema schema) {
        Entity note = schema.addEntity(TableName);

        note.addLongProperty("id").index();
        note.addStringProperty("domain_name").primaryKey();
        note.addStringProperty("original");
        note.addStringProperty("code");
        note.addLongProperty("update_time");
        note.addStringProperty("whois_id");

    }
}
