import com.lgior.split.Splitter
import com.lgior.split.index.IndexCollector
import com.lgior.split.tokenizer.StringTokenizer
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.ListAssert
import org.junit.Test

class SplitShould {
	private val collector = IndexCollector()
	private val tokenizer = StringTokenizer()

	private val splitter = Splitter(collector, tokenizer)

	@Test
	fun `return an array with only one object if there is no match`() {
		val string = "basic_example"

		val result = splitter.split(string, "xxx")

		assertThat(result).containsOnly(string)
	}

	@Test
	fun `split in elements when there is a match`() {
		splitting("a,b,c", ",").containsExactly("a", "b", "c")
		splitting("a,b,c", "b").containsExactly("a,", ",c")
		splitting("a,b,c", "a").containsExactly(",b,c")
		splitting("a,b,c", "c").containsExactly("a,b,")
		splitting("", "a").containsExactly("")
		splitting("ABABABABABA", "BAB").containsExactly("A","A", "ABA")
		splitting("ABABABABABAB", "BAB").containsExactly("A","A", "A")
	}

	private fun splitting(string: String, delimiter: String): ListAssert<String> {
		return assertThat(splitter.split(string, delimiter))
	}

}

