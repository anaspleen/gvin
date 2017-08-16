//
// Fichier :
// BouteilleDaoMongo.java, v 1.0 19 mai 2016 15:00:03
//
package fr.greeniot.ambrosia.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;

public class TestUtils {
	@Test
	public void testPersist() throws Exception {

		byte header = 0x47;
		System.out.println("header : " + header);
		byte uid = 0x08;
		System.out.println("uid : " + uid);
		System.out.println(getConsistent(header, uid, null));
	}

	/**
	 * Check data integrity by Checksum
	 * 
	 * @return TRUE on good checksum | FALSE else
	 */
	public byte getConsistent(byte header, byte uid, byte[] data) {
		byte sum = 0;
		sum ^= header;
		sum ^= uid;

		if (data != null) {
			for (int i = 0; i < data.length; i++) {
				sum ^= data[i];
			}
		}

		byte[] bytes = (sum + "").getBytes();
		byte t;
		for (int i = 0; i < bytes.length; i++) {
			t = bytes[i];
			System.out.println(t);
		}
		System.out.println(bytes);
		// 0x4f
		return sum;
	}

	public static void main(String[] args) {
		try {
			SimpleDateFormat parserSDF = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
			Date date = parserSDF.parse("Wed Oct 16 00:00:00 CEST 2013");
			System.out.println("date: " + date.toString());
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
	}
}
