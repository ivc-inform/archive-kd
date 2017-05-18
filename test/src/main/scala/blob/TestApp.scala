package blob

import java.io.{File, FileInputStream}

import com.simplesys.connectionStack.BoneCPStack
import com.simplesys.tuple.TupleSS2
import ru.simplesys.defs.bo.test.table.{Upload_filesTbl, Upload_filesTblFile_content}

object TestApp extends App with BoneCPStack {
    val ds = OracleDataSource("oracleEAKD")
    
    //val upload_filesTbl = Upload_filesTbl(ds)
    //val upload_filesBo1 = Upload_filesTblFile_content(ds)


    //upload_filesTbl.insert(TupleSS2("Red_Hot.mkv", 1))
    val fileName = "Red_Hot.mkv"
    //val fileName = "build.sbt"

    val file = new File(fileName)
    val out = new FileInputStream(file)

    val con = ds.Connection
    con setAutoCommit false

    val sql = "INSERT INTO TEST_UPLOAD_FILES VALUES(?, ?, ?)"
    val pstmt = con prepareStatement sql
    pstmt.setLong(1, 1L)
    pstmt.setString(2, fileName)
    println(s"seting BinaryStream for file: $fileName ${System.currentTimeMillis()}")
    pstmt.setBinaryStream(3, out)
    pstmt.executeUpdate()
    con.commit()
    println(s"${System.currentTimeMillis()}")

    println("Test executed")

    //upload_filesBo1.updateP(Upload_filesFile_content(1L, "Red_Hot.mkv"))
}
