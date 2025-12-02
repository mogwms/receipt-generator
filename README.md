# receipt-generator
ReceiptGenerator

# Pseudocode

```
DECLARE tax = 6 AS DOUBLE
DECLARE discount = 5 AS DOUBLE

METHOD processReceipt(itemName, quantity, unitPrice, useDiscount)
    DECLARE convertedTax = tax / 100 AS DOUBLE
    DECLARE convertedDiscount = discount / 100 AS DOUBLE
    DECLARE subtotal = unitPrice * quantity AS DOUBLE
    IF useDiscount THEN
        SET subtotal = subtotal - (convertedDiscount * subtotal)
    ENDIF
    DECLARE total = subtotal + (subtotal * tax)
    WRITE to receipt.txt
END METHOD

START
   DECLARE STRING order = READ from purchases.txt
   DECLARE items = SPLIT(order) AS ARRAY of STRING
   
   DECLARE itemName = items[0] AS STRING
   DECLARE quantity = items[1] AS INTEGER
   DECLARE unitPrice = items[2] AS DOUBLE
   DECLARE useDiscount AS BOOLEAN
   CALL processReceipt(itemName, quantity, unitPrice, useDiscount)
END

```