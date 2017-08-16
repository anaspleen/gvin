/**
 * 
 */
package fr.greeniot.ambrosia.utils;

import org.junit.Test;

/**
 * @author
 *
 */
public class Java8Tester {

	/**
	 * @param args
	 */
	@Test
	public void test() {

		Sayable vv = name -> {
			return "Hello : " + name;
		};

		System.out.println(vv.say("dd"));

		Addable add = (a, b) -> a + b;

		System.out.println(add.add(10, 20));

	}

}

interface Sayable {
	public String say(String name);
}

interface Addable {
	int add(int a, int b);
}
