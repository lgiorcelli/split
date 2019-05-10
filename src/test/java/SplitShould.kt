import com.lgior.split.index.IndexCollector
import org.assertj.core.api.Assertions.assertThat
import org.junit.Ignore
import org.junit.Test

class SplitShould {
	val collector = IndexCollector()
	val tokenizer = StringTokenizer()

	val splitter = Splitter(collector, tokenizer)

	@Test
	fun `return an array with only one object if there is no match`() {
		val string = "basic_example"

		val result = splitter.split(string, "xxx")

		assertThat(result).containsOnly(string)
	}

	@Ignore
	@Test
	fun `split in elements when there is a match`() {
		val string = "a,b,c"

		val result = splitter.split(string, ",")

		assertThat(result).containsExactly("a", "b", "c")
	}

	@Test
	fun testName() {
		val string = "abcbd"

		val result = splitter.split(string, "bc")

		assertThat(result).containsExactly("a", "bd")
	}
}

class Splitter(private val indexCollector: IndexCollector, private val tokenizer: StringTokenizer) {

	fun split(string: String, delimiter: String): List<String> {
		val index = indexCollector.collect(string, delimiter)
		return tokenizer.tokenize(string, index)
	}
}