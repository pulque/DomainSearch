
import de.greenrobot.daogenerator.Schema;

/**
 * Created by Brian on 2016-10-13.
 */

public abstract class TableIniEntity implements TableIniInterface{
   protected final String TableName=getClass().getName();

   protected abstract void iniEntity(Schema schema);
   @Override
   public void tableini(Schema schema) {
      iniEntity(schema);
   }
}
