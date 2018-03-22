package avengers.marvel.store

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseHelper {

    init {
        Database.connect(
            url = "jdbc:mysql://localhost:3306/keys",
            driver = "com.mysql.jdbc.Driver",
            user = "root",
            password = "password")
        transaction {
                logger.addLogger(StdOutSqlLogger)
                create(UsedKeysTable)
        }
    }
}