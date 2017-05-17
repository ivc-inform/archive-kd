package blob

import com.simplesys.connectionStack.BoneCPStack
import com.simplesys.tuple.TupleSS2
import ru.simplesys.defs.bo.test.table.{Upload_filesTbl, Upload_filesTblFile_content}

object TestApp extends App with BoneCPStack {
    val ds = OracleDataSource("oracleEAKD")
    
    val upload_filesTbl = Upload_filesTbl(ds)
    //val upload_filesBo1 = Upload_filesTblFile_content(ds)


    upload_filesTbl.insert(TupleSS2("Red_Hot.mkv", 1))
    println("Test executed")

    //upload_filesBo1.updateP(Upload_filesFile_content(1L, "Red_Hot.mkv"))
}
