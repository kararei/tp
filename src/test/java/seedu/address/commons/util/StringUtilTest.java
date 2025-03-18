package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

public class StringUtilTest {

    //---------------- Tests for isNonZeroUnsignedInteger --------------------------------------

    @Test
    public void isNonZeroUnsignedInteger() {

        // EP: empty strings
        assertFalse(StringUtil.isNonZeroUnsignedInteger(""));
        assertFalse(StringUtil.isNonZeroUnsignedInteger("  "));

        // EP: not a number
        assertFalse(StringUtil.isNonZeroUnsignedInteger("a"));
        assertFalse(StringUtil.isNonZeroUnsignedInteger("aaa"));

        // EP: zero
        assertFalse(StringUtil.isNonZeroUnsignedInteger("0"));

        // EP: zero as prefix
        assertTrue(StringUtil.isNonZeroUnsignedInteger("01"));

        // EP: signed numbers
        assertFalse(StringUtil.isNonZeroUnsignedInteger("-1"));
        assertFalse(StringUtil.isNonZeroUnsignedInteger("+1"));

        // EP: numbers with white space
        assertFalse(StringUtil.isNonZeroUnsignedInteger(" 10 "));
        assertFalse(StringUtil.isNonZeroUnsignedInteger("1 0"));

        // EP: number larger than Integer.MAX_VALUE
        assertFalse(StringUtil.isNonZeroUnsignedInteger(Long.toString(Integer.MAX_VALUE + 1)));

        // EP: valid numbers, should return true
        assertTrue(StringUtil.isNonZeroUnsignedInteger("1"));
        assertTrue(StringUtil.isNonZeroUnsignedInteger("10"));
    }


    //---------------- Tests for containsWordIgnoreCase --------------------------------------

    /*
     * Invalid equivalence partitions for word: null, empty, multiple words
     * Invalid equivalence partitions for sentence: null
     * The four test cases below test one invalid input at a time.
     */

    @Test
    public void containsWordIgnoreCase_nullWord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.containsWordIgnoreCase(
                "typical sentence", null));
    }

    @Test
    public void containsWordIgnoreCase_emptyWord_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> StringUtil.containsWordIgnoreCase(
                "typical sentence", "  "));
    }

    @Test
    public void containsWordIgnoreCase_multipleWords_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> StringUtil.containsWordIgnoreCase(
                "typical sentence", "aaa bbb"));
    }

    @Test
    public void containsWordIgnoreCase_nullSentence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.containsWordIgnoreCase(null, "abc"));
    }

    /*
     * Valid equivalence partitions for word:
     *   - any word
     *   - word containing symbols/numbers
     *   - word with leading/trailing spaces
     *
     * Valid equivalence partitions for sentence:
     *   - empty string
     *   - one word
     *   - multiple words
     *   - sentence with extra spaces
     *
     * Possible scenarios returning true:
     *   - matches first word in sentence
     *   - last word in sentence
     *   - middle word in sentence
     *   - matches multiple words
     *
     * Possible scenarios returning false:
     *   - query word matches part of a sentence word
     *   - sentence word matches part of the query word
     *
     * The test method below tries to verify all above with a reasonably low number of test cases.
     */

    @Test
    public void containsWordIgnoreCase_validInputs_correctResult() {
        // Empty sentence
        assertFalse(StringUtil.containsWordIgnoreCase("", "abc"));
        assertFalse(StringUtil.containsWordIgnoreCase("    ", "abc"));

        // Matches word in the sentence, different upper/lower case letters
        assertTrue(StringUtil.containsWordIgnoreCase("TyPiCaL SeNtEnCe", "typical"));
        assertTrue(StringUtil.containsWordIgnoreCase("AAA bBb ccc", "aaa"));
        assertTrue(StringUtil.containsWordIgnoreCase("AAA bBb ccc DDD", "BBB"));
        assertTrue(StringUtil.containsWordIgnoreCase("aaa bbb ccc", "bbb"));

        // Matches multiple words in sentence
        assertTrue(StringUtil.containsWordIgnoreCase("AAA bBb ccc  bbb", "bbb"));

        // Matches part of a word only
        assertFalse(StringUtil.containsWordIgnoreCase("aaa bbb ccc", "bb"));

        // Does not match
        assertFalse(StringUtil.containsWordIgnoreCase("aaa bbb ccc", "ddd"));

        // Non-word characters
        assertFalse(StringUtil.containsWordIgnoreCase("aaa bbb ccc", "a*a"));
        assertFalse(StringUtil.containsWordIgnoreCase("1234567890", "a"));
        assertTrue(StringUtil.containsWordIgnoreCase("aaa123bbb", "aaa123bbb"));
    }

    @Test
    public void containsWordIgnoreCase_specialCharacters_correctResult() {
        // Special characters in sentence
        assertTrue(StringUtil.containsWordIgnoreCase("$#@ abc !@#", "abc"));
        assertFalse(StringUtil.containsWordIgnoreCase("$#@ !@#", "abc"));

        // Unicode characters
        assertTrue(StringUtil.containsWordIgnoreCase("über", "über"));
        assertTrue(StringUtil.containsWordIgnoreCase("ÜBER", "über"));
    }

    //---------------- Tests for getDetails --------------------------------------

    /*
     * Equivalence Partitions: null, valid throwable object
     */

    @Test
    public void getDetails_exceptionGiven() {
        assertTrue(StringUtil.getDetails(new FileNotFoundException("file not found"))
            .contains("java.io.FileNotFoundException: file not found"));
    }

    @Test
    public void getDetails_nullGiven_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.getDetails(null));
    }

}
