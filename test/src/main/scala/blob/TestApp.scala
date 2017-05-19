package blob

import java.io._

import oracle.jdbc.OraclePreparedStatement
import oracle.jdbc.pool.OracleDataSource
import org.apache.commons.io._

object DT {
    def apply(d: Long): DT = {
        var seconds: Long = d / 1000
        var minits = 0L
        var hours = 0L
        var days = 0L

        if (seconds / 60 > 0) {
            minits = seconds / 60
            seconds -= minits * 60
        }

        if (minits / 60 > 0) {
            hours = minits / 60
            minits -= hours * 60
        }

        if (hours / 24 > 0) {
            days = hours / 24
            hours -= days * 24
        }

        DT(days, hours, minits, seconds)
    }
}

case class DT(days: Long, hours: Long, minits: Long, seconds: Long) {
    override def toString: String = s"Days: $days, Hours: $hours, Mins: $minits, Secs: $seconds"
}

object TestApp2 {
    def main(args: Array[String]): Unit = {
        val ds = new OracleDataSource

        val bufferSize = 1024 * 1024 * 100

        ds.setURL("jdbc:oracle:thin:@//orapg.simplesys.lan:1521/test")
        ds.setUser("eakd")
        ds.setPassword("eakd")

        val conn = ds.getConnection
        conn setAutoCommit false

        // Create Oracle DatabaseMetaData object
        val meta = conn.getMetaData

        // gets driver info:
        println(s"JDBC driver version is ${meta.getDriverVersion}")

        val startTime = System.currentTimeMillis()

        //val fileName = "Red_Hot.mkv"
        //val fileName = "Кейт и Лео.avi"
        val fileName = "SoapUI-x64-5.3.0.sh"
        //val fileName = "Chelovek_bez_pasporta.avi"
        //val fileName = "build.sbt"

        val sql = "INSERT INTO TEST_UPLOAD_FILES VALUES(?, ?, ?)"
        //val pstmt = conn prepareStatement sql
        val pstmt = (conn prepareStatement sql).asInstanceOf[OraclePreparedStatement]

        pstmt.setLong(1, 1L)
        pstmt.setString(2, fileName)

        val file = new File(fileName)
        val inputStream = new FileInputStream(file)

//        val blob = conn.createBlob()
//
//        val outputStream = blob.setBinaryStream(1)
//
//        IOUtils.copy(inputStream, outputStream, bufferSize)
        pstmt.setBinaryStream(3, inputStream, file.length())

        pstmt.executeUpdate()
        println(s"pstmt.executeUpdate")

        conn.commit()
        println(s"con.commit")

        val elapsedTime = System.currentTimeMillis() - startTime

        println(s"elapsedTime for $fileName : ${DT(elapsedTime).toString}")
        println("Test executed")

    }
}
