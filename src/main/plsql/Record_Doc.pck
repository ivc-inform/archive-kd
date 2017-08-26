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

  function check_loc_record(fid integer) return integer;

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
    select id
    into   edit_id
    from   arx_attatch
    where  id = fid
    for    update nowait;
    ord_doc := new
               ordsys.orddoc(source => new
                                       ordsys.ordsource(localData => source_localData, srcType => source_srcType, srcLocation => source_srcLocation, srcName => source_srcName, updateTime => source_updateTime, local => source_local), format => orddoc_format, mimeType => orddoc_mimeType, contentLength => orddoc_contentLength, comments => orddoc_comments);
  
    update arx_attatch
    set    attfile = ord_doc
    where  id = fid;
  /*exception
    when others then
      null;*/
  end;

  function check_loc_record(fid integer) return integer is
    edit_id number;
  begin
    select id
    into   edit_id
    from   arx_attatch
    where  id = fid
    for    update nowait;
    return 3;
  exception
    when others then
      return 2;
  end;

begin
  -- Initialization
  null;
end Record_Doc;
/
