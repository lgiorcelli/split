package com.lgior.split

import com.lgior.split.tokenizer.StringTokenizer
import com.lgior.split.index.SubStringValueInterpreter

class Splitter(private val indexCollector: SubStringValueInterpreter, private val tokenizer: StringTokenizer) {

	fun split(string: String, delimiter: String): List<String> {
		val index = indexCollector.excluding(string, delimiter)
		if (index.isEmpty()) {
			return listOf(string)
		}
		return tokenizer.tokenize(string, index)
	}
}