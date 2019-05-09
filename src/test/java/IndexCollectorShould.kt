import org.assertj.core.api.Assertions.assertThat
import org.junit.Ignore
import org.junit.Test

class IndexCollectorShould {

	@Test
	fun `collect one index when string has one match`() {
		val string = "12345678"

		val toSearch = "12"
		val indexes = IndexCollector().collect(string, toSearch)

		assertThat(indexes).containsExactly(Index(0, 2))

		println("string.substring(0,2) = ${string.substring(0, 2)}")
	}

	@Test
	fun `collect two indexes when there is two matches`() {
		val string = "12341234"

		val toSearch = "12"
		val indexes = IndexCollector().collect(string, toSearch)

		assertThat(indexes).containsExactly(Index(0, 2), Index(4, 6))

		println("string.substring(0,2) = ${string.substring(0, 2)}")
		println("string.substring(4,6) = ${string.substring(4, 6)}")
	}

	@Test
	fun `collect three indexes when there is three matches`() {
		val string = "123123123"

		val toSearch = "12"
		val indexes = IndexCollector().collect(string, toSearch)

		assertThat(indexes).containsExactly(Index(0, 2), Index(3, 5), Index(6,8))
	}
}

class IndexCollector {

	fun collect(input: String, toSearch: String): List<Index> {
		val start = input.indexOf(toSearch)
		if (start == -1) {
			return emptyList()
		}

		val indexes = mutableListOf<Index>()
		var newStart = start
		val end = toSearch.length
		while (newStart != -1) {
			indexes.add(Index(newStart, newStart + end))
			newStart = input.indexOf(toSearch, newStart + end)
		}

		return indexes
	}
}

data class Index(val start: Int, val end: Int)