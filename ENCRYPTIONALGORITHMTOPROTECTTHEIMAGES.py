from Crypto.Cipher import AES
from Crypto.Util.Padding import pad
import boto3

# Set AWS S3 credentials and bucket name
AWS_ACCESS_KEY_ID = 'the_access_key'
AWS_SECRET_ACCESS_KEY = 'the_secret_access_key'
BUCKET_NAME = 'the_bucket_name'

# Set encryption key (must be 16, 24, or 32 bytes long)
encryption_key = b'ThisIsASecretKey!'

def encrypt_image(input_file):
    # Read the image file
    with open(input_file, 'rb') as file:
        image_data = file.read()

    # Generate a random initialization vector (IV)
    iv = os.urandom(16)

    # Create an AES cipher object
    cipher = AES.new(encryption_key, AES.MODE_CBC, iv)

    # Pad the image data
    padded_data = pad(image_data, AES.block_size)

    # Encrypt the padded data
    encrypted_data = cipher.encrypt(padded_data)

    # Return encrypted data and IV
    return encrypted_data, iv

def upload_encrypted_image(encrypted_data, iv, filename):
    # Create an S3 client
    s3 = boto3.client(
        's3',
        aws_access_key_id=AWS_ACCESS_KEY_ID,
        aws_secret_access_key=AWS_SECRET_ACCESS_KEY
    )

    # Upload encrypted data as an S3 object
    s3.put_object(
        Body=encrypted_data,
        Bucket=BUCKET_NAME,
        Key=filename
    )

    # Upload IV as a separate S3 object
    iv_filename = f"{filename}.iv"

    s3.put_object(
        Body=iv,
        Bucket=BUCKET_NAME,
        Key=iv_filename
    )

# Set the path to the image file
input_file = 'original_image.jpg'

# Encrypt the image
encrypted_data, iv = encrypt_image(input_file)

# Set the filename for the encrypted image
filename = 'encrypted_image.jpg'

# Upload the encrypted image to S3
upload_encrypted_image(encrypted_data, iv, filename)
