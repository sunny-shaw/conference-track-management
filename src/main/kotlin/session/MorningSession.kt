package session

import model.Talk
import java.time.Duration
import java.time.LocalTime

class MorningSession(startTime: LocalTime, endTime: LocalTime) {
    private val talks = mutableListOf<Talk>()
    private val totalDuration = Duration.between(startTime, endTime)

    fun getTalkCount(): Int {
        return talks.size
    }

    fun addTalk(talk: Talk): Boolean {
        return when {
            totalDuration - occupiedTalkDuration() >= talk.duration -> talks.add(talk)
            else -> false
        }
    }

    private fun occupiedTalkDuration(): Duration? {
        var minutesOccupied = Duration.ofMinutes(0)
        talks.map {
            minutesOccupied += it.duration
        }
        return minutesOccupied
    }
}
