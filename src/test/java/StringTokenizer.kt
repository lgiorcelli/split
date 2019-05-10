import com.lgior.split.index.Index

class StringTokenizer {

	fun tokenize(input: String, index: List<Index>): List<String> {
		index.sortedBy { it.start }
		val tokens = mutableListOf<String>()
		var start = 0
		var end: Int
		index.forEach {
			end = it.start
			tokens.add(input.substring(start, end))
			start = it.end + 1
		}
		if (start < input.length) {
			tokens.add(input.substring(start))
		}
		return tokens
	}
}