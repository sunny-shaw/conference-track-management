package session

import io.kotlintest.shouldBe
import model.Talk
import org.junit.jupiter.api.Test
import java.time.Duration
import java.time.LocalTime

class AfternoonSessionTest {
    private val startTime = LocalTime.of(1, 0)
    private val endTime = LocalTime.of(4, 0)

    @Test
    fun `should return session summary with talk details like start time & duration`() {
        val afternoonSession = AfternoonSession(startTime, endTime)
        val talk1 = Talk("TDD Explained", Duration.ofMinutes(60))
        val talk2 = Talk("TDD Explained", Duration.ofMinutes(20))

        val isTalkAdded1 = afternoonSession.addTalk(talk1)
        val isTalkAdded2 = afternoonSession.addTalk(talk2)
        val talkSummary = afternoonSession.summary()

        isTalkAdded1 shouldBe true
        isTalkAdded2 shouldBe true
        talkSummary shouldBe "01:00PM TDD Explained 60min\n" +
                                "02:00PM TDD Explained 20min"
    }
}