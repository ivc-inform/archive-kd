package blob

import com.simplesys.connectionStack.BoneCPStack

object TestApp extends App with BoneCPStack {
    val ds = OracleDataSource("oracleEAKD")
    val connection = ds.Connection
    connection.close()
}
