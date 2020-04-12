package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import EFL1.Method1;
import test3methods.Testclass1;
import test3methods.Testclass2;
import test3methods.Testclass3;

class test {

	@Test
	void test1() throws IOException {
		Testclass1 testcase1 = new Testclass1();
		Testclass1.operation();;
		Assert.assertTrue(testcase1.success>1500);
	}
	
	@Test
	void test2() throws IOException {
		Testclass2 testcase2 = new Testclass2();
		Testclass2.operation();;
		Assert.assertTrue(testcase2.success>1300);
	}
	
	@Test
	void test3() throws IOException {
		Testclass3 testcase3 = new Testclass3();
		Testclass3.operation();;
		Assert.assertTrue(testcase3.success>1300);
	}

	
	
}
