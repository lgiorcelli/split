import org.assertj.core.api.Assertions
import org.junit.Ignore
import org.junit.Test

class SplitShould {

	@Test
	fun `return an array with only one object if there is no match`() {
		val string = "basic_example"

		val result = Splitter().split(string, "xxx")

		Assertions.assertThat(result).containsOnly(string)
	}

	@Ignore
	@Test
	fun `split in elements when there is a match`() {
		val string = "a,b"

		val result = Splitter().split(string, ",")

		Assertions.assertThat(result).containsExactly("a", "b")
	}
}

class Splitter {

	fun split(string: String, delimiter: String): List<String> {
		val indexes = mutableListOf<Int>()
		var index = string.indexOf(delimiter)

		while (index != -1) {
			indexes.add(index)
			index = string.indexOf(delimiter, ++index)
		}

		if (indexes.isEmpty()) {
			return listOf(string)
		}


		val chunks = mutableListOf<String>()
		var startIndex = 0
		var end = 0
		indexes.forEach {
			end = it
			val chunk = string.substring(startIndex, end)
			chunks.add(chunk)
			startIndex = end
		}

		return chunks
	}
}