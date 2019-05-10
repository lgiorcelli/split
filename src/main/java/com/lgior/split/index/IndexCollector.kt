package com.lgior.split.index

class IndexCollector {

	fun collect(input: String, toSearch: String): List<Index> {
		if (thereIsNoMatch(input, toSearch)) {
			return emptyList()
		}
		return collectIndexOfMisses(input, toSearch)
	}

	private fun collectIndexOfMisses(input: String, toSearch: String): List<Index> {
		val index = mutableListOf<Index>()
		var start = input.indexOf(toSearch)
		if (start > 0) {
			index.add(Index(0, start))
		}
		while (thereIsAMatch(start)) {
			val nextMatch = input.indexOf(toSearch, start + toSearch.length)
			val end = selectEndOfRange(nextMatch, input)
			if (start + toSearch.length != input.length) {
				index.add(Index(start + toSearch.length, end))
			}
			start = nextMatch
		}
		return index
	}

	private fun selectEndOfRange(nextMatch: Int, input: String): Int {
		return if (thereIsAMatch(nextMatch)) {
			nextMatch
		} else {
			input.length
		}
	}

	private fun collectIndexOfMatches(input: String, toSearch: String): MutableList<Index> {
		val index = mutableListOf<Index>()
		var start = input.indexOf(toSearch)
		while (thereIsAMatch(start)) {
			index.add(Index(start, start + toSearch.length))
			start = input.indexOf(toSearch, start + toSearch.length)
		}
		return index
	}

	private fun thereIsAMatch(start: Int) = start != -1

	private fun thereIsNoMatch(input: String, toSearch: String) = input.indexOf(toSearch) == -1
}