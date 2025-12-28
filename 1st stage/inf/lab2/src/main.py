def hamming(num):
    bits = [int(i) for i in str(num)]

    s1 = (bits[0] + bits[2] + bits[4] + bits[6]) % 2
    s2 = (bits[1] + bits[2] + bits[5] + bits[6]) % 2 
    s3 = (bits[3] + bits[4] + bits[5] + bits[6]) % 2

    syndr = s1 + s2 * 2 + s3 * 4

    if syndr == 0:
        corr = bits
    else:
        err = syndr - 1
        corr = bits
        if corr[err] == 1:
            corr[err] = 0
        else:
            corr[err] = 1

    info_bits = f"{corr[2]}{corr[4]}{corr[5]}{corr[6]}"

    return info_bits

def main(num):
    if len(num) == 7:
        return hamming(num)
    else:
        return print("число не соответствует условию")
print(main(input("введите число: ")))
