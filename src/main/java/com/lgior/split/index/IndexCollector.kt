package com.lgior.split.index

class IndexCollector {

	fun collect(input: String, toSearch: String): List<Index> {
		if (thereIsNoMatch(input, toSearch)) {
			return emptyList()
		}
		return collectIndexOfMatches(input, toSearch)
	}

	private fun collectIndexOfMatches(input: String, toSearch: String): MutableList<Index> {
		val index = mutableListOf<Index>()
		var start = input.indexOf(toSearch)
		val end = toSearch.length
		while (start != -1) {
			index.add(Index(start, start + end))
			start = input.indexOf(toSearch, start + end)
		}
		return index
	}

	private fun thereIsNoMatch(input: String, toSearch: String) = input.indexOf(toSearch) == -1
}