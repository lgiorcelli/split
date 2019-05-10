package com.lgior.split.tokenizer

import com.lgior.split.index.Range

class StringTokenizer {

	fun tokenize(input: String, index: List<Range>): List<String> {
		val tokens = mutableListOf<String>()
		index.forEach {
			tokens.add(input.substring(it.start, it.end))
		}
		return tokens
	}
}