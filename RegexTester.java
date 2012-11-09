import static org.junit.Assert.*;
import java.text.ParseException;

import org.junit.Test;


public class RegexTester {

    @Test
        public void testSingleCharacter() throws ParseException {
            Regex simpleRegex = new Regex();
            simpleRegex.initialize("a");
            assertTrue(simpleRegex.matches("a"));
            assertFalse(simpleRegex.matches("aa"));
            assertFalse(simpleRegex.matches(""));
            assertFalse(simpleRegex.matches("A"));
        }

    @Test
        public void testMultipleCharacters() throws ParseException {
            Regex simpleRegex = new Regex();
            simpleRegex.initialize("partytime");
            assertTrue(simpleRegex.matches("partytime"));
            assertFalse(simpleRegex.matches("party"));
            assertFalse(simpleRegex.matches("p"));
            assertFalse(simpleRegex.matches("a"));
            assertFalse(simpleRegex.matches(""));
        }

    @Test
        public void testEmptyString() throws ParseException {
            Regex simpleRegex = new Regex();
            simpleRegex.initialize("");
            assertTrue(simpleRegex.matches(""));
            assertFalse(simpleRegex.matches("a"));
        }

    @Test
        public void testDot() throws ParseException {
            Regex simpleRegex = new Regex();
            simpleRegex.initialize(".");
            assertTrue(simpleRegex.matches("a"));
            assertTrue(simpleRegex.matches("b"));
        }

    @Test
        public void testCharStar() throws ParseException {
            Regex simpleRegex = new Regex();
            simpleRegex.initialize("a*");
            assertTrue(simpleRegex.matches(""));
            assertTrue(simpleRegex.matches("a"));
            assertTrue(simpleRegex.matches("aaa"));
            assertFalse(simpleRegex.matches("b"));
        }

    @Test
        public void testCharPlus() throws ParseException {
            Regex simpleRegex = new Regex();
            simpleRegex.initialize("a+");
            assertFalse(simpleRegex.matches(""));
            assertTrue(simpleRegex.matches("a"));
            assertTrue(simpleRegex.matches("aa"));
            assertTrue(simpleRegex.matches("aaa"));
            assertFalse(simpleRegex.matches("b"));
        }

    @Test
        public void testDotStar() throws ParseException {
            Regex simpleRegex = new Regex();
            simpleRegex.initialize(".*");
            assertTrue(simpleRegex.matches("abcdefg"));
            assertTrue(simpleRegex.matches("aaaaa"));
            assertTrue(simpleRegex.matches(""));
        }

    @Test
        public void testDotPlus() throws ParseException {
            Regex simpleRegex = new Regex();
            simpleRegex.initialize(".+");
            assertTrue(simpleRegex.matches("abcdefg"));
            assertTrue(simpleRegex.matches("aaaaa"));
            assertFalse(simpleRegex.matches(""));
        }

    @Test
        public void testBrackets() throws ParseException {
            Regex simpleRegex = new Regex();
            simpleRegex.initialize("[a|b]");
            assertTrue(simpleRegex.matches("a"));
            assertTrue(simpleRegex.matches("b"));
            simpleRegex = new Regex();
            simpleRegex.initialize("[a|b]+");
            assertFalse(simpleRegex.matches(""));
            assertTrue(simpleRegex.matches("a"));
            assertTrue(simpleRegex.matches("aa"));
            assertTrue(simpleRegex.matches("b"));
            assertTrue(simpleRegex.matches("bb"));
            assertFalse(simpleRegex.matches("ab"));
            assertFalse(simpleRegex.matches("bbba"));

            simpleRegex = new Regex();
            simpleRegex.initialize("[a|]");
            assertTrue(simpleRegex.matches(""));
            assertTrue(simpleRegex.matches("a"));

        }

