package avengers.marvel.store

import org.jetbrains.exposed.sql.Table

object UsedKeysTable: Table()
{
    val id = integer("id").autoIncrement().primaryKey()
    val name = varchar("name", length = 10)
}
