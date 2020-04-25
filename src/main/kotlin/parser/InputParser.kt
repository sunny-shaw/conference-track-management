package parser

class InputParser(private val input: String) {
    companion object {
        private const val SPACE = " "
        private const val LIGHTNING = "lightning"
        private const val FIVE = 5
        private const val LENGTH_DELIMITER = 'm'
    }

    fun extractLength(): Int {
        val inputArray = input.split(SPACE).toTypedArray()
        return when(inputArray.contains(LIGHTNING)) {
            true -> FIVE
            else -> readLength(inputArray)
        }
    }

    private fun readLength(inputArray: Array<String>) =
        inputArray[inputArray.size - 1].substringBefore(LENGTH_DELIMITER).toInt()

}
