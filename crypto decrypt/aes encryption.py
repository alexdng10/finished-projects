def pad_message(message, block_size):
    # Calculate the required padding length
    padding_length = block_size - (len(message) % block_size)

    # Create a string of padding characters
    padding = chr(padding_length) * padding_length

    # Return the original message with padding added
    return message + padding

def process_encryption_block(block, key, start_index):
    # Encrypt each character in a block
    encrypted_block = bytearray()
    key_length = len(key)
    for i in range(len(block)):
        key_char = key[(start_index + i) % key_length]  # Cycle through the key
        encrypted_char = ord(block[i]) ^ ord(key_char)  # XOR encryption
        encrypted_block.append(encrypted_char)
    return encrypted_block, (start_index + len(block)) % key_length

def process_decryption_block(block, key, start_index):
    # Decrypt each character in a block
    decrypted_block = ''
    key_length = len(key)
    for i in range(len(block)):
        key_char = key[(start_index + i) % key_length]  # Cycle through the key
        decrypted_char = chr(block[i] ^ ord(key_char))  # XOR decryption
        decrypted_block += decrypted_char
    return decrypted_block, (start_index + len(block)) % key_length

def encrypt_message(message, key, block_size):
    padded_message = pad_message(message, block_size)
    encrypted_message = bytearray()
    start_index = 0  # Initial start index for key
    shift_counter = 2
    for i in range(0, len(padded_message), block_size):
        block = padded_message[i:i + block_size]
        
        result = process_encryption_block(block, key, start_index)  # Get the result as a single tuple
        encrypted_block = result[0]  # Extract encrypted block
        start_index = result[1]  # Update start index
        rotated_block = rotate_block(encrypted_block, shift_counter)
        
        
        encrypted_message.extend(rotated_block)
    return encrypted_message

def decrypt_message(encrypted_bytes, key, block_size):
    decrypted_message = ''
    start_index = 0  # Reset start index for decryption
    shift_counter = 2
    for i in range(0, len(encrypted_bytes), block_size):
        block = encrypted_bytes[i:i + block_size]
        unrotated_block = unrotate_block(block, shift_counter)
        
        
        result = process_decryption_block(unrotated_block, key, start_index) # Get the result as a single tuple
        decrypted_block = result[0]  # Extract decrypted block
        start_index = result[1]  # Update start index
        decrypted_message += decrypted_block
    last_char = decrypted_message[-1]
    padding_length = ord(last_char)
    original_message = decrypted_message[:-padding_length]
    return original_message

#extra layer of security rotating make sure that it is in block size please.
def rotate_block(block, byte_shift):
    return block[-byte_shift:] + block[:-byte_shift]

#extra layer
def unrotate_block(rotated_block, byte_shift):
    return rotate_block(rotated_block, -byte_shift)

def middlesquaremethod(seed, length):
    key = ""
    seed_str = str(seed)

    while len(key) < length:
        # Square the seed
        seed = int(seed_str) ** 2
        print(seed)
        
        
        # Convert the square to a string
        square_str = str(seed)

        # Determine the starting and ending indices for the middle digits
        start_index = (len(square_str) // 2) -1
        end_index = -start_index + 1

        # Extract the middle digits
        middle_digits = square_str[start_index:end_index]
        print(middle_digits)
        
        if not middle_digits:
            middle_digits = 0
        # Append the middle digits to the key
        key += middle_digits
        
        # Update the seed_str for next iteration
        seed_str = middle_digits 

    return key[:length]

def converter(bytearray):
    result = ""
    for x in bytearray:
        if 32 <= x <= 126:
            car = chr(x) 
            result = result + car
            #i#f (car == "T"):
                #print("\n")
                #print(bytearray.index(84))
        else:
            result = result + "_"
    return result

# Example usage
block_size = 4  # Define block size
 # Define encryption/decryption key

# Encrypt a message
original_message = input("Enter a message to encrypt: ")
key = middlesquaremethod(713,len(original_message))
encrypted_bytes = encrypt_message(original_message, key, block_size)
print("Encrypted Message:", converter(encrypted_bytes))

# Decrypt the message
decrypted_message = decrypt_message(encrypted_bytes, key, block_size)
print("Decrypted Message:", decrypted_message)