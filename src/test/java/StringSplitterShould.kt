import com.lgior.split.index.Index
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class StringSplitterShould {

	private val tokenizer = StringTokenizer()

	@Test
	fun `split in two parts for one index`() {
		val input = "123-45-678"

		val tokens = tokenizer.tokenize(input, listOf(Index(3, 6)))

		assertThat(tokens).containsExactly("123", "678")
	}

	@Test
	fun `split in three parts for two index`() {
		val input = "123-XX-456-XX-789"

		val tokens = tokenizer.tokenize(input, listOf(Index(3, 6), Index(10, 13)))

		assertThat(tokens).containsExactly("123", "456", "789")
	}

	@Test
	fun `split in three parts for three index`() {
		val input = "123-XX-456-XX-789"

		val tokens = tokenizer.tokenize(input, listOf(Index(3, 6), Index(10, 13)))

		assertThat(tokens).containsExactly("123", "456", "789")
	}


	@Test
	fun `split correctly when index has size 1`() {
		val input = "a,b,c"

		val tokens = tokenizer.tokenize(input, listOf(Index(1, 2), Index(3, 4)))

		assertThat(tokens).containsExactly("a", "b", "c")
	}


}

