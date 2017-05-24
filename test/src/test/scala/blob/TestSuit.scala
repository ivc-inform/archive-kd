package blob

import com.simplesys.connectionStack.BoneCPStack
import oracle.jdbc.pool.OracleDataSource
import org.scalatest.FunSuite

class TestSuit extends FunSuite with BoneCPStack {
    test("load to blob") {
        val ods = new OracleDataSource

        ods.setURL("jdbc:oracle:thin:@//orapg.simplesys.lan:1521/test")
        ods.setUser("eakd")
        ods.setPassword("eakd")

        val conn = ods.getConnection

        // Create Oracle DatabaseMetaData object
        val meta = conn.getMetaData

        // gets driver info:
        println("JDBC driver version is " + meta.getDriverVersion)
    }
}
