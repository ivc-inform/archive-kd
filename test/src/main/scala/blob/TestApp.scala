package blob

import com.simplesys.connectionStack.BoneCPStack
import com.simplesys.jdbc.control.table.Where
import ru.simplesys.defs.bo.test.{Upload_files, Upload_filesBo, Upload_filesBoFile_content, Upload_filesFile_content}

object TestApp extends App with BoneCPStack {
    val ds = OracleDataSource("oracleEAKD")
    val upload_filesBo = Upload_filesBo(ds)
    val upload_filesBo1 = Upload_filesBoFile_content(ds)


    upload_filesBo.insertP(Upload_files(1L, "Red_Hot.mkv"))
    println("Test executed")

    //upload_filesBo1.updateP(Upload_filesFile_content(1L, "Red_Hot.mkv"))
}
