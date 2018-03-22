package avengers.marvel.store

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseHelper {

    init {
        val db = Database.connect(
            url = "jdbc:postgresql://localhost:5432/keys",
            driver = "org.postgresql.Driver",
            user = "user",
            password = "password")
        transaction { create(UsedKeysTable) }
    }
}