    @Test
        public void testBracketsTrailing() throws ParseException {
            Regex simpleRegex = new Regex();
            simpleRegex.initialize("[a|b]c");
            assertTrue(simpleRegex.matches("ac"));
            assertTrue(simpleRegex.matches("bc"));

            simpleRegex = new Regex();
            simpleRegex.initialize("[a|]+");
            assertTrue(simpleRegex.matches("aa"));
            assertTrue(simpleRegex.matches(""));
        }

    @Test
        public void testNestedStatements() throws ParseException {
            Regex simpleRegex = new Regex();
            simpleRegex.initialize("[a*|b]");
            assertTrue(simpleRegex.matches("aaaaa"));
            assertTrue(simpleRegex.matches(""));
            assertTrue(simpleRegex.matches("b"));
            simpleRegex = new Regex();
            simpleRegex.initialize("[a*|.]");

            assertTrue(simpleRegex.matches("aaa"));
            assertTrue(simpleRegex.matches("a"));
            assertTrue(simpleRegex.matches("y"));
        }

    @Test
        public void testPartialMatch() throws ParseException {
            Regex simpleRegex = new Regex();
            simpleRegex.initialize("a");
            assertEquals(simpleRegex.partialMatch("a"), "a");
            assertEquals(simpleRegex.partialMatch("aa"), "a");
            assertEquals(simpleRegex.partialMatch("aaa"), "a");

            simpleRegex = new Regex();
            simpleRegex.initialize("a+");
            assertNotNull(simpleRegex.partialMatch("a"));
            assertNotNull(simpleRegex.partialMatch("aa"));
            assertNotNull(simpleRegex.partialMatch("baa"));
            assertNull(simpleRegex.partialMatch("b"));
            assertNull(simpleRegex.partialMatch(""));

            assertTrue(simpleRegex.partialMatch("aaaaa").equals("aaaaa"));
            assertTrue(simpleRegex.partialMatch("faaafaaaaaf").equals("aaaaa"));
        }

    @Test
        public void testReplace() throws ParseException {
            Regex simpleRegex = new Regex();
            simpleRegex.initialize("aaa");
            assertEquals("bba", simpleRegex.replaceMatching("aaaaaaa", "b"));
            assertEquals("bb", simpleRegex.replaceMatching("aaaaaa", "b"));

            simpleRegex = new Regex();
            simpleRegex.initialize("ab");
            assertEquals("fffbff", simpleRegex.replaceMatching("abababbabab", "f"));

            simpleRegex = new Regex();
            simpleRegex.initialize("ab+");
            assertEquals("fffff", simpleRegex.replaceMatching("ababbabbabab", "f"));

            simpleRegex = new Regex();
            simpleRegex.initialize("aaa");
            assertEquals("bba", simpleRegex.replaceMatching("aaaaaaa", "b"));
        }

    // bad regexes
    @Test (expected = ParseException.class)
        public void badRegexChar() throws ParseException {
            Regex simpleRegex = new Regex();
            simpleRegex.initialize("\n");
        }

    @Test (expected = ParseException.class)
        public void badRegex1() throws ParseException {
            Regex simpleRegex = new Regex();
            simpleRegex.initialize("[");
        }

    @Test (expected = ParseException.class)
        public void badRegex2() throws ParseException {
            Regex simpleRegex = new Regex();
            simpleRegex.initialize("a[");
        }

    @Test (expected = ParseException.class)
        public void badRegex3() throws ParseException {
            Regex simpleRegex = new Regex();
            simpleRegex.initialize("+");
        }

    @Test (expected = ParseException.class)
        public void badRegex4() throws ParseException {
            Regex simpleRegex = new Regex();
            simpleRegex.initialize("*");
        }

    @Test (expected = ParseException.class)
        public void badRegex5() throws ParseException {
            Regex simpleRegex = new Regex();
            simpleRegex.initialize("a*+");
        }

    @Test (expected = ParseException.class)
        public void badRegex6() throws ParseException {
            Regex simpleRegex = new Regex();
            simpleRegex.initialize("a+*");
        }
}
