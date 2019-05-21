import org.scalatest.{FlatSpec, Matchers}


class MainTest extends FlatSpec with Matchers {
  val importString = " import  file2 "

  "string with import" should "match importRegex" in {
    importString.matches(Main.importRegex.toString()) shouldBe true
  }

  val incorrectImportString = "file2"

  "string with incorrect import" should "not match importRegex" in {
    incorrectImportString.matches(Main.importRegex.toString()) shouldBe false
  }

  val number = "a = 15"

  "variable declaration with number" should "match numberRegex" in {
    number.matches(Main.numberRegex.toString()) shouldBe true
  }

  val doubleNumber = "b = 0.15"

  "variable declaration with double number" should "match numberRegex" in {
    doubleNumber.matches(Main.numberRegex.toString()) shouldBe true
  }

  val incorrectNumber = "o = .0"

  "variable declaration with incorrect number" should "not match numberRegex" in {
    incorrectNumber.matches(Main.numberRegex.toString()) shouldBe false
  }

  val string = "x = \"test string\""

  "variable declaration with string" should "match stringRegex" in {
    string.matches(Main.stringRegex.toString()) shouldBe true
  }

  val incorrectString = "e = 'test string'"

  "variable declaration with incorrect string" should "not match stringRegex" in {
    incorrectString.matches(Main.stringRegex.toString()) shouldBe false
  }

  val boolean = "c = true"

  "variable declaration with boolean" should "match booleanRegex" in {
    boolean.matches(Main.booleanRegex.toString()) shouldBe true
  }

  val incorrectBoolean = "z = fail "

  "variable declaration with incorrect boolean" should "not match booleanRegex" in {
    incorrectBoolean.matches(Main.booleanRegex.toString()) shouldBe false
  }

  val variableDeclaration = "a = b"

  "assigning a value from another variable" should "match valueRegex" in {
    variableDeclaration.matches(Main.valueRegex.toString()) shouldBe true
  }

  val incorrectVariableDeclaration = "a - b"

  "incorrect assigning a value from another variable" should "not match valueRegex" in {
    incorrectVariableDeclaration.matches(Main.valueRegex.toString()) shouldBe false
  }

}
