package test

import com.simplesys.container.{Helper, OrdDoc}
import oracle.jdbc.OracleResultSet
import oracle.jdbc.driver.OracleConnection
import oracle.jdbc.pool.OracleDataSource
import oracle.sql.NUMBER

object TestApp2 {


    def main(args: Array[String]): Unit = {
        val ds = new OracleDataSource

        val bufferSize = 1024 * 1024 * 100

        //ds.setURL("jdbc:oracle:thin:@//orapg.simplesys.lan:1521/test")
        ds.setURL("jdbc:oracle:thin:@//localhost:1521/orcl")
        ds.setUser("B404SP3DEMO")
        ds.setPassword("dfqc2")

        val conn = ds.getConnection.asInstanceOf[OracleConnection]
        conn setAutoCommit false

        // Create Oracle DatabaseMetaData object
        val meta = conn.getMetaData

        // gets driver info:
        println(s"JDBC driver version is ${meta.getDriverVersion}")

        val startTime = System.currentTimeMillis()

        val stmt = conn.createStatement

        val ors = stmt.executeQuery("select ATTFILE from ARX_ATTATCH").asInstanceOf[OracleResultSet]
        val ordDoc = if (ors.next())
            Some(ors.getObject(1, OrdDoc.getOracleDataFactory()).asInstanceOf[OrdDoc])
        else
            None

        var nextExists = ors.next()
        var i = 1

        while (nextExists) {
            val ordDoc = Option(ors.getObject(1, OrdDoc.getOracleDataFactory()).asInstanceOf[OrdDoc])
            ordDoc match {
                case Some(ordDoc) ⇒
                    println(s"#$i ordDoc: {source: ${ordDoc.source}, format: ${Helper.asString(ordDoc.format)}, mimeType: ${Helper.asString(ordDoc.mimeType)}, contentLength: ${NUMBER.toBigDecimal(ordDoc.contentLength.toBytes)}, comments: ${Helper.clobToString(ordDoc.comments)}")
                case None ⇒
                    println(s"#$i ordDoc: null")
            }
            nextExists = ors.next()
            i += 1
        }


        //
        //        //val fileName = "Red_Hot.mkv"
        //        //val fileName = "Кейт и Лео.avi"
        //        //val fileName = "SoapUI-x64-5.3.0.sh"
        //        val fileName = "files/Вий.avi"
        //        //val fileName = "Chelovek_bez_pasporta.avi"
        //        //val fileName = "build.sbt"
        //
        //        println(s"Start at ${DateTime.now()} file: $fileName")
        //
        //        val sql = "INSERT INTO TEST_UPLOAD_FILES VALUES(?, ?, ?)"
        //        //val pstmt = conn prepareStatement sql
        //        val pstmt = (conn prepareStatement sql).asInstanceOf[OraclePreparedStatement]
        //
        //        pstmt.setLong(1, 1L)
        //        pstmt.setString(2, fileName)
        //
        //        val file = new File(fileName)
        //        val inputStream = new FileInputStream(file)
        //
        //        pstmt.setBlob(3, inputStream, file.length())
        //        //pstmt.setBinaryStream(3, )
        //
        //        pstmt.execute()
        //        println(s"pstmt.executeUpdate")
        //
        //        conn.commit()
        //        println(s"con.commit")
        //
        //        val elapsedTime = System.currentTimeMillis() - startTime
        //
        //        println(s"elapsedTime for $fileName : ${DT(elapsedTime).toString}")
        //        println("Test executed")
        //        println(s"Stop at ${DateTime.now()}")
    }
}
