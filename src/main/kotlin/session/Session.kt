package session

import model.Talk
import java.lang.StringBuilder
import java.time.Duration
import java.time.LocalTime

abstract class Session(private val startTime: LocalTime, endTime: LocalTime) {
    private val talks = mutableListOf<Talk>()
    private val totalDuration = Duration.between(startTime, endTime)

    abstract fun getMeridiem(): String

    fun addTalk(talk: Talk): Boolean {
        return when {
            availableDuration() >= talk.duration -> talks.add(talk)
            else -> false
        }
    }

    private fun availableDuration() = totalDuration - occupiedTalkDuration()

    private fun occupiedTalkDuration(): Duration? {
        var occupiedDuration = Duration.ofMinutes(0)
        talks.map {
            occupiedDuration += it.duration
        }
        return occupiedDuration
    }

    fun summary(): String {
        val summaryBuilder = StringBuilder()
        var talkStartTime = startTime

        talks.forEach {
            summaryBuilder.append(formattedTalkInfo(talkStartTime, it)).appendln()
            talkStartTime = talkStartTime.plusMinutes(it.duration.toMinutes())
        }

        return summaryBuilder.trimEnd().toString()
    }

    private fun formattedTalkInfo(talkStartTime: LocalTime, talk: Talk) =
        "${talkStartTime}${getMeridiem()} ${talk.title} ${talk.duration.toMinutes()}$MIN"

    companion object {
        private const val  MIN = "min"
    }
}

class MorningSession(
    startTime: LocalTime,
    endTime: LocalTime
) : Session(startTime, endTime){
    override fun getMeridiem() = AM

    companion object {
        private const val AM = "AM"
    }
}

class AfternoonSession(
    startTime: LocalTime,
    endTime: LocalTime
) : Session(startTime, endTime){
    override fun getMeridiem() = PM

    companion object {
        private const val PM = "PM"
    }
}
