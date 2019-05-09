import org.assertj.core.api.Assertions
import org.junit.Test

class SplitShould {

	@Test
	fun `return an array with only one object if there is no match`() {
		val string = "basic_example"

		val result = Splitter().split(string, "xxx")

		Assertions.assertThat(result).containsOnly(string)
	}
}

class Splitter {

	fun split(string: String, delimiter: String): List<String> {
		return listOf(string)
	}
}