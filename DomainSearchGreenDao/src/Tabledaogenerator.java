

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Schema;

public class Tabledaogenerator {

	public static void main(String[] args) throws Exception {
		Schema schema = null;
		schema = new Schema(1, "com.lizheblogs.domainsearch.bean.db.bean");
		schema.setDefaultJavaPackageDao("com.lizheblogs.domainsearch.bean.db.dao");
		
		addTables(schema);
		new DaoGenerator().generateAll(schema, "D:\\workspace\\DomainSearch\\app\\src\\main\\java");
	}

	/**
	 * @param schema
	 */
	private static void addTables(Schema schema) {
		Domain.getInstance().tableini(schema);
		Whois.getInstance().tableini(schema);
	}
}
