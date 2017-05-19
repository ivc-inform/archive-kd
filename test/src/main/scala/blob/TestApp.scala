package blob

import java.io._

import com.simplesys.connectionStack.BoneCPStack
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

/*object TestApp extends App with BoneCPStack {
    val ds = OracleDataSource("oracleEAKD")

    //val upload_filesTbl = Upload_filesTbl(ds)
    //val upload_filesBo1 = Upload_filesTblFile_content(ds)


    //upload_filesTbl.insert(TupleSS2("Red_Hot.mkv", 1))
    //val fileName = "Red_Hot.mkv"
    //val fileName = "Кейт и Лео.avi"
    val fileName = "SoapUI-x64-5.3.0.sh"
    //val fileName = "Chelovek_bez_pasporta.avi"
    //val fileName = "build.sbt"

    /*val file = new File(fileName)
    val fin = new FileInputStream(file)
    val l = file.length
    val fileContent = new Array[Byte](file.length.asInstanceOf[Int])*/

    val con = ds.Connection
    con setAutoCommit false

    val sql = "INSERT INTO TEST_UPLOAD_FILES VALUES(?, ?, ?)"
    val pstmt = con prepareStatement sql
    pstmt.setLong(1, 1L)
    pstmt.setString(2, fileName)

    val startTime = System.currentTimeMillis()

    val path = Paths.get(fileName)
    println(s"Paths.get")

    val sbc = Files.newByteChannel(path)
    println(s"Files.newByteChannel")

    val in = Channels.newInputStream(sbc)
    println(s"Channels.newInputStream")

    pstmt.setBlob(3, in)
    println(s"pstmt.setBinaryStream")

    pstmt.executeUpdate()
    println(s"pstmt.executeUpdate")

    con.commit()
    println(s"con.commit")

    val elapsedTime = System.currentTimeMillis() - startTime

    println(s"elapsedTime for $fileName : ${DT(elapsedTime).toString}")
    println("Test executed")

    //upload_filesBo1.updateP(Upload_filesFile_content(1L, "Red_Hot.mkv"))
}*/

/*object TestApp extends App with BoneCPStack {
    val ds = OracleDataSource("oracleEAKD")

    //val upload_filesTbl = Upload_filesTbl(ds)
    //val upload_filesBo1 = Upload_filesTblFile_content(ds)


    //upload_filesTbl.insert(TupleSS2("Red_Hot.mkv", 1))
    //val fileName = "Red_Hot.mkv"
    val fileName = "SoapUI-x64-5.3.0.sh"
    //val fileName = "Chelovek_bez_pasporta.avi"
    //val fileName = "build.sbt"

    val file = new File(fileName)
    val fin = new FileInputStream(file)
    val fileContent = new Array[Byte](file.length.asInstanceOf[Int])

    val con = ds.Connection
    con setAutoCommit false

    val sql = "INSERT INTO TEST_UPLOAD_FILES VALUES(?, ?, ?)"
    val pstmt = con prepareStatement sql
    pstmt.setLong(1, 1L)
    pstmt.setString(2, fileName)

    val startTime = System.currentTimeMillis()

    fin.read(fileContent)
    println(s"fin.read")

    pstmt.setBytes(3, fileContent)
    println(s"pstmt.setBytes")

    pstmt.executeUpdate()
    println(s"pstmt.executeUpdate")

    con.commit()
    println(s"con.commit")

    val elapsedTime = System.currentTimeMillis() - startTime

    println(s"elapsedTime for $fileName : ${DT(elapsedTime).toString}")
    println("Test executed")

    //upload_filesBo1.updateP(Upload_filesFile_content(1L, "Red_Hot.mkv"))
}*/


object TestApp1 extends App with BoneCPStack {
    //val oracleDS = OracleConnectionPoolDataSource

    val ds = OracleDataSource("oracleEAKD")

    //val upload_filesTbl = Upload_filesTbl(ds)
    //val upload_filesBo1 = Upload_filesTblFile_content(ds)


    //upload_filesTbl.insert(TupleSS2("Red_Hot.mkv", 1))
    val fileName = "Red_Hot.mkv"
    //val fileName = "SoapUI-x64-5.3.0.sh"
    //val fileName = "Chelovek_bez_pasporta.avi"
    //val fileName = "build.sbt"

    val file = new File(fileName)
    val fileSize = file.length()
    val inputStream = new FileInputStream(file)
    val bufferSize = 1024 * 1024 * 100

    val buffer = new Array[Byte](bufferSize)

    val con = ds.Connection
    con setAutoCommit false

    val sqlInsert = "INSERT INTO TEST_UPLOAD_FILES VALUES(?, ?, EMPTY_BLOB())"

    val startTime = System.currentTimeMillis()

    val pstmt = con prepareStatement sqlInsert
    pstmt.setLong(1, 1)
    pstmt.setString(2, fileName)
    pstmt.executeUpdate()

    {
        val sqlSelect = "SELECT BLOB_VALUE FROM TEST_UPLOAD_FILES WHERE ID = ? FOR UPDATE"
        val pstmt = con prepareStatement sqlSelect
        pstmt.setLong(1, 1)

        val rset = pstmt.executeQuery

        rset.next()
        val blob = rset.getBlob("BLOB_VALUE")

        //val outputStream = new BufferedOutputStream(blob.setBinaryStream(1L))
        val outputStream = blob.setBinaryStream(1L)

        IOUtils.copy(inputStream, outputStream, bufferSize)

        outputStream.close()
        inputStream.close()

        con.commit()
        rset.close()
        ds.close()
    }

    val elapsedTime = System.currentTimeMillis() - startTime

    println(s"elapsedTime for $fileName : ${DT(elapsedTime).toString}")
    println("Test executed")

    //upload_filesBo1.updateP(Upload_filesFile_content(1L, "Red_Hot.mkv"))
}

object TestApp2 {
    def main(args: Array[String]): Unit = {
        val ods = new OracleDataSource

        ods.setURL("jdbc:oracle:thin:@//orapg.simplesys.lan:1521/test")
        ods.setUser("eakd")
        ods.setPassword("eakd")

        val conn = ods.getConnection

        // Create Oracle DatabaseMetaData object
        val meta = conn.getMetaData

        // gets driver info:
        println("JDBC driver version is " + meta.getDriverVersion)

        //val fileName = "Red_Hot.mkv"
        //val fileName = "Кейт и Лео.avi"
        val fileName = "SoapUI-x64-5.3.0.sh"
        //val fileName = "Chelovek_bez_pasporta.avi"
        //val fileName = "build.sbt"

        val file = new File(fileName)
        val inputStream = new FileInputStream(file)

    }
}
