package test

import com.simplesys.container.scala.GetAttFile
import oracle.jdbc.driver.OracleConnection
import oracle.jdbc.pool.OracleDataSource


object TestApp2 {

    def main(args: Array[String]): Unit = {
        val ds = new OracleDataSource

        val bufferSize = 1024 * 1024 * 100

        //ds.setURL("jdbc:oracle:thin:@//orapg.simplesys.lan:1521/test")
        ds.setURL("jdbc:oracle:thin:@//localhost:1521/orcl")
        ds.setUser("B404SP3DEMO")
        ds.setPassword("dfqc2")

        implicit val conn = ds.getConnection.asInstanceOf[OracleConnection]
        conn setAutoCommit false

        // Create Oracle DatabaseMetaData object
        val meta = conn.getMetaData

        // gets driver info:
        println(s"JDBC driver version is ${meta.getDriverVersion}")

        val startTime = System.currentTimeMillis()

        val ordDoc = GetAttFile.getOrdDocs((131 to 190).map(BigDecimal(_)): _*)

        ordDoc.foreach(ordDoc ⇒ println(s"ordDoc: {source: ${ordDoc.source}, format: ${ordDoc.format}, mimeType: ${ordDoc.mimeType}, contentLength: ${ordDoc.contentLength}, comments: ${ordDoc.comments}"))

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
