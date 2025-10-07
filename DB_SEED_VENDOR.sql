-- Example SQL to create a vendor (adjust column names/types to match your schema)
-- Replace values with real vendor credentials. Do NOT commit secrets.

INSERT INTO subvendors (businessname, contact_person, email, phone, address, password_hash, created_at)
VALUES (
  'Example Vendor',
  'Ali',
  'vendor@example.com',
  '+1234567890',
  '123 Vendor Street',
  -- It's recommended to store a salted hash. For testing you can store plain-text then change later.
  'changeme',
  NOW()
);
