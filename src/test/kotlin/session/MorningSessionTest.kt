package session

import io.kotlintest.shouldBe
import model.Talk
import org.junit.jupiter.api.Test
import java.time.Duration
import java.time.LocalTime

class MorningSessionTest {
    private val startTime = LocalTime.of(9, 0)
    private val endTime = LocalTime.of(12, 0)

    @Test
    fun `should create a session with given start and end time`() {
        val morningSession = MorningSession(startTime, endTime)


        morningSession.javaClass shouldBe MorningSession::class.java
    }

    @Test
    fun `should be able to add talks for morning session`() {
        val morningSession = MorningSession(startTime, endTime)
        val kotlinTalk = Talk("Kotlin talk", Duration.ofMinutes(45))

        val isTalkAdded = morningSession.addTalk(kotlinTalk)

        isTalkAdded shouldBe true
    }

    @Test
    fun `should not be able to add talk if talk length is more than session unoccupied duration`() {
        val startTime = LocalTime.of(9, 0)
        val endTime = LocalTime.of(9, 30)
        val morningSession = MorningSession(startTime, endTime)
        val talk = Talk("Java talk", Duration.ofMinutes(35))

        morningSession.addTalk(talk) shouldBe false
    }

    @Test
    fun `should return session summary with talk details like start time & duration`() {
        val morningSession = MorningSession(startTime, endTime)
        val talk1 = Talk("TDD Explained", Duration.ofMinutes(60))
        val talk2 = Talk("TDD Explained", Duration.ofMinutes(20))

        val isTalkAdded1 = morningSession.addTalk(talk1)
        val isTalkAdded2 = morningSession.addTalk(talk2)
        val talkSummary = morningSession.summary()

        isTalkAdded1 shouldBe true
        isTalkAdded2 shouldBe true
        talkSummary shouldBe "09:00AM TDD Explained 60min\n" +
                                "10:00AM TDD Explained 20min"
    }
}