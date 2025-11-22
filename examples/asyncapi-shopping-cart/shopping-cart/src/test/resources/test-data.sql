select nextval('shopping_cart_seq');
INSERT INTO shopping_cart (id, version, customer_id, items, created_by, created_date, last_modified_by, last_modified_date)
VALUES (1, 0, 1, '[
  {
    "name": "itemToUpdate",
    "quantity": 2
  },
  {
    "name": "itemToRemove",
    "quantity": 2
  }
]'::json, 'test-user', CURRENT_TIMESTAMP, 'test-user', CURRENT_TIMESTAMP);
