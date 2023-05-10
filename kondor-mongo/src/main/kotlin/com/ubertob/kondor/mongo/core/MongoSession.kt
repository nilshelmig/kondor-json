package com.ubertob.kondor.mongo.core

import com.mongodb.client.MongoCollection
import com.mongodb.client.model.FindOneAndDeleteOptions
import com.mongodb.client.model.FindOneAndReplaceOptions
import com.mongodb.client.model.FindOneAndUpdateOptions
import com.mongodb.client.model.ReturnDocument
import org.bson.BsonDocument
import org.bson.BsonValue
import org.bson.Document
import org.bson.conversions.Bson

interface MongoSession {

    //General Methods
    fun listCollections(filter: Bson? = null): List<Document>

    fun listCollectionNames(filter: Bson? = null): List<String>

    //Edit Methods
    fun <T : Any> MongoTable<T>.addDocument(doc: T): BsonValue?
    fun <T : Any> MongoTable<T>.addDocuments(docs: Iterable<T>): List<BsonValue>

    fun <T : Any> MongoTable<T>.removeDocuments(queryString: String): Long
    fun <T : Any> MongoTable<T>.removeDocuments(bsonFilters: Bson): Long
    fun <T : Any> MongoTable<T>.findOneAndUpdate(
        bsonFilters: Bson,
        bsonSetter: Bson,
        options: FindOneAndUpdateOptions = RETURN_UPDATED
    ): T?

    fun <T : Any> MongoTable<T>.findOneAndReplace(
        bsonFilters: Bson,
        doc: T,
        options: FindOneAndReplaceOptions = UPSERT_RETURN_UPDATED
    ): T?

    fun <T : Any> MongoTable<T>.findOneAndDelete(
        bsonFilters: Bson,
        options: FindOneAndDeleteOptions = FindOneAndDeleteOptions()
    ): T?

    //Query Methods
    fun <T : Any> MongoTable<T>.findById(id: Any): T?
    fun <T : Any> MongoTable<T>.find(queryString: String): Sequence<T>
    fun <T : Any> MongoTable<T>.find(bsonFilters: Bson): Sequence<T>

    fun <T : Any> MongoTable<T>.all(): Sequence<T> = find("")
    fun <T : Any> MongoTable<T>.aggregate(vararg pipeline: Bson): Sequence<BsonDocument>

    fun MongoTable<*>.countDocuments(): Long

    //Table Methods
    fun <T : Any> MongoTable<T>.drop()
    fun <T : Any> MongoTable<T>.listIndexes(): Sequence<BsonDocument>

    companion object {
        val RETURN_UPDATED = FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER)
        val RETURN_BEFORE = FindOneAndUpdateOptions().returnDocument(ReturnDocument.BEFORE)
        val UPSERT_RETURN_UPDATED = FindOneAndReplaceOptions().upsert(true).returnDocument(ReturnDocument.AFTER)
    }

}

typealias CollectionCache = MutableMap<String, MongoCollection<BsonDocument>>

