package com.ubertob.kondor.mongo.core

import com.mongodb.client.model.Filters
import com.ubertob.kondor.json.JsonProperty
import org.bson.conversions.Bson

infix fun <T> JsonProperty<T>.eq(value: T): Bson =
    Filters.eq(propName, value)

infix fun <T : Any> JsonProperty<T>.lt(value: T): Bson =
    Filters.lt(propName, value)

infix fun <T : Any> JsonProperty<T>.lte(value: T): Bson =
    Filters.lte(propName, value)

infix fun <T : Any> JsonProperty<T>.gt(value: T): Bson =
    Filters.gt(propName, value)

infix fun <T : Any> JsonProperty<T>.gte(value: T): Bson =
    Filters.gte(propName, value)


infix fun JsonProperty<*>.size(value: Int): Bson =
    Filters.size(propName, value)

infix fun <T> JsonProperty<T>.all(values: Iterable<T>): Bson =
    Filters.all(propName, values)

infix fun <T> JsonProperty<T>.`in`(values: Iterable<T>): Bson =
    Filters.`in`(propName, values)
