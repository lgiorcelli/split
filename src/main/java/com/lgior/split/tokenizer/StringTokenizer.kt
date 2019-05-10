package com.lgior.split.tokenizer

import com.lgior.split.index.Index

class StringTokenizer {

	fun tokenize(input: String, index: List<Index>): List<String> {
		val tokens = mutableListOf<String>()
		index.forEach {
			tokens.add(input.substring(it.start, it.end))
		}
		return tokens
	}
}