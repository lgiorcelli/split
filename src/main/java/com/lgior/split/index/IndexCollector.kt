package com.lgior.split.index

class IndexCollector {

	fun collect(input: String, toSearch: String): List<Index> {
		if (thereIsNoMatch(input, toSearch)) {
			return emptyList()
		}
//		return collectIndexOfMatches(input, toSearch)
		return collectIndexOfMisses(input, toSearch)
	}

	private fun collectIndexOfMisses(input: String, toSearch: String): List<Index> {
		val index = mutableListOf<Index>()
		var start = input.indexOf(toSearch)
		if (start > 0) {
			index.add(Index(0, start))
		}
		while (start != -1) {
			val nextMatch = input.indexOf(toSearch, start + toSearch.length)
			if (nextMatch != -1) {
				index.add(Index(start + toSearch.length, nextMatch))
			} else {
				if (start + toSearch.length != input.length) {
					index.add(Index(start + toSearch.length, input.length))
				}
			}
			start = nextMatch
		}
		return index
	}

	private fun collectIndexOfMatches(input: String, toSearch: String): MutableList<Index> {
		val index = mutableListOf<Index>()
		var start = input.indexOf(toSearch)
		while (start != -1) {
			index.add(Index(start, start + toSearch.length))
			start = input.indexOf(toSearch, start + toSearch.length)
		}
		return index
	}

	private fun thereIsNoMatch(input: String, toSearch: String) = input.indexOf(toSearch) == -1
}