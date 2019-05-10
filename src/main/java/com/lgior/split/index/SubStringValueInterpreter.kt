package com.lgior.split.index

import java.lang.RuntimeException

class SubStringValueInterpreter {

	fun excluding(string: String, toExclude: String): List<Range> {
		validateValueToExclude(toExclude)
		if (thereIsNoMatch(string, toExclude)) {
			return emptyList()
		}
		return collectIndexOfExclusions(string, toExclude)
	}

	private fun validateValueToExclude(toSearch: String) {
		if (toSearch.isBlank()) {
			throw RuntimeException("Can not collect values for an empty value to search")
		}
	}

	private fun collectIndexOfExclusions(input: String, toSearch: String): List<Range> {
		val exclusions = mutableListOf<Range>()
		analiseFirstRange(input, toSearch, exclusions)
		collectExclusions(input, toSearch, exclusions)
		return exclusions
	}

	private fun collectExclusions(input: String, toSearch: String, index: MutableList<Range>) {
		var begin = input.indexOf(toSearch)
		while (thereIsAMatch(begin)) {
			val nextMatch = input.indexOf(toSearch, begin + toSearch.length)
			val end = selectEndOfRange(nextMatch, input)
			if (rangeHasValue(begin, toSearch, input)) {
				index.add(Range(begin + toSearch.length, end))
			}
			begin = nextMatch
		}
	}

	private fun rangeHasValue(begin: Int, toSearch: String, input: String): Boolean {
		return begin + toSearch.length != input.length
	}

	private fun analiseFirstRange(input: String, toSearch: String, index: MutableList<Range>) {
		val start= input.indexOf(toSearch)
		if (start > 0) {
			index.add(Range(0, start))
		}
	}

	private fun selectEndOfRange(nextMatch: Int, input: String): Int {
		return if (thereIsAMatch(nextMatch)) {
			nextMatch
		} else {
			input.length
		}
	}

	private fun thereIsAMatch(start: Int) = start != -1

	private fun thereIsNoMatch(input: String, toSearch: String) = input.indexOf(toSearch) == -1
}