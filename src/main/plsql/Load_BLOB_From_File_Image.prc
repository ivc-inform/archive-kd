create or replace procedure Load_BLOB_From_File_Image(file_name_ varchar2) is
  dest_loc blob;
  src_loc  bfile := BFILENAME('EXAMPLE_LOB_DIR', file_name_);
  fid integer;
  flength integer := DBMS_LOB.getLength(src_loc);
begin
  -- +-------------------------------------------------------------+
  -- | INSERT INITIAL BLOB VALUE (an image file) INTO THE TABLE    |
  -- +-------------------------------------------------------------+
  select SEQ_TEST_UPLOAD_FILES.Nextval into fid from dual;
  
  insert into test_upload_files
    (id,
     sfile_name,
		 blob_value,
     sz)
  values
    (fid,
     file_name_,
     empty_blob(),
     flength)
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
  DBMS_LOB.LOADFROMFILE(dest_lob => dest_loc, src_lob => src_loc, amount => flength);

  -- +-------------------------------------------------------------+
  -- | CLOSING ANY LOB IS MANDATORY IF YOU HAVE OPENED IT          |
  -- +-------------------------------------------------------------+
  DBMS_LOB.CLOSE(dest_loc);
  DBMS_LOB.CLOSE(src_loc);

  commit;

end Load_BLOB_From_File_Image;
/
