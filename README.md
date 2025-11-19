# receipt-generator
ReceiptGenerator

# Pseudocode

```
METHOD processReceipt(item_name, quantity, unit_price)

END METHOD

METHOD validateInput(item_name, quantity, unit_price)
    IF item_name == "" THEN
        PRINT "Empty Item Name! Please Enter a Valid Item Name!"
        RETURN
    ELSEIF quantity <= 0 THEN
        PRINT "Invalid Quantity! Please Enter A Quantity of 1 or Greater!"
        RETURN
    ELSEIF unit_price <= 0 THEN
        PRINT "Invalid Price! Please Enter A Price Greater Than Zero!"
        RETURN
    ENDIF
END METHOD

START
    DECLARE item_name : STRING
    DECLARE quantity : DOUBLE
    DECLARE unit_price : DOUBLE
    DECLARE validInput : BOOLEAN

    WHILE NOT validInput DO
        INPUT item_name, quantity, unit_price

        IF validateInput(item_name, quantity unit_price) THEN
            validInput = true
        ENDIF
    ENDWHILE

    processReceipt(item_name, quantity, unit_price)
END

```