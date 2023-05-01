@file:Suppress("unused")

package net.kigawa.kutil.kutil.list

object KutilList

fun <E> Collection<E>.contains(filter: (E)->Boolean): Boolean {
  forEach {
    if (filter(it)) return true
  }
  return false
}

fun <E, R: Any> Collection<E>.firstOrNullMap(filter: (E)->R?): R? {
  forEach {
    return filter.invoke(it) ?: return@forEach
  }
  return null
}
