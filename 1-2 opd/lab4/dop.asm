org 0x000

start:
    cla
    st res_low
    st res_high

    ld arr1
    add arr2
    add arr3
    add arr4
    add arr5
    st res_low

    bmi set_minus
    cla
    jump store_high
set_minus:
    ld minus_one
store_high:
    st res_high

    ld res_low
    asl
    st res_low

    ld res_high
    rol
    st res_high

    hlt

minus_one: word 0xFFFF
arr1: word 0xFFFF
arr2: word 0xFFFF
arr3: word 0xFFFF
arr4: word 0xFFFF
arr5: word 0xFFFF
res_low:  word 0
res_high: word 0
