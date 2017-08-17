CREATE OR REPLACE FUNCTION get_md5sum_blob_fn( i_blob IN BLOB)
    RETURN RAW
IS
BEGIN
    RETURN
        SYS.DBMS_CRYPTO.HASH
        (
            src => i_blob,
            typ => SYS.DBMS_CRYPTO.HASH_MD5
        );
END;
/
