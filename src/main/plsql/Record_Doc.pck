create or replace package Record_Doc is
  procedure MainRecOrdDoc(source_srcName       varchar2,
                          source_srcLocation   varchar2,
                          source_updateTime    date,
                          source_local         integer,
                          source_srcType       varchar2,
                          source_localData     blob,
                          orddoc_format        varchar2,
                          orddoc_mimeType      varchar2,
                          orddoc_contentLength integer,
                          orddoc_comments      clob,
                          fid                  number);

  function get_lock_record(fid integer) return integer;

  procedure set_lock_record(fid integer);

end Record_Doc;
/
create or replace package body Record_Doc is
  procedure MainRecOrdDoc(source_srcName       varchar2,
                          source_srcLocation   varchar2,
                          source_updateTime    date,
                          source_local         integer,
                          source_srcType       varchar2,
                          source_localData     blob,
                          orddoc_format        varchar2,
                          orddoc_mimeType      varchar2,
                          orddoc_contentLength integer,
                          orddoc_comments      clob,
                          fid                  number) as
    ord_doc ordsys.orddoc;
    edit_id number;
  begin
    ord_doc := new
               ordsys.orddoc(source => new
                                       ordsys.ordsource(localData => source_localData, srcType => source_srcType, srcLocation => source_srcLocation, srcName => source_srcName, updateTime => source_updateTime, local => source_local), format => orddoc_format, mimeType => orddoc_mimeType, contentLength => orddoc_contentLength, comments => orddoc_comments);
  
    update arx_attatch
    set    attfile = ord_doc
    where  id = fid;
  end;

  function get_lock_record(fid integer) return integer is
    edit_id number;
  begin
    select id
    into   edit_id
    from   arx_attatch_blk
    where  id = fid
    for    update nowait;
		return 0;
  exception
    when NO_DATA_FOUND then
      insert into arx_attatch_blk
        (id)
      values
        (fid);
      return get_lock_record(fid);
    when others then
      return 2;
  end;

  procedure set_lock_record(fid integer) is
    edit_id number;
  begin
    select id
    into   edit_id
    from   arx_attatch_blk
    where  id = fid
    for    update nowait;
  exception
    when NO_DATA_FOUND then
      insert into arx_attatch_blk
        (id)
      values
        (fid);
      set_lock_record(fid);
    /*when others then
      null;*/
  end;

begin
  -- Initialization
  null;
end Record_Doc;
/
