package si.wolf.planea;

import org.junit.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import si.wolf.tools.cc.CardValidationResult;
import si.wolf.tools.cc.RegexCardValidator;

import static org.junit.Assert.*;

public class CCTest {


  //VIN vin;

  /*
  @BeforeEach
  public void setUp() {
    vin = new VIN("1J4FF68S5XL503754");
  }

   */

  @Test
  @DisplayName("Invalid Card Number")
  public void TestInvalidCC()
  {
    String cardIn = "abcdabcdabcdabcd";
    CardValidationResult result = RegexCardValidator.isValid(cardIn);

    assertFalse(result.isValid());
    //assertEquals(false, result.isValid());
  }

  @Test
  @DisplayName("Valid Card Number")
  public void TestValidCC()
  {
    String cardIn = "4444444444444448";
    CardValidationResult result = RegexCardValidator.isValid(cardIn);
    assertTrue(result.isValid());
  }

  @Test
  @DisplayName("Visa Card Number")
  public void TestVisa()
  {
    String cardIn = "4444444444444448";
    CardValidationResult result = RegexCardValidator.isValid(cardIn);
    assertEquals("VISA", result.getCardType().getIssuerName());
  }

  @Test
  @DisplayName("MasterCard Card Number")
  public void TestMasterCC()
  {
    String cardIn = "5500005555555559";
    CardValidationResult result = RegexCardValidator.isValid(cardIn);
    assertEquals("MASTER", result.getCardType().getIssuerName());
  }

  @Test
  @DisplayName("Amex Card Number")
  public void TestAmex()
  {
    String cardIn = "371449635398431";
    CardValidationResult result = RegexCardValidator.isValid(cardIn);
    assertEquals("AMEX", result.getCardType().getIssuerName());
  }

  @Test
  @DisplayName("Diners Card Number")
  public void TestDiners()
  {
    String cardIn = "36438936438936";
    CardValidationResult result = RegexCardValidator.isValid(cardIn);
    assertEquals("Diners", result.getCardType().getIssuerName());
  }

  @Test
  @DisplayName("Discover Card Number")
  public void TestDiscover()
  {
    String cardIn = "6011016011016011";
    CardValidationResult result = RegexCardValidator.isValid(cardIn);
    assertEquals("DISCOVER", result.getCardType().getIssuerName());
  }

  @Test
  @DisplayName("JCB Card Number")
  public void TestJCB()
  {
    String cardIn = "3566003566003566";
    CardValidationResult result = RegexCardValidator.isValid(cardIn);
    assertEquals("JCB", result.getCardType().getIssuerName());
  }

  @Test(expected = NullPointerException.class)
  @DisplayName("Test Null pointer exeption")
  public void TestLuhnFail()
  {
    String cardIn = "1111111111111111";
    CardValidationResult result = RegexCardValidator.isValid(cardIn);
    assertEquals("JCB", result.getCardType().getIssuerName());
  }

  @Test
  @DisplayName("Library response test: Visa")
  public void LibraryVisaTest()
  {
    String cardIn = "4444444444444448";
    CardValidationResult result = RegexCardValidator.isValid(cardIn);
    String response = result.isValid() + " : " + (result.isValid()? result.getCardType().getIssuerName(): "")  + " : " + result.getMessage();
    assertEquals("true : VISA : 4444444444444448    >>    card: VISA", response);
  }

  @Test
  @DisplayName("Library response test: luhnFail")
  public void LibraryLFTest()
  {
    String cardIn = "1111111111111111";
    CardValidationResult result = RegexCardValidator.isValid(cardIn);
    String response = result.isValid() + " : " + (result.isValid()? result.getCardType().getIssuerName(): "")  + " : " + result.getMessage();
    assertEquals("false :  : 1111111111111111    >>    failed luhn check", response);
  }
}
