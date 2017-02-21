/**
 * 
 */
package snmp;

import org.bson.Document;
import org.snmp4j.agent.mo.MOAccessImpl;
import org.snmp4j.agent.mo.MOScalar;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;

/**
 * @author anaspleen
 *
 */
public class MOCreator {
	public static MOScalar createReadOnly(OID oid, Object value) {
		return new MOScalar(oid, MOAccessImpl.ACCESS_READ_ONLY, getVariable(value));
	}

	private static Variable getVariable(Object value) {
		if (value instanceof String) {
			return new OctetString((String) value);
		}
		if (value instanceof Document) {
			return new OctetString(((Document) value).toJson());
		}
		throw new IllegalArgumentException("Unmanaged Type: " + value.getClass());
	}
}
