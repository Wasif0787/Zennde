def discount_amount(subtotal, countA, countB, countC):
    result = [0, 0, 0, 0, 0]
    discountAmount = 0
    discA = 0
    discB = 0
    discC = 0

    if subtotal > 200:
        discountAmount = 10
        result[0] = 1
        result[4] = discountAmount

    if countA > 10:
        discA = (countA * 20) * 0.05
    if countB > 10:
        discB = (countB * 40) * 0.05
    if countC > 10:
        discC = (countC * 50) * 0.05

    if discA + discB + discC > discountAmount:
        discountAmount = discA + discB + discC
        result[0] = 2
        result[1] = discA
        result[2] = discB
        result[3] = discC
        result[4] = discountAmount

    if countA + countB + countC > 20:
        discount_cart = ((countA * 20) + (countB * 40) + (countC * 50)) * 0.1
        if discount_cart > discountAmount:
            discountAmount = discount_cart
            result[0] = 3
            result[4] = discountAmount

    if countA + countB + countC > 30 and (countA > 15 or countB > 15 or countC > 15):
        if countA > 15:
            extra = countA - 15
            extra_cost = extra * 20
            discA = extra_cost * 0.5
        if countB > 15:
            extra = countB - 15
            extra_cost = extra * 40
            discB = extra_cost * 0.5
        if countC > 15:
            extra = countC - 15
            extra_cost = extra * 50
            discC = extra_cost * 0.5

        if discA + discB + discC > discountAmount:
            discountAmount = discA + discB + discC
            result[0] = 4
            result[1] = discA
            result[2] = discB
            result[3] = discC
            result[4] = discountAmount

    return result


countA = int(input("How many Product A : $20?\n"))
wrapA = False
if countA > 0:
    ch = input("Do u want to wrap Product A? (y/n)\n")
    if ch.lower() == "y":
        wrapA = True
    elif ch.lower() == "n":
        wrapA = False
price_A = 20 * countA

countB = int(input("How many Product B : $40?\n"))
wrapB = False
if countB > 0:
    ch = input("Do u want to wrap Product B? (y/n)\n")
    if ch.lower() == "y":
        wrapB = True
    elif ch.lower() == "n":
        wrapB = False
price_B = 40 * countB

countC = int(input("How many Product C : $50?\n"))
wrapC = False
if countC > 0:
    ch = input("Do u want to wrap Product C? (y/n)\n")
    if ch.lower() == "y":
        wrapC = True
    elif ch.lower() == "n":
        wrapC = False
price_C = 50 * countC

subtotal = price_A + price_B + price_C
print("Product Name    Quantity    Total_Amount")
print(f"Product A       {countA}          {price_A}")
print(f"Product B       {countB}          {price_B}")
print(f"Product C       {countC}          {price_C}")
print(f"Subtotal : {subtotal}")

if countA + countB + countC > 0:
    disc_info = discount_amount(subtotal, countA, countB, countC)
    disc_name = ""
    if disc_info[0] == 1:
        disc_name = "flat_10_discount"
    elif disc_info[0] == 2:
        disc_name = "bulk_5_discount"
    elif disc_info[0] == 3:
        disc_name = "bulk_10_discount"
    elif disc_info[0] == 4:
        disc_name = "tiered_50_discount"
    else:
        disc_name = "Not Applicable"

    print("Discount Name   Discount Amount")
    print(f"{disc_name}  {disc_info[4]}")

    wrapFee = 0
    if wrapA:
        wrapFee += countA
    if wrapB:
        wrapFee += countB
    if wrapC:
        wrapFee += countC

    print(f"Wrap Fee : {wrapFee}")
    totalBox = (countA + countB + countC) // 10 + 1
    shippingFee = totalBox * 5

    print(f"Shipping Fee : {shippingFee}")
    finalAmount = (subtotal - disc_info[4]) + wrapFee + shippingFee

    print(f"Total : {finalAmount}")
else:
    print("Enter Quantity greater than 0")
