SELECT XMLElement("customer", XMLATTRIBUTES (c.customer_id AS "id",
                  c.cust_first_name AS "name",
                  c.cust_last_name AS "lastname",
                  c.cust_city AS "city",
                  c.cust_state AS "state",
                  c.cust_email AS "email"),
                  XMLElement("orders", (
                    SELECT XMLAgg(XMLElement("order",
                                             XMLATTRIBUTES (o.order_id AS "id",
                                             o.order_total AS "total",
                                             to_char(o.order_timestamp) AS "timestamp",
                                             o.tags AS "tags"),
                                             (SELECT XMLAGG(XMLELEMENT("item",
                                                                       XMLATTRIBUTES (
                                                                       i.unit_price AS "price",
                                                                       i.quantity AS "quantity"
                                                                       ),
                                                                       XMLELEMENT("product", XMLCONCAT(
                                                                           XMLELEMENT("name",
                                                                                      p.product_name),
                                                                           XMLELEMENT("description",
                                                                                      p.product_description),
                                                                           XMLELEMENT("category",
                                                                                      p.category),
                                                                           XMLELEMENT("avail",
                                                                                      p.product_avail),
                                                                           XMLELEMENT("list_price",
                                                                                      p.list_price),
                                                                           XMLELEMENT("tags", p.tags)

                                                                       ))

                                                            ))
                                              FROM demo_order_items i
                                                JOIN demo_product_info p
                                                  ON (i.product_id = p.product_id)
                                              WHERE o.order_id = i.order_id)
                                  ))
                    FROM demo_orders o
                    WHERE o.customer_id = c.customer_id)
                  ))
FROM demo_customers C;
