package blob

import java.io.{File, FileInputStream}

import com.simplesys.connectionStack.BoneCPStack

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

object TestApp extends App with BoneCPStack {
    val ds = OracleDataSource("oracleEAKD")

    //val upload_filesTbl = Upload_filesTbl(ds)
    //val upload_filesBo1 = Upload_filesTblFile_content(ds)


    //upload_filesTbl.insert(TupleSS2("Red_Hot.mkv", 1))
    val fileName = "Red_Hot.mkv"
    //val fileName = "SoapUI-x64-5.3.0.sh"
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
}
