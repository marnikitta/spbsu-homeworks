-- noinspection SqlResolveForFile
DECLARE
  var  XMLTYPE;
  doc  DBMS_XMLDOM.DOMDocument;
  node DBMS_XMLDOM.DOMNode;
  buff VARCHAR2(4096);
BEGIN
  SELECT payload
  INTO var
  FROM mmxml;

  doc := DBMS_XMLDOM.newDOMDocument(var);
  node := DBMS_XMLDOM.makeNode(doc);
  DBMS_XMLDOM.writeToBuffer(node, buff);
  DBMS_OUTPUT.put_line(buff);
END;