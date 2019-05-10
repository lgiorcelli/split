package com.lgior.split

import com.lgior.split.tokenizer.StringTokenizer
import com.lgior.split.index.IndexCollector

class Splitter(private val indexCollector: IndexCollector, private val tokenizer: StringTokenizer) {

	fun split(string: String, delimiter: String): List<String> {
		val index = indexCollector.collect(string, delimiter)
		if (index.isEmpty()) {
			return listOf(string)
		}
		return tokenizer.tokenize(string, index)
	}
}