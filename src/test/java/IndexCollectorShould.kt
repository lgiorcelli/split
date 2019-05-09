import com.lgior.split.index.Index
import com.lgior.split.index.IndexCollector
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Test

class IndexCollectorShould {

	private val toSearch = "12"
	lateinit var index: List<Index>
	lateinit var string: String

	@Test
	fun `collect one index when string has one match`() {
		string = "12345678"

		index = IndexCollector().collect(string, toSearch)

		assertThat(index).containsExactly(Index(0, 2))
	}

	@Test
	fun `collect two indexes when there is two matches`() {
		string = "12341234"

		index = IndexCollector().collect(string, toSearch)

		assertThat(index).containsExactly(Index(0, 2), Index(4, 6))
	}

	@Test
	fun `collect three indexes when there is three matches`() {
		string = "123123123"

		index = IndexCollector().collect(string, toSearch)

		assertThat(index).containsExactly(Index(0, 2), Index(3, 5), Index(6, 8))
	}

	@After
	fun tearDown() {
		println("searching $toSearch in $string")
		index.map { println("index: $it - chunk: ${string.substring(it.start, it.end)}") }
	}
}

