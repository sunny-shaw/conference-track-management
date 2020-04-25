package model

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class TalkTest {
    @Test
    fun `should create a talk for given title and length`() {
        val talk = Talk("Rails for Python Developers lightning", 5)

        talk.javaClass shouldBe Talk::class.java
    }
}