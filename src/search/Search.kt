package search

object Search {

    fun binarySearch(list: List<Int>, item: Int): Int? {
        var low = 1
        var high = list.size - 1
        while (low <= high) {
            var mid = (low + high) / 2
            val guess = list[mid]
            if (guess == item) {
                return mid
            }
            if (guess > item) {
                high = mid - 1
            } else {
                low = mid + 1
            }
        }
        return null
    }
}
