# receipt-generator
ReceiptGenerator

# Pseudocode

```
DECLARE tax = 6 AS DOUBLE

METHOD processReceipt(item_name, quantity, unit_price, useDiscount)
    DECLARE subtotal AS DOUBLE
    DECLARE total AS DOUBLE

    SET tax TO tax / 100
    SET subtotal TO unit_price * quantity
    SET total TO ROUND(subtotal + subtotal * tax)
END METHOD

METHOD validateInput(item_name, quantity, unit_price)
    IF item_name == "" THEN
        PRINT "Empty Item Name! Please Enter a Valid Item Name!"
        RETURN FALSE
    ELSEIF quantity <= 0 THEN
        PRINT "Invalid Quantity! Please Enter A Quantity of 1 or Greater!"
        RETURN FALSE
    ELSEIF unit_price <= 0 THEN
        PRINT "Invalid Price! Please Enter A Price Greater Than Zero!"
        RETURN FALSE
    ENDIF
    RETURN TRUE
END METHOD

START
    DECLARE item_name AS STRING
    
    DECLARE quantity AS DOUBLE
    DECLARE unit_price AS DOUBLE

    DECLARE validInput AS BOOLEAN
    DECLARE usingDiscount AS BOOLEAN

    DECLARE section AS INTEGER
    SET section TO 1

    WHILE NOT validInput DO
        SWITCH(section)
            CASE 1:
                PRINT "Input Item Name"
                INPUT item_name
                PRINT "Input Item Quantity"
                INPUT quantity
                PRINT "Input Unit Price Of Item"
                INPUT unit_price
                
                IF validateInput(item_name, quantity unit_price) THEN
                    SET section TO 2
                ENDIF
            BREAK
            CASE 2:
            IF quantity > 10 THEN
                PRINT "Discount Available! Would you like to apply the 5% off discount for buying 10 items or more? (Y/N)"

                DECLARE answer AS STRING
                INPUT answer

                IF answer == "Y" THEN
                    SET usingDiscount TO TRUE
                ELSEIF answer == "N" THEN
                    SET usingDiscount TO FALSE
                ELSE
                    PRINT "Invalid Input! Please Enter 'Y' or 'N'!"
                    CONTINUE
                ENDIF

                SET section TO 3
            ENDIF
            
            BREAK
            CASE 3:
                SET validInput TO TRUE
                BREAK
            DEFAULT:
                BREAK
    ENDWHILE
    
    processReceipt(item_name, quantity, unit_price, usingDiscount)
END

```