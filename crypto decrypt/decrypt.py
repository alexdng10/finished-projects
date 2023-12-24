class key2: 

    def __init__(self, key, index):
        self.key = str(key)
        self.index = index
       
    def getKey(self):
        print(self.key)

    def getIndex(self):
        print(self.index)

def convert(bytearray1):
    byte = bytearray.fromhex(bytearray1)
    return byte

def shorter(a1,a2):
    min1 = min(len(a1),len(a2))
    return a1[:min1]

def xor(a1,a2):
    result = bytearray()
    for i in range(len(a1)):
        xor = a1[i] ^ a2[i]
        result.append(xor)

    return result

def space_guess(bytearray1):
    cool = bytearray()
    space = " ".encode('ascii')
    for i in range(len(bytearray1)):
        xor = bytearray1[i] ^ space[0]
        cool.append(xor)
    return cool

def decode(bytearray1):
    result = ""
    for i in range(len(bytearray1)):
        if (32 <= bytearray1[i] <= 126):
            char =chr(bytearray1[i])
            result += char
        else:
            result += '_'
    return result


def findindex(bytearray1,char):
    result = ""
    char = char.encode("ascii")
    for i in range(len(bytearray1)):
        if (bytearray1[i] == char[0]):
            result += str(i) + " "
    
    return result
            

def advanced_guess(bytearray1,word,index):
    result = bytearray()
    result = bytearray1[:index]
    word = word.encode("ascii")
    length = 0

    for i in range(index,len(bytearray1)):
        xor = bytearray1[i] ^ word[length]
        length += 1
        if (length == len(word)):
            length = 0
        result.append(xor)
    return result

def extender(a1,a2):
    bye = bytearray()
    l1 = len(a1)
    l2 = len(a2)
    if (l1 > l2):
        bye = a2
        while (len(a1) != len(a2)):
            col = " ".encode()
            bye.append(col[0])
    return bye

def key_create(a1, key, index):
    key = key.encode('ascii')
    result = bytearray()
    for i in range(len(key)):
        xor = a1[index]^key[i]
        result.append(xor)
        index += 1
    return result

def key_check(a1,key,index):
    
    l = 0
    result = bytearray()
    for i in range(len(key)):
        xor = a1[index] ^ key[i]
        index =+ 1
        result.append(xor)
    return result



c1 = "315c4eeaa8b5f8aaf9174145bf43e1784b8fa00dc71d885a804e5ee9fa40b16349c146fb778cdf2d3aff021dfff5b403b510d0d0455468aeb98622b137dae857553ccd8883a7bc37520e06e515d22c954eba5025b8cc57ee59418ce7dc6bc41556bdb36bbca3e8774301fbcaa3b83b220809560987815f65286764703de0f3d524400a19b159610b11ef3e"
c2 = "234c02ecbbfbafa3ed18510abd11fa724fcda2018a1a8342cf064bbde548b12b07df44ba7191d9606ef4081ffde5ad46a5069d9f7f543bedb9c861bf29c7e205132eda9382b0bc2c5c4b45f919cf3a9f1cb74151f6d551f4480c82b2cb24cc5b028aa76eb7b4ab24171ab3cdadb8356f"

t1 = convert(c1)
t2 = convert(c2)

short1 = shorter(t1,t2)
short2 = shorter(t2,t1)

result = xor(short1,short2)
print("result************************************************")
print(decode(result))

space = space_guess(result)
print("space****************************************")
print(decode(space))

print("advanced guess*******************************")
manipulate = advanced_guess(result,' the ',70)
print(decode(manipulate))


shit = convert(c1)
shit2 = convert(c2)
key = key_create(shit,' the ',70)
"""print("")
yor = str(key.hex())
print(yor)"""


print("keycheck")
check1 = key_check(shit2,key,70)
print(decode(check1))
#70 is the one. index... key is #" the "" and " of c""
print("")
print(findindex(space,'e'))

"""k1 = key2(yor,70)
k1.getKey()
k1.getIndex()"""

"""while(True):
    user_input = input("what's next captain?: ")
    if (user_input == "exit"):
        break
    proc = user_input.split(" ")
    print(proc)"""