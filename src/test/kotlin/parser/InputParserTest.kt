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
    fun `should return length if title ends with lenth in minutes`() {
        val length = InputParser("Overdoing it in Python 45min").extractLength()

        length shouldBe 45
    }
}