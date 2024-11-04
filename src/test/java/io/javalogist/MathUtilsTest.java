package io.javalogist;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

@DisplayName("While runnig Math Utils Test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MathUtilsTest {

	MathUtils mathUtils;
	TestInfo testInfo;
	TestReporter testReporter;
	
	@AfterAll
	static void afterAllDone() {
		System.out.println("After all done");
	}
	@BeforeAll
	void beforeAllInit() {
		System.out.println("Before all Initialization");
	}
	
	@BeforeEach
	void init(TestInfo testInfo, TestReporter testReporter) {
		this.testInfo= testInfo;
		this.testReporter = testReporter;
		mathUtils = new MathUtils();
		System.out.println(" Initializing ");
		testReporter.publishEntry("Running " + testInfo.getDisplayName()+" Tag "+testInfo.getTags());
	}
	
	@AfterEach
	void cleanUp() {
		System.out.println(" Cleaning up...");
	}
	
	@Nested
	@DisplayName("Test to Subtract")
	@Tag("Math")
	class SubtractTest{
		
		@Test
		@DisplayName("Subtract Negative")
		void testNegative() {
			int expected = 6;
			int actual = mathUtils.subtract(4, -2);
			assertEquals(expected,actual,()->"should return "+expected+" but actual "+actual);
		}
		@Test
		@DisplayName("Subtract Positive")
		void testPositive() {
			assertEquals(2,mathUtils.subtract(4, 2),"should return correct result of +ve");
		}
	}	
	
	
	@Test
	@DisplayName("Adding Method")
	@RepeatedTest(3)
	@Tag("Math")
	void testAdd() {
		
		MathUtils mathUtils = new MathUtils();
		int expect = 4;
		int actual = mathUtils.add(2, 2);
		assertEquals(expect, actual,"you should add two numbers");
		
	}
	
	@Test
	@DisplayName("AssertAll")
	void testMultiply() {
			assertAll(
					()->assertEquals(4,mathUtils.multiply(2, 2)),
					()->assertEquals(-4,mathUtils.multiply(-2, 2)),
					()->assertEquals(2,mathUtils.multiply(-1, -2))					
					);

	}
	
	@Test
	void testDivide() {
		MathUtils mathUtils = new MathUtils();
		assertThrows(ArithmeticException.class,()-> mathUtils.divide(1, 0),"Divide by zero");
	}
	
	@Test
//	@EnabledOnOs(OS.LINUX)
	@Tag("circle")
	void testArea() {
		boolean serverUp=true;
		assumeTrue(serverUp);
		MathUtils mathUtils = new MathUtils();
		assertEquals(314.1592653589793,mathUtils.area(10.00), "Should return radius circle area");
	}
	
	@Test
	@DisplayName("Disabled Method")
	@Disabled
	void testDisabledMethod() {
		fail("Disable test has been failed");
	}

}
