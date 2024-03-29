import com.lgior.split.index.Range
import com.lgior.split.tokenizer.StringTokenizer
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class SubStringCollectorShould {

	private val tokenizer = StringTokenizer()

	@Test
	fun `split in two parts for one index`() {
		val input = "123-45-678"

		val tokens = tokenizer.tokenize(input, listOf(Range(0, 3), Range(7, 10)))

		assertThat(tokens).containsExactly("123", "678")
	}

	@Test
	fun `split in three parts for two index`() {
		val input = "123-XX-456-XX-789"

		val tokens = tokenizer.tokenize(input, listOf(Range(0, 3), Range(7, 10), Range(14, 17)))

		assertThat(tokens).containsExactly("123", "456", "789")
	}

	@Test
	fun `split in three parts for three index`() {
		val input = "123-XX-456-XX-789"

		val tokens = tokenizer.tokenize(input, listOf(Range(0, 3), Range(7, 10), Range(14, 17) ))

		assertThat(tokens).containsExactly("123", "456", "789")
	}


	@Test
	fun `split correctly when index has size 1`() {
		val input = "a,b,c"

		val tokens = tokenizer.tokenize(input, listOf(Range(0, 1), Range(2, 3), Range(4,5)))

		assertThat(tokens).containsExactly("a", "b", "c")
	}


}

