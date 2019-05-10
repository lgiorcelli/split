import com.lgior.split.index.Index
import com.lgior.split.index.IndexCollector
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.lang.RuntimeException

class IndexCollectorShould {

	lateinit var toSearch: String
	lateinit var index: List<Index>
	lateinit var string: String

	@Before
	fun setUp() {
		index = emptyList()
	}

	@Test
	fun `collect two index when match is in the middle of string`() {
		string = "AA12AA"
		toSearch = "12"

		index = IndexCollector().collect(string, toSearch)

		assertThat(index).containsExactly(Index(0, 2), Index(4, 6))
	}

	@Test
	fun `collect one index when string has one match at start`() {
		string = "12345678"
		toSearch = "12"

		index = IndexCollector().collect(string, toSearch)

		assertThat(index).containsExactly(Index(2, 8))
	}

	@Test
	fun `collect two indexes when there is two matches`() {
		string = "12341234"
		toSearch = "12"

		index = IndexCollector().collect(string, toSearch)

		assertThat(index).containsExactly(Index(2, 4), Index(6, 8))
	}

	@Test
	fun `collect three indexes when there is three matches`() {
		string = "123123123"
		toSearch = "12"

		index = IndexCollector().collect(string, toSearch)

		assertThat(index).containsExactly(Index(2, 3), Index(5, 6), Index(8, 9))
	}

	@Test
	fun `collect misses at the start`() {
		string = "ABCDE12"
		toSearch = "12"

		index = IndexCollector().collect(string, toSearch)

		assertThat(index).containsExactly(Index(0, 5))
	}

	@Test(expected = RuntimeException::class)
	fun `throw an exception when searched token is empty`() {
		string = "123456"
		toSearch = ""

		index = IndexCollector().collect(string, toSearch)
	}

	@After
	fun tearDown() {
		println("searching $toSearch in $string")
		index.map { println("index: $it - chunk: ${string.substring(it.start, it.end)}") }
	}
}

