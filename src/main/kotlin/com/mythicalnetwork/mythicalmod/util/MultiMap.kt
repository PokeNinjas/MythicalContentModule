package com.mythicalnetwork.mythicalmod.util

class MultiMap<K, V> {
    private val map: MutableMap<K, MutableCollection<V>?> = hashMapOf()

    fun put(key: K, value: V) {
        if (map[key] == null) map[key] = arrayListOf()
        map[key]!!.add(value)
    }

    fun putIfAbsent(key: K, value: V) {
        if (map[key] == null) map[key] = arrayListOf()
        if (!map[key]!!.contains(value)) map[key]!!.add(value)
    }

    fun clear() {
        map.clear()
    }

    fun remove(key: K, value: V): Boolean {
        return if (map[key] != null) map[key]!!.remove(value) else false
    }

    fun containsKey(key: K?): Boolean = map.containsKey(key)

    fun remove(key: K) {
        map.remove(key)
    }

    fun get(key: K): MutableCollection<V>? = map[key]

    fun interator(): MutableIterator<MutableMap.MutableEntry<K, MutableCollection<V>?>> {
        return map.iterator()
    }

    fun values(): MutableCollection<MutableCollection<V>?> {
        return map.values
    }

    fun size(): Int {
        var size = 0
        for (value in map.values) size += value!!.size
        return size
    }

    fun entrySet(): Set<Map.Entry<K, Collection<V>?>> = map.entries
}