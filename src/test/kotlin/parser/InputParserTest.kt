package parser

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class InputParserTest {
    @Test
    fun `should return length as 5 min if title ends with lightning keyword`() {
        val length = InputParser("Rails for Python Developers lightning").extractLength()

        length shouldBe 5
    }

    @Test
    fun `should return length if title ends with length in minutes`() {
        val length = InputParser("Overdoing it in Python 45min").extractLength()

        length shouldBe 45
    }

    @Test
    fun `should extract title for input containing lightning keyword`() {
        val title = InputParser("Rails for Python Developers lightning").extractTitle()

        title shouldBe "Rails for Python Developers lightning"
    }

    @Test
    fun `should extract title for input containing length in minutes`() {
        val title = InputParser("Overdoing it in Python 45min").extractTitle()

        title shouldBe "Overdoing it in Python"
    }
}