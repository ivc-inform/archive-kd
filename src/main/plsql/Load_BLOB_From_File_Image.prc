create or replace procedure Load_BLOB_From_File_Image is
  file_name_ varchar2(255) := 'file.txt';
  dest_loc blob;
  src_loc  bfile := BFILENAME('EXAMPLE_LOB_DIR', file_name_);
begin
  -- +-------------------------------------------------------------+
  -- | INSERT INITIAL BLOB VALUE (an image file) INTO THE TABLE    |
  -- +-------------------------------------------------------------+
  insert into test_upload_files
    (id,
     sfile_name,
		 blob_value)
  values
    (1001,
     file_name_,
     empty_blob())
  returning blob_value into dest_loc;

  -- +-------------------------------------------------------------+
  -- | OPENING THE SOURCE BFILE IS MANDATORY                       |
  -- +-------------------------------------------------------------+
  DBMS_LOB.OPEN(src_loc, DBMS_LOB.LOB_READONLY);

  -- +-------------------------------------------------------------+
  -- | OPENING THE LOB IS OPTIONAL                                 |
  -- +-------------------------------------------------------------+
  DBMS_LOB.OPEN(dest_loc, DBMS_LOB.LOB_READWRITE);

  -- +-------------------------------------------------------------+
  -- | SIMPLY CALL "loadfromfile" TO LOAD FILES INTO A LOB COLUMN  |
  -- +-------------------------------------------------------------+
  DBMS_LOB.LOADFROMFILE(dest_lob => dest_loc, src_lob => src_loc, amount => DBMS_LOB.getLength(src_loc));

  -- +-------------------------------------------------------------+
  -- | CLOSING ANY LOB IS MANDATORY IF YOU HAVE OPENED IT          |
  -- +-------------------------------------------------------------+
  DBMS_LOB.CLOSE(dest_loc);
  DBMS_LOB.CLOSE(src_loc);

  commit;

end Load_BLOB_From_File_Image;
/